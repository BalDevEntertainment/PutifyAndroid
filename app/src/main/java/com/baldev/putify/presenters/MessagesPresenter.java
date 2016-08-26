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
		this.messagesManager = new FirebaseMessagesManager(context, this);
	}

	@Override
	public void sendMessage(final Context context, final Editable editable) {
		if (isMessageValid(editable)) {
			final String messageText = editable.toString();
			this.messagesManager.getRandomToken(new MessagesManager.TokenCallback() {
				@Override
				public void onTokenRetrieved(final String token) {
					Message newMessage = new Message(token, messageText);
					messagesManager.sendMessage(newMessage);
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
	public void onNewMessage(Message message) {
		if (this.adapter != null) { //TODO remove on Nullable patter implementation
			this.adapter.addMessage(message);
		}
	}

	private boolean isMessageValid(Editable editable) {
		return editable != null && !editable.toString().trim().equals("");
	}
}
