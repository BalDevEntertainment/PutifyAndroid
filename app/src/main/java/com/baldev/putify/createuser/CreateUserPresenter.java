package com.baldev.putify.createuser;

import com.baldev.putify.createuser.CreateUserMVP.Presenter;
import com.baldev.putify.createuser.CreateUserMVP.View;
import com.baldev.putify.data.UsersManager;
import com.baldev.putify.model.User;
import com.google.common.base.Preconditions;

import javax.inject.Inject;

public class CreateUserPresenter implements Presenter {

	protected View view;
	protected UsersManager usersManager;

	@Inject
	CreateUserPresenter(View view, UsersManager usersManager) {
		Preconditions.checkNotNull(view);
		Preconditions.checkNotNull(usersManager);

		this.view = view;
		this.usersManager = usersManager;
	}

	@Inject
	void setupPresenter() {
		this.view.setPresenter(this);
	}

	@Override
	public void onAcceptButtonClicked(String userName) {
		this.view.disableAcceptButton();
		this.usersManager.createNewUser(new User(userName), () -> {
			this.view.startMainActivity();
		});
	}
}
