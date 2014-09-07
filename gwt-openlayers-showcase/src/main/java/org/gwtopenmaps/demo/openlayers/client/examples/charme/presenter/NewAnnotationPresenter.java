package org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter;

import java.util.List;

/**
 * The Interface NewAnnotationPresenter.
 * 
 * @author: raquel
 */
public interface NewAnnotationPresenter extends PresenterPopup {

	/**
	 * On ok clicked.
	 *
	 * @param type the type
	 * @param motivation the motivation
	 * @param tags the tags
	 * @param comment the comment
	 * @param wktText the wkt text
	 * @param timeFormat the time format
	 * @param valStart the val start
	 * @param valStop the val stop
	 * @param depthStart the depth start
	 * @param depthStop the depth stop
	 * @param variables the variables
	 */
	public void onOkClicked(String type, String motivation, String tags, String comment, String wktText, String timeFormat, String valStart, String valStop, String depthStart, String depthStop, List<String> variables);
	
	/**
	 * On cancel clicked.
	 */
	public void onCancelClicked();

}
