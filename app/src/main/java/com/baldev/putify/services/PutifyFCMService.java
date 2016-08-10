package com.baldev.putify.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PutifyFCMService extends FirebaseMessagingService {
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		Log.d("FCM", remoteMessage.getNotification().getTitle());

		Log.d("FCM", remoteMessage.getNotification().getBody());
	}

}
