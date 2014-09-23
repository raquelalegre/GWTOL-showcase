package uk.ac.rdg.resc.charme.maps.client.jsonld;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

//{
//    "@id": "chnode:subsetSelectorID",
//    "@type": [
//      "http://www.charme.org.uk/def/SubsetSelector"
//    ],
//    "http://strdf.di.uoa.gr/ontology#hasGeometry": [
//      {
//        "@type": "http://strdf.di.uoa.gr/ontology#WKT",
//        "@value": "POINT(-50 44);<http://www.opengis.net/def/crs/EPSG/0/4326>"
//      }
//    ],
//    "http://www.charme.org.uk/def/hasTemporalExtent": [
//      {
//        "@id": "chnode:temporalExtentID"
//      }
//    ],
//    "http://www.charme.org.uk/def/hasVariables": [
//      {
//        "@value": "sst"
//      },
//      {
//        "@value": "chlor_a"
//      }
//    ]
//  },
public class JSONLDSubsetSelector extends JSONLDElement {

	public JSONLDSubsetSelector(JSONObject wrapped) {
		super(wrapped);
		setId("chnode:subsetSelectorID");
		setTypeAsArray("http://www.charme.org.uk/def/SubsetSelector");
		add("http://www.charme.org.uk/def/hasTemporalExtent", createPredicate("chnode:temporalExtentID", null, null));
	}
	
	public void setHasGeometry(String value) {
		JSONLDElement geometry = new JSONLDElement(new JSONObject());
		geometry.setTypeAsString("http://strdf.di.uoa.gr/ontology#WKT");
		geometry.setValue(value);
		
		JSONLDElementArray array = new JSONLDElementArray(new JSONArray());
		array.add(geometry);
		
		this.add("http://strdf.di.uoa.gr/ontology#hasGeometry", array);
	}

	public String getHasGeometryStr() {
		JSONLDElementArray jsonldElementArray = get("http://strdf.di.uoa.gr/ontology#hasGeometry");
		JSONLDElement firstElem = jsonldElementArray.get(0);
		return firstElem.getValue();
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
	public void setHasVariables(List<String> variables) {
		JSONLDElementArray varsArray = new JSONLDElementArray(new JSONArray());
		for (String var : variables) {
			JSONLDElement elem = new JSONLDElement(new JSONObject());
			elem.setValue(var);
			varsArray.add(elem);
		}
		this.add("http://www.charme.org.uk/def/hasVariables", varsArray);
	}




}
