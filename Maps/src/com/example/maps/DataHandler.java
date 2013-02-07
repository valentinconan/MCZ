package com.example.maps;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public class DataHandler extends DefaultHandler { 
	  
	 
	  // this holds the data 
	  private Quizz quizz; 
	 
	 
	  /** 
	   * This gets called  when the xml document is first opened 
	   * 
	   * @throws SAXException 
	   */ 
	  @Override 
	  public void startDocument() throws SAXException { 
		  quizz=new Quizz();
	    quizz.setPoints(new ArrayList<CluePoint>()); 
	  } 
	 
	  /** 
	   * Called when it's finished handling the document 
	   * 
	   * @throws SAXException 
	   */ 
	  @Override 
	  public void endDocument() throws SAXException { 
	 
	  } 
	 
	  /** 
	   * This gets called at the start of an element. Here we're also setting the booleans to true if it's at that specific tag. (so we 
	   * know where we are) 
	   * 
	   * @param namespaceURI 
	   * @param localName 
	   * @param qName 
	   * @param atts 
	   * @throws SAXException 
	   */ 
	  @Override 
	  public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException { 
	 
	    if(localName.equals("point")) { 
	    	CluePoint point = new CluePoint();
			point.setClue( atts.getValue("clue"));
			Location location=new Location("");
			location.setLatitude(Double.valueOf(atts.getValue("latitude")));
			location.setLongitude(Double.valueOf(atts.getValue("longitude")));
			point.setLocation(location);
			quizz.getPoints().add(point);
		}
		if (localName.equals("points")) {
			quizz.setReponse(atts.getValue("reponse"));
		}
	  } 
	 
	  /** 
	   * Called at the end of the element. Setting the booleans to false, so we know that we've just left that tag. 
	   * 
	   * @param namespaceURI 
	   * @param localName 
	   * @param qName 
	   * @throws SAXException 
	   */ 
	  @Override 
	  public void endElement(String namespaceURI, String localName, String qName) throws SAXException { 
	   
	  } 
	 
	  /** 
	   * Calling when we're within an element. Here we're checking to see if there is any content in the tags that we're interested in 
	   * and populating it in the Config object. 
	   * 
	   * @param ch 
	   * @param start 
	   * @param length 
	   */ 
	  @Override 
	  public void characters(char ch[], int start, int length) { 
	    String chars = new String(ch, start, length); 
	    chars = chars.trim(); 
	 
	  }

	public Quizz getQuizz() {
		return quizz;
	}

	  
	} 
