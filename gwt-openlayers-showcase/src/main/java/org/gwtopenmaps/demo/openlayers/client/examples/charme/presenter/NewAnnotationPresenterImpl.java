package org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter;

import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld.JSONLDAnnotation;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.Annotation;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.SpecificResource;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.SubsetSelector;
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
			String comment, String wktText, String timeFormat, String valStart,
			String valStop, String depthStart, String depthStop,
			List<String> variables) {

		// Create new Annotation object with user's input

		// Initialise a SubsetSelector
		SubsetSelector ss = new SubsetSelector();
		ss.setGeometry(wktText);
		ss.setTimeFormat(timeFormat);
		ss.setValidityStart(valStart);
		ss.setValidityStop(valStop);
		ss.setVariables(variables);
		ss.setDepthStart(depthStart);
		ss.setDepthStop(depthStop);

		// Initialise a SpecificResource object
		SpecificResource sr = new SpecificResource();
		sr.setSelector(ss);
		String source = null; // URI of the dataset
		sr.setSource(source);

		// /
		
		String body = null; // URI of the subset selector?
		String target = null; // URI of the subset selector

		final Annotation annotation = new Annotation(body, target, type, motivation, comment, sr, ss);

		JSONLDAnnotation jsonAnnotation = annotation.toJson();
		System.out.println("Annotation created: " + jsonAnnotation);
		System.out.println("Body retrieved from json annotation: " + jsonAnnotation.getBodyStr());
		System.out.println("GEometry retrieved from json annotation: " + jsonAnnotation.getSubsetSelector().getHasGeometryStr());
		
		// Serialise (Java Object --> JSON) using GSON
//		Gson gson = new Gson();
//		gson.toJson(annotation);
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(annotation);
//		System.out.println(json);

		popupPanel.hide();
	}

	/* (non-Javadoc)
	 * @see org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenter#onCancelClicked()
	 */
	@Override
	public void onCancelClicked() {
		popupPanel.hide();
	}


}
