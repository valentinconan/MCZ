package com.example.maps;

import java.util.List;

public class Quizz {
	  private List<CluePoint> points; 
	  private String Reponse;
	public Quizz() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<CluePoint> getPoints() {
		return points;
	}
	public void setPoints(List<CluePoint> points) {
		this.points = points;
	}
	public String getReponse() {
		return Reponse;
	}
	public void setReponse(String reponse) {
		Reponse = reponse;
	} 

}
