package com.baldev.putify.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersManagerModule {

	private final UsersManager usersManager;

	public UsersManagerModule(UsersManager usersManager) {
		this.usersManager = usersManager;
	}

	@Provides
	@Singleton
	public UsersManager provideUsersManager() {
		return usersManager;
	}
}
