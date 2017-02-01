package com.baldev.putify.data;

import javax.inject.Singleton;

@Singleton
public class UserPreferenceManager implements LocalRepository {

	private static final UserPreferenceManager instance = new UserPreferenceManager();

	private UserPreferenceManager() {
	}

	public static UserPreferenceManager getInstance(){
		return instance;
	}



}
