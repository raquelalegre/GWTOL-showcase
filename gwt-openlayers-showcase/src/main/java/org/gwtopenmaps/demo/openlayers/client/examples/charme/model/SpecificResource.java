

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
	 * Sets the dataset source, which is the parent dataset the subset comes from. It should be a URI pointing to the dataset..
	 *
	 * @param source the dataset source
	 */
	public void setSource(String source){
		this.source = source;
	}
	
	/**
	 * Sets the subset selector object that contains all the information about the subset: geotemporal boundaries, target variables, etc.
	 *
	 * @param ss the subset selector
	 */
	public void setSelector(SubsetSelector ss){
		this.selector = ss;
	}
		
	/**
	 * Gets the source, which is the parent dataset the subset comes from. It should be a URI pointing to the dataset.
	 *
	 * @return the dataset source
	 */
	public String getSource(){
		return this.source;
	}
	
	/**
	 * Gets the subset selector object that contains all the information about the subset: geotemporal boundaries, target variables, etc.
	 *
	 * @return the subset selector
	 */
	public SubsetSelector getSelector(){
		return this.selector;
	}
}
