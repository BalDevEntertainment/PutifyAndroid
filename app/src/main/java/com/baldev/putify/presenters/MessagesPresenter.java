package com.baldev.putify.presenters;

import android.content.Context;
import android.text.Editable;
import android.util.Log;

import com.baldev.putify.adapters.MessagesAdapter;
import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.helpers.MessagesManager.NewMessageListener;
import com.baldev.putify.model.Message;
import com.baldev.putify.mvps.MessagesMVP;

public class MessagesPresenter implements MessagesMVP.Presenter, NewMessageListener {

	private final FirebaseMessagesManager messagesManager;
	private MessagesAdapter adapter; //Implement Nullable Pattern

	public MessagesPresenter(Context context) {
		messagesManager = new FirebaseMessagesManager(context, this);
	}

	@Override
	public void sendMessage(final Context context, Editable editable) {
		if (editable != null) {
			final String messageText = editable.toString();
			messagesManager.getRandomToken(new MessagesManager.TokenCallback() {
				@Override
				public void onTokenRetrieved(String token) {
					messagesManager.sendMessage(token, messageText);
				}

				@Override
				public void onError() {
					Log.e("Error", "Error");
				}
			});

		}
	}

	@Override
	public void setAdapter(MessagesAdapter adapter) {
		this.adapter = adapter;
	}

	@Override
	public void onNewMessage(String message) {
		if (adapter != null) { //TODO remove on Nullable patter implementation
			Message newMessage = new Message(message);
			adapter.addMessage(newMessage);
		}
	}
}
