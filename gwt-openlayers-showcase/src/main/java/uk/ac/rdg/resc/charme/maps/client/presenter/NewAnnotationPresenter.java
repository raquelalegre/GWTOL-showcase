package uk.ac.rdg.resc.charme.maps.client.presenter;

import java.util.List;

import uk.ac.rdg.resc.charme.maps.client.model.Annotation;

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
	 * @param calendar 
	 * @param variables the variables
	 */
	public void onOkClicked(String type, String motivation, String tags, String comment, String wktText, String calendar, String timeFormat, String valStart, String valStop, String depthStart, String depthStop, List<String> variables);
	
	/**
	 * On cancel clicked.
	 */
	public void onCancelClicked();
	
	
	/**
	 * Method used by the listener of this UI to be subscribed to the events triggered from this UI.
	 * @param listener
	 */
	public void setListener(NewAnnotationPresenterListener listener);
	
	/**
	 * Interface to be implemented by objects interested in knowing about events happened in the NewAnnotation UI
	 * @author raquel
	 */
	public static interface NewAnnotationPresenterListener {
		public void onNewAnnotationCrated(Annotation annotation);
	}

}