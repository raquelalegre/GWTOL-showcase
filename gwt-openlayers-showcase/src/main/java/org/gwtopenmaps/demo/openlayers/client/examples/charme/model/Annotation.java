package org.gwtopenmaps.demo.openlayers.client.examples.charme.model;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld.JSONLDAnnotation;

import com.google.gwt.json.client.JSONObject;

/**
 * 
 * Represents a CHARMe Annotation created specifically for annotating subsets of data.
 * TODO: Incorporate all other possible fields (citation, etc) when we have a clearly defined Annotation schema from the CHARMe Node
 * 
 * This class also includes the conversion from Annotation Object -> JSON-LD and viceversa.
 * TODO: Separate this JSON logic to somewhere else.
 * 
 * This is how an FG Annotation should look like in RDF-Turtle:
 * GWTOL-showcase/gwt-openlayers-showcase/annotation_sample/fgc_annot.ttl
 * 
 * This is the JSON-LD equivalent (from RDF translator: http://rdf-translator.appspot.com/):
 * GWTOL-showcase/gwt-openlayers-showcase/annotation_sample/fgc_annot.json
 *  
 * TODO: Work is still undergoing in the CHARMe Node side, and the following are not yet translated to CHARMe URIs, but will:
 * chnode:datasetSubsetID - target of a FG annotation; refers to the abstraction of the data subset, that has a temporal extent, a subset selector and points to a dataset source.
 * chnode:temporalExtentID - refers to the time interval that defines the data subset
 * chnode:subsetSelectorID - refers to the geometry that defines the data subset
 *
 * @author: raquel
 */
public class Annotation {

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
	 * @param sr the sr
	 * @param ss the ss
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
	 * Instantiates a new annotation object from a JSONValue object received from the CHARMe Node.
	 *
	 * @param json A JSON-LD Array object received from the CHARMe Node
	 */
	public Annotation(JSONLDAnnotation jsonAnnotation) {
		// TODO parse json
		this.body = jsonAnnotation.getBodyStr();
		this.target = null;
		this.motivation = null;
		this.type = null;
		this.comment = null;
		this.sr = null;
		this.ss = new SubsetSelector(jsonAnnotation.getSubsetSelector());
		
		
		//Gets a JSONValue that is a JSONArray formed by JSONObjects. 
		//TODO:Check the JSONValue is an array, otherwise throw exception
		//TODO:Go through elements in JSONArray that are JSONObjects.
		//TODO:Check for each element in JSONArray that it is a JSONObject, otherwise throw exception
		//TODO:Get the "@id" value in each element and check if it's the expected for the body, the target, etc.
		//TODO:Return JSONObject from the JSONArray that contains the needed id.

	}
	
	public JSONObject getJSONElement (String id){
		
		
		JSONObject element = null;
		
		
		return element;
	}
	
	/**
	 * Gets the annotation body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return this.body;
	}
	
	/**
	 * Gets the annotation target, which is a subset of a dataset.
	 *
	 * @return the annotation target
	 */
	public String getTarget() {
		return this.target;
	}

	/**
	 * Gets the user's motivation for the annotation.
	 *
	 * @return the user's motivation for the annotation
	 */
	public String getMotivation() {
		return this.motivation;
	}
	
	/**
	 * Gets the annotation type.
	 *
	 * @return the annotation type
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Gets the user comment.
	 *
	 * @return the user comment
	 */
	public String getContent() {
		return this.comment;
	}

	/**
	 * Gets the specific resource.
	 *
	 * @return the specific resource
	 */
	public SpecificResource getSpecificResource() {
		return this.sr;
	}

	/**
	 * Gets the subset selector.
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
	public JSONLDAnnotation toJson() {
		return new JSONLDAnnotation(this);
	}


}
