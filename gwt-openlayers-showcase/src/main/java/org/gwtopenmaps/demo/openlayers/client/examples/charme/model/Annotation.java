package org.gwtopenmaps.demo.openlayers.client.examples.charme.model;

import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld.JSONLDElement;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld.JSONLDElementArray;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * 
 * Represents a CHARMe Annotation created specifically for annotating geotemporal (fine-grained) subsets of data.
 * TODO: Incorporate all other possible fields (citation, etc) when we have a clearly defined Annotation schema from the CHARMe Node
 * 
 * This class also includes functionality for the conversion from Annotation Object -> JSON-LD and viceversa.
 * TODO: Separate the JSON logic to somewhere else.
 * 
 * This is how an fine-grained Annotation should look like in RDF-Turtle:
 * GWTOL-showcase/gwt-openlayers-showcase/annotation_sample/fgc_annot.ttl
 * 
 * This is the JSON-LD equivalent (from RDF translator: http://rdf-translator.appspot.com/):
 * GWTOL-showcase/gwt-openlayers-showcase/annotation_sample/fgc_annot.json
 *  
 * TODO: Work is still undergoing in the CHARMe Triplestore/Node side, and the following are not yet translated to CHARMe URIs, but will:
 * chnode:datasetSubsetID - target of a FG annotation; refers to the abstraction of the data subset, that has a temporal extent, a subset selector and points to a dataset source.
 * chnode:temporalExtentID - refers to the time interval that defines the data subset
 * chnode:subsetSelectorID - refers to the geometry that defines the data subset
 *
 * @author: raquel
 */
public class Annotation {

	/* The parts of a CHARMe Annotation following the latest CHARMe data model, not yet definitive */
	private final String body;
	private final String target;
	private final String motivation;
	private final String type;
	private final String comment;
	private final SpecificResource sr;
	private final SubsetSelector ss;

	/**
	 * Instantiates a new annotation given a set of parameters
	 *
	 * @param body the annotation body, following W3C's OA standard
	 * @param target the parent dataset the annotation target refers to
	 * @param type the annotation type, based on W3C's OA standard
	 * @param motivation the user's type of motivation for creating the annotation
	 * @param content the user comment about the annotation
	 * @param sr the specific resource (see W3C Open Annotation model)
	 * @param ss the subset selector (see W3C Open Annotation model)
	 */
	public Annotation(String body, String target, String type,
			String motivation, String comment, SpecificResource sr, SubsetSelector ss) {
		super();
		this.body = body;
		this.target = target;
		this.motivation = motivation;
		this.type = type;
		this.comment = comment;
		this.sr = sr;
		this.ss = ss;
	}

	/**
	 * Instantiates a new annotation object from a JSONValue object received from the CHARMe Triplestore/Node.
	 *
	 * @param json A JSON-LD Array object received from the CHARMe Triplestore/Node
	 */
	public Annotation(JSONValue json) {
		// TODO parse json
		this.body = null;
		this.target = null;
		this.motivation = null;
		this.type = null;
		this.comment = null;
		this.sr = null;
		this.ss = null;
	}
	
	/**
	 * Gets the annotation body, which is a user comment about the dataset.
	 *
	 * @return the annotation body, which is a user comment about the dataset.
	 */
	public String getBody() {
		return this.body;
	}
	
	/**
	 * Gets the annotation target, which is the URI of a subset of a dataset.
	 *
	 * @return the annotation target, which is the URI of a subset of a dataset.
	 */
	public String getTarget() {
		return this.target;
	}

	/**
	 * Gets the user's motivation for the annotation, has to be one of the enumerated type described in W3C OA standard.
	 *
	 * @return the user's motivation for the annotation, from the enumerated type described in W3C OA standard.
	 */
	public String getMotivation() {
		return this.motivation;
	}
	
	/**
	 * Gets the annotation type, has to be one of the enumerated type described in W3C OA standard. 
	 *
	 * @return the annotation type
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Gets the user comment, to be deprecated as so does the getBody method.
	 * TODO: Deprecate
	 *
	 * @return the user comment
	 */
	public String getContent() {
		return this.comment;
	}

	/**
	 * Gets the specific resource, an object that links the object representing the dataset subset (charme:datasetSubset) with the dataset it comes from.
	 *
	 * @return the specific resource
	 */
	public SpecificResource getSpecificResource() {
		return this.sr;
	}

	/**
	 * Gets the subset selector, describes the boundaries of the subset.
	 * TODO: Might be better to access this from the subset selector instead of directly from the annotation object.
	 *
	 * @return the subset selector
	 */
	public SubsetSelector getSubsetSelector() {
		return this.ss;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Annotation [body=" + body + ", target=" + target
				+ ", motivation=" + motivation + ", type=" + type
				+ ", comment=" + comment + ", sr=" + sr + ", ss=" + ss + "]";
	}
	
	/**
	 * Creates a JSON-LD Array like the one in:
	 * 		GWTOL-showcase/gwt-openlayers-showcase/annotation_sample/fgc_annot.output_from_charme_maps.json
	 * 
	 *
	 * @return the JSON-LD Array 
	 */
	public JSONValue toJson() {
		JSONArray annotationJson = new JSONArray();
		
		annotationJson.set(0, createBodyJson());
		annotationJson.set(1, createTemporalExtent());
		annotationJson.set(2, createSubSelector());
		annotationJson.set(3, createAnno());
		annotationJson.set(4, createDataSubset());
		annotationJson.set(5, createAgent());
		annotationJson.set(6, createTargetDataset());
		annotationJson.set(7, createAuthor());
		
		return annotationJson;
	}


	/**
	 * Creates a predicate for a JSON-LD Element in the JSON-LD Object that represents the Annotation.
	 * The predicate can have one or a combination of the the following elements:
	 * "@id"
	 * "@type"
	 * "@value"
	 * This method receives these elements as parameters, and creates predicates for those parameters that are not null.
	 * 
	 * For example, if we need to represent:
	 * 
	 *     "http://www.charme.org.uk/def/hasContent": [
	 *	      {
	 *	        "@type": "http://www.w3.org/1999/02/22-rdf-syntax-ns#text",
	 *	        "@value": "There is a sampling station here"
	 *	      }
	 *	    ]
	 * 
	 * we'd call the method like this:
	 * 
	 *     createPredicate(null, "http://www.w3.org/1999/02/22-rdf-syntax-ns#text", "There is a sampling station here");
	 *     
	 * which would return:
	 * 
	 * 	    [
	 *	      {
	 *	        "@type": "http://www.w3.org/1999/02/22-rdf-syntax-ns#text",
	 *	        "@value": "There is a sampling station here"
	 *	      }
	 *	    ]
	 * 
	 * and in turn needs to be passed as a value of the predicate "http://www.charme.org.uk/def/hasContent" with another method such as the JSONLDElement.add() method.
	 * 
	 * @param id Represents an "@id" predicate.
	 * @param type Represents a "@type" predicate.
	 * @param value Represents a "@value" predicate.
	 * 
	 * @return the JSONLD element array
	 */
	private JSONLDElementArray createPredicate(String id, String type, String value) {
		JSONLDElementArray hasContent = new JSONLDElementArray();
		JSONLDElement elem = new JSONLDElement();
		if (id != null) {
			elem.setId(id);
		}
		if (type != null) {
			elem.setTypeAsString(type);
		}
		if (value != null) {
			elem.setValue(value);
		}
		hasContent.add(elem);
		return hasContent;
	}

	/**
	 * Creates the "chnode:bodyID" element of the annotation. For example:
	 *
	 *	  {
	 *	    "@id": "chnode:bodyID",
	 *	    "@type": [
	 *	      "http://www.charme.org.uk/def/user_comment"
	 *	    ],
	 *	    "http://www.charme.org.uk/def/hasContent": [
	 *	      {
	 *	        "@type": "http://www.w3.org/1999/02/22-rdf-syntax-ns#text",
	 *	        "@value": "There is a sampling station here"
	 *	      }
	 *	    ]
	 *	  },
	 * 
	 *
	 * @return The JSON-LD Element that represents a chnode:bodyID node. 
	 */
	private JSONValue createBodyJson() {
		JSONLDElement body = new JSONLDElement();
		body.setId("chnode:bodyID");
		body.setTypeAsArray("http://www.charme.org.uk/def/user_comment");
		body.add("http://www.charme.org.uk/def/hasContent", createPredicate(null, "http://www.w3.org/1999/02/22-rdf-syntax-ns#text", this.comment));

		return body;
	}


	/**
	 * Creates the "chnode:temporalExtentID" element of the annotation. For example:
	 *
	 *	  {
	 *	    "@id": "chnode:temporalExtentID",
	 *	    "http://www.charme.org.uk/def/hasCalendar": [
	 *	      {
	 *	        "@value": "Gregorian"
	 *	      }
	 *	    ],
	 *	    "http://www.charme.org.uk/def/hasStart": [
	 *	      {
	 *	        "@type": "http://www.w3.org/2001/XMLSchema#dateTime",
	 *	        "@value": "2008-01-01T00:00:00+00:00"
	 *	      }
	 *	    ],
	 *	    "http://www.charme.org.uk/def/hasStop": [
	 *	      {
	 *	        "@type": "http://www.w3.org/2001/XMLSchema#dateTime",
	 *	        "@value": "2009-01-01T00:00:00+00:00"
	 *	      }
	 *	    ]
	 *	  },
	 * 
	 *
	 * @return The JSON-LD Element that represents a chnode:temporalExtentID node. 
	 */
	private JSONLDElement createTemporalExtent() {
		JSONLDElement temporalExtent = new JSONLDElement();
		temporalExtent.setId("chnode:temporalExtentID");
		temporalExtent.add("http://www.charme.org.uk/def/hasCalendar", createPredicate(null, null, "Gregorian"));
		temporalExtent.add("http://www.charme.org.uk/def/hasStart", createPredicate(null, "http://www.w3.org/2001/XMLSchema#dateTime", this.sr.getSelector().getValidityStart()));
		temporalExtent.add("http://www.charme.org.uk/def/hasStop", createPredicate(null, "http://www.w3.org/2001/XMLSchema#dateTime", this.sr.getSelector().getValidityStop()));

		return temporalExtent;
	}
	
	/**
	 * Creates the "chnode:subsetSelectorID" element of the annotation. For example:
	 *
	 *    {
	 *	    "@id": "chnode:subsetSelectorID",
	 *	    "@type": [
	 *	      "http://www.charme.org.uk/def/SubsetSelector"
	 *	    ],
	 *	    "http://strdf.di.uoa.gr/ontology#hasGeometry": [
	 *	      {
	 *	        "@type": "http://strdf.di.uoa.gr/ontology#WKT",
	 *	        "@value": "POINT(-50 44);<http://www.opengis.net/def/crs/EPSG/0/4326>"
	 *	      }
	 *	    ],
	 *	    "http://www.charme.org.uk/def/hasTemporalExtent": [
	 *	      {
	 *	        "@id": "chnode:temporalExtentID"
	 *	      }
	 *	    ],
	 *	    "http://www.charme.org.uk/def/hasVariables": [
	 *	      {
	 *	        "@value": "sst"
	 *	      },
	 *	      {
	 *	        "@value": "chlor_a"
	 *	      }
	 * 	    ]
	 *	  },
	 * 
	 *
	 * @return The JSON-LD Element that represents a chnode:subsetSelectorID node. 
	 */
	private JSONValue createSubSelector() {
		JSONLDElement subSelector = new JSONLDElement();
		subSelector.setId("chnode:subsetSelectorID");
		subSelector.setTypeAsArray("http://www.charme.org.uk/def/SubsetSelector");
		subSelector.add("http://strdf.di.uoa.gr/ontology#hasGeometry", createPredicate(null, "http://strdf.di.uoa.gr/ontology#WKT", this.sr.getSelector().getGeometry()));
		subSelector.add("http://www.charme.org.uk/def/hasTemporalExtent", createPredicate("chnode:temporalExtentID", null, null));
		subSelector.add("http://www.charme.org.uk/def/hasVariables", createHasVariables(this.sr.getSelector().getVariables()));

		return subSelector;
	}
	
	/**
	 * Creates the "charme:hasVariables" element of the annotation. For example:
	 *
	 *     "http://www.charme.org.uk/def/hasVariables": [
     * 			{
     *   			"@value": "sst"
     * 			},
     * 			{
     *   			"@value": "chlor_a"
     * 			}
     *		]
	 * 
	 *
	 * @param variables List of the dataset's variables the annotation applies to.
	 * @return The JSON-LD Array that represents a charme:hasVariables node. 
	 */
	private JSONLDElementArray createHasVariables(List<String> variables) {
		JSONLDElementArray varsArray = new JSONLDElementArray();
		for (String var : variables) {
			JSONLDElement elem = new JSONLDElement();
			elem.setValue(var);
			varsArray.add(elem);
		}
		return varsArray;
	}
	
	/**
	 * Creates the target JSONValue representing the annoID element and its auxiliar data (creating time, motivation, etc) in JSON-LD format, following the W3C OA standard and the CHARMe datamodel.
	 *
	 * @return a JSONValue representing the annoID element of the annotation.
	 */
	private JSONValue createAnno() {
		JSONLDElement anno = new JSONLDElement();
		anno.setId("chnode:annoID");
		anno.setTypeAsArray("http://www.w3.org/ns/oa#Annotation");
		anno.add("http://www.w3.org/ns/oa#annotatedAt", createPredicate(null, null, "TODO")); //TODO
		anno.add("http://www.w3.org/ns/oa#annotatedBy", createPredicate("TODO", null, null)); //TODO
		anno.add("http://www.w3.org/ns/oa#hasBody", createPredicate("chnode:bodyID", null, null));
		anno.add("http://www.w3.org/ns/oa#hasTarget", createPredicate("chnode:datasetSubsetID", null, null));
		anno.add("http://www.w3.org/ns/oa#motivatedBy", createPredicate("http://www.w3.org/ns/oa#" +this.getMotivation(), null, null));
		anno.add("http://www.w3.org/ns/oa#serializedAt", createPredicate(null, null, "TODO")); //TODO
		anno.add("http://www.w3.org/ns/oa#serializedBy", createPredicate("chnode:agentID", null, null));
		
		return anno;
	}
	
	/**
	 * Creates the target JSONValue representing the specific resource element of the annotation in JSON-LD format.
	 *
	 * @return a JSONValue representing the specific resource of the annotation is a subset of.
	 */
	private JSONValue createDataSubset() {
		JSONLDElement datasetSubset = new JSONLDElement();
		datasetSubset.setId("chnode:datasetSubsetID");
		datasetSubset.setTypeAsArray("http://www.w3.org/ns/oa#SpecificResource");
		datasetSubset.add("http://www.w3.org/ns/oa#hasSelector", createPredicate("chnode:subsetSelectorID", null, null)); //TODO: Charme Node doesn't understand this yet
		datasetSubset.add("http://www.w3.org/ns/oa#hasSource", createPredicate("http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png", null, null)); //TODO: Should come from Godiva
		
		return datasetSubset;
	}
	
	/**
	 * Creates the agent element of the annotation in JSON-LD format.
	 *
	 * @return a JSONValue representing the agent software that has created the annotation, the CHARMe Maps Tool, in this case.
	 */
	private JSONValue createAgent() {
		JSONLDElement agent = new JSONLDElement();
		agent.setId("chnode:agentID");
		agent.setTypeAsArray("http://www.w3.org/ns/prov#SoftwareAgent");
		agent.add("http://xmlns.com/foaf/0.1/name", createPredicate(null, null, "CHARMe Maps Tool v0.1b"));

		return agent;
	}

	/**
	 * Creates the target dataset element of the annotation in JSON-LD format.
	 *
	 * @return a JSONValue representing the dataset the target of the annotation is a subset of.
	 */
	private JSONValue createTargetDataset() {
		
		JSONLDElement targetDataset = new JSONLDElement();
		targetDataset.setId("http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png");
		targetDataset.setTypeAsArray("http://www.charme.org.uk/def/dataset");

		return targetDataset;
	}

	/**
	 * Creates the author element of the annotation in JSON-LD format.
	 *
	 * @return a JSONValue representing the author of the annotation.
	 */
	private JSONValue createAuthor() {
		JSONLDElement author = new JSONLDElement();
		author.setId("http://pk904866");
		author.setTypeAsArray("http://xmlns.com/foaf/0.1/Person");
		author.add("http://xmlns.com/foaf/0.1/mbox", createPredicate("mailto:r.alegre@reading.ac.uk", null, null)); //TODO: Charme Node doesn't understand this yet
		author.add("http://xmlns.com/foaf/0.1/name", createPredicate(null, null, "Raquel Alegre")); //TODO: Should come from OAuth? Node? HTTPUrl?
		
		return author;
	}

}
