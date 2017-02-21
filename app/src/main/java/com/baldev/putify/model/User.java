package com.baldev.putify.model;


import com.google.common.base.Preconditions;

public class User {

	private String messagesToken;
	private String username;

	private User(){
		//Empty constructor for automagical mapping.
	}

	public User(String username) {
		Preconditions.checkNotNull(username);
		this.username = username;
	}


	public String getUsername() {
		return username;
	}

	public String getMessagesToken() {
		return messagesToken;
	}

	public void setMessagesToken(String messagesToken) {
		this.messagesToken = messagesToken;
	}
}
