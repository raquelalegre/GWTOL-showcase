package org.gwtopenmaps.demo.openlayers.client.examples.charme.model;

import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld.JSONLDSubsetSelector;

/**
 * The Class SubsetSelector contains the elements relative to the subset boundaries (variables, time extent, depth interval and geographic boundaries).
 * Note a SubsetSelector may have several temporal extents, or variables, and so on.
 * This follows the W3C OA standard, the Strabon stRDF model for annotation geographical subsets and needs an adhoc ontology from CHARMe for completion - this is under development at the moment, so I'm using the model from the latest discussion on 14th July 2014.
 *
 * For example (RDF-Turtle):
 * 
 * <chnode:subsetSelectorID> a charme:SubsetSelector ;
 * charme:hasVariable "sst" ;
 * charme:hasVariable "chlor_a" ;  
 * charme:hasSpatialExtent <chnode:spatialExtentID> ;
 * charme:hasTemporalExtent <chnode:temporalExtentID> .
 * 
 * @author raquel 
 */
public class SubsetSelector {
	
	private List<String> variables;
	private List<TemporalExtent> temporalExtents;
	private List<SpatialExtent> spatialExtents;
	private List<VerticalExtent> verticalExtents;
	
	public SubsetSelector(List<String> variables, List<TemporalExtent> temporalExtents, List<SpatialExtent> spatialExtents, List<VerticalExtent> verticalExtents) {
		super();
		this.variables = variables;
		this.temporalExtents = temporalExtents;
		this.spatialExtents = spatialExtents;
		this.verticalExtents = verticalExtents;
	}

	//Simpler constructor with only one variable, one temporal extent, vertical extent and one geometry 
	public SubsetSelector(List<String> variables, TemporalExtent temporalExtent, SpatialExtent spatialExtent, VerticalExtent verticalExtent) {
		super();
		this.setVariables(variables);
		this.setTemporalExtent(temporalExtent);
		this.setSpatialExtent(spatialExtent);
		this.setVerticalExtent(verticalExtent);
	}
	
	
	/**
	 * Creates a SubsetSelector from a JSON-LD object
	 * 
	 * @param subsetSelector
	 */
	public SubsetSelector(JSONLDSubsetSelector subsetSelector) {
		// TODO get the rest of members
		this.setSpatialExtent(new SpatialExtent(subsetSelector.getHasGeometryStr()));
	}


	/**
	 * Adds a variable from the dataset to the list of variables the annotation applies to.
	 *
	 * @param variable the new variable
	 */
	public void setVariables (List<String> variables){		
		this.variables = variables;
	}
	
	/**
	 * Adds a time interval to the list of variables the annotation applies to.
	 *
	 * @param temporalExtent The time interval
	 */
	public void setTemporalExtent (TemporalExtent temporalExtent){
		this.temporalExtents.add(temporalExtent);
	}

	
	/**
	 * Adds a geographic interval to the list of variables the annotation applies to.
	 *
	 * @param spatialExtent The geographic interval
	 */
	public void setSpatialExtent (SpatialExtent spatialExtent){
		this.spatialExtents.add(spatialExtent);
	}

	
	/**
	  * Adds a vertical interval to the list of variables the annotation applies to.
	 *
	 * @param verticalExtent The vertical interval
	 */
	public void setVerticalExtent (VerticalExtent verticalExtent){
		this.verticalExtents.add(verticalExtent);
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
	 * Gets the time intervals the annotation applies to.
	 *
	 * @return the time intervals
	 */
	public List<TemporalExtent> getTemporalExtents (){
		return this.temporalExtents;
	}
	
	/**
	 * Gets the geographic intervals the annotation applies to.
	 *
	 * @return the geometries
	 */
	public List<SpatialExtent> getSpatialExtents (){
		return this.spatialExtents;
	}
	
	/**
	 * Gets the vertical intervals the annotation applies to.
	 *
	 * @return the vertical intervals
	 */
	public List<VerticalExtent> getVerticalExtent (){
		return this.verticalExtents;
	}

}
