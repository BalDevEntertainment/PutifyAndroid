package com.baldev.putify.splash;

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
}