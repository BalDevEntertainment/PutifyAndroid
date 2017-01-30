package com.baldev.putify;

import android.app.Application;

import com.baldev.putify.data.LocalRepository;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class PutifyApplication extends Application {

	private LocalRepository localRepositoryComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		Fabric.with(this, new Crashlytics());
	}

	public LocalRepository getLocalRepositoryComponent() {
		return localRepositoryComponent;
	}
}