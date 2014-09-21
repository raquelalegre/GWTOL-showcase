package org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter;

import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld.JSONLDAnnotation;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.Annotation;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.SpatialExtent;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.SpecificResource;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.SubsetSelector;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.TemporalExtent;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.VerticalExtent;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.view.NewAnnotationView;
import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.PopupPanel;


/**
 * The Class NewAnnotationPresenterImpl.
 *
 *
 * @author: raquel
 */
public class NewAnnotationPresenterImpl implements NewAnnotationPresenter {

	/** The view. */
	private final NewAnnotationView view;
	
	/** The popup panel. */
	private PopupPanel popupPanel;

	private NewAnnotationPresenterListener listener;

	/**
	 * Instantiates a new new annotation presenter impl.
	 *
	 * @param view the view
	 * @param initialLonlat the initial lonlat
	 */
	public NewAnnotationPresenterImpl(NewAnnotationView view, LonLat initialLonlat) {
		this.view = view;
		view.initialiseView(initialLonlat);
		view.setPresenter(this);
	}
	
	/* (non-Javadoc)
	 * @see org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.PresenterPopup#go(com.google.gwt.user.client.ui.PopupPanel)
	 */
	@Override
	public void go(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		popupPanel.setWidget(view);
		popupPanel.center();
	}

	
	/* (non-Javadoc)
	 * @see org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenter#onOkClicked(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public void onOkClicked(String type, String motivation, String tags,
			String comment, String wktText, String calendar, String timeFormat, String temporalStart,
			String temporalStop, String depthStart, String depthStop,
			List<String> variables) {

		// Create new Annotation object with user's input
		String source = null; // TODO: URI of the dataset
		String geoname = null; //TODO

		final Annotation annotation = new Annotation(comment, motivation, type, source, variables, calendar, temporalStart, temporalStop, wktText, geoname, depthStart, depthStop);
			
		
		listener.onNewAnnotationCrated(annotation);
		
		popupPanel.hide();
	}

	/* (non-Javadoc)
	 * @see org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenter#onCancelClicked()
	 */
	@Override
	public void onCancelClicked() {
		popupPanel.hide();
	}

	@Override
	public void setListener(NewAnnotationPresenterListener listener) {
		this.listener = listener;
	}


}
