package uk.ac.rdg.resc.charme.maps.client.model;

import org.gwtopenmaps.openlayers.client.LonLat;

/**
 * The Class SpatialExtent contains the information about a geographic area.
 * TODO: Initially for simplification we'll only use points, but this is to be
 * upgraded to use other features like linestrings and polygons TODO: Needs a
 * WKT Parsers, but none for GWT found yet. TODO: Interaction with Geonames? No
 * accessible DB to obtain a geoname from a point entered by the user, maybe
 * just leave for the user to select a Geoname from a bounding box? But how does
 * this relate to the user entered coordinates? Could be just one or the
 * other... Also, just one geoname or a list of them? Several can apply to the
 * same area, but how to allow a user enter this?
 * 
 * <chnode:spatialExtentID> a charme:SpatialExtent ; geonames:name
 * "North Atlantic Ocean" ; geosparql:hasGeometry
 * "POINT(40 40);<http://www.opengis.net/def/crs/EPSG/0/4326>"
 * ^^geosparql:wktLiteral .
 * 
 * @author raquel
 */
public class SpatialExtent {

	private final String wktText;
	private final String geoname;

	/**
	 * Instantiates a new spatial extent.
	 */
	public SpatialExtent(String wktText, String geoname) {
		super();
		this.wktText = wktText;
		this.geoname = geoname;
	}

	public SpatialExtent(String wktText) {
		super();
		this.wktText = wktText;
		this.geoname = "";
	}

	public String getWktText() {
		return this.wktText;
	}

	public String getGeoname() {
		return this.geoname;
	}

	/**
	 * TODO: this is very dirty - Needs a WKT parser, but none available for GWT...
	 * Just assuming we are only dealing with POINTs which always look like:
	 * "POINT(-50 44)
	 * @return
	 */
	public LonLat getLonLatFromWktText() {
		String lonStr = wktText.substring(wktText.indexOf("(") + 1,
				wktText.indexOf(" "));
		double lon = Double.parseDouble(lonStr);
		String latStr = wktText.substring(wktText.indexOf(" ") + 1,
				wktText.indexOf(")"));
		double lat = Double.parseDouble(latStr);
		LonLat lonlat = new LonLat(lon, lat);
		return lonlat;
	}

}

