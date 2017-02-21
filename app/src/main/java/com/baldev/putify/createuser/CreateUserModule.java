package com.baldev.putify.createuser;

import com.baldev.putify.createuser.CreateUserMVP.View;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateUserModule {
	private final View view;

	public CreateUserModule(View view) {
		this.view = view;
	}

	@Provides
	public View provideView() {
		return this.view;
	}
}