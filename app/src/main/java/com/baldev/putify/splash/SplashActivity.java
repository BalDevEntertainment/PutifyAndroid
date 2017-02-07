package com.baldev.putify.splash;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.ImageView;
import android.widget.Toast;

import com.baldev.putify.BaseActivity;
import com.baldev.putify.PutifyApplication;
import com.baldev.putify.R;
import com.baldev.putify.createuser.CreateUserActivity;
import com.baldev.putify.splash.SplashMVP.Presenter;
import com.baldev.putify.splash.SplashMVP.View;
import com.baldev.putify.views.MessagesActivity;

import javax.inject.Inject;

import butterknife.BindView;

import static com.google.common.base.Preconditions.checkNotNull;

public class SplashActivity extends BaseActivity implements View {

	@BindView(R.id.logo) ImageView logo;

	@Inject
	SplashPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((PutifyApplication)getApplication()).waitForInitialization(this::setupComponent);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_splash;
	}

	@Override
	public void setPresenter(Presenter splashPresenter) {
		presenter = checkNotNull((SplashPresenter) splashPresenter);
	}

	@Override
	public void goToCreateUserActivity() {
		Intent intent = new Intent(this, CreateUserActivity.class);
		ActivityOptionsCompat options = ActivityOptionsCompat.
				makeSceneTransitionAnimation(this, logo, "profile");
		if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
			startActivity(intent, options.toBundle());
		}
	}

	@Override
	public void goToMessagesActivity() {
		Intent messagesIntent = new Intent(this, MessagesActivity.class);
		startActivity(messagesIntent);
		finish();
	}

	private void setupComponent() {
		DaggerSplashComponent.builder()
				.appComponent(getApplicationComponent())
				.splashModule(new SplashModule(this))
				.build()
				.inject(this);
	}
}
