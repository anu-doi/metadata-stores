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
package au.edu.anu.metadatastores.rdf;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.datamodel.store.Item;
import au.edu.anu.metadatastores.datamodel.store.ItemRelation;
import au.edu.anu.metadatastores.datamodel.store.ItemRelationId;
import au.edu.anu.metadatastores.datamodel.store.SystemType;
import au.edu.anu.metadatastores.datamodel.store.annotations.ItemAttributeTrait;
import au.edu.anu.metadatastores.datamodel.store.annotations.TraitType;
import au.edu.anu.metadatastores.rdf.annotation.RDFDefaultTriple;
import au.edu.anu.metadatastores.rdf.annotation.RDFSet;
import au.edu.anu.metadatastores.rdf.annotation.RDFSetWithDefault;
import au.edu.anu.metadatastores.rdf.annotation.RDFSets;
import au.edu.anu.metadatastores.rdf.annotation.RDFType;
import au.edu.anu.metadatastores.rdf.annotation.RDFUri;
import au.edu.anu.metadatastores.rdf.exception.RDFException;
import au.edu.anu.metadatastores.rdf.namespace.RDFNS;
import au.edu.anu.metadatastores.rdf.namespace.StoreNS;
import au.edu.anu.metadatastores.rdf.namespace.VCardNS;
import au.edu.anu.metadatastores.rdf.parser.RDFAnnotationParser;
import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.store.misc.Subject;
import au.edu.anu.metadatastores.store.search.ItemDTO;
import au.edu.anu.metadatastores.store.search.Search;
import au.edu.anu.metadatastores.store.search.SearchTerm;
import au.edu.anu.metadatastores.store.util.ItemResolver;
import au.edu.anu.metadatastores.util.properties.PropertyLoader;

import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.expr.E_IsBlank;
import com.hp.hpl.jena.sparql.expr.E_IsIRI;
import com.hp.hpl.jena.sparql.expr.E_OneOf;
import com.hp.hpl.jena.sparql.expr.E_Regex;
import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.ExprList;
import com.hp.hpl.jena.sparql.expr.ExprVar;
import com.hp.hpl.jena.sparql.modify.UpdateProcessRemote;
import com.hp.hpl.jena.sparql.modify.request.QuadAcc;
import com.hp.hpl.jena.sparql.modify.request.UpdateDeleteInsert;
import com.hp.hpl.jena.sparql.syntax.Element;
import com.hp.hpl.jena.sparql.syntax.ElementFilter;
import com.hp.hpl.jena.sparql.syntax.ElementGroup;
import com.hp.hpl.jena.sparql.syntax.ElementOptional;
import com.hp.hpl.jena.sparql.syntax.ElementTriplesBlock;
import com.hp.hpl.jena.sparql.syntax.ElementUnion;
import com.hp.hpl.jena.sparql.util.ExprUtils;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateRequest;

/**
 * <p>RDFService</p>
 *
 * <p>The Australian National University</p>
 *
 * <p>Service class for performing actions on an RDF base. i.e. it updates and searches the RDF Triple store</p>
 *
 * @author Genevieve Turner
 *
 */
public class RDFService implements Search {
	//TODO check if comparing values to insert/delete would be faster than the update
	static final Logger LOGGER = LoggerFactory.getLogger(RDFService.class);
	
	private static RDFService singleton_;
	private Properties properties_;
	
	/**
	 * Constructor
	 */
	private RDFService() {
		properties_ = PropertyLoader.loadProperties("rdf.properties");
		
		if (properties_ == null) {
			LOGGER.debug("RDFService.init(..): No rdf service property file found.");
		}
	}
	
	/**
	 * Get the RDF Service
	 * 
	 * @return The RDF Service object
	 */
	public static RDFService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new RDFService();
		}
		
		return singleton_;
	}
	
	/**
	 * Get all the objects from the metadata stores database and reference them.
	 */
	public void updateAll() {
		LOGGER.debug("In Update All");
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			//TODO remove the limit however for testing purposes the limit creates issues
			org.hibernate.Query query = session.createQuery("FROM Item");
			query.setMaxResults(10);
			@SuppressWarnings("unchecked")
			List<Item> items = query.list();
			update(items);
		}
		finally {
			session.close();
		}
	}
	//TODO add data commons and dublin core records to the mix
	
	//TODO test this
	public void updateModifiedAfterDate(Date modifiedDate) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			org.hibernate.Query query = session.createQuery("SELECT item FROM Item item WHERE EXISTS ( SELECT 1 FROM item.itemAttributes ia WHERE ia.lastModified > :lastModifiedDate ) ");
			query.setParameter("lastModifiedDate", modifiedDate);
			
			@SuppressWarnings("unchecked")
			List<Item> items = query.list();
			update(items);
		}
		finally {
			session.close();
		}
	}
	
	public void updateFromSpecifiedSystem(String extSystem) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			org.hibernate.Query query = session.createQuery("FROM Item WHERE extSystem = :extSystem");
			query.setParameter("extSystem", extSystem);
			
			@SuppressWarnings("unchecked")
			List<Item> items = query.list();
			update(items);
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Update the system types
	 */
	public void updateSystemTypes() {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		try {
			org.hibernate.Query query = session.createQuery("FROM SystemType");
//TODO add graph capabilities into code!
			@SuppressWarnings("unchecked")
			List<SystemType> systemTypes = query.list();
			for (SystemType sysType : systemTypes) {
				save(sysType.getExtSystem(), sysType);
			}
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Update the record with the given id
	 * 
	 * @param id The id
	 */
	public void update(Long id) {
		Session session = StoreHibernateUtil.getSessionFactory().openSession();
		
		try {
			Object item = session.get(Item.class, id);
			update(Arrays.asList((Item) item));
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Update the triplestore with the given items
	 * 
	 * @param items The list of items to update the triplestore for
	 */
	private void update(List<Item> items) {
		RDFAnnotationParser parser = new RDFAnnotationParser();
		int numCount = 0;
		int numAtOnce = 50;
		Model model = ModelFactory.createDefaultModel();
		List<Triple> deleteTriples = new ArrayList<Triple>();
		List<String> objectURIs = new ArrayList<String>();
		for (Item item : items) {
			if (!Boolean.FALSE.equals(item.getDeleted())) {
				Object obj = ItemResolver.resolve(item);
				try {
					Resource resource = parser.getResource(item.getIid().toString(), item.getExtSystem(), obj, model);
					//Resource resource = parser.getResource(item.getIid().toString(), obj, model);
					addRelationsToModel(model, item.getItemRelationsForIid());
					LOGGER.info("URI: {}", resource.getURI());
					objectURIs.add(resource.getURI());
					numCount++;
				}
				catch (RDFException e) {
					LOGGER.error("Exception retrieving resource");
				}
				if (numCount % numAtOnce == 0) {
					saveUpdates(model, objectURIs);
					
					deleteTriples.clear();
					objectURIs.clear();
					model = ModelFactory.createDefaultModel();
					StmtIterator it = model.listStatements();
					if (it.hasNext()) {
						LOGGER.info("Still Values in Iterator?");
					}
				}
			}
		}

		saveUpdates(model, objectURIs);
	}
	
	private void addRelationsToModel(Model model, Set<ItemRelation> relations) {
		for (ItemRelation relation : relations) {
			ItemRelationId id = relation.getId();
			Resource iid = model.createResource(properties_.getProperty("rdf.object.uri") + id.getIid().toString());
			Resource relatedIid = model.createResource(properties_.getProperty("rdf.object.uri") + id.getRelatedIid().toString());
			iid.addProperty(model.createProperty(properties_.getProperty("rdf.relation.uri") + id.getRelationValue()), relatedIid);
		}
	}
	
	private void saveUpdates(Model insertModel, List<String> objectURIs) {
		LOGGER.info("Number of objects to update: {}", objectURIs.size());
		if (objectURIs.size() == 0) {
			return;
		}
		
		ElementUnion union = new ElementUnion();
		union.addElement(getChildToDeleteElement(objectURIs));
		union.addElement(getDirectToDeleteElement(objectURIs));
		
		UpdateDeleteInsert delUpdate = new UpdateDeleteInsert();
		QuadAcc delQuad = delUpdate.getDeleteAcc();
		delQuad.addTriple(new Triple(Var.alloc("o"), Var.alloc("q"), Var.alloc("r")));
		
		delUpdate.setElement(union);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Update Query: {}", delUpdate.toString());
		}
		String endPoint = properties_.getProperty("rdf.server") + properties_.getProperty("rdf.server.update");
		UpdateProcessRemote riStore = (UpdateProcessRemote) UpdateExecutionFactory.createRemote(delUpdate, endPoint);
		riStore.execute();
		
		UpdateDeleteInsert update = new UpdateDeleteInsert();
		QuadAcc insertQuads = update.getInsertAcc();
		
		StmtIterator it = insertModel.listStatements();
		while (it.hasNext()) {
			Statement stmt = it.next();
			stmt.asTriple();
			insertQuads.addTriple(stmt.asTriple());
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info("Update Query: {}", update.toString());
		}
		
		UpdateProcessRemote riStore2 = (UpdateProcessRemote) UpdateExecutionFactory.createRemote(update, endPoint);
		riStore2.execute();
	}
	
	/**
	 * Update the given item
	 * 
	 * @param item The item to update
	 */
	public void update(Item item) {
		LOGGER.debug("Updating record: {}", item.getIid());
		Object obj = ItemResolver.resolve(item);
		save(item.getIid().toString(), obj);
		saveRelations(item);
	}
	
	/**
	 * Save the object
	 * 
	 * @param id The id of the object
	 * @param object The object to save
	 */
	private void save(String id, Object object) {
		RDFAnnotationParser parser = new RDFAnnotationParser();
		save(id, object, parser);
	}
	
	/**
	 * Save the object 
	 * 
	 * @param id THe id of the object
	 * @param object The object to save
	 * @param parser The parser to use
	 */
	private void save(String id, Object object, RDFAnnotationParser parser) {
		Model model = ModelFactory.createDefaultModel();
		try {
			try {
				Resource resource = parser.getResource(id, object, model);
				if (resource == null) {
					LOGGER.error("Resource is null for some reason!");
					return;
				}
				List<String> objectURIs = new ArrayList<String>();
				objectURIs.add(resource.getURI());
				saveUpdates(model, objectURIs);
			}
			catch (RDFException e) {
				LOGGER.error("Exception saving resource", e);
			}
			catch (Exception e) {
				LOGGER.error("Exception saving resource", e);
			}
		}
		finally {
			model.close();
		}
	}
	
	/**
	 * Save the relations of the item
	 * 
	 * @param item The item to extract and save the relations of
	 */
	private void saveRelations(Item item) {
		saveRelations(item.getItemRelationsForIid());
		saveRelations(item.getItemRelationsForRelatedIid());
		
	}
	
	/**
	 * Save the relations from the given set
	 * 
	 * @param relations The set of relations to save
	 */
	private void saveRelations(Set<ItemRelation> relations) {
		Model model = ModelFactory.createDefaultModel();
		for (ItemRelation relation : relations) {
			ItemRelationId id = relation.getId();
			Resource iid = model.createResource(properties_.getProperty("rdf.object.uri") + id.getIid().toString());
			Resource relatedIid = model.createResource(properties_.getProperty("rdf.object.uri") + id.getRelatedIid().toString());
			iid.addProperty(model.createProperty(properties_.getProperty("rdf.relation.uri") + id.getRelationValue()), relatedIid);
		}
		updateRemote(model);
	}
	
	/*private Element getChildToDeleteElement(List<String> uris) {
		// Create the part of the where query that finds blank nodes associated with the given record
		ElementUnion union = new ElementUnion();
		for (String uri : uris) {
			Triple uriTriple = Triple.create(NodeFactory.createURI(uri), Var.alloc("p"), Var.alloc("o"));
			Triple fieldTriple = Triple.create(Var.alloc("o"), Var.alloc("q"), Var.alloc("r"));
			
			ElementTriplesBlock relatedElementTriples = new ElementTriplesBlock();
			relatedElementTriples.addTriple(uriTriple);
			relatedElementTriples.addTriple(fieldTriple);

			Expr oVarIsBlank = new E_IsBlank(new ExprVar("o"));
			ElementFilter oIsBlankFilter = new ElementFilter(oVarIsBlank);

			ElementGroup blankRelGroup = new ElementGroup();
			blankRelGroup.addElement(relatedElementTriples);
			blankRelGroup.addElementFilter(oIsBlankFilter);
			
			union.addElement(blankRelGroup);
		}
		
		return union;
	}*/
	
	private Element getChildToDeleteElement(List<String> uris) {
		// Create the part of the where query that finds blank nodes associated with the given record
		Triple uriTriple = Triple.create(Var.alloc("s"), Var.alloc("p"), Var.alloc("o"));
		Triple fieldTriple = Triple.create(Var.alloc("o"), Var.alloc("q"), Var.alloc("r"));
		
		ElementTriplesBlock relatedElementTriples = new ElementTriplesBlock();
		relatedElementTriples.addTriple(uriTriple);
		relatedElementTriples.addTriple(fieldTriple);
		
		Expr oVarIsBlank = new E_IsBlank(new ExprVar("o"));
		ElementFilter oIsBlankFilter = new ElementFilter(oVarIsBlank);

		ExprList uriExpr = new ExprList();
		for (String uri : uris) {
			uriExpr.add(ExprUtils.nodeToExpr(NodeFactory.createURI(uri)));
		}
		Expr sOneOf = new E_OneOf(new ExprVar("s"), uriExpr);
		ElementFilter sVarOneOfFilter = new ElementFilter(sOneOf); 
		
		ElementGroup blankRelGroup = new ElementGroup();
		blankRelGroup.addElement(relatedElementTriples);
		blankRelGroup.addElementFilter(oIsBlankFilter);
		blankRelGroup.addElementFilter(sVarOneOfFilter);
		return blankRelGroup;
	}

	/*private Element getDirectToDeleteElement(List<String> uris) {
		ElementUnion union = new ElementUnion();
		for (String uri : uris) {
			ElementTriplesBlock block = new ElementTriplesBlock();
			Triple fieldTriple = Triple.create(NodeFactory.createURI(uri), Var.alloc("q"), Var.alloc("r"));
			block.addTriple(fieldTriple);
			union.addElement(block);
		}
		return union;
	}*/

	private Element getDirectToDeleteElement(List<String> uris) {
		Triple fieldTriple = Triple.create(Var.alloc("o"), Var.alloc("q"), Var.alloc("r"));
		
		// Create the part of the where query that finds triples associated with the record
		ElementTriplesBlock triplesWithURI = new ElementTriplesBlock();
		triplesWithURI.addTriple(fieldTriple);
		
		ExprList uriExpr = new ExprList();
		for (String uri : uris) {
			uriExpr.add(ExprUtils.nodeToExpr(NodeFactory.createURI(uri)));
		}
		Expr oOneOf = new E_OneOf(new ExprVar("o"), uriExpr);
		ElementFilter oVarOneOfFilter = new ElementFilter(oOneOf);
		
		ElementGroup elementWithURI = new ElementGroup();
		elementWithURI.addElement(triplesWithURI);
		elementWithURI.addElement(oVarOneOfFilter);
		return elementWithURI;
	}
	
	/**
	 * Execute the update
	 * 
	 * @param model The model to save
	 */
	public void updateRemote(Model model) {
		StringWriter sw = new StringWriter();
		model.write(sw, "N-TRIPLES");
		updateRemote(sw.toString());
	}
	
	/**
	 * Execute the update
	 * 
	 * @param nTripleString The string of triples to save
	 */
	private void updateRemote(String nTripleString) {
		if (nTripleString.length() > 0) {
			// It would be nice to have something to generate and insert the data rather than needing to write it to a n-triple string then adding 'INSERT DATA around the string
			UpdateRequest update = UpdateFactory.create("INSERT DATA { " + nTripleString + " }");
			LOGGER.debug("Insert Query: {}", update.toString());
			UpdateProcessRemote riStore = (UpdateProcessRemote) UpdateExecutionFactory.createRemote(update, properties_.getProperty("rdf.server") + properties_.getProperty("rdf.server.update"));
			riStore.execute();
		}
	}
	
	@Override
	public List<ItemDTO> search(String value) {
		String numStr = "1";
		Element childFieldsElement = getChildFieldsElement(numStr, value);
		Element directFieldsElement = getDirectFieldsElement(numStr, value);
		Element associatedPeople = getAssociatedPeopleElement(numStr, value);
		
		ElementUnion union = new ElementUnion();
		union.addElement(directFieldsElement);
		union.addElement(childFieldsElement);
		union.addElement(associatedPeople);
		
		//Expr expr3 = new E_Regex(new ExprVar(numStr), value, "i");
		//ElementFilter ef3 = new ElementFilter(expr3);
		
		Element titleOpt = getOptionalTitleElement();
		Element fullNameOpt = getOptionalFullNameElementAsTitle();
		Element extSystemOpt = getExtSystemElement();
		
		//Construct the element query
		ElementGroup elG3 = new ElementGroup();
		elG3.addElement(union);
		//elG3.addElementFilter(ef3);
		elG3.addElement(titleOpt);
		elG3.addElement(fullNameOpt);
		elG3.addElement(extSystemOpt);
		
		Query query = QueryFactory.make();
		query.setQueryPattern(elG3);
		query.setQuerySelectType();
		query.addResultVar("id");
		query.addResultVar("title");
		query.addResultVar("extsystem");
		
		return executeItemDTOQuery(query);
	}
	
	/**
	 * Execute the given query on the RDF triplestore
	 * 
	 * @param query The query to execute
	 * @return The list of items returned
	 */
	private List<ItemDTO> executeItemDTOQuery(Query query) {
		String endPointAddress = properties_.getProperty("rdf.server") + properties_.getProperty("rdf.server.query");
		LOGGER.debug("Query: {}", query.toString());
		QueryExecution qExec = QueryExecutionFactory.sparqlService(endPointAddress, query);
		
		List<ItemDTO> items = new ArrayList<ItemDTO>();
		ItemDTO item = null;
		try {
			ResultSet results = qExec.execSelect();
			while (results.hasNext()) {
				item = new ItemDTO();
				QuerySolution soln = results.nextSolution();
				Resource id = soln.getResource("id");
				item.setId(parseId(id.getURI()));
				Literal title = soln.getLiteral("title");
				if (title != null) {
					item.setTitle(title.toString());
				}
				Literal extSystem = soln.getLiteral("extsystem");
				if (extSystem != null) {
					item.setExtSystem(extSystem.toString());
				}
				items.add(item);
			}
		}
		finally {
			qExec.close();
		}
		return items;
	}
	
	/**
	 * Generate the Triples Element that will retrieve child fields
	 * 
	 * @param numStr The id for this set of triples
	 * @return The generated Element
	 */
	private Element getChildFieldsElement(String numStr, String value) {
		Triple triple1 = new Triple(Var.alloc("id"), Var.alloc(numStr + "_2"), Var.alloc(numStr + "_3"));
		Triple triple2 = new Triple(Var.alloc(numStr + "_3"), Var.alloc(numStr + "_1"), Var.alloc(numStr));
		Expr expr = new E_IsBlank(new ExprVar(numStr + "_3"));
		
		ElementTriplesBlock relatedTriples = new ElementTriplesBlock();
		relatedTriples.addTriple(triple1);
		relatedTriples.addTriple(triple2);
		ElementFilter filter = new ElementFilter(expr);

		Expr valueExpr = new E_Regex(new ExprVar(numStr), value, "i");
		ElementFilter valueFilter = new ElementFilter(valueExpr);
		
		ElementGroup group = new ElementGroup();
		group.addElement(relatedTriples);
		group.addElementFilter(filter);
		group.addElementFilter(valueFilter);
		return group;
	}
	
	/**
	 * Generate the Triples Element that will retrieve fields that are directly associated with the id
	 * 
	 * @param numStr The id for this set of triples
	 * @return The generated Element
	 */
	private Element getDirectFieldsElement(String numStr, String value) {
		Triple triple = new Triple(Var.alloc("id"), Var.alloc(numStr + "_1"), Var.alloc(numStr));
		Expr expr2 = new E_IsIRI(new ExprVar("id"));
		ElementFilter filter = new ElementFilter(expr2);

		Expr valueExpr = new E_Regex(new ExprVar(numStr), value, "i");
		ElementFilter valueFilter = new ElementFilter(valueExpr);
		ElementGroup group = new ElementGroup();
		group.addTriplePattern(triple);
		group.addElementFilter(filter);
		group.addElementFilter(valueFilter);
		return group;
	}
	
	/**
	 * Generate the Triples Element that will retrieve records for which the name of a person has been found
	 * 
	 * @param numStr The id for this set of triples
	 * @return The generated element
	 */
	private Element getAssociatedPeopleElement(String numStr, String value) {
		Triple t1 = new Triple(Var.alloc("id"), Var.alloc(numStr + "_5"), Var.alloc(numStr + "_4"));
		Triple t2 = new Triple(Var.alloc(numStr + "_4"), NodeFactory.createURI(RDFNS.TYPE), NodeFactory.createURI(properties_.getProperty("rdf.object.uri") + "PERSON"));
		Triple t3 = new Triple(Var.alloc(numStr + "_4"), Var.alloc(numStr + "_6"), Var.alloc(numStr));

		Expr valueExpr = new E_Regex(new ExprVar(numStr), value, "i");
		ElementFilter valueFilter = new ElementFilter(valueExpr);
		
		ElementTriplesBlock triplesBlock = new ElementTriplesBlock();
		triplesBlock.addTriple(t1);
		triplesBlock.addTriple(t2);
		triplesBlock.addTriple(t3);
		
		ElementGroup group = new ElementGroup();
		group.addElement(triplesBlock);
		group.addElementFilter(valueFilter);
		
		return group;
	}
	
	/**
	 * Get the optional title element
	 * 
	 * @return The generated element
	 */
	private Element getOptionalTitleElement() {
		Triple titleTriple = new Triple(Var.alloc("id"), NodeFactory.createURI(StoreNS.TITLE), Var.alloc("title"));
		ElementTriplesBlock titleElement = new ElementTriplesBlock();
		titleElement.addTriple(titleTriple);
		ElementOptional titleOpt = new ElementOptional(titleElement);
		return titleOpt;
	}
	
	/**
	 * Get a persons name as the title element
	 * 
	 * @return The generated element
	 */
	private Element getOptionalFullNameElementAsTitle() {
		//Create the optional full name element as a title
		Triple fullNameTriple = new Triple(Var.alloc("id"), NodeFactory.createURI(VCardNS.FN), Var.alloc("title"));
		ElementTriplesBlock fullNameElement = new ElementTriplesBlock();
		fullNameElement.addTriple(fullNameTriple);
		ElementOptional fullNameOpt = new ElementOptional(fullNameElement);
		return fullNameOpt;
	}
	
	/**
	 * Get the system type element
	 * 
	 * @return The generated element
	 */
	private Element getExtSystemElement() {
		Triple extSystemTriple = new Triple(Var.alloc("id"), NodeFactory.createURI(RDFNS.TYPE), Var.alloc("t"));
		Triple t2 = new Triple(Var.alloc("t"), NodeFactory.createURI(StoreNS.TITLE), Var.alloc("extsystem"));
		ElementTriplesBlock extSystemElement = new ElementTriplesBlock();
		extSystemElement.addTriple(extSystemTriple);
		extSystemElement.addTriple(t2);
		return extSystemElement;
	}
	
	/**
	 * Get the Long id value instead of the uri
	 * 
	 * @param uri The uri to find the Long value of
	 * @return The Long id
	 */
	private Long parseId(String uri) {
		String idStr = uri.substring(uri.lastIndexOf("/") + 1);
		LOGGER.debug("idStr: {}", idStr);
		Long id = new Long(idStr);
		return id;
	}

	@Override
	public List<ItemDTO> search(String system, List<SearchTerm> searchTerms) {
		
		ElementGroup queryPattern = null;
		if (system == null || system.trim().length() == 0) {
			queryPattern =  searchWithoutSystem(searchTerms);
		}
		else {
			queryPattern = searchWithSystem(system, searchTerms);
		}
		
		queryPattern.addElement(getOptionalTitleElement());
		queryPattern.addElement(getOptionalFullNameElementAsTitle());
		queryPattern.addElement(getExtSystemElement());

		Query query = QueryFactory.make();
		query.setQueryPattern(queryPattern);
		query.setQuerySelectType();
		query.addResultVar("id");
		query.addResultVar("title");
		query.addResultVar("extsystem");

		return executeItemDTOQuery(query);
	}
	
	/**
	 * Search all the records for the given values with no system type defined
	 * 
	 * @param searchTerms The search terms
	 * @return The element group representing a search without a system type
	 */
	private ElementGroup searchWithoutSystem(List<SearchTerm> searchTerms) {
		int i = 1;
		
		// Due to the difficulties posed we are essentially ignoring the search term field type
		ElementGroup elG1 = new ElementGroup();
		for (SearchTerm searchTerm : searchTerms) {
			String numStr = Integer.valueOf(i++).toString();
			elG1.addElement(getAllElement(searchTerm, null, numStr));
		}

		return elG1;
	}
	
	/**
	 * Search and filter with the given system type
	 * 
	 * @param system The type of objects to return
	 * @param searchTerms The terms to  search for
	 * @return The element group representing the search
	 */
	private ElementGroup searchWithSystem(String system, List<SearchTerm> searchTerms) {
		LOGGER.debug("Execute Query With Field Limits");
		Class<?> clazz = ItemResolver.resolveTypeBySystemId(system);
		
		ElementTriplesBlock block = new ElementTriplesBlock();
		if (clazz.isAnnotationPresent(RDFType.class)) {
			RDFType rdfType = clazz.getAnnotation(RDFType.class);
			Triple t1 = new Triple(Var.alloc("id"), NodeFactory.createURI(RDFNS.TYPE), NodeFactory.createURI(properties_.getProperty("rdf.object.uri") + rdfType.value()));
			block.addTriple(t1);
		}
		Map<String, String> searchValues = new HashMap<String, String>();
		Method[] methods = clazz.getMethods();
		int i = 1;
		RDFSets sets = null;
		if (clazz.isAnnotationPresent(RDFSets.class)) {
			sets = clazz.getAnnotation(RDFSets.class);
		}
		ElementGroup elG1 = new ElementGroup();
		for (SearchTerm searchTerm : searchTerms) {
			if (searchTerm.getField() == null || searchTerm.getField().length() == 0) {
				String numStr = Integer.valueOf(i++).toString();
				elG1.addElement(getAllElement(searchTerm, searchValues, numStr));
			}
		}
		List<Triple> tripleList = new ArrayList<Triple>();
		for (Method method : methods) {
			if (method.isAnnotationPresent(ItemAttributeTrait.class)) {
				ItemAttributeTrait trait = method.getAnnotation(ItemAttributeTrait.class);
				if (trait.traitType().equals(TraitType.SUBJECT_LIST)) {
					if (method.isAnnotationPresent(RDFUri.class)) {
						for (SearchTerm searchTerm : searchTerms) {
							Method[] subjMethods = Subject.class.getMethods();
							for (Method subjMethod : subjMethods) {
								if (subjMethod.isAnnotationPresent(ItemAttributeTrait.class)) {
									ItemAttributeTrait subjTrait = subjMethod.getAnnotation(ItemAttributeTrait.class);
									if (subjTrait.attrType().equals(searchTerm.getField())) {
										if (subjMethod.isAnnotationPresent(RDFUri.class)) {
											String numStr = Integer.valueOf(i++).toString();
											tripleList.addAll(getSubjectTriples(method, subjMethod, searchTerm, searchValues, numStr));
										}
									}
								}
							}
						}
					}
				}
				else {
					String traitVal = trait.attrType();
					for (SearchTerm searchTerm : searchTerms) {
						if (traitVal.equals(searchTerm.getField())) {
							String numStr = Integer.valueOf(i++).toString();
							tripleList.addAll(getSearchTermElement(sets, method, searchTerm, searchValues, numStr));
						}
					}
				}
			}
		}
		for (Triple triple : tripleList) {
			block.addTriple(triple);
		}
		elG1.addElement(block);
		for (Entry<String, String> entry : searchValues.entrySet()) {
			elG1.addElementFilter(getCaseInsensitiveRegexFilter(entry.getKey(), entry.getValue()));
		}
		
		return elG1;
	}
	
	/**
	 * Generate a filter object with the given field id and value
	 * 
	 * @param fieldId The field id
	 * @param value The value to filter for
	 * @return The filter
	 */
	private ElementFilter getCaseInsensitiveRegexFilter(String fieldId, String value) {
		Expr regex = new E_Regex(new ExprVar(fieldId), value, "i");
		ElementFilter filter = new ElementFilter(regex);
		
		return filter;
	}
	
	/**
	 * Generate the search term triples
	 * 
	 * @param sets The RDFSets associated with the system types class
	 * @param method The method to get the search terms for
	 * @param searchTerm The search term to generate triples for
	 * @param searchValues The values to generate filters with
	 * @param numStr The id number to associate with triples
	 * @return A list of generated triples
	 */
	private List<Triple> getSearchTermElement(RDFSets sets, Method method, SearchTerm searchTerm, Map<String, String> searchValues, String numStr) {
		if (method.isAnnotationPresent(RDFUri.class)) {
			RDFUri rdfUri = method.getAnnotation(RDFUri.class);
			boolean found = false;
			if (sets != null) {
				String methodField = method.getName();
				methodField = methodField.substring(3).toLowerCase();
				RDFSet[] rdfSets = sets.sets();
				for (int i = 0; !found && i < rdfSets.length; i++) {
					RDFSet set = rdfSets[i];
					String[] fields = set.fields();
					for (int j = 0; j < fields.length; j++) {
						String field = fields[j];

						if (methodField.equals(field.toLowerCase())) {
							found = true;
							
							return getSetTriples(rdfUri, searchTerm, searchValues, numStr, set);
						}
					}
				}
				RDFSetWithDefault[] rdfSetsDef = sets.setsWithDefaults();
				for (int i = 0; !found && i < rdfSetsDef.length; i++) {
					RDFSetWithDefault set = rdfSetsDef[i];
					if (methodField.equals(set.field().toLowerCase())) {
						found = true;
						
						return getDefaultSetTriples(rdfUri, searchTerm, searchValues, numStr, set);
					}
				}
				if (!found) {
					return getSingleTriple(rdfUri, searchTerm, searchValues, numStr);
				}
			}
			else {
				return getSingleTriple(rdfUri, searchTerm, searchValues, numStr);
			}
		}
		LOGGER.info("Returning null for method: {}", method.getName());
		return null;
	}
	
	/**
	 * Generated a triple from the given information
	 * 
	 * @param rdfUri The RDFUri object associated with the method for the search term
	 * @param searchTerm The search term
	 * @param searchValues The values to generate filters with
	 * @param numStr The id number to associate with triples
	 * @return
	 */
	private List<Triple> getSingleTriple(RDFUri rdfUri, SearchTerm searchTerm, Map<String, String> searchValues, String numStr) {
		Triple t1 = new Triple(Var.alloc("id"), NodeFactory.createURI(rdfUri.uri()), Var.alloc(numStr));
		searchValues.put(numStr, searchTerm.getValue());
		return Arrays.asList(t1);
	}
	
	/**
	 * Get an element that does not care what the field type is
	 * 
	 * @param searchTerm The search term 
	 * @param searchValues The values to generate filters with
	 * @param numStr The id number to associate with triples
	 * @return The generated element
	 */
	private Element getAllElement(SearchTerm searchTerm, Map<String, String> searchValues, String numStr) {
		// We create the group and add the filter directly here as otherwise the query takes too long.  Having the filter
		// where the unions are makes the query a lot faster
		
		ElementGroup group = new ElementGroup();
		ElementUnion union = new ElementUnion();
		union.addElement(getDirectFieldsElement(numStr, searchTerm.getValue()));
		union.addElement(getChildFieldsElement(numStr, searchTerm.getValue()));
		union.addElement(getAssociatedPeopleElement(numStr, searchTerm.getValue()));
		group.addElement(union);
		//group.addElementFilter(getCaseInsensitiveRegexFilter(numStr, searchTerm.getValue()));
		return group;
	}
	
	/**
	 * Get a list of triples to represent a search based on a set item 
	 * 
	 * @param rdfUri The RDFUri annotation associated with the method found
	 * @param searchTerm The search term
	 * @param searchValues The values to generate filters with
	 * @param numStr The id number to associate with triples
	 * @param set The set to generate triples from
	 * @return The generated triples
	 */
	private List<Triple> getSetTriples(RDFUri rdfUri, SearchTerm searchTerm, Map<String, String> searchValues, String numStr, RDFSet set) {
		String groupIdStr = numStr + "_0";
		Var groupIdVar = Var.alloc(groupIdStr);
		
		List<Triple> triples = new ArrayList<Triple>();
		Triple triple = new Triple(Var.alloc("id"), NodeFactory.createURI(set.uri()), groupIdVar);
		triples.add(triple);
		
		triple = new Triple(groupIdVar, NodeFactory.createURI(rdfUri.uri()), Var.alloc(numStr));
		triples.add(triple);
		searchValues.put(numStr, searchTerm.getValue());
		
		return triples;
	}
	
	/**
	 * Get a list of triples to represent a search based on a default set item
	 * 
	 * @param rdfUri The RDFUri annotation associated with the method found
	 * @param searchTerm The search term
	 * @param searchValues The values to generate filters with
	 * @param numStr The id number to associate with triples
	 * @param set The set to generate triples from
	 * @return The generated triples
	 */
	private List<Triple> getDefaultSetTriples(RDFUri rdfUri, SearchTerm searchTerm, Map<String, String> searchValues, String numStr, RDFSetWithDefault set) {
		RDFDefaultTriple[] tripleDefaults = set.defaults();
		String groupIdStr = numStr + "_0";
		List<Triple> triples = new ArrayList<Triple>();
		Var groupIdVar = Var.alloc(groupIdStr);
		Triple triple = new Triple(Var.alloc("id"), NodeFactory.createURI(set.uri()), groupIdVar);
		triples.add(triple);
		for (RDFDefaultTriple tripleDefault : tripleDefaults) {
			triple = new Triple(groupIdVar, NodeFactory.createURI(tripleDefault.predicate()), NodeFactory.createLiteral(tripleDefault.object()));
			triples.add(triple);
		}
		triple = new Triple(groupIdVar, NodeFactory.createURI(rdfUri.uri()), Var.alloc(numStr));
		triples.add(triple);
		searchValues.put(numStr, searchTerm.getValue());
		
		return triples;
	}
	
	/**
	 * Get triples based on a query for a subject
	 * 
	 * @param method The method associated with the subject
	 * @param subjMethod The method associated with a field in the subject
	 * @param searchTerm The search term
	 * @param numStr The id number to associate with triples
	 * @param set The set to generate triples from
	 * @return The generated triples
	 */
	private List<Triple> getSubjectTriples(Method method, Method subjMethod, SearchTerm searchTerm, Map<String, String> searchValues, String numStr) {
		List<Triple> triples = new ArrayList<Triple>();
		
		RDFUri subjUri = method.getAnnotation(RDFUri.class);
		RDFUri subjMethUri = subjMethod.getAnnotation(RDFUri.class);
		Triple subjTriple = new Triple(Var.alloc("id"), NodeFactory.createURI(subjUri.uri()), Var.alloc(numStr + "_1"));
		Triple methSubjTriple = new Triple(Var.alloc(numStr + "_1"), NodeFactory.createURI(subjMethUri.uri()), Var.alloc(numStr));
		triples.add(subjTriple);
		triples.add(methSubjTriple);
		searchValues.put(numStr, searchTerm.getValue());
		
		return triples;
	}
}
