package com.baldev.putify.mvps;

import android.content.Context;
import android.text.Editable;

import com.baldev.putify.adapters.MessagesAdapter;

public interface MessagesMVP { //SUPER AWESOME CONVENTION FOR NAMING MVPs, MIND = BLOWN.

	interface Model{

	}

	interface View{
		void onSendPressed();
	}

	interface Presenter {
		void sendMessage(Context context, Editable text);

		void setAdapter(MessagesAdapter adapter);
	}

}
