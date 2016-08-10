package com.baldev.putify.presenters;

import android.content.Context;
import android.text.Editable;
import android.util.Log;

import com.baldev.putify.helpers.FirebaseHelper.TokenCallback;
import com.baldev.putify.helpers.VolleyHelper;
import com.baldev.putify.helpers.VolleyHelperImplementation;

public class SendMessagePresenter implements MessagePresenter {

	VolleyHelper volleyHelper = VolleyHelperImplementation.getInstance();

	@Override
	public void sendMessage(final Context context, Editable editable) {
		if (editable != null) {
			final String messageText = editable.toString();
			firebaseHelper.getRandomToken(new TokenCallback() {
				@Override
				public void onTokenRetrieved(String token) {
					volleyHelper.sendPushNotification(context, token, "Alto title", messageText);
				}

				@Override
				public void onError() {
					Log.e("Error", "Error");
				}
			});

		}
	}
}
