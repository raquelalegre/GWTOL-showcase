package org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter;

import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Presenter to be used with Popup. Declares the method that will present the initial view of the application.
 * 
 * TODO: make this most compliant with http://javadoc.gwt-platform.googlecode.com/hg/0.5/com/gwtplatform/mvp/client/PresenterWidget.html
 * 
 * @author raquel
 *
 */
public interface PresenterPopup {
	
	/**
	 * Go. Main method that triggers the MVP pattern.
	 *
	 * @param popupPanel the popup panel
	 */
	public  void go(final PopupPanel popupPanel);
}
