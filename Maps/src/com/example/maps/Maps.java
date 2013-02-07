package com.example.maps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
	private OverlayGamer gamer;
	private GeoSensor sensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		m_mapView = (MapView) findViewById(R.id.map_view);
		m_mapView.setBuiltInZoomControls(true);

		mController = m_mapView.getController();
		mController.animateTo(new GeoPoint( 43830298,4359080));
        mController.setZoom(16);
		List<Overlay> map_overlays = m_mapView.getOverlays();
		clues = new OverlayClues(this, getResources().getDrawable(
				R.drawable.blue_dot));
		map_overlays.add(clues);		
		readConfFile();
		addPoint2Map();
		sensor=new GeoSensor(this);
		
		gamer = new OverlayGamer(this, getResources().getDrawable(R.drawable.user));
		map_overlays.add(gamer);
		gamer.addOverlay(new OverlayItem(new GeoPoint((int)(43.830347*1E6),(int)( 4.352753*1E6)), "I am a Gamer", "Gamer's name"));


	}

	private void addPoint2Map() {
		for (CluePoint point : Global.QUIZZ.getPoints()) {
			OverlayItem item = new OverlayItem(Utils.LocationToGeo(point.getLocation()),
					point.getClue(), "Item 1 description");
			clues.addOverlay(item);
		}
	}
	
	public void changeUserPosition(Location location){
		
		if(gamer.getItem(0)!=null)
			gamer.removeItem(0);

		gamer.addOverlay(new OverlayItem(new GeoPoint((int)(location.getLatitude()*1E6),(int)( location.getLongitude()*1E6)), "I am a Gamer", "Gamer's name"));
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
		    Global.QUIZZ=dataHandler.getQuizz();
		    //data = dataHandler.getData(); 
		 
		  } catch(ParserConfigurationException pce) { 
		    Log.e("SAX XML", "sax parse error", pce); 
		  } catch(SAXException se) { 
		    Log.e("SAX XML", "sax error", se); 
		  } catch(IOException ioe) { 
		    Log.e("SAX XML", "sax parse io error", ioe); 
		  } 
		 
	}
	
	public void goToAnswer(View v){
		Intent intent = new Intent(v.getContext(),Answer.class);
		startActivity(intent);		
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sensor.onStop();
	}


	public void callPopUp(String message) {
		
		PopupInformation newFragment = new PopupInformation();
	    newFragment.setMessage(message);
	    newFragment.show(getFragmentManager(),"InformationPopup");
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
	
}
