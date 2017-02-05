package com.baldev.putify.data;

import com.baldev.putify.helpers.MessagesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MessagesManagerModule {

	private final MessagesManager messagesManager;

	public MessagesManagerModule(MessagesManager messagesManager) {
		this.messagesManager = messagesManager;
	}

	@Provides
	@Singleton
	public MessagesManager provideMessagesManager() {
		return messagesManager;
	}
}
