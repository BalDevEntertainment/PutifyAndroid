package com.baldev.putify.splash;


import com.baldev.putify.data.LocalRepository;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.helpers.MessagesManager.TokenCallback;

public class SplashModel implements SplashMVP.Model {

	protected MessagesManager messagesManager;

	protected LocalRepository localRepository;

	public SplashModel(MessagesManager messagesManager, LocalRepository localRepository) {
		this.messagesManager = messagesManager;
		this.localRepository = localRepository;
	}

	@Override
	public void checkFirebaseToken(TokenCallback callback) {
		if (!messagesManager.hasTokenBeenRetrieved()) {
			messagesManager.askForToken(callback);
		}
	}
}
