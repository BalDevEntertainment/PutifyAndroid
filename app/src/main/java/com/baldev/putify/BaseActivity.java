package com.baldev.putify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(getLayoutResource());
		ButterKnife.bind(this);
		this.getApplicationComponent().inject(this);
	}

	protected abstract int getLayoutResource();

	protected AppComponent getApplicationComponent() {
		return ((PutifyApplication) getApplication()).getAppComponent();
	}
}
