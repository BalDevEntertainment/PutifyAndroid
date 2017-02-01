package com.baldev.putify.splash;

import com.baldev.putify.helpers.MessagesManager.TokenCallback;

public interface SplashMVP {

	interface View {
		void goToMessagesActivity();

		void setPresenter(Presenter splashPresenter);
	}

	interface Presenter extends TokenCallback {
		void checkFirebaseToken();
	}

}
