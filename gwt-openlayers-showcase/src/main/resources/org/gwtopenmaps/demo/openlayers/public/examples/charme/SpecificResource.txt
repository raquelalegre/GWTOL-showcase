/**
 * 
 *  Specific Resource is the target of an annotation about a dataset subset.
 *  It describes how to determine the aspects of the Source that constitute the subset.
 * 
 * <http://localhost:8000/resource/target1> a oa:SpecificResource ;
 * oa:hasSource  <http://oceancolor.gsfc.nasa.gov/cgi/l3/A20140912014120.L3m_MO_CHL_chlor_a_9km.png> ;
 * oa:hasSelector <http://localhost:8000/resource/selector1> .
 * 
 */

package org.gwtopenmaps.demo.openlayers.client.examples.charme;

public class SpecificResource {

	String source;
	SubsetSelector selector;
	
	public void setSource(String source){
		this.source = source;
	}
	
	public void setSelector(SubsetSelector ss){
		this.selector = ss;
	}
		
	public String getSource(){
		return this.source;
	}
	
	public SubsetSelector getSelector(){
		return this.selector;
	}
}
