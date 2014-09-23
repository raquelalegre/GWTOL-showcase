package uk.ac.rdg.resc.charme.maps.client.jsonld;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
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

public class JSONLDElementArray extends JSONLDValue {

	private final JSONArray wrapped;
	
	public JSONLDElementArray(JSONArray wrapped) {
		this.wrapped = wrapped;
	}

	public JSONArray getWrappedJson() {
		return wrapped;
	}
	
	/**
	 * Adds a new JSONLDElement in the JSONLDArray at the end of the array (where this.size() points to).
	 *
	 * @param element A JSON-LD key-value/predicate-object pair.
	 */
	public void add(JSONLDElement element) {
		wrapped.set(wrapped.size(), element.getWrappedJson());
	}
	
	public int size() {
		return wrapped.size();
	}
	
	public JSONObject findElementWithId(String desiredId) {
		String desiredIdAux = '"' + desiredId + '"';
		
		for (int i = 0; i < size(); i++) {
			JSONValue jsonValue =  getWrappedJson().get(i);
			JSONLDElement element = new JSONLDElement((JSONObject)jsonValue);
			String foundStr = element.getId();
			if (desiredIdAux.equals(foundStr)) {
				return element.getWrappedJson();
			}
		}
		return null;
	}

	public JSONLDElement get(int i) {
		return new JSONLDElement((JSONObject)wrapped.get(i));
	}


	
}
