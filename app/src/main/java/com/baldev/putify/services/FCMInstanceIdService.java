package com.baldev.putify.services;

import android.util.Log;

import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMInstanceIdService extends FirebaseInstanceIdService {

	@Override
	public void onTokenRefresh() {
		// Get updated InstanceID token.
		String refreshedToken = FirebaseInstanceId.getInstance().getToken();
		Log.i("FCMTOKEN", refreshedToken != null ? refreshedToken : "Missing");

		//TODO improve this is only temporal
		FirebaseMessagesManager.registerToken();


	}
}
