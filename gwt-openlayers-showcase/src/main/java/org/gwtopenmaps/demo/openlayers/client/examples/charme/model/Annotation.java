/**
 * 
 * Represents all CHARMe Annotations fields for annotation subsets of data.
 * Useful for GSON serialisation and deserialisation 
 * 
 * @prefix charme:  <http://www.charme.org.uk/def/> .
 * @prefix oa: <http://www.w3.org/ns/oa#> .
 * @prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
 * @prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
 * @prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
 * @prefix geo: <http://www.opengis.net/def/geosparql/> .
 * @prefix sf: <http://www.opengis.net/def/sf/> .
 * 
 * <http://localhost:8000/resource/anno1> a oa:Annotation ;
 * oa:hasBody <http://localhost:8000/resource/body1> ;
 * oa:hasTarget <http://localhost:8000/resource/target1> ;
 * oa:motivatedBy oa:linking .
 * 
 * <http://localhost:8000/resource/body1> a charme:user_comment ;
 * charme:hasContent "There is a sampling station here"^^rdf:text.
 * 
 * <http://localhost:8000/resource/target1> a oa:SpecificResource ;
 * oa:hasSource  <http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png> ;
 * oa:hasSelector <http://localhost:8000/resource/selector1> .
 * 
 * <http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png> a charme:dataset .
 * <http://localhost:8000/resource/selector1> a charme:SubsetSelector ;
 * charme:hasVariables "chlor_a" ;
 * geo:hasGeometry "POINT(-50 44);<http://www.opengis.net/def/crs/EPSG/0/4326>"^^sf:WktLiteral ;
 * charme:validityStart "2008-01-01T00:00:00Z"^^xsd:dateTime ;
 * charme:validityStop "2009-01-01T00:00:00Z"^^xsd:dateTime .
 *  
 * 
 */

package org.gwtopenmaps.demo.openlayers.client.examples.charme.model;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class Annotation {

	private final String body;
	private final String target;
	private final String motivation;
	private final String type;
	private final String content;
	private final SpecificResource sr;
	private final SubsetSelector ss;

	public Annotation(String body, String target, String motivation,
			String type, String content, SpecificResource sr, SubsetSelector ss) {
		super();
		this.body = body;
		this.target = target;
		this.motivation = motivation;
		this.type = type;
		this.content = content;
		this.sr = sr;
		this.ss = ss;
	}

	public Annotation(JSONValue json) {
		// TODO parse json
		this.body = null;
		this.target = null;
		this.motivation = null;
		this.type = null;
		this.content = null;
		this.sr = null;
		this.ss = null;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public String getTarget() {
		return this.target;
	}

	public String getMotivation() {
		return this.motivation;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getContent() {
		return this.content;
	}

	public SpecificResource getSpecificResource() {
		return this.sr;
	}

	public SubsetSelector getSubsetSelector() {
		return this.ss;
	}

	@Override
	public String toString() {
		return "Annotation [body=" + body + ", target=" + target
				+ ", motivation=" + motivation + ", type=" + type
				+ ", content=" + content + ", sr=" + sr + ", ss=" + ss + "]";
	}
	
	public JSONValue toJson() {
		JSONArray annotationJson = new JSONArray();
		
		annotationJson.set(0, createBodyJson());
		annotationJson.set(1, createTemporalExtent());

		
		
		
		
		return annotationJson;
	}


//	  {
//		    "@id": "chnode:bodyID",
//		    "@type": [
//		      "http://www.charme.org.uk/def/user_comment"
//		    ],
//		    "http://www.charme.org.uk/def/hasContent": [
//		      {
//		        "@type": "http://www.w3.org/1999/02/22-rdf-syntax-ns#text",
//		        "@value": "There is sampling station here"
//		      }
//		    ]
//		  },
	private JSONValue createBodyJson() {
		JSONObject body = new JSONObject();
		body.put("@id", new JSONString("chnode:bodyID"));
		body.put("@type", createBodyType());
		body.put("http://www.charme.org.uk/def/hasContent", createBodyHasContent());
		return body;
	}


	private JSONValue createBodyType() {
		JSONArray array = new JSONArray();
		array.set(0, new JSONString("http://www.charme.org.uk/def/user_comment"));
		return array;
	}
	
	private JSONValue createBodyHasContent() {
		JSONObject hasContent = new JSONObject();
		hasContent.put("@type", new JSONString("http://www.w3.org/1999/02/22-rdf-syntax-ns#text"));
		hasContent.put("@value", new JSONString(this.content));
		return hasContent;
	}

	private JSONValue createTemporalExtent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
