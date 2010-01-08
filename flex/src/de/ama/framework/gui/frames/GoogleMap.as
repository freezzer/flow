package de.ama.framework.gui.frames {
import com.google.maps.InfoWindowOptions;
import com.google.maps.LatLng;
import com.google.maps.Map;
import com.google.maps.MapEvent;
import com.google.maps.MapMouseEvent;
import com.google.maps.controls.MapTypeControl;
import com.google.maps.controls.ZoomControl;
import com.google.maps.overlays.Marker;
import com.google.maps.overlays.MarkerOptions;
import com.google.maps.services.ClientGeocoder;
import com.google.maps.services.GeocodingEvent;

import de.ama.framework.gui.fields.EditField;
import de.ama.framework.util.*;

import flash.events.Event;

import mx.utils.StringUtil;


public class GoogleMap extends EditPanel {

    private var _silent:Boolean = true;
    private var _adjusting:Boolean = false;

    private var _countryField:EditField;
    private var _cityField:EditField;
    private var _streetField:EditField;
    private var _zipField:EditField;
    private var _map:Map;


    public function GoogleMap(x :int, y:int, w:int, h:int) {
        super.x = x;
        super.y = y;
        super.height = h;
        super.width = w;

        _map = new Map();
        _map.key = "ABQIAAAAK7AsHxY1HCEEhqzGfPOq4RQNBUbHWORdu9BffY4TAGVewGMWjBQEc7BLYXOpOkze1XGxFxU9NFB8aA";
        _map.addEventListener(MapEvent.MAP_READY, onMapReady);
        _map.x = 5;
        _map.y = 5;
        _map.width = w-10;
        _map.height = h-10;
        _map.language = "de";
        addChild(_map);
        
        callLater(doGeocode,null);
    }


    public function set countryField(f:EditField):void {
        if (f == null) return;
        f.enterCallback = new Callback(this, readEditFields);
        _countryField = f;
    }

    public function set cityField(f:EditField):void {
        if (f == null) return;
        f.enterCallback = new Callback(this, readEditFields);
        _cityField = f;
    }

    public function set streetField(f:EditField):void {
        if (f == null) return;
        f.enterCallback = new Callback(this, readEditFields);
        _streetField = f;
    }

    public function set zipField(f:EditField):void {
        if (f == null) return;
        f.enterCallback = new Callback(this, readEditFields);
        _zipField = f;

    }

    public function readEditFields(f:EditField):void {

        if (f == _countryField) {
            if (_zipField != null) _zipField.setInputText("");
            if (_streetField != null) _streetField.setInputText("");
            if (_cityField != null) _cityField.setInputText("");
        }

        if (f == _zipField) {
            if (_cityField != null) _cityField.setInputText("");
            if (_streetField != null) _streetField.setInputText("");
        }

        if (f == _cityField) {
            if (_zipField != null) _zipField.setInputText("");
            if (_streetField != null) _streetField.setInputText("");
        }

        doGeocode(null);
    }


    private function onMapReady(event:Event):void {
        _map.enableScrollWheelZoom();
        _map.enableContinuousZoom();
        _map.addControl(new ZoomControl());
        _map.addControl(new MapTypeControl());
        _map.addEventListener(MapMouseEvent.CLICK, onMapClick);

        doGeocode(null);
    }

    private function onMapClick(event:MapMouseEvent):void {
        doGeocode(event.latLng);
    }

    private function interpretCity(str:String):void {
        if (Util.isEmpty(str)) return;
        var split:Array = StringUtil.trim(str).split(" ");
        if (split.length == 2) {
            _zipField.setInputText(split[0]);
            _cityField.setInputText(split[1]);
        } else {
            _cityField.setInputText(str);
        }
    }

    private function doGeocode(latlng:LatLng = null):void {
        if (_adjusting) return;

        _adjusting = true;


        try {
            var geocoder:ClientGeocoder = new ClientGeocoder();
            geocoder.addEventListener(
                    GeocodingEvent.GEOCODING_SUCCESS,
                    function(event:GeocodingEvent):void {
                        var placemarks:Array = event.response.placemarks;
                        if (placemarks.length > 0) {
                            _map.clearOverlays();
                            _map.setCenter(placemarks[0].point);
                            var marker:Marker = new Marker(placemarks[0].point, new MarkerOptions({draggable: true}));

                            if (placemarks[0].address != null) {
                                var address:String = placemarks[0].address;
                                var arr:Array = address.split(",");

                                if (arr.length == 1) {
                                    _countryField.setInputText(arr[0]);
                                }

                                if (arr.length == 2) {
                                    interpretCity(arr[0]);
                                    _countryField.setInputText(arr[1]);
                                }

                                if (arr.length == 3) {
                                    _streetField.setInputText(arr[0]);
                                    interpretCity(arr[1]);
                                    _countryField.setInputText(arr[2]);
                                }
                            }

                            marker.addEventListener(MapMouseEvent.CLICK, function (event:MapMouseEvent):void {
                                marker.openInfoWindow(new InfoWindowOptions({content: placemarks[0].address}));
                            });

                            marker.addEventListener(MapMouseEvent.DRAG_END, function(event:Event):void {
                                geocoder.reverseGeocode(marker.getLatLng());
                            });

                            _map.addOverlay(marker);
                        }
                    }
                    );

            geocoder.addEventListener(
                    GeocodingEvent.GEOCODING_FAILURE,
                    function(event:GeocodingEvent):void {
                        if (!_silent) {
                            Util.showMessage("Dieser Ort ist nicht verzeichnet");
                        }
                    }
                    );


            if (latlng != null) {

                geocoder.reverseGeocode(latlng);

            } else {
                var zoom:Number = 5;
                var addr:String = "Deutschland";
                if (_countryField != null && !_countryField.isEmpty()) {
                    addr = _countryField.getInputText();
                }
                if (_cityField != null && !_cityField.isEmpty()) {
                    addr += "," + _cityField.getInputText();
                    zoom = 11;
                }
                if (_zipField != null && !_zipField.isEmpty()) {
                    addr += "," + _zipField.getInputText();
                    zoom = 15;
                }
                if (_streetField != null && !_streetField.isEmpty()) {
                    addr += "," + _streetField.getInputText();
                    zoom = 17;
                }

                _map.setZoom(zoom);

                geocoder.geocode(addr);
            }
        } finally {
            _adjusting = false;
        }

    }

}
}



