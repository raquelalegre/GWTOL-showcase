package org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class JSONLDValue {
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
	 protected JSONLDElementArray createPredicate(String id, String type, String value) {
		JSONLDElementArray hasContent = new JSONLDElementArray(new JSONArray());
		JSONLDElement elem = new JSONLDElement(new JSONObject());
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
}
