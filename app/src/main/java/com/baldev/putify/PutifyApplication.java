package com.baldev.putify;

import android.app.Application;

import com.baldev.putify.data.LocalRepositoryModule;
import com.baldev.putify.data.UserPreferenceManager;
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
				.build();
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}
}