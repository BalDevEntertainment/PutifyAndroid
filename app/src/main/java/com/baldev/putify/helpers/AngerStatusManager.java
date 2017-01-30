package com.baldev.putify.helpers;


public interface AngerStatusManager {


	void getRemainingAnger(AngerStatusManagerListener listener);

	void getCurrentAngerPoints(AngerStatusManagerListener listener);

	interface AngerStatusManagerListener{
		void onGetRemainingAngerRetrieved(int remainingAnger);
		void onGetCurrentAngerPoints(int currentAngerPoints);
	}
}
