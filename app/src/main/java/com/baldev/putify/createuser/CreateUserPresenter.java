package com.baldev.putify.createuser;

import com.baldev.putify.createuser.CreateUserMVP.Presenter;
import com.baldev.putify.createuser.CreateUserMVP.View;

import javax.inject.Inject;

public class CreateUserPresenter implements Presenter {

	protected View view;

	@Inject
	CreateUserPresenter(View view) {
		this.view = view;
	}

	@Inject
	void setupPresenter() {
		this.view.setPresenter(this);
	}
}
