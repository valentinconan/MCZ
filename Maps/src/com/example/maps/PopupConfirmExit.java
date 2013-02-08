package com.example.maps;

import android.app.Activity;

public class PopupConfirmExit extends PopupConfirm {

	public PopupConfirmExit(String message, String title, Activity activity) {
		super(message, title);
	}

	@Override
	protected void executeYesAction() {
		Global.goToWelcomeActivity(this.getActivity());
	}

	@Override
	protected void executeNoAction() {
	}

}
