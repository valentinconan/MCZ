package com.example.maps;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.google.android.maps.OverlayItem;

public class Global {
	public static Quizz QUIZZ;
	public static OverlayItem GAMER;
	public static int METER_GAMER_TO_CLUE=200;
	
	public static void goToWelcomeActivity(Activity activityCalling){
		Intent intent = new Intent(activityCalling.getApplicationContext(),Welcome.class);
		activityCalling.startActivity(intent);
		activityCalling.finish();
	}
}
