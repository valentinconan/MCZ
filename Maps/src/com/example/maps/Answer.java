package com.example.maps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * @author Valentin
 *
 */
/**
 * @author Valentin
 *
 */
public class Answer extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);//TODO VALENTIN VERIFIER CAR savedInstance EST NULL ET CE N EST PAS NORMAL
		setContentView(R.layout.activity_answer);
		Global.addRunningActivity(this);
		refreshClueList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_answer, menu);
		return true;
	}
	
	public void refreshClueList(){
		EditText text = (EditText) findViewById(R.id.cluesText);
		text.setText("Use thoses clues to find the magic word !\n\n");
//		for (int i = 0; i < 100; i++) { //TODO enlever test
//		text.append("\n"+"test "+i);
//		}
		int i=0;
		if(Global.QUIZZ.getPoints().size()>0){
			for (CluePoint point : Global.QUIZZ.getPoints()) {
				if (point.getStatus().equals(CluePointStatus.OPEN)) {
					i++;
					text.append("\nClue "+i+": "+point.getClue());
				}
			}
		}
	}
	
	public void tryAgain(View v){
		EditText field = (EditText)findViewById(R.id.userAnswer);
		
		if( (Global.QUIZZ.getReponse()).equals(field.getText().toString()) ){
			callPopUp(this.getString(R.string.great_response));
			
		}
		else 
			callPopUp(this.getString(R.string.wrong_response));
		field.setText("");
		
	}
	
	public void callPopUp(String message) {
		
		PopupInformation newFragment = new PopupInformation();
	    newFragment.setMessage(message);
	    newFragment.show(getFragmentManager(),"InformationPopup");
	    //newFragment.
	}
	
	public void goBack(View v){
		finish();
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

	@Override
	protected void onDestroy() {
		Global.removeRunningActivity(this);
		super.onDestroy();
	}
	
	
	
}
