package com.baldev.putify.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteRepositoryModule {

	@Provides
	@Singleton
	public RemoteRepository provideRemoteRepository() {
		return new FirebaseRepository();
	}
}
