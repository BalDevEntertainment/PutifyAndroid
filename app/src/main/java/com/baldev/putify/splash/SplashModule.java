package com.baldev.putify.splash;

import android.support.v7.app.AppCompatActivity;

import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.baldev.putify.splash.SplashMVP.Model;
import com.baldev.putify.splash.SplashMVP.Presenter;
import com.baldev.putify.splash.SplashMVP.View;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {
	private final View view;

	public SplashModule(View view) {
		this.view = view;
	}

	@Provides
	public View provideView() {
		return this.view;
	}

	@Provides
	public Presenter providePresenter(View view, Model model) {
		return new SplashPresenter(view, model);
	}

	@Provides
	public Model provideModel() {
		return new SplashModel(new FirebaseMessagesManager((AppCompatActivity)view), new );
	}
}