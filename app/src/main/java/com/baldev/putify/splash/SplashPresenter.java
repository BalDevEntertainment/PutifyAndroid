package com.baldev.putify.splash;

import com.baldev.putify.splash.SplashMVP.Model;
import com.baldev.putify.splash.SplashMVP.View;

public class SplashPresenter implements SplashMVP.Presenter {

	protected View view;

	protected Model model;

	public SplashPresenter(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void checkFirebaseToken() {
		this.model.checkFirebaseToken(this);
	}

	@Override
	public void onTokenRetrieved(String string) {
		checkIfUserExists();
		view.goToMessagesActivity();
	}

	private void checkIfUserExists() {

	}

	@Override
	public void onError() {
		// TODO: 29/01/2017 do something
	}
}
