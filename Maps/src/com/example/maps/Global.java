package com.example.maps;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.google.android.maps.OverlayItem;

public class Global {
	public static Quizz QUIZZ;
	public static OverlayItem GAMER;
	public static int METER_GAMER_TO_CLUE=200;
	private static List<Activity> runningActivities = new ArrayList<Activity>();
		
	public static void addRunningActivity(Activity activity){
		runningActivities.add(activity);
	}
	
	public static void removeRunningActivity(Activity activity){
		runningActivities.remove(activity);
	}
	
	public static void finishAllRunningActivities(){
		for(Activity a : runningActivities){
			a.finish();
		}
		runningActivities = new ArrayList<Activity>();
	}
	
	public static void finishAllRunningActivitiesExceptOne(Activity activity){
		for(Activity a : runningActivities){
			if(!a.equals(activity)){
				a.finish();
			}
		}
	}
	
	public static void goToWelcomeActivity(Activity activityCalling){
		Intent intent = new Intent(activityCalling.getApplicationContext(),Welcome.class);
		activityCalling.startActivity(intent);
		activityCalling.finish();
	}
}
