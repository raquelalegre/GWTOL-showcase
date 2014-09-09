package org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter;

import java.util.List;

import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.Annotation;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.SpecificResource;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.SubsetSelector;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.view.NewAnnotationView;
import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.user.client.ui.PopupPanel;


/**
 * The Class NewAnnotationPresenterImpl contains the logic behind the application and communicates the view and the model, keeping them independent and modular.
 *
 *
 * @author: raquel
 */
public class NewAnnotationPresenterImpl implements NewAnnotationPresenter {

	private final NewAnnotationView view;
	private PopupPanel popupPanel;

	/**
	 * Instantiates a new presenter object, initializing the view and declaring itself as presenter of the MVP pattern.
	 *
	 * @param view the view, including the popup and the markers showing the annotations
	 * @param initialLonlat the initial lonlat
	 */
	public NewAnnotationPresenterImpl(NewAnnotationView view, LonLat initialLonlat) {
		this.view = view;
		view.initialiseView(initialLonlat);
		view.setPresenter(this);
	}
	
	/**
	 * Triggers the start of the MVP pattern.
	 *
	 * @param popupPanel the popup panel for the new annotation
	 */
	/* (non-Javadoc)
	 * @see org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.PresenterPopup#go(com.google.gwt.user.client.ui.PopupPanel)
	 */
	@Override
	public void go(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		popupPanel.setWidget(view);
		popupPanel.center();
	}

	/**
	 * Defines how to proceed when the user clicks the OK button in the New Annotation pop up
	 *
	 * @param popupPanel the popup panel for the new annotation
	 */
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
		
		String body = null; // User comment
		String target = null; // URI of the subset selector

		//Create a CHARMe Annotation object containing the information the user has entered.
		final Annotation annotation = new Annotation(body, target, type, motivation, comment, sr, ss);

		//For debug purposes: check the annotation in JSON-LD looks as spected
		System.out.println("Annotation created: " + annotation.toJson());

		//Close the panel when the annotation has been succesfully created.
		popupPanel.hide();
	}

	/**
	 * Defines how to proceed when the user clicks the Cancel button in the New Annotation pop up
	 *
	 * @param popupPanel the popup panel for the new annotation
	 */
	/* (non-Javadoc)
	 * @see org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenter#onCancelClicked()
	 */
	@Override
	public void onCancelClicked() {
		//Just close the window
		popupPanel.hide();
	}


}
