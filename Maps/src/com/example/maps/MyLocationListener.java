package com.example.maps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MyLocationListener implements LocationListener {

	public Maps act = null;

	@Override
	public void onLocationChanged(Location location) {
		EditText text = (EditText) act.findViewById(R.id.cluesText);
		text.setText("");
		// todo move gamer marker
		for (CluePoint point : Global.QUIZZ.getPoints()) {
			if (point.getStatus().equals(CluePointStatus.OPEN)) {
				text.append("\n"+point.getClue());
			} else {
				float distance = location.distanceTo(point.getLocation());
				Log.e("distance", String.valueOf(distance));
				if (distance <= Global.METER_GAMER_TO_CLUE) {
					// todo display clue
					point.setStatus(CluePointStatus.OPEN);
					text.append("\n"+point.getClue());
				}
			}
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
