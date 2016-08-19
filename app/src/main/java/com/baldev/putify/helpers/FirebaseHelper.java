package com.baldev.putify.helpers;


public interface FirebaseHelper {
	void registerFCMToken();

	void notifyTokenRegistration();

	String getMyToken();

	void getRandomToken(FirebaseTokenCallback callback);

	void sendMessage(String to, String message);

	void registerListenerForMessages(FirebaseMessageListener listener);

	void askForToken(FirebaseTokenCallback callback);

	interface FirebaseTokenCallback {
		void onTokenRetrieved(String string);

		void onError();
	}

	interface FirebaseMessageListener {
		void onNewMessage(String message);
	}

}
