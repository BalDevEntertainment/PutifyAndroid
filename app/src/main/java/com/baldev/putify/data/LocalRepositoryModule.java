package com.baldev.putify.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalRepositoryModule {

	private final LocalRepository localRepository;

	public LocalRepositoryModule(LocalRepository localRepository) {
		this.localRepository = localRepository;
	}

	@Provides
	@Singleton
	public LocalRepository provideLocalRepository() {
		return localRepository;
	}
}
