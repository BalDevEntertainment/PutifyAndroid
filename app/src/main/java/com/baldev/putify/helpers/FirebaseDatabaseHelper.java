package com.baldev.putify.helpers;


import com.baldev.putify.model.Message;
import com.baldev.putify.model.User;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public interface FirebaseDatabaseHelper {
	void registerFCMToken();

	void notifyTokenRegistration();

	String getMyToken();

	void getRandomToken(FirebaseTokenCallback callback);

	void saveMessage(Message message);

	void registerListenerForMessages(FirebaseMessageListener listener);

	void askForToken(FirebaseTokenCallback callback);

	void invalidateToken(String token);

	User createNewUser(User username);

	void getMyself(UserCallback callback);

	interface FirebaseTokenCallback {
		void onTokenRetrieved(String string);

		void onError(String errorMsg);
	}

	interface UserCallback{
		void onUserRetrieved(User user);
	}

	interface FirebaseMessageListener {
		void onNewMessage(String to, String messageBody, long messageTimestamp);

	}

}
