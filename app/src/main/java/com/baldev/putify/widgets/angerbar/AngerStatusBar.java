package com.baldev.putify.widgets.angerbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baldev.putify.R;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.Presenter;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.View;
import com.baldev.putify.widgets.angerbar.AngerStatusBarMVP.Widget;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AngerStatusBar extends LinearLayout implements View, Widget {

	private static final int ANGER_BAR_MAX_VALUE = 100;

	@BindView(R.id.progress_bar_anger) ProgressBar angerBar;
	@BindView(R.id.text_view_anger_points) TextView angerPoints;

	@Inject
	private Presenter presenter;

	public AngerStatusBar(Context context) {
		super(context);
		this.init();
	}

	public AngerStatusBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init();
	}

	public AngerStatusBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.init();
	}

	private void init() {
		inflate(getContext(), R.layout.widget_anger_bar, this);
		ButterKnife.bind(this);
		this.angerBar.setMax(ANGER_BAR_MAX_VALUE);
		this.presenter.onViewCreated();
	}

	@Override
	public void setAngerBarProgress(int remainingAnger) {
		this.angerBar.setProgress(remainingAnger);
	}

	@Override
	public void setCurrentAngerPoints(int currentAngerPoints) {
		this.angerPoints.setText(String.valueOf(currentAngerPoints));
	}

	@Override
	public void updateData() {
		this.presenter.retrieveAngerData();
	}
}
