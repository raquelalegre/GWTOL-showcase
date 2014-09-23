package uk.ac.rdg.resc.charme.maps.client.view;

import org.gwtopenmaps.openlayers.client.LonLat;

import uk.ac.rdg.resc.charme.maps.client.presenter.NewAnnotationPresenter;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * The Interface NewAnnotationView.
 *
 *
 * @author: raquel
 */
public interface NewAnnotationView extends IsWidget{

	/**
	 * Sets the presenter.
	 *
	 * @param presenter the new presenter
	 */
	public void setPresenter(NewAnnotationPresenter presenter);

	// TODO: Set the other elements coming from Godiva (vars, depth, time)
	//       public void initialiseView(LonLat lonlat, List<int> depth, ...);
	/**
	 * Initialise view.
	 *
	 * @param lonlat the lonlat
	 */
	public void initialiseView(LonLat lonlat);

	
	
}
