package com.example.maps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public abstract class PopupConfirm extends DialogFragment {
	protected String message = "null";
	protected String title = "null";
	//protected AlertDialog.Builder builder;
	
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public PopupConfirm(String message, String title) {
		super();
		this.message = message;
		this.title = title;
		//this.builder = new AlertDialog.Builder(getActivity());
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message).setTitle(title)
               .setPositiveButton(R.string.btn_popup_yes, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   executeYesAction();
                   }
               })
               .setNegativeButton(R.string.btn_popup_no, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   executeNoAction();
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
	
	protected abstract void executeYesAction();
	protected abstract void executeNoAction();
}
