package com.baldev.putify.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baldev.putify.R;
import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.helpers.MessagesManager.TokenCallback;
import com.baldev.putify.mvps.SplashMVP.View;

public class SplashActivity extends AppCompatActivity implements View, TokenCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_splash);

		MessagesManager messagesManager = new FirebaseMessagesManager(this);
		if (!messagesManager.hasTokenBeenRetrieved()) {
			messagesManager.askForToken(this);
		} else {
			goToMessagesActivity();
		}
	}

	@Override
	public void onTokenRetrieved(String string) {
		goToMessagesActivity();
	}

	@Override
	public void onError() {
		Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
	}

	private void goToMessagesActivity() {
		Intent messagesIntent = new Intent(this, MessagesActivity.class);
		messagesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(messagesIntent);
	}
}
