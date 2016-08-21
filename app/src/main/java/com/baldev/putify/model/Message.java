package com.baldev.putify.model;

import com.baldev.putify.mvps.MessagesMVP;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import java.util.Calendar;
import java.util.Locale;

@IgnoreExtraProperties
public class Message implements MessagesMVP.Model {


	public static final String KEY_BODY = "body";
	public static final String KEY_TIMESTAMP = "timestamp";

	private String to;
	private String text;
	private long timestamp;

	public Message() { //For Firebase

	}

	public Message(String to, String message) {
		this.to = to;
		this.text = message;
		this.timestamp = Calendar.getInstance(Locale.getDefault()).getTimeInMillis();
	}

	public Message(String to, String message, long timestamp) {
		this.to = to;
		this.text = message;
		this.timestamp = timestamp;
	}

	@Exclude
	@Override
	public String getDestinatary() {
		return this.to;
	}

	@PropertyName(KEY_BODY)
	public String getText() {
		return this.text;
	}

	@PropertyName(KEY_TIMESTAMP)
	public long getTimestamp() {
		return this.timestamp;
	}


}
