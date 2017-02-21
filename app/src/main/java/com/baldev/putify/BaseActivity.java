package com.baldev.putify;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(getLayoutResource());
		bindActivityToButterknife();
		initializeApplicationComponent();
	}

	protected void bindActivityToButterknife() {
		ButterKnife.bind(this);
	}

	protected void initializeApplicationComponent() {
		this.getApplicationComponent().inject(this);
	}

	protected abstract int getLayoutResource();

	protected AppComponent getApplicationComponent() {
		return ((PutifyApplication) getApplication()).getAppComponent();
	}

	@Override
	public void startActivity(Intent intent, Bundle options) {
		if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
			super.startActivity(intent, options);
		} else {
			super.startActivity(intent);
		}
	}
}
