/**
 * 
 * Contains the information relative to the subset boundaries. 
 *  
 * <http://localhost:8000/resource/selector1> a charme:SubsetSelector ;
 * charme:hasVariables "chlor_a" ;
 * geo:hasGeometry "POINT(-50 44);<http://www.opengis.net/def/crs/EPSG/0/4326>"^^sf:WktLiteral ;
 * charme:validityStart "2008-01-01T00:00:00Z"^^xsd:dateTime ;
 * charme:validityStop "2009-01-01T00:00:00Z"^^xsd:dateTime .
 *
 */

package org.gwtopenmaps.demo.openlayers.client.examples.charme.model;

import java.util.List;

public class SubsetSelector {
	
	List<String> variables;
	String geometry;
	String validityStart;
	String validityStop;
	String dateFormat;
	String depthStart;
	String depthStop;
	
	public void setVariables (List<String> variables){
		this.variables = variables;
	}
	
	public void setGeometry (String geometry){
		this.geometry = geometry;
	}
	
	public void setValidityStart (String validityStart){
		this.validityStart = validityStart;
	}
	
	public void setValidityStop (String validityStop){
		this.validityStop = validityStop;
	}
		
	public void setTimeFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setDepthStart(String depthStart) {
		this.depthStart = depthStart;
	}
	
	public void setDepthStop(String depthStop) {
		this.depthStop = depthStop;
	}
	
	
	public List<String> getVariables (){
		return this.variables;
	}
	
	public String getGeometry (){
		return this.geometry;
	}
	
	public String getValidityStart (){
		return this.validityStart;
	}
	
	public String getValidityStop (){
		return this.validityStop;
	}
	
	public String getTimeFormat() {
		return this.dateFormat;
	}

	public String getDepthStart() {
		return this.depthStart;
	}
	
	public String getDepthStop() {
		return this.depthStop;
	}


}
