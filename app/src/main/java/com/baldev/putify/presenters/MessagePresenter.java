package com.baldev.putify.presenters;

import android.content.Context;
import android.text.Editable;

import com.baldev.putify.helpers.FirebaseHelper;
import com.baldev.putify.helpers.FirebaseHelperImplementation;

public interface MessagePresenter {

	FirebaseHelper firebaseHelper = FirebaseHelperImplementation.getInstance();

	void sendMessage(Context context, Editable text);
}
