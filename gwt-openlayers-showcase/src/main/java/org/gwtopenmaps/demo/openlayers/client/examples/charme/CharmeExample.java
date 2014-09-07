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

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.gwtopenmaps.demo.openlayers.client.DialogBoxWithCloseButton;
import org.gwtopenmaps.demo.openlayers.client.basic.AbstractExample;
import org.gwtopenmaps.demo.openlayers.client.components.store.ShowcaseExampleStore;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.jsonld.JSONLDAnnotation;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.model.Annotation;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenter;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenter.NewAnnotationPresenterListener;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.presenter.NewAnnotationPresenterImpl;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.view.NewAnnotationView;
import org.gwtopenmaps.demo.openlayers.client.examples.charme.view.NewAnnotationViewImpl;
import org.gwtopenmaps.openlayers.client.Icon;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Marker;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.Size;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.OverviewMap;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.event.EventType;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;
import org.gwtopenmaps.openlayers.client.event.MarkerBrowserEventListener;
import org.gwtopenmaps.openlayers.client.layer.Markers;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;
import org.gwtopenmaps.openlayers.client.popup.FramedCloud;
import org.gwtopenmaps.openlayers.client.popup.Popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;


/**
 *
 * @author Raquel Alegre
 *
 */

public class CharmeExample extends AbstractExample implements NewAnnotationPresenterListener {

    private Popup popup;
    
    private List<Annotation> annotations = new LinkedList<Annotation>();
    
    private static final Projection DEFAULT_PROJECTION = new Projection(
            "EPSG:4326");

	private Map map;

    @Inject
    public CharmeExample(ShowcaseExampleStore store) {
        super("Charme",
              "Demonstrates the use of the CHARMe Maps tool "
                + "to insert a new annotation and visualize existing ones for the displayed dataset and boundaries.",
              new String[]{"cancel", "vector", "feature", "drawing", "sketch"},
              store);
    }

    @Override
    public void buildPanel() {
        // create controls
        final HTML htmlInfo = new HTML(
                "<p>This example shows how you can create a new annotation for fine-grained commentary data and visualizes the ones existing for this dataset subset.</p>");

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

        map = mapWidget.getMap();
        map.addLayer(wmsLayer);

        // Lets add some default controls to the map
        map.addControl(new LayerSwitcher()); // + sign in the upperright corner to display the layer switcher
        map.addControl(new OverviewMap()); // + sign in the lowerright to display the overviewmap
        map.addControl(new ScaleLine()); // Display the scaleline
        // Center and zoom to a location
        map.setCenter(new LonLat(0, 0), 2);

        // Get coordinated from user click and display new annotation pop up
        map.addMapClickListener(new MapClickListener() {

			public void onClick(MapClickListener.MapClickEvent mapClickEvent) {
				openNewAnnotationPopup(map, mapClickEvent);
        	}
        });
        
        addAnnotationsMarkersToUI();

        //add things to main panel
        contentPanel.add(htmlInfo);
        contentPanel.add(mapWidget);
        initWidget(contentPanel);
        mapWidget.getElement().getFirstChildElement().getStyle().setZIndex(0); // force the map to fall behind pop ups

    }

    private void addAnnotationsMarkersToUI() {
    	for (Annotation annotation : annotations) {
    		addAnnotationMarkerToUI(annotation);
    	}
    }
    
	private void addAnnotationMarkerToUI(final Annotation annotation) {
		//Draw interactive annotation markers in the map    
        LonLat p = annotation.getSubsetSelector().getLonLatFromGeometry();
        p.transform(DEFAULT_PROJECTION.getProjectionCode(), map.getProjection());
        
        Markers layer = new Markers("markers");
        map.addLayer(layer);
 
        String iconImageURL = "http://icongal.com/gallery/image/460109/chartreuse_base_con_pixe_marker_map_outside_biswajit.png";
        Icon icon = new Icon(iconImageURL, new Size(32, 32));
        final Marker marker = new Marker(p, icon);
        layer.addMarker(marker);
 
        marker.addBrowserEventListener(EventType.MOUSE_OVER, new MarkerBrowserEventListener() {
 
            public void onBrowserEvent(MarkerBrowserEventListener.MarkerBrowserEvent markerBrowserEvent) {
                popup = new FramedCloud("id1", marker.getLonLat(), null, annotation.getContent(), null, false);
                popup.setPanMapIfOutOfView(true); //this set the popup in a strategic way, and pans the map if needed.
                popup.setAutoSize(true);
                map.addPopup(popup);
            }
 
        });
 
        marker.addBrowserEventListener(EventType.MOUSE_OUT, new MarkerBrowserEventListener() {
 
            public void onBrowserEvent(MarkerBrowserEventListener.MarkerBrowserEvent markerBrowserEvent) {
                if(popup != null) {
                    map.removePopup(popup);
                    popup.destroy();
                }
            }
 
        });
	}

	private void openNewAnnotationPopup(final Map map, MapClickListener.MapClickEvent mapClickEvent) {
	    LonLat lonLat = mapClickEvent.getLonLat();
	    lonLat.transform(map.getProjection(), DEFAULT_PROJECTION.getProjectionCode()); //transform lonlat to more readable format
	    
        // Create CHARMe pop up
	    DialogBoxWithCloseButton dialogBox = new DialogBoxWithCloseButton(false);
		dialogBox.setText("New Annotation");
	    
	    final NewAnnotationView newAnnotationPopupView = new NewAnnotationViewImpl();
		NewAnnotationPresenter nap = new NewAnnotationPresenterImpl(newAnnotationPopupView, lonLat);
		nap.setListener(this);
		nap.go(dialogBox);
		
	}
	
    @Override
    public String getSourceCodeURL() {
        return GWT.getModuleBaseURL() + "examples/charme/"
                + "CharmeExample.txt";
    }

	@Override
	public void onNewAnnotationCrated(Annotation annotation) {
		JSONLDAnnotation jsonAnnotation = annotation.toJson();
		System.out.println("Annotation created: " + jsonAnnotation);
		System.out.println("Body retrieved from json annotation: " + jsonAnnotation.getBodyStr());
		System.out.println("GEometry retrieved from json annotation: " + jsonAnnotation.getSubsetSelector().getHasGeometryStr());

		this.annotations.add(annotation);
		addAnnotationsMarkersToUI();
	}
}


