package org.gwtopenmaps.demo.openlayers.client.examples.charme.view;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenter;
import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.user.client.ui.IsWidget;

public interface NewAnnotationView extends IsWidget{

	public void setPresenter(NewAnnotationPresenter presenter);

	// TODO: Set the other elements coming from Godiva (vars, depth, time)
	//       public void initialiseView(LonLat lonlat, List<int> depth, ...);
	public void initialiseView(LonLat lonlat);

	
	
}
