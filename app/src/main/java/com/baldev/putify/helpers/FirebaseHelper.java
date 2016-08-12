package com.baldev.putify.helpers;


public interface FirebaseHelper {
	void registerCurrentFCMToken();

	void registerFCMToken(String token);

	void getRandomToken(FirebaseTokenCallback callback);

	void sendMessage(String to, String message);

	void registerListenerForMessages(FirebaseMessageListener listener);

	interface FirebaseTokenCallback {
		void onTokenRetrieved(String string);

		void onError();
	}

	interface FirebaseMessageListener {
		void onNewMessage(String message);
	}

}
