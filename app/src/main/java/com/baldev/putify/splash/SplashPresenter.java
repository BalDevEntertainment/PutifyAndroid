package com.baldev.putify.splash;

import com.baldev.putify.data.LocalRepository;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.splash.SplashMVP.View;

import javax.inject.Inject;

public class SplashPresenter implements SplashMVP.Presenter {

	protected View view;
	protected LocalRepository localRepository;

	@Inject
	SplashPresenter(View view, LocalRepository localRepository) {
		this.view = view;
		this.localRepository = localRepository;
	}

	@Inject
	void setupPresenter() {
		this.view.setPresenter(this);
	}

	@Override
	public void checkFirebaseToken() {
		/*
		if (!this.messagesManager.hasTokenBeenRetrieved()) {
			this.messagesManager.askForToken(this);
		}
		*/
	}

	@Override
	public void onTokenRetrieved(String string) {
		this.checkIfUserExists();
		this.view.goToMessagesActivity();
	}

	private void checkIfUserExists() {

	}

	@Override
	public void onError() {
		// TODO: 29/01/2017 do something
	}
}
