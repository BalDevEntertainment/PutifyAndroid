package com.baldev.putify.presenters;

import android.text.Editable;

import com.baldev.putify.helpers.VolleyHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendMessagePresenter implements MessagePresenter {

	@Override
	public void sendMessage(Editable editable) {
		if (editable != null) {
			String messageText = editable.toString();
			FirebaseDatabase database = FirebaseDatabase.getInstance();
			DatabaseReference myRef = database.getReference("message");

			myRef.setValue(messageText);

		}
	}
}
