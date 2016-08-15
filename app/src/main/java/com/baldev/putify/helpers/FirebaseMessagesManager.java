package com.baldev.putify.helpers;

import android.content.Context;

import com.baldev.putify.helpers.FirebaseHelper.FirebaseMessageListener;
import com.baldev.putify.helpers.FirebaseHelper.FirebaseTokenCallback;

public class FirebaseMessagesManager implements MessagesManager {

	private final FirebaseHelper firebaseHelper = FirebaseHelperImplementation.getInstance();
	private final PushNotificationsManager pushNotificationsManager = VolleyHelperImplementation.getInstance();
	private final Context context;
	private NewMessageListener listener;

	public FirebaseMessagesManager(final Context context, NewMessageListener newMessageListener) {
		this.context = context;
		this.listener = newMessageListener;
		this.firebaseHelper.registerCurrentFCMToken();
		this.firebaseHelper.registerListenerForMessages(new FirebaseMessageListener() {
			@Override
			public void onNewMessage(String message) {
				listener.onNewMessage(message);
			}
		});
	}

	@Override
	public void sendMessage(String to, String message) {
		this.firebaseHelper.sendMessage(to, message);
		this.pushNotificationsManager.sendPushNotification(context, to, "You have a new message!", message);
	}

	@Override
	public void getRandomToken(final TokenCallback callback) {
		this.firebaseHelper.getRandomToken(new FirebaseTokenCallback() {
			@Override
			public void onTokenRetrieved(String string) {
				callback.onTokenRetrieved(string);
			}

			@Override
			public void onError() {
				callback.onError();
			}
		});
	}

	//TODO remove this is temporal
	public static void registerTokenTEMPORAL(){
		FirebaseHelper firebaseHelper = FirebaseHelperImplementation.getInstance();
		firebaseHelper.registerCurrentFCMToken();
	}

}
