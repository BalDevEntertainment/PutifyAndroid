package com.baldev.putify.widgets.angerbar;

import com.baldev.putify.helpers.PutifyAngerStatusManager;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.Presenter;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.View;

import dagger.Module;
import dagger.Provides;

@Module
public class AngerStatusBarModule {

	private final View view;

	public AngerStatusBarModule(View view) {
		this.view = view;
	}

	@Provides
	Presenter providePresenter() {
		return new AngerStatusBarPresenter(view, PutifyAngerStatusManager.getInstance());
	}
}
