package com.baldev.putify.widgets.angerbar;


public interface AngerStatusBarMVP {

	interface View {

		void setAngerBarProgress(int remainingAnger);

		void setCurrentAngerPoints(int currentAngerPoints);
	}

	interface Presenter {

		void onViewCreated();

		void retrieveAngerData();
	}

	interface Widget {

		void updateData();

	}
}
