package org.gwtopenmaps.demo.openlayers.client.examples.charme;

import java.util.List;

import org.gwtopenmaps.openlayers.client.LonLat;

public class NewAnnotationPresenterImpl implements NewAnnotationPresenter {

	private final NewAnnotationView view;

	public NewAnnotationPresenterImpl(NewAnnotationView view, LonLat initialLonlat) {
		this.view = view;
		view.setPresenter(this);
		view.initialiseView(initialLonlat);
	}
	
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
		Annotation annotation = new Annotation();
		String body = null; // URI of the subset selector?
		annotation.setBody(body);
		String target = null; // URI of the subset selector
		annotation.setTarget(target);
		annotation.setType(type);
		annotation.setMotivation(motivation);
		annotation.setContent(comment);
		annotation.setSpecificResource(sr);
		annotation.setSubsetSelector(ss);

		System.out.println("Annotation created: " + annotation.toString());
		
		// Serialise (Java Object -> JSON) using GSON
//		Gson gson = new Gson();
//		gson.toJson(annotation);
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(annotation);
//		System.out.println(json);

		view.close();
	}

	@Override
	public void onCancelClicked() {
		view.close();
	}

}
