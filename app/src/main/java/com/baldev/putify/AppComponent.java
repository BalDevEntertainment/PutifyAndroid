package com.baldev.putify;

import android.support.v7.app.AppCompatActivity;

import com.baldev.putify.data.LocalRepository;
import com.baldev.putify.data.LocalRepositoryModule;
import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.baldev.putify.helpers.MessagesManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules = {LocalRepositoryModule.class}
)
@SuppressWarnings("package")
public interface AppComponent {
	LocalRepository getLocalRepository(); //This method is needed to expose the value of provider method at the LocalRepositoryModule.

	void inject(AppCompatActivity activity);
}
