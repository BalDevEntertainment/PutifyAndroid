package com.baldev.putify.data;

import com.baldev.putify.scopes.ApplicationScoped;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules = {RemoteRepositoryModule.class}
)
public interface RepositoryComponent {
	RemoteRepository getRemoteRepository();
}
