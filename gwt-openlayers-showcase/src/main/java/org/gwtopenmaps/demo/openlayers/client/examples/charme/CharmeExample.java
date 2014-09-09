/**
 *
 *   Copyright 2014 sourceforge.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.gwtopenmaps.demo.openlayers.client.examples.charme;

import javax.inject.Inject;

import org.gwtopenmaps.demo.openlayers.client.DialogBoxWithCloseButton;
import org.gwtopenmaps.demo.openlayers.client.basic.AbstractExample;
import org.gwtopenmaps.demo.openlayers.client.components.store.ShowcaseExampleStore;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenter;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenterImpl;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.view.NewAnnotationView;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.view.NewAnnotationViewImpl;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.OverviewMap;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;


/**
 *
 * Entrypoint for the CHARMe Example that showcases the CHARMe Maps tool, a prototype that will show the ability of the CHARMe Triplestore/Node to use Linked Data and W3C's Open Annotation for creating annotations about subsets of scientific data.
 * This tool is developed using GWT and Open Layers. This example extends the showcase created by the developer of the GWT wrapper for OpenLayers, which is not part of the CHARMe Project, but is used by the developers of the CHARMe Maps.
 * This example will be exported to the Godiva2 tool developed at University of Reading. This tool uses ncWMS, a Web Map Service that loads mapped scientific data and plot them in Open Layers using the GWT wrapper this example is based on.
 * The logic of this example is been designed based on the MVVP (Model-View-View Presenter) architectural patter, which nicely uses the view presenter to perform all the logic of the application. The view is totally independent from the model, making the code more modular and easy to change.  
 * This example should show an interactive map where a user can select a subset in the map, insert some input data in a pop-up, and send it to the CHARMe Triplestore/Node. The application will create a JSON-LD (JSON for Linked Data) file and send it to the node.
 * It'll also show the existent annotations inside the boundaries shown in the map. Interactive markers for each of the annotation will allow users visualize the content of the annotation.
 * 
 * 
 * @author raquel
 *
 */

public class CharmeExample extends AbstractExample {

    private static final Projection DEFAULT_PROJECTION = new Projection(
            "EPSG:4326");

    @Inject
    public CharmeExample(ShowcaseExampleStore store) {
        super("Charme",
              "Demonstrates the use of the CHARMe Maps tool "
                + "to insert a new annotation.",
              new String[]{"cancel", "vector", "feature", "drawing", "sketch"},
              store);
    }

    @Override
    public void buildPanel() {
        // create controls
        final HTML htmlInfo = new HTML(
                "<p>This example shows how you can create a new annotation for fine-grained commentary data.</p>");

        // create some MapOptions
        MapOptions defaultMapOptions = new MapOptions();
        defaultMapOptions.setNumZoomLevels(16);

        // Create a MapWidget
        MapWidget mapWidget = new MapWidget("800px", "500px", defaultMapOptions);
        // Create a WMS layer as base layer
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("basic");
        wmsParams.setStyles("");

        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setUntiled();
        wmsLayerParams.setTransitionEffect(TransitionEffect.RESIZE);
        String wmsUrl = "http://vmap0.tiles.osgeo.org/wms/vmap0";
        WMS wmsLayer = new WMS("Basic WMS", wmsUrl, wmsParams, wmsLayerParams);

        // Add the WMS to the map
        final Map map = mapWidget.getMap();
        map.addLayer(wmsLayer);

        // Lets add some default controls to the map
        map.addControl(new LayerSwitcher()); // + sign in the upperright corner to display the layer switcher
        map.addControl(new OverviewMap()); // + sign in the lowerright to display the overviewmap
        map.addControl(new ScaleLine()); // Display the scaleline
        // Center and zoom to a location
        map.setCenter(new LonLat(0, 0), 5);

        // Get coordinated from user click and display new annotation pop up
        map.addMapClickListener(new MapClickListener() {

			public void onClick(MapClickListener.MapClickEvent mapClickEvent) {
				openNewAnnotationPopup(map, mapClickEvent);
        	}
        });


        //add things to main panel
        contentPanel.add(htmlInfo);
        contentPanel.add(mapWidget);
        initWidget(contentPanel);
        mapWidget.getElement().getFirstChildElement().getStyle().setZIndex(0); // force the map to fall behind popups

    }

    
	/**
	 * Open new annotation popup for the user to insert a commentary about the subset they've selected.
	 *
	 * @param map the map object
	 * @param mapClickEvent the map click event
	 */
	private void openNewAnnotationPopup(final Map map, MapClickListener.MapClickEvent mapClickEvent) {

	    LonLat lonLat = mapClickEvent.getLonLat();
	    lonLat.transform(map.getProjection(), DEFAULT_PROJECTION.getProjectionCode()); //transform lonlat to more readable format
	    
        // Create CHARMe pop up
	    DialogBoxWithCloseButton dialogBox = new DialogBoxWithCloseButton(false);
		dialogBox.setText("New Annotation");

		//Call the view implementation and make the presenter start the logic of the example.
	    final NewAnnotationView newAnnotationPopupView = new NewAnnotationViewImpl();
		NewAnnotationPresenter nap = new NewAnnotationPresenterImpl(newAnnotationPopupView, lonLat);
		nap.go(dialogBox);
	}
	
	/* Needed by the showcase developed by GWT-OpenLayers developers. */
    @Override
    public String getSourceCodeURL() {
        return GWT.getModuleBaseURL() + "examples/charme/"
                + "CharmeExample.txt";
    }
}


