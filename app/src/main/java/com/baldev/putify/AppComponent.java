package com.baldev.putify;

import android.support.v7.app.AppCompatActivity;

import com.baldev.putify.data.MessagesManagerModule;
import com.baldev.putify.data.RepositoryComponent;
import com.baldev.putify.data.UsersManager;
import com.baldev.putify.data.UsersManagerModule;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.scopes.ApplicationScoped;

import dagger.Component;

@ApplicationScoped
@Component(dependencies = RepositoryComponent.class,
		modules = {MessagesManagerModule.class, UsersManagerModule.class}
)
@SuppressWarnings("package")
public interface AppComponent {
	//These methods are needed to expose the value of provider method at the corresponding Modules.
	MessagesManager getMessagesManager();

	UsersManager getUsersManager();

	void inject(AppCompatActivity activity);
}
