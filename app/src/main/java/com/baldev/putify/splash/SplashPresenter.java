package com.baldev.putify.splash;

import com.baldev.putify.data.UsersManager;
import com.baldev.putify.data.UsersManager.UsersManagerInitializationListener;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.model.User;
import com.baldev.putify.splash.SplashMVP.Presenter;
import com.baldev.putify.splash.SplashMVP.View;

import javax.inject.Inject;

public class SplashPresenter implements Presenter, UsersManagerInitializationListener {

	protected View view;
	protected MessagesManager messagesManager;
	protected UsersManager usersManager;

	@Inject
	SplashPresenter(View view, MessagesManager messagesManager, UsersManager usersManager) {
		this.view = view;
		this.messagesManager = messagesManager;
		this.usersManager = usersManager;
	}

	@Inject
	void setupPresenter() {
		this.view.setPresenter(this);
		this.usersManager.initialize(this);
	}

	@Override
	public void onInitializationCompleted() {
		this.getMyself();
	}

	private void getMyself() {
		if (!this.messagesManager.hasTokenBeenRetrieved()) {
			this.messagesManager.askForToken(this);
		}

		if(usersManager.getMyself() == null){
			this.view.showToast("User doesn't exists.");
		}

	}


	@Override
	public void onTokenRetrieved(String string) {
		//this.view.goToMessagesActivity();
	}

	@Override
	public void onError() {
	}
}
