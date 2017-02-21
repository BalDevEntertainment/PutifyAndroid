package com.baldev.putify.createuser;

public interface CreateUserMVP {

	interface View {
		void setPresenter(Presenter createUserPresenter);

		void disableAcceptButton();

		void enableAcceptButton();

		void startMainActivity();
	}

	interface Presenter {
		void onAcceptButtonClicked(String text);
	}

}
