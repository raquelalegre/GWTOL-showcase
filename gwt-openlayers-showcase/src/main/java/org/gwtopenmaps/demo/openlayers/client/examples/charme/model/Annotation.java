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

public class Annotation {

	String body;
	String target;
	String motivation;
	String type;
	String content;
	SpecificResource sr;
	SubsetSelector ss;

	public void setBody(String body) {
		this.body = body;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public void setSpecificResource(SpecificResource sr) {
		this.sr = sr;
	}

	public void setSubsetSelector(SubsetSelector ss) {
		this.ss = ss;
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
	

	
	
}
