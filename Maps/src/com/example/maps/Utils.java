package com.example.maps;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public  class Utils {
	public static Location GeoToLocation(GeoPoint gp) {
        Location location = new Location("");
        location.setLatitude(gp.getLatitudeE6()/1E6);
        location.setLongitude(gp.getLongitudeE6()/1E6);
        return location;
    }
	public static GeoPoint LocationToGeo(Location location) {
		GeoPoint point=new GeoPoint((int)(location.getLatitude()*1E6),(int)( location.getLongitude()*1E6));
		return point;
	}
}
