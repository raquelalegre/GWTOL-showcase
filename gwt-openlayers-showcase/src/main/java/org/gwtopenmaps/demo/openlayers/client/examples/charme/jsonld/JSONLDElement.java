package org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * The Class JSONLDElement describes JSON-LD Elements necessary to pass the Annotation information to the CHARMe Node.
 * The pattern these follow is a bit tricky, as sometimes they have just the id, or just the value, or just the type, or a mixture of them, plus the type might come in different formats (as key-value pairs or as a string with one key-value pair).
 * TODO: Note this description of the JSON-LD Element has been created adhoc for an annotation example from the FGC meeting in 14th July 2014, as the CHARMe datamodel is still undergoing, and the Node implementation is not finished and might require a SPARQL or stSPARQL complement instead.
 * 
 * Elements' ids and values always come in key-value pairs, like:
 * 	  "@id": "chnode:bodyID"
 * 	  "@value": "2008-01-01T00:00:00+00:00"
 * 
 * Types may come as key-value pairs or as an array of key-value pairs, like:
 *    "@type": "http://www.w3.org/1999/02/22-rdf-syntax-ns#text"
 *    "@type": [
 *     	"http://www.charme.org.uk/def/user_comment"
 *     ]
 *     
 *  @author raquel
 *  
 */
public class JSONLDElement extends JSONObject {
	
	private static final String ID_KEY = "@id";
	private static final String TYPE_KEY = "@type";
	private static final String VALUE_KEY = "@value";

	/**
	 * Sets the "@id" as a key-value/predicate-object pair in the JSON-LD Element.
	 *
	 * @param id String representing the unique "@id" of the JSON-LD Element.
	 */
	public void setId(String id) {
		super.put(ID_KEY, new JSONString(id));
	}
	
	/**
	 * Sets the "@value" as a key-value/predicate-object pair  in the JSON-LD Element.
	 *
	 * @param value String representing the object of the "@value" predicate.
	 */
	public void setValue(String value) {
		super.put(VALUE_KEY, new JSONString(value));
	}

	/**
	 * Sets the "@type" as an array with one element that is a key-value/predicate-object pair  in the JSON-LD Element.
	 *
	 * @param type String representing the object of the "@type" predicate.
	 */
	public void setTypeAsArray(String type) {
		JSONArray array = new JSONArray();
		array.set(0, new JSONString(type));
		super.put(TYPE_KEY, array);
	}
	
	/**
	 * Sets the "@type" as a key-value/predicate-object pair in the JSON-LD Element.
	 *
	 * @param type String representing the object of the "@type" predicate.
	 */
	public void setTypeAsString(String type) {
		super.put(TYPE_KEY, new JSONString(type));
	}
	

	/**
	 * Adds a key-value pair, being the value an array of JSON-LD objects. 
	 *
	 * @param key The predicate of the triple, being the same as the key of a key-value pair.
	 * @param elementArray An adhoc JSONArray element with a set of key-value/precdicate-object pairs.
	 */
	public void add(String key, JSONLDElementArray elementArray) {
		super.put(key, elementArray);
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.json.client.JSONObject#put(java.lang.String, com.google.gwt.json.client.JSONValue)
	 * Deprecated to use the put of the super class instead.
	 */
	//@Override
	//@Deprecated
	//public JSONValue put(String key, JSONValue jsonValue) {
	//	throw new IllegalAccessError();
	//}
	

	

}
