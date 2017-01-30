package com.baldev.putify.splash;

import com.baldev.putify.helpers.MessagesManager.TokenCallback;

public interface SplashMVP {

	interface Model {

		void checkFirebaseToken(TokenCallback callback);
	}

	interface View {
		void goToMessagesActivity();
	}

	interface Presenter extends TokenCallback {
		void checkFirebaseToken();
	}

}
