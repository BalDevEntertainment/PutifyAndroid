package com.baldev.putify;

import android.app.Application;

import com.baldev.putify.data.DaggerRepositoryComponent;
import com.baldev.putify.data.MessagesManagerModule;
import com.baldev.putify.data.RepositoryComponent;
import com.baldev.putify.data.UsersManagerModule;
import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import io.fabric.sdk.android.Fabric;

import static com.google.common.base.Strings.isNullOrEmpty;

public class PutifyApplication extends Application {

	private AppComponent appComponent;

	private InitializationListener initializationListener;
	private boolean appIsReady;

	@Override
	public void onCreate() {
		super.onCreate();
		initializeFabric();
		initializeFirebase();
		initializeFirebaseDatabase();

		appIsReady = !isNullOrEmpty(FirebaseInstanceId.getInstance().getToken());
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}

	public void waitForInitialization(InitializationListener initializationListener) {
		this.initializationListener = initializationListener;
		if (appIsReady) {
			initializationCompleted();
		}
	}

	public void onFCMTokenRetrieved() {
		appIsReady = true;
		if (this.initializationListener != null) {
			initializationCompleted();
		}
	}

	private void initializationCompleted() {
		RepositoryComponent repositoryComponent = DaggerRepositoryComponent.create();

		appComponent = DaggerAppComponent.builder()
				.repositoryComponent(repositoryComponent)
				.messagesManagerModule(new MessagesManagerModule(new FirebaseMessagesManager(this)))
				.usersManagerModule(new UsersManagerModule(repositoryComponent.getRemoteRepository()))
				.build();
		initializationListener.onInitializationCompleted();
		this.initializationListener = null;
	}

	private void initializeFabric() {
		Fabric.with(this, new Crashlytics());
	}

	private void initializeFirebase() {
		FirebaseApp.initializeApp(this);
	}

	private void initializeFirebaseDatabase() {
		FirebaseDatabase.getInstance().setPersistenceEnabled(false);
	}

	public interface InitializationListener {
		void onInitializationCompleted();
	}
}