package com.baldev.putify.splash;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules = {SplashModule.class}
)
@SuppressWarnings("package")
public interface SplashComponent {
	void inject(SplashActivity activity);
}

