package com.baldev.putify.helpers;


import com.baldev.putify.model.Message;

public interface FirebaseDatabaseHelper {
	void registerFCMToken();

	void notifyTokenRegistration();

	String getMyToken();

	void getRandomToken(FirebaseTokenCallback callback);

	void saveMessage(Message message);

	void registerListenerForMessages(FirebaseMessageListener listener);

	void askForToken(FirebaseTokenCallback callback);

	void invalidateToken(String token);

	interface FirebaseTokenCallback {
		void onTokenRetrieved(String string);

		void onError(String errorMsg);
	}

	interface FirebaseMessageListener {
		void onNewMessage(String to, String messageBody, long messageTimestamp);
	}

}
