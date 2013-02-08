package com.example.maps;

import android.location.Location;

public class CluePoint {
	private Location location;
	private String clue;
	private CluePointStatus status=CluePointStatus.CLOSE;
	
	public CluePoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CluePoint(Location location, String clue) {
		super();
		this.location = location;
		this.clue = clue;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getClue() {
		return clue;
	}
	public void setClue(String clue) {
		this.clue = clue;
	}
	public CluePointStatus getStatus() {
		return status;
	}
	public void setStatus(CluePointStatus status) {
		this.status = status;
	}
	
}
