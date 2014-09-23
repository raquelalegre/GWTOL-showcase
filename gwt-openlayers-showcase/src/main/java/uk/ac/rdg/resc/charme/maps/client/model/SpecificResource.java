

package uk.ac.rdg.resc.charme.maps.client.model;

import java.util.List;

/**
 * The Class SpecificResource describes the annotation target which is a dataset subset that is the target of an annotation.
 * See W3C's OA standard specs.
 * 
 * Example (RDF-Turtle):
 * <chnode:datasetSubsetID> a oa:SpecificResource ;
 *  oa:hasSource  <http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png> ;
 *  oa:hasSelector <chnode:subsetSelectorID> .
 * 
 * @author: raquel
 * 
 */
public class SpecificResource {

	private String source;
	private SubsetSelector selector;
	
	public SpecificResource(String source, SubsetSelector selector){
		super();
		this.source = source;
		this.selector = selector;	
	}
	
	//Simpler constructor with only one variable, one temporal extent, vertical extent and one geometry 
	public SpecificResource(String source, List<String> variables, TemporalExtent temporalExtent, SpatialExtent spatialExtent, VerticalExtent verticalExtent){
		super();
		this.source = source;
		this.selector = new SubsetSelector(variables, temporalExtent, spatialExtent, verticalExtent);
	}
	
	
	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(String source){
		this.source = source;
	}
	
	/**
	 * Sets the selector.
	 *
	 * @param ss the new selector
	 */
	public void setSelector(SubsetSelector selector){
		this.selector = selector;
	}
		
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource(){
		return this.source;
	}
	
	/**
	 * Gets the selector.
	 *
	 * @return the selector
	 */
	public SubsetSelector getSelector(){
		return this.selector;
	}
}
