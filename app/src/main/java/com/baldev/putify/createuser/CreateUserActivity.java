package com.baldev.putify.createuser;

import android.os.Bundle;

import com.baldev.putify.BaseActivity;
import com.baldev.putify.R;
import com.baldev.putify.createuser.CreateUserMVP.Presenter;
import com.baldev.putify.createuser.CreateUserMVP.View;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreateUserActivity extends BaseActivity implements View {

	@Inject CreateUserPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_create_user);
		this.setupComponent();
	}

	@Override
	public void setPresenter(Presenter createUserPresenter) {
		presenter = checkNotNull((CreateUserPresenter) createUserPresenter);
	}

	private void setupComponent() {
		DaggerCreateUserComponent.builder()
				.appComponent(getApplicationComponent())
				.createUserModule(new CreateUserModule(this))
				.build()
				.inject(this);
	}
}
