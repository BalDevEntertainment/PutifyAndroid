package com.baldev.putify.splash;

import com.baldev.putify.AppComponent;
import com.baldev.putify.scopes.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(	dependencies = AppComponent.class,
			modules = {SplashModule.class} )

@SuppressWarnings("package")
public interface SplashComponent {
	void inject(SplashActivity activity);
}

