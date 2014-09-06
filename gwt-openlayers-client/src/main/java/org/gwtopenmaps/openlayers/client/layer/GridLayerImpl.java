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
package org.gwtopenmaps.openlayers.client.layer;

import org.gwtopenmaps.openlayers.client.util.JSObject;


public class GridLayerImpl
{

    public static native JSObject create(String name, String url,
        JSObject params, JSObject options) /*-{
        return new $wnd.OpenLayers.Layer.Grid(name, url, params,options);
    }-*/;

    public static native JSObject create(String name, String url,
        JSObject params) /*-{
        return new $wnd.OpenLayers.Layer.Grid(name, url, params);
    }-*/;

    public static native void setTitleSize(JSObject self, int tileSize) /*-{
        self.tileSize = tileSize;
    }-*/;

    public static native void setSingleTile(JSObject self, boolean singleTile) /*-{
        self.singleTile = singleTile;
    }-*/;

    public static native void setRatio(JSObject self, float ratio) /*-{
        self.ratio = ratio;
    }-*/;

    public static native void setBuffer(JSObject self, Integer buffer) /*-{
        self.buffer = buffer;
    }-*/;

    public static native void setNumLoadingTiles(JSObject self, Integer numLoadingTiles) /*-{
        self.numLoadingTiles = numLoadingTiles;
    }-*/;
}