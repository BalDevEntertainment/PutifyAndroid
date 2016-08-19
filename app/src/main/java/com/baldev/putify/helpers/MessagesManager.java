package com.baldev.putify.helpers;

//Facade for messages

public interface MessagesManager {

	void askForToken(TokenCallback callback);

	void sendMessage(String to, String message);

	void getRandomToken(TokenCallback callback);

	boolean hasTokenBeenRetrieved();

	interface NewMessageListener {
		void onNewMessage(String message);
	}

	interface TokenCallback {
		void onTokenRetrieved(String string);

		void onError();
	}
}
