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
package org.gwtopenmaps.demo.openlayers.client.examples.gwtwidget;

import javax.inject.Inject;

import org.gwtopenmaps.demo.openlayers.client.DialogBoxWithCloseButton;
import org.gwtopenmaps.demo.openlayers.client.basic.AbstractExample;
import org.gwtopenmaps.demo.openlayers.client.components.store.ShowcaseExampleStore;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Pixel;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.MousePosition;
import org.gwtopenmaps.openlayers.client.control.MousePositionOptions;
import org.gwtopenmaps.openlayers.client.control.MousePositionOutput;
import org.gwtopenmaps.openlayers.client.control.OverviewMap;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.event.MapMoveListener;
import org.gwtopenmaps.openlayers.client.layer.OSM;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Shows how to add a GwtWidget to the map.
 * @author Frank Wynants
 *
 */
public class GwtWidgetExample extends AbstractExample {

    private static final Projection DEFAULT_PROJECTION = new Projection(
            "EPSG:4326");

    @Inject
    public GwtWidgetExample(ShowcaseExampleStore store) {
        super("GWT Widget Example", "Show how to add a GWT widget to the map at a LatLng",
              new String[]{"widget", "gwt"}, store);
    }

    @Override
    public void buildPanel() {
        //create some MapOptions
        MapOptions defaultMapOptions = new MapOptions();
        defaultMapOptions.setDisplayProjection(new Projection("EPSG:4326")); //causes the mouse popup to display coordinates in this format
        defaultMapOptions.setNumZoomLevels(16);

        //Create a MapWidget and add 2 OSM layers
        MapWidget mapWidget = new MapWidget("800px", "500px", defaultMapOptions);
        OSM osm_1 = OSM.Mapnik("Mapnik");
        OSM osm_2 = OSM.CycleMap("CycleMap");
        osm_1.setIsBaseLayer(true);
        osm_2.setIsBaseLayer(true);
        final Map map = mapWidget.getMap();
        map.addLayer(osm_1);
        map.addLayer(osm_2);

        //Lets add some default controls to the map
        map.addControl(new LayerSwitcher()); //+ sign in the upperright corner to display the layer switcher
        map.addControl(new OverviewMap()); //+ sign in the lowerright to display the overviewmap
        map.addControl(new ScaleLine()); //Display the scaleline

        //Center and zoom to a location
        final LonLat lonLat = new LonLat(6.95, 50.94);
        lonLat.transform(DEFAULT_PROJECTION.getProjectionCode(),
                         map.getProjection()); //transform lonlat to OSM coordinate system
        map.setCenter(lonLat, 12);

        //Prepare elements that will form part of the pop up panel
        
        //Users can insert in the text area their free text comment about the subset
        final TextArea taAnnot = new TextArea();

        //Label containing an explanation about what the pop up is trying to annotate
        final Label lIntro = new Label();
        lIntro.setText("Insert a comment about this dataset subset:");
        
        //Panel containing the intro label and the text area
        final VerticalPanel vpAnnot = new VerticalPanel();
        vpAnnot.add(lIntro);
        vpAnnot.add(taAnnot);
        
        //Users can confirm or cancel the comment they've made about the subset
        final Button okButton = new Button();
        okButton.setText("OK");        
        final Button cancelButton = new Button();
        cancelButton.setText("Cancel");
        
        //A flow panel will contain the OK and Cancel buttons                 
        final FlowPanel fpButtons = new FlowPanel();
        fpButtons.add(okButton);
        fpButtons.add(cancelButton);       
        
        //TODO: Add calendar for time period selection based on dataset's covered period
        //Leave Some text boxes by now...
        final Label lBegin = new Label();
        lBegin.setText("Begin: ");
        final Label lEnd = new Label();
        lEnd.setText("End: ");
        final TextBox tbBegin = new TextBox();        
        final TextBox tbEnd = new TextBox();
        
        //A Horizontal Panel will contain these
        final HorizontalPanel hpPeriod = new HorizontalPanel();
        hpPeriod.add(lBegin);
        hpPeriod.add(tbBegin);
        hpPeriod.add(lEnd);
        hpPeriod.add(tbEnd);
              
        //TODO: Add something for users to insert a set of coordinates or a polygon... Maybe some text area for WKT areas?
        final Label lCoords = new Label();
        lCoords.setText("Introduce a GML description of the geographical subset: ");
        final TextArea taGeometry = new TextArea();
        
        //A Vertical Panel will contain these
        final VerticalPanel vpGeometry = new VerticalPanel();
        vpGeometry.add(lCoords);
        vpGeometry.add(taGeometry);
        
        final VerticalPanel vpPopup = new VerticalPanel();
        vpPopup.setSize("200", "200");
        vpPopup.add(vpAnnot);
        vpPopup.add(vpGeometry);
        vpPopup.add(hpPeriod);
        vpPopup.add(fpButtons);
        
        //Main container dialog box panel        
        final DialogBoxWithCloseButton dialogBox = new DialogBoxWithCloseButton(false);        
        dialogBox.setWidget(vpPopup);
        dialogBox.center();
        dialogBox.setText("New Annotation");
        
        
        final AbsolutePanel panel = new AbsolutePanel(); //use a GWT AbsolutePanel
        panel.setSize("2000px", "2000px"); //give it the same size as the MapWidget
        panel.add(mapWidget, 0, 0); //add the MapWidget to the AbsolutePanel
        Pixel pxLonLat = map.getPixelFromLonLat(lonLat); //calculate px coordinates from lonLat
        panel.add(dialogBox, pxLonLat.x(), pxLonLat.y()); //add TextBox add these px coordinates


        
        map.addMapMoveListener(new MapMoveListener() { //when map moves, recalculate position of TextBox			
			public void onMapMove(MapMoveEvent eventObject) {
				panel.remove(dialogBox);
		        Pixel pxLonLat = map.getPixelFromLonLat(lonLat);
		        panel.add(dialogBox, pxLonLat.x(), pxLonLat.y());
			}
		});
        
        
        
      //Adds the custom mouse position to the map
        MousePositionOutput mpOut = new MousePositionOutput() {
            @Override
            public String format(LonLat lonLat, Map map) {
                String out = "";
                out += "Longitude: ";
                out += lonLat.lon();
                out += ", Latitude: ";
                out += lonLat.lat();
 
                return out;
            }
        };
        
        MousePositionOptions mpOptions = new MousePositionOptions();
        mpOptions.setFormatOutput(mpOut); // rename to setFormatOutput
 
        map.addControl(new MousePosition(mpOptions));
 
        //Lets add some default controls to the map
        map.addControl(new LayerSwitcher()); //+ sign in the upperright corner to display the layer switcher
        map.addControl(new OverviewMap()); //+ sign in the lowerright to display the overviewmap
        map.addControl(new ScaleLine()); //Display the scaleline

        contentPanel.add(
                new HTML(
                "<p>This example shows how to add a GWT widget to the map, at a certain location.</p>" +
                "<p>In this example we add a TextBox at LonLat(6.95, 50.94). The upperleft point of the widget will be placed at this point."));
        contentPanel.add(panel);

        initWidget(contentPanel);

        mapWidget.getElement().getFirstChildElement().getStyle().setZIndex(0); //force the map to fall behind popups
    }

    @Override
    public String getSourceCodeURL() {
        return GWT.getModuleBaseURL() + "examples/gwtwidget/"
                + "GwtWidgetExample.txt";
    }
}
