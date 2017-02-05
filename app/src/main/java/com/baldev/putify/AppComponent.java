package com.baldev.putify;

import android.support.v7.app.AppCompatActivity;

import com.baldev.putify.data.LocalRepository;
import com.baldev.putify.data.LocalRepositoryModule;
import com.baldev.putify.data.MessagesManagerModule;
import com.baldev.putify.data.UsersManager;
import com.baldev.putify.data.UsersManagerModule;
import com.baldev.putify.helpers.MessagesManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules = {LocalRepositoryModule.class, MessagesManagerModule.class, UsersManagerModule.class}
)
@SuppressWarnings("package")
public interface AppComponent {
	//These methods are needed to expose the value of provider method at the corresponding Modules.
	LocalRepository getLocalRepository();
	MessagesManager getMessagesManager();
	UsersManager getUsersManager();

	void inject(AppCompatActivity activity);
}
