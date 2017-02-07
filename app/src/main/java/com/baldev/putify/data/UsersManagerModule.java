package com.baldev.putify.data;

import com.baldev.putify.scopes.ApplicationScoped;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersManagerModule {

	private final RemoteRepository remoteRepository;

	public UsersManagerModule(RemoteRepository remoteRepository) {
		this.remoteRepository = remoteRepository;
	}

	@Provides
	@ApplicationScoped
	public UsersManager provideUsersManager() {
		return new FirebaseUsersManager(remoteRepository);
	}
}
