package com.baldev.putify.data;

import com.baldev.putify.helpers.MessagesManager;
import com.baldev.putify.scopes.ApplicationScoped;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MessagesManagerModule {

	@Inject RemoteRepository remoteRepository;

	private final MessagesManager messagesManager;

	public MessagesManagerModule(MessagesManager messagesManager) {
		this.messagesManager = messagesManager;
	}

	@Provides
	@ApplicationScoped
	public MessagesManager provideMessagesManager() {
		return messagesManager;
	}
}
