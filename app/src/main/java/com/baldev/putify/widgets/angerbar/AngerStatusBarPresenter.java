package com.baldev.putify.widgets.angerbar;


import com.baldev.putify.helpers.AngerStatusManager;
import com.baldev.putify.helpers.AngerStatusManager.AngerStatusManagerListener;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.Presenter;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.View;

import javax.inject.Inject;

public class AngerStatusBarPresenter implements Presenter, AngerStatusManagerListener {

	private View view;

	private AngerStatusManager angerStatusManager;

	@Inject
	public AngerStatusBarPresenter(View view, AngerStatusManager angerStatusManager) {
		this.view = view;
		this.angerStatusManager = angerStatusManager; // TODO: 30/01/2017 replace
	}

	@Override
	public void onViewCreated() {
		retrieveAngerData();
	}

	@Override
	public void retrieveAngerData() {
		this.angerStatusManager.getRemainingAnger(this);
		this.angerStatusManager.getCurrentAngerPoints(this);
	}

	@Override
	public void onGetRemainingAngerRetrieved(int remainingAnger) {
		this.view.setAngerBarProgress(remainingAnger);
	}

	@Override
	public void onGetCurrentAngerPoints(int currentAngerPoints) {
		this.view.setCurrentAngerPoints(currentAngerPoints);
	}

}
