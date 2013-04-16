/*******************************************************************************
 * Australian National University Metadata Stores
 * Copyright (C) 2013  The Australian National University
 * 
 * This file is part of Australian National University Metadata Stores.
 * 
 * Australian National University Metadata Stores is free software: you
 * can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package au.edu.anu.metadatastores.harvester;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ORG.oclc.oai.harvester2.verb.ListRecords;
import au.edu.anu.metadatastores.datamodel.harvester.HarvestContent;
import au.edu.anu.metadatastores.datamodel.harvester.Location;
import au.edu.anu.metadatastores.harvester.xml.oaipmh.OAIPMHtype;
import au.edu.anu.metadatastores.harvester.xml.oaipmh.RecordType;
import au.edu.anu.metadatastores.harvester.xml.oaipmh.StatusType;

/**
 * Harvest
 * 
 * The Australian National University
 * 
 * Class that harvests data from designated locations
 * 
 * @author Genevieve Turner
 *
 */
public class Harvest {
	static final Logger LOGGER = LoggerFactory.getLogger(Harvest.class);
	
	/**
	 * Starter class for harvesting
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			LOGGER.error("No Location Specified");
			return;
		}
		
		Harvest harvest = new Harvest();
		try {
			harvest.harvest(args[0]);
		}
		catch (TransformerException e) {
			LOGGER.error("Transformation exception", e);
		}
		catch (SAXException e) {
			LOGGER.error("SAXException exception", e);
		}
		catch (IOException e) {
			LOGGER.error("IOException exception", e);
		}
		catch (ParserConfigurationException e) {
			LOGGER.error("ParserConfigurationException exception", e);
		}
		catch (NoSuchFieldException e) {
			LOGGER.error("NoSuchFieldException exception", e);
		}
	}
	
	/**
	 * Constructor
	 */
	public Harvest() {
		
	}
	
	/**
	 * Harvest the records from the given system
	 * 
	 * @param harvestSystem The string of the system to harvest records for
	 * @throws TransformerException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws NoSuchFieldException
	 */
	public void harvest(String harvestSystem) throws TransformerException, SAXException, IOException, ParserConfigurationException, NoSuchFieldException {
		Session session = HarvesterHibernateUtil.getSessionFactory().openSession();
		
		Query query = session.createQuery("FROM Location WHERE system = :system");
		query.setParameter("system", harvestSystem);
		
		Location location = (Location) query.uniqueResult();
		session.close();
		//TODO throw exception if no location
		harvest(location);
	}
	
	/**
	 * Harvest the records from the given system
	 * 
	 * @param location The location to harvest from
	 * @throws TransformerException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws NoSuchFieldException
	 */
	public void harvest(Location location) throws TransformerException, SAXException, IOException, ParserConfigurationException, NoSuchFieldException {
		LOGGER.info("Begin Harvest");
		location.getSystem();
		Date lastHarvestDate = location.getLastHarvestDate();
		Date now = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String from = null;
		String until = null;
		if (lastHarvestDate != null) {
			//TODO put back in the from
			from = sdf.format(lastHarvestDate);
			until = sdf.format(now);
			LOGGER.info("From: {}, Until: {}", from, until);
		}
		location.setLastHarvestDate(new Date());
		
		ListRecords listRecords = new ListRecords(location.getUrl(), from, until, null, location.getMetadataPrefix());
		while (listRecords != null) {
			NodeList errors = listRecords.getErrors();
			if (errors != null && errors.getLength() > 0) {
				processErrors(errors);
				break;
			}
			
			List<HarvestContent> harvestContents = processList(listRecords.getDocument(), location.getSystem());
			saveList(harvestContents);

			String resumptionToken = listRecords.getResumptionToken();
			if (resumptionToken == null || resumptionToken.length() == 0) {
				listRecords = null;
			}
			else {
				listRecords = new ListRecords(location.getUrl(), resumptionToken);
			}
		}
		updateLocation(location);
		LOGGER.info("End Harvest");
	}
	
	/**
	 * Process harvesting errors and log errors appropriately
	 * 
	 * @param errors The errors that occured while harvesting
	 */
	public void processErrors(NodeList errors) {
		for (int i = 0; i < errors.getLength(); i++) {
			Node error = errors.item(i);
			if (error.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) error;
				String errorCode = element.getAttribute("code");
				if ("noRecordsMatch".equals(errorCode)) {
					LOGGER.debug("No records found");
				}
				else {
					LOGGER.error("Error harvesting records - {} - {}", errorCode, element.getTextContent());
				}
			}
		}
	}
	
	/**
	 * Update the location information
	 * 
	 * @param location The location information to update
	 */
	public void updateLocation(Location location) {
		Session session = HarvesterHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.merge(location);
		
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Process the list of records that have been harvested
	 * 
	 * @param listRecords The list of records to process
	 * @param system The system that has been harvested from
	 * @return The list of harvested content
	 */
	private List<HarvestContent> processList(Node listRecords, String system) {
		List<HarvestContent> harvestContents = new ArrayList<HarvestContent>();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(OAIPMHtype.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<OAIPMHtype> element = unmarshaller.unmarshal(listRecords, OAIPMHtype.class);
			OAIPMHtype oaipmh = element.getValue();
			List<RecordType> records = oaipmh.getListRecords().getRecord();
			HarvestContent harvestContent = null;
			String identifier = null;
			for (RecordType pmhRecord : records) {
				harvestContent = new HarvestContent();
				harvestContent.setSystem(system);
				identifier = pmhRecord.getHeader().getIdentifier();
				LOGGER.debug("Identifier: " + identifier);
				harvestContent.setIdentifier(identifier);
				
				if (StatusType.DELETED.equals(pmhRecord.getHeader().getStatus())) {
					harvestContent.setContent("deleted");
				}
				else if (pmhRecord.getMetadata() != null) {
					StringWriter sw = new StringWriter();
					streamNode((Element)pmhRecord.getMetadata().getAny(), sw);
					harvestContent.setContent(sw.toString());
				}
				else {
					LOGGER.error("Record does not have the status of deleted and has no metadata");
					LOGGER.info("Status: {}", pmhRecord.getHeader().getStatus());
				}
				harvestContents.add(harvestContent);
			}
		}
		catch(JAXBException e) {
			LOGGER.error("Exception performing unmarshal", e);
		}
		
		return harvestContents;
	}
	
	/**
	 * Save the list of harvested content
	 * 
	 * @param harvestContents The list of harvested content to save
	 */
	private void saveList(List<HarvestContent> harvestContents) {
		Session session = HarvesterHibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		HarvestContent content = null;
		for (int i = 0; i < harvestContents.size(); i++) {
			content = harvestContents.get(i);
			session.save(content);
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Put the node into a stream
	 * 
	 * @param node The node to put into a stream
	 * @param writer The writer for the stream
	 */
	private void streamNode(Node node, Writer writer) {
		StreamResult result = new StreamResult(writer);
		streamNode(node, result);
	}
	
	/**
	 * Write the node to the given stream
	 * 
	 * @param node The node to write into the stream
	 * @param result The stream to write to
	 */
	private void streamNode(Node node, StreamResult result) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(node);
			transformer.transform(source, result);
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
