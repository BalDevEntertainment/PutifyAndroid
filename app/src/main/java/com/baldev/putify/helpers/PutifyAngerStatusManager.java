package com.baldev.putify.helpers;

// TODO: 30/01/2017 review how to use singletons of Dagger2
public class PutifyAngerStatusManager implements AngerStatusManager {
	private static AngerStatusManager instance = new PutifyAngerStatusManager();

	public static AngerStatusManager getInstance() {
		return instance;
	}

	private PutifyAngerStatusManager() {
	}

	@Override
	public void getRemainingAnger(AngerStatusManagerListener listener) {
		// TODO: 30/01/2017 Get if from firebase
		listener.onGetRemainingAngerRetrieved(84);
	}

	@Override
	public void getCurrentAngerPoints(AngerStatusManagerListener listener) {
		// TODO: 30/01/2017 Get if from firebase
		listener.onGetCurrentAngerPoints(84);
	}
}
