package org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;

/**
 * The Class JSONLDElementArray represents an array of key-value/predicate-object pairs in a JSON-LD element.
 * For example, 
 *     
 * The "charme:hasContent" predicate has an array of key-value/predicate-object pairs that can be represented as JSONLDElements: 
 *     "http://www.charme.org.uk/def/hasContent": [
 *       {
 *         "@type": "http://www.w3.org/1999/02/22-rdf-syntax-ns#text",
 *         "@value": "There is sampling station here"
 *       }
 *     ]
 *   
 * @author: raquel
 */

public class JSONLDElementArray extends JSONArray {

	/**
	 * Adds a new JSONLDElement in the JSONLDArray at the end of the array (where this.size() points to).
	 *
	 * @param element A JSON-LD key-value/predicate-object pair.
	 */
	public void add(JSONLDElement element) {
		super.set(this.size(), element);
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.json.client.JSONArray#set(int, com.google.gwt.json.client.JSONValue)4
	 * We need to use the "set" method of the superclass intead.
	 */
	@Override
	@Deprecated
	public JSONValue set(int index, JSONValue value) {
		throw new RuntimeException("You need to add elements using add()");
	}
}
