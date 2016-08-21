package com.baldev.putify.helpers;

import android.content.Context;

import com.baldev.putify.helpers.FirebaseDatabaseHelper.FirebaseMessageListener;
import com.baldev.putify.helpers.FirebaseDatabaseHelper.FirebaseTokenCallback;
import com.baldev.putify.model.Message;

public class FirebaseMessagesManager implements MessagesManager {

	private final FirebaseDatabaseHelper firebaseDatabaseHelper = FirebaseDatabaseHelperImplementation.getInstance();
	private final PushNotificationsManager pushNotificationsManager = VolleyHelperImplementation.getInstance();
	private final Context context;
	private NewMessageListener listener;

	public FirebaseMessagesManager(final Context context) {
		this.context = context;
	}

	public FirebaseMessagesManager(final Context context, NewMessageListener newMessageListener) {
		this.context = context;
		this.listener = newMessageListener;
		this.firebaseDatabaseHelper.registerFCMToken();
		this.firebaseDatabaseHelper.registerListenerForMessages(new FirebaseMessageListener() {
			@Override
			public void onNewMessage(String to, String messageBody, long messageTimestamp) {
				Message message = new Message(to, messageBody, messageTimestamp);
				listener.onNewMessage(message);
			}
		});
	}

	public void askForToken(final TokenCallback callback) {
		this.firebaseDatabaseHelper.askForToken(new FirebaseTokenCallback() {
			@Override
			public void onTokenRetrieved(String string) {
				callback.onTokenRetrieved(string);
			}

			@Override
			public void onError(String errorMsg) {
				callback.onError();
			}
		});
	}

	@Override
	public void sendMessage(Message message) {
		String myToken = this.firebaseDatabaseHelper.getMyToken();
		Message copyForMyself = new Message(myToken, message.getText(), message.getTimestamp());
		this.firebaseDatabaseHelper.saveMessage(copyForMyself);
		this.firebaseDatabaseHelper.saveMessage(message);
		this.pushNotificationsManager.sendPushNotification(context, message.getDestinatary(), "You have been Putified!", message.getText());
	}

	@Override
	public void getRandomToken(final TokenCallback callback) {
		this.firebaseDatabaseHelper.getRandomToken(new FirebaseTokenCallback() {
			@Override
			public void onTokenRetrieved(String string) {
				callback.onTokenRetrieved(string);
			}

			@Override
			public void onError(String errorMsg) {
				callback.onError();
			}
		});
	}

	@Override
	public boolean hasTokenBeenRetrieved() {
		String myToken = this.firebaseDatabaseHelper.getMyToken();
		return myToken != null && !myToken.equals("");
	}

	public static void registerToken() {
		FirebaseDatabaseHelper firebaseDatabaseHelper = FirebaseDatabaseHelperImplementation.getInstance();
		firebaseDatabaseHelper.registerFCMToken();
		firebaseDatabaseHelper.notifyTokenRegistration();
	}

}
