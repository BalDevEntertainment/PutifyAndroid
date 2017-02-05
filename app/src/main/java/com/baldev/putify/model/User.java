package com.baldev.putify.model;


public class User {

	private String messagesToken;
	private String username;

	private User(){
		//Empty constructor for automagical mapping.
	}

	public User(String username) {
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
