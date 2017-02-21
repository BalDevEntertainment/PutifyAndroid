package com.baldev.putify.createuser;

import com.baldev.putify.AppComponent;
import com.baldev.putify.scopes.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(	dependencies = AppComponent.class,
			modules = {CreateUserModule.class} )

@SuppressWarnings("package")
public interface CreateUserComponent {
	void inject(CreateUserActivity activity);
}

