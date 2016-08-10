package com.baldev.putify.helpers;


public interface FirebaseHelper {
	void registerCurrentFCMToken();

	void registerFCMToken(String token);

	void getRandomToken(TokenCallback callback);

	interface TokenCallback {
		void onTokenRetrieved(String string);

		void onError();
	}
}
