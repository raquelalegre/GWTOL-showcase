package uk.ac.rdg.resc.charme.maps.client.model;

/**
 * The Class TemporalExtent as per charme datamodel v0.1:
 * 
 * <chnode:temporalExtentID> a charme:TemporalExtent ;
 *   charme:hasCalendar "Gregorian" ;
 *   charme:hasTemporalStart "2008-01-01T00:00:00Z"^^xsd:dateTime ;
 *   charme:hasTemporalStop "2009-01-01T00:00:00Z"^^xsd:dateTime .
 * 
 * @author raquel
 * 
 */
public class TemporalExtent {
	
	private final String calendar;
	private final String temporalStart;
	private final String temporalStop;
	
	public TemporalExtent (String calendar, String temporalStart, String temporalStop){
		super();
		this.calendar = calendar;
		this.temporalStart = temporalStart;
		this.temporalStop = temporalStop;
		
	}
	
	public String getCalendar(){
		return this.calendar;
	}
	
	public String getTemporalStart(){
		return this.temporalStart;
	}
	
	public String getTemporalStop(){
		return this.temporalStop;
	}
	
	//Handle date conversion and formatting here?
	
}

