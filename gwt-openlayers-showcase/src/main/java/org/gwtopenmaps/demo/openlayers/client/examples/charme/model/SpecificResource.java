

package org.gwtopenmaps.demo.openlayers.client.examples.charme.model;

/**
 * The Class SpecificResource describes the subset from a dataset that is the target of an annotation.
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
	public void setSelector(SubsetSelector ss){
		this.selector = ss;
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
