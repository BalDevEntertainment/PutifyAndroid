package com.baldev.putify.widgets.angerbar;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AngerStatusBarModule.class})
public interface AngerStatusBarComponent {

	void inject(AngerStatusBar view);
}
