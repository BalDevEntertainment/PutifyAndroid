package com.baldev.putify.helpers;

//Facade for messages

import com.baldev.putify.model.Message;

@Deprecated
public interface MessagesManager {

	void askForToken(TokenCallback callback);

	void sendMessage(Message message);

	void getRandomToken(TokenCallback callback);

	boolean hasTokenBeenRetrieved();

	void invalidateToken(String token);

	interface NewMessageListener {
		void onNewMessage(Message message);
	}

	interface TokenCallback {
		void onTokenRetrieved(String string);

		void onError();
	}

	interface MessageDeliveryCallback {
		void onInvalidRecipient();
	}
}
