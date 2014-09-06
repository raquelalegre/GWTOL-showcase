package org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter;

import java.util.List;

public interface NewAnnotationPresenter extends PresenterPopup {

	public void onOkClicked(String type, String motivation, String tags, String comment, String wktText, String timeFormat, String valStart, String valStop, String depthStart, String depthStop, List<String> variables);
	public void onCancelClicked();

}
