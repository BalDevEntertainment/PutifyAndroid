package com.baldev.putify.widgets.angerbar;


import com.baldev.putify.helpers.AngerStatusManager;
import com.baldev.putify.helpers.AngerStatusManager.AngerStatusManagerListener;
import com.baldev.putify.helpers.PutifyAngerStatusManager;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.Presenter;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.View;

import javax.inject.Inject;

public class AngerStatusBarPresenter implements Presenter, AngerStatusManagerListener {

	@Inject
	View view;

	private AngerStatusManager angerStatusManager;

	public AngerStatusBarPresenter() {
		this.angerStatusManager = PutifyAngerStatusManager.getInstance(); // TODO: 30/01/2017 replace
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
