package org.gwtopenmaps.demo.openlayers.client.examples.charme.model;


//TODO: It's not been defined yet how a vertical extent looks like
public class VerticalExtent {

	private final String depthStart;
	private final String depthStop;
	
	public VerticalExtent(String depthStart, String depthStop){
		super();
		this.depthStart = depthStart;
		this.depthStop = depthStop;
	}
	
	public String getDepthStart(){
		return this.depthStart;
	}
	
	public String getDepthStop(){
		return this.depthStop;
	}
}
