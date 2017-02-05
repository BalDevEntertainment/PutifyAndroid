package com.baldev.putify.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.baldev.putify.R;

import javax.inject.Singleton;

@Singleton
public class UserPreferenceManager implements LocalRepository {

	private static final UserPreferenceManager instance = new UserPreferenceManager();

	private UserPreferenceManager() {
	}

	public static UserPreferenceManager getInstance(){
		return instance;
	}


	@Override
	public String getCurrentUser(AppCompatActivity activity) {
		SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
		return sharedPref.getString(activity.getString(R.string.preference_key_user_name), null);
	}
}
