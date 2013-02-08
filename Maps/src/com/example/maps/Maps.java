package com.example.maps;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Maps<MapFragment> extends MapActivity {

	private MapView m_mapView;
	private MapController mController;
	private OverlayClues clues;
	private List<Overlay> map_overlays;
	private OverlayGamer gamer;
	private GeoSensor sensor;
	private Location location;
	private boolean positionSet=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		Global.addRunningActivity(this);

		m_mapView = (MapView) findViewById(R.id.map_view);
		m_mapView.setBuiltInZoomControls(true);

		mController = m_mapView.getController();
		map_overlays = m_mapView.getOverlays();
		clues = new OverlayClues(this, getResources().getDrawable(
				R.drawable.blue_dot));
		map_overlays.add(clues);
		readConfFile();
		sensor = new GeoSensor(this);


	}

	private void addPoint2Map() {

		gamer = new OverlayGamer(this, getResources().getDrawable(
				R.drawable.user));
		gamer.addOverlay(new OverlayItem(new GeoPoint((int) ( location.getLatitude()* 1E6),
				(int) ( location.getLongitude()* 1E6)), "I am a Gamer", "Gamer's name"));
		map_overlays.add(gamer);
		
		int minLat = Integer.MAX_VALUE;
		int maxLat = Integer.MIN_VALUE;
		int minLon = Integer.MAX_VALUE;
		int maxLon = Integer.MIN_VALUE;

		maxLat = Math.max((int) ( location.getLatitude()* 1E6), maxLat);
		minLat = Math.min((int) ( location.getLatitude()* 1E6), minLat);
		maxLon = Math.max((int) ( location.getLongitude()* 1E6), maxLon);
		minLon = Math.min((int) ( location.getLongitude()* 1E6), minLon);
		
		for (CluePoint point : Global.QUIZZ.getPoints()) {
			OverlayItem item = new OverlayItem(Utils.LocationToGeo(point
					.getLocation()), point.getClue(), "Item 1 description");
			clues.addOverlay(item);
			int lat = (int) (point.getLocation().getLatitude() * 1E6);
			int lon = (int) (point.getLocation().getLongitude() * 1E6);

			maxLat = Math.max(lat, maxLat);
			minLat = Math.min(lat, minLat);
			maxLon = Math.max(lon, maxLon);
			minLon = Math.min(lon, minLon);
		}
		
		double fitFactor = 1.5;
		mController.zoomToSpan((int) (Math.abs(maxLat - minLat) * fitFactor),
				(int) (Math.abs(maxLon - minLon) * fitFactor));
		mController.animateTo(new GeoPoint((maxLat + minLat) / 2,
				(maxLon + minLon) / 2));
		
	}

	public void changeUserPosition(Location location) {
		this.location=location;
		m_mapView.invalidate();// refresh the map

		if(!positionSet){
			addPoint2Map();
			positionSet=true;
		}

		gamer.addOverlay(new OverlayItem(new GeoPoint((int) (location
				.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6)),
				"I am a Gamer", "Gamer's name"));
		if (gamer.getItem(0) != null)
			gamer.removeItem(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_maps, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private void readConfFile() {

		// sax stuff
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			DataHandler dataHandler = new DataHandler();
			xr.setContentHandler(dataHandler);
			xr.parse(new InputSource(getAssets().open("points.xml")));
			Global.QUIZZ = dataHandler.getQuizz();
			// data = dataHandler.getData();

		} catch (ParserConfigurationException pce) {
			Log.e("SAX XML", "sax parse error", pce);
		} catch (SAXException se) {
			Log.e("SAX XML", "sax error", se);
		} catch (IOException ioe) {
			Log.e("SAX XML", "sax parse io error", ioe);
		}

	}

	public void goToAnswer(View v) {
		Intent intent = new Intent(v.getContext(), Answer.class);
		startActivity(intent);
	}

	@Override
	protected void onDestroy() {
		Global.removeRunningActivity(this);
		super.onDestroy();
		sensor.onStop();
	}

	public void callPopUp(String message) {

		PopupInformation newFragment = new PopupInformation();
		newFragment.setMessage(message);
		newFragment.show(getFragmentManager(), "InformationPopup");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sensor.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sensor.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		sensor.onStop();
	}
	
	@Override
	public void onBackPressed() {
		callPopupConfirmExit();
	}
	
	private void callPopupConfirmExit(){
		PopupConfirmExit pCE = new PopupConfirmExit(getString(R.string.message_confirm_exit), getString(R.string.title_confirm_exit), this);
		pCE.show(getFragmentManager(),"ConfirmExitPopup");
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item.getItemId() == R.id.menu_exit){
			callPopupConfirmExit();
		}
		return true;
	}
}
