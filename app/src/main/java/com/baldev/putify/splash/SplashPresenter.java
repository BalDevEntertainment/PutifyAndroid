package com.baldev.putify.splash;

import android.util.Log;

import com.baldev.putify.data.UsersManager;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.splash.SplashMVP.Presenter;
import com.baldev.putify.splash.SplashMVP.View;

import javax.inject.Inject;

public class SplashPresenter implements Presenter {

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
		usersManager.instantiateMyself();
		Log.e("TEST", usersManager.getMyself().getUsername());
	}

	private void getMyself() {
		if(usersManager.getMyself() == null){
			this.view.goToCreateUserActivity();
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
