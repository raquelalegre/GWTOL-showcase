package org.gwtopenmaps.demo.openlayers.client.examples.charme.model;

import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld.JSONLDSubsetSelector;
import org.gwtopenmaps.openlayers.client.LonLat;

/**
 * The Class SubsetSelector contains the information relative to the subset boundaries (time extent, depth interval and geographic boundaries)
 * This follows the W3C OA standard, the Strabon stRDF model for annotation geographical subsets and needs an adhoc ontology from CHARMe for completion - this is under development at the moment, so I'm using the model from the latest discussion on 14th July 2014.
 *
 * For example (RDF-Turtle):
 * 
 * <chnode:subsetSelectorID> a charme:SubsetSelector ;
 * charme:hasVariables "chlor_a", "sst" ;
 * strdf:hasGeometry "POINT(-50 44);<http://www.opengis.net/def/crs/EPSG/0/4326>"^^strdf:WKT ;
 * charme:hasDepthInterval <chnode:depthIntervalID>
 * charme:hasTemporalExtent <chnode:temporalExtentID> .
 * 
 * @author raquel 
 */
public class SubsetSelector {
	
	
	private List<String> variables;
	private String geometry;
	private String validityStart;
	private String validityStop;
	private String dateFormat;
	private String depthStart;
	private String depthStop;
	
	public SubsetSelector() {
		// TODO Auto-generated constructor stub
	}
	
	public SubsetSelector(JSONLDSubsetSelector subsetSelector) {
		// TODO get the rest of members
		geometry = subsetSelector.getHasGeometryStr();
	}


	/**
	 * Sets the list of variables from the dataset the annotation applies to.
	 *
	 * @param variables the new variables
	 */
	public void setVariables (List<String> variables){
		this.variables = variables;
	}
	
	/**
	 * Sets the geometry the annotation applies to.
	 *
	 * @param geometry the new geometry
	 */
	public void setGeometry (String geometry){
		this.geometry = geometry;
	}
	
	/**
	 * Sets the validity start the annotation applies to.
	 *
	 * @param validityStart the new validity start
	 */
	public void setValidityStart (String validityStart){
		this.validityStart = validityStart;
	}
	
	/**
	 * Sets the validity stop the annotation applies to.
	 *
	 * @param validityStop the new validity stop
	 */
	public void setValidityStop (String validityStop){
		this.validityStop = validityStop;
	}
		
	/**
	 * Sets the time format the annotation applies to.
	 *
	 * @param dateFormat the new time format
	 */
	public void setTimeFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * Sets the depth start the annotation applies to.
	 *
	 * @param depthStart the new depth start
	 */
	public void setDepthStart(String depthStart) {
		this.depthStart = depthStart;
	}
	
	/**
	 * Sets the depth stop the annotation applies to.
	 *
	 * @param depthStop the new depth stop
	 */
	public void setDepthStop(String depthStop) {
		this.depthStop = depthStop;
	}
	
	
	/**
	 * Gets the variables the annotation applies to.
	 *
	 * @return the variables
	 */
	public List<String> getVariables (){
		return this.variables;
	}
	
	/**
	 * Gets the geometry the annotation applies to.
	 *
	 * @return the geometry
	 */
	public String getGeometry (){
		return this.geometry;
	}
	
	/**
	 * TODO: this is very dirty...
	 * @return
	 */
	public LonLat getLonLatFromGeometry() {
		//"POINT(-50 44)
		
		String lonStr = geometry.substring(geometry.indexOf("(") + 1, geometry.indexOf(" "));
		double lon = Double.parseDouble(lonStr);
		String latStr = geometry.substring(geometry.indexOf(" ") + 1, geometry.indexOf(")"));
		double lat = Double.parseDouble(latStr);
		LonLat ll = new LonLat(lon, lat);
		return ll;
	}
	/**
	 * Gets the validity start the annotation applies to.
	 *
	 * @return the validity start
	 */
	public String getValidityStart (){
		return this.validityStart;
	}
	
	/**
	 * Gets the validity stop the annotation applies to.
	 *
	 * @return the validity stop
	 */
	public String getValidityStop (){
		return this.validityStop;
	}
	
	/**
	 * Gets the time format the annotation applies to.
	 *
	 * @return the time format
	 */
	public String getTimeFormat() {
		return this.dateFormat;
	}

	/**
	 * Gets the depth start the annotation applies to.
	 *
	 * @return the depth start
	 */
	public String getDepthStart() {
		return this.depthStart;
	}
	
	/**
	 * Gets the depth stop the annotation applies to.
	 *
	 * @return the depth stop
	 */
	public String getDepthStop() {
		return this.depthStop;
	}


}
