package com.baldev.putify;

import android.app.Application;

import com.baldev.putify.data.FirebaseUsersManager;
import com.baldev.putify.data.LocalRepositoryModule;
import com.baldev.putify.data.MessagesManagerModule;
import com.baldev.putify.data.UserPreferenceManager;
import com.baldev.putify.data.UsersManagerModule;
import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class PutifyApplication extends Application {

	private AppComponent appComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		Fabric.with(this, new Crashlytics());

		appComponent = DaggerAppComponent.builder()
				.localRepositoryModule(new LocalRepositoryModule(UserPreferenceManager.getInstance()))
				.messagesManagerModule(new MessagesManagerModule(new FirebaseMessagesManager(this)))
				.usersManagerModule(new UsersManagerModule(FirebaseUsersManager.getInstance()))
				.build();
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}
}