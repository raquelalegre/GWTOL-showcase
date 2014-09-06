package org.gwtopenmaps.demo.openlayers.client.examples.charme;

import org.gwtopenmaps.openlayers.client.LonLat;

public interface NewAnnotationView {

	public void setPresenter(NewAnnotationPresenter presenter);

	// TODO: Set the other elements coming from Godiva (vars, depth, time)
	//       public void initialiseView(LonLat lonlat, List<int> depth, ...);
	public void initialiseView(LonLat lonlat);

	public void close();
	
	
}
