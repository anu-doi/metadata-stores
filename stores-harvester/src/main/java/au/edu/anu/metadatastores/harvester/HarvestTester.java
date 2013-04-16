package au.edu.anu.metadatastores.harvester;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ORG.oclc.oai.harvester2.verb.Identify;
import ORG.oclc.oai.harvester2.verb.ListRecords;

public class HarvestTester {
	private String url;
	
	public static void main(String[] args) {
		HarvestTester harvest = new HarvestTester("http://localhost:8380/oaiprovider/oai");
		harvest.getIdentity();
		harvest.harvestStuff();
	}
	
	public HarvestTester(String url) {
		this.url = url;
	}
	
	public void getIdentity() {
		try {
			Identify identify = new Identify(url);
			System.out.println("Protocol Version: " + identify.getProtocolVersion());
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	public void harvestStuff() {
		try {
			ListRecords listRecords = new ListRecords(url, "2012-10-01", "2013-02-11", null, "oai_dc");
			System.out.println("Start Harvest");
			OutputStream out = System.out;
			int i = 0;
			while (listRecords != null) {
				System.out.println("Round: " + i);
				NodeList errors = listRecords.getErrors();
				if (errors != null && errors.getLength() > 0) {
					System.out.println("Found Errors");
					System.out.println("Error record: " + listRecords.toString());
					break;
				}
				NodeList stuff = listRecords.getNodeList("//*[local-name() = 'OAI-PMH']");
				System.out.println("Number of nodes: " + stuff.getLength());
				
				for (int j = 0; j < stuff.getLength(); j++) {
					processNodes(stuff.item(j), 0);
				}
				
				String resumptionToken = listRecords.getResumptionToken();
				System.out.println("Resumption Token: " + resumptionToken);
				
				if (resumptionToken == null || resumptionToken.length() == 0) {
					listRecords = null;
				}
				else {
					listRecords = new ListRecords(url, resumptionToken);
				}
				
				i++;
			}
			
			System.out.println("End Harvest");
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	private void processData(Node node) {
		System.out.println("--------------");
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			NodeList headerList = element.getElementsByTagName("header");
			if (headerList.getLength() > 0) {
				Element header = (Element) headerList.item(0);
				processHeader(element);
			}
			NodeList metadataList = element.getElementsByTagName("metadata");
			if (metadataList.getLength() > 0) {
				Element metadata = (Element) metadataList.item(0);
				processMetadata(metadata);
			}
		}
	}
	
	private void processHeader(Element element) {
		NodeList identifierList = element.getElementsByTagName("identifier");
		if (identifierList.getLength() > 0) {
			Element identifier = (Element) identifierList.item(0);
			String idValue = identifier.getTextContent();
			System.out.println("ID: " + idValue);
		}
		
		String status = element.getAttribute("status");
		if ("deleted".equals(status)) {
			System.out.println("DELETED");
		}
	}
	
	private void processMetadata(Element element) {
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				printNode(childNode);
			}
		}
	}
	
	private void processNodes(Node node, int maximumRounds) {
		System.out.println("------ Round: " + maximumRounds + " ------");
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			NodeList headerList = element.getElementsByTagName("header");
			System.out.println("Number of headers: " + headerList.getLength());
			NodeList metadataList = element.getElementsByTagName("metadata/dc");
			System.out.println("Number of headers: " + metadataList.getLength());
			NodeList dcList = element.getElementsByTagName("dc");
			System.out.println("Number of headers: " + dcList.getLength());
		}
		printNodeInformation(node);
		if (maximumRounds == 0 && node.getNodeType() == Node.ELEMENT_NODE) {
			printNode(node);
		}
		NodeList childNodes = node.getChildNodes();
		if (maximumRounds > 0 && childNodes != null) {
			for (int i = 0; i < childNodes.getLength(); i++) {
				if (childNodes.item(i).getNodeType() != Node.TEXT_NODE) {
					processNodes(childNodes.item(i), maximumRounds - 1);
				}
			}
		}
	}
	
	private void printNodeInformation(Node node) {
		System.out.println("Number of child nodes: " + node.getChildNodes().getLength());
		System.out.println("Namespace: " + node.getNamespaceURI() + ", Local Name: " + node.getLocalName());
		System.out.println("Node Type: " + node.getNodeType());
		if (node.getNodeType() == Node.TEXT_NODE) {
			System.out.println("Node Value: " + node.getTextContent());
		}
	}
	
	private void printNode(Node node) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(node);
			transformer.transform(source, result);
			System.out.println("XML: " + sw.toString());
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
