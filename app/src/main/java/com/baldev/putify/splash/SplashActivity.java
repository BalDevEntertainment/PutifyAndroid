package com.baldev.putify.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baldev.putify.BaseActivity;
import com.baldev.putify.R;
import com.baldev.putify.data.LocalRepository;
import com.baldev.putify.data.LocalRepositoryModule;
import com.baldev.putify.data.UserPreferenceManager;
import com.baldev.putify.helpers.FirebaseMessagesManager;
import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.helpers.MessagesManager.TokenCallback;
import com.baldev.putify.splash.SplashMVP.Presenter;
import com.baldev.putify.splash.SplashMVP.View;
import com.baldev.putify.views.MessagesActivity;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class SplashActivity extends BaseActivity implements View {

	@Inject SplashPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_splash);
		this.setupComponent();
	}

	@Override
	public void setPresenter(Presenter splashPresenter) {
		presenter = checkNotNull((SplashPresenter) splashPresenter);
	}

	@Override
	public void showToast(String text) {
		Toast.makeText(this, text != null && !text.isEmpty() ? text : "Empty", Toast.LENGTH_SHORT).show();
	}

	private void setupComponent() {
		DaggerSplashComponent.builder()
				.appComponent(getApplicationComponent())
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
