package com.baldev.putify.createuser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.baldev.putify.BaseActivity;
import com.baldev.putify.R;
import com.baldev.putify.createuser.CreateUserMVP.Presenter;
import com.baldev.putify.createuser.CreateUserMVP.View;
import com.baldev.putify.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class CreateUserActivity extends BaseActivity implements View {

	@BindView(R.id.username)
	EditText username;

	@BindView(R.id.accept_button)
	Button acceptButton;

	@Inject CreateUserPresenter presenter;

	@Override
	public void setPresenter(Presenter createUserPresenter) {
		presenter = checkNotNull((CreateUserPresenter) createUserPresenter);
	}

	@Override
	public void enableAcceptButton() {
		this.acceptButton.setEnabled(true);
	}

	@Override
	public void startMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public void disableAcceptButton() {
		this.acceptButton.setEnabled(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setupComponent();
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_create_user;
	}

	@OnClick(R.id.accept_button)
	protected void onAcceptButtonClicked() {
		presenter.onAcceptButtonClicked(username.getText().toString());
	}

	private void setupComponent() {
		DaggerCreateUserComponent.builder()
				.appComponent(getApplicationComponent())
				.createUserModule(new CreateUserModule(this))
				.build()
				.inject(this);
	}
}
