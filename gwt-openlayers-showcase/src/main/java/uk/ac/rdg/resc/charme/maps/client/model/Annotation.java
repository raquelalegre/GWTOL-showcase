package uk.ac.rdg.resc.charme.maps.client.model;

import java.util.List;

import uk.ac.rdg.resc.charme.maps.client.jsonld.JSONLDAnnotation;

import com.google.gwt.json.client.JSONObject;

/**
 * 
 * Represents a CHARMe Annotation created specifically for annotating subsets of data.
 * TODO: Incorporate all other possible fields (citation, etc) when we have a clearly defined Annotation schema from the CHARMe Node
 * 
 * This class also includes access to the conversion from Annotation Object -> JSON-LD and viceversa.
 * 
 * This is how an FG Annotation should look like in RDF-Turtle:
 * GWTOL-showcase/gwt-openlayers-showcase/annotation_sample/*.ttl
 * 
 * This is the JSON-LD equivalent (from RDF translator: http://rdf-translator.appspot.com/):
 * GWTOL-showcase/gwt-openlayers-showcase/annotation_sample/*.json-ld
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
	private final SpecificResource target;
	private final String motivation;
	private final String type;
	
	/**
	 * Instantiates a new annotation given a set of parameters
	 *
	 * @param body the annotation body, following W3C's OA standard, in this case it will always be a user comment
	 * @param target the dataset subset the annotation refers to, which is a OA Specific Resource
	 * @param type the annotation type, based on W3C's OA standard
	 * @param motivation the user's type of motivation for creating the annotation, from the enumerate type defined by W3C OA
	 * TODO: Make constructor receive all inputs from the user and elaborate it's own Specific Resource, which in turns creates the selector, which in turn elaborates the extents.
	 */
	public Annotation(String body, SpecificResource target, String motivation, String type) {
		super();
		this.body = body;
		this.target = target;
		this.motivation = motivation;
		this.type = type;
	}
	
	//Simpler constructor with only one variable, one temporal extent, vertical extent and one geometry 
	public Annotation(String body, String motivation, String type, String source, List<String> variables, TemporalExtent temporalExtent, SpatialExtent spatialExtent, VerticalExtent verticalExtent){
		super();
		this.body = body;
		this.target = new SpecificResource(source, variables, temporalExtent, spatialExtent, verticalExtent);
		this.motivation = motivation;
		this.type = type;
	}

	//Another simpler constructor with only primitives for one variable, one temporal extent, vertical extent and one geometry 
	public Annotation(String body, String motivation, String type, String source, List<String> variables, 
			String calendar, String temporalStart, String temporalStop, String wktText, String geoname, String depthStart, String depthStop){
		super();
		this.body = body;
		this.motivation = motivation;
		this.type = type;
		TemporalExtent temporalExtent = new TemporalExtent(calendar, temporalStart, temporalStop);
		SpatialExtent spatialExtent = new SpatialExtent(wktText, geoname);
		VerticalExtent verticalExtent = new VerticalExtent(depthStart, depthStop);
		this.target = new SpecificResource(source, variables, temporalExtent, spatialExtent, verticalExtent);
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
//		this.comment = null;
//		this.sr = null;
//		this.ss = new SubsetSelector(jsonAnnotation.getSubsetSelector());
		
		
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
	 * Gets the annotation body, which is the user comment.
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
	public SpecificResource getTarget() {
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

	//TODO: Create getters for the annotation to obtain from the subset selector, extents, etc

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	//TODO: Review and update when the rest of the elements are complete
	@Override
	public String toString() {
		return "Annotation [body=" + body + ", target=" + target
				+ ", motivation=" + motivation + ", type=" + type + "]";
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
