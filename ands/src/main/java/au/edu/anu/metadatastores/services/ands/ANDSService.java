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
package au.edu.anu.metadatastores.services.ands;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import au.edu.anu.metadatastores.util.properties.PropertyLoader;

/**
 * <p>ANDSService<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Service class for retrieving information from ANDS/RDA</p>
 * 
 * @author Genevieve Turner
 *
 */
public class ANDSService {
	static final Logger LOGGER = LoggerFactory.getLogger(ANDSService.class);
	
	private static ANDSService singleton_;
	private static String rifCsUrl_;
	private Properties properties_;
	
	/**
	 * Constructor
	 */
	private ANDSService() {
		properties_ = PropertyLoader.loadProperties("ands.properties");
		rifCsUrl_ = properties_.getProperty("ands.service.url") + properties_.getProperty("ands.service.key") + "/getRIFCS";
	}
	
	/**
	 * Get the ANDS Service instance
	 * 
	 * @return The ANDSService
	 */
	public static ANDSService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new ANDSService();
		}
		return singleton_;
	}
	
	/**
	 * Get the description of the activity
	 * 
	 * @param key The key to search on
	 * @return The Activity Description
	 */
	public String getActivityDescription(String key) {
		String description = null;
		
		String query = "?q=key:\"" + key + "\"";
		String queryURL = rifCsUrl_ + query;
		LOGGER.info("Query URL: {}", queryURL);
		try {
			URL url = new URL(queryURL);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xpath = xPathFactory.newXPath();
			XPathExpression expr = xpath.compile("registryObjects/registryObject/activity/description");
			NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			
			//TODO make the value a list as there could be duplicates
			Map<String, String> descriptionMap = new HashMap<String, String>();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String type = element.getAttribute("type");
					String value = element.getTextContent();
					descriptionMap.put(type, value);
				}
			}
			
			//remove the potential for the logo url
			descriptionMap.remove("logo");
			
			//Attempt to ensure the descriptions are in the correct order
			StringBuilder sb = new StringBuilder();
			addToDescription(descriptionMap, sb, "brief");
			addToDescription(descriptionMap, sb, "full");
			addToDescription(descriptionMap, sb, "significanceStatment");
			addToDescription(descriptionMap, sb, "notes");
			
			//Add the rest of the description types
			for (String value : descriptionMap.values()) {
				sb.append(value);
				sb.append("\n");
			}
			
			description = sb.toString();
		}
		catch (ParserConfigurationException e) {
			LOGGER.error("Error creating document builder", e);
		}
		catch (IOException e) {
			LOGGER.error("Error reading document", e);
		}
		catch (SAXException e) {
			LOGGER.error("Error rading document", e);
		}
		catch (XPathExpressionException e) {
			LOGGER.error("Error creationg xpath expression", e);
		}
		
		return description;
	}
	
	/**
	 * Add the map value to the description that will be returned.  This is utilised so that the descriptions
	 * may be placed in the correct order for readability 
	 * 
	 * @param descriptionMap Map of the description values
	 * @param sb The string builder creating the description
	 * @param key The key of the field
	 */
	private void addToDescription(Map<String, String> descriptionMap, StringBuilder sb, String key) {
		if (descriptionMap.containsKey(key)) {
			sb.append(descriptionMap.get(key));
			sb.append("\n");
			descriptionMap.remove(key);
		}
	}
}
