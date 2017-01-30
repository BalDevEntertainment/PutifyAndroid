package com.baldev.putify.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baldev.putify.R;
import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.helpers.MessagesManager.TokenCallback;
import com.baldev.putify.splash.SplashMVP.Presenter;
import com.baldev.putify.splash.SplashMVP.View;
import com.baldev.putify.views.MessagesActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements View {

	@Inject
	Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_splash);
		this.setupComponent();

		this.presenter.checkFirebaseToken();
	}

	private void setupComponent() {
		DaggerSplashComponent.builder()
				.splashModule(new SplashModule(this))
				.build()
				.inject(this);
	}

	@Override
	public void goToMessagesActivity() {
		Intent messagesIntent = new Intent(this, MessagesActivity.class);
		startActivity(messagesIntent);
		finish();
	}
}
