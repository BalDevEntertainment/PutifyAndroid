package com.baldev.putify.data;

import android.support.annotation.NonNull;

import com.baldev.putify.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirebaseUsersManager implements UsersManager {

	private static User myself;

	private final RemoteRepository remoteRepository;

	@Inject
	protected FirebaseUsersManager(RemoteRepository remoteRepository) {
		this.remoteRepository = remoteRepository;
	}

	@Override
	public void createNewUser(final User user, final CreateUserCallback callback) {
		remoteRepository.createUser(user, () -> FirebaseUsersManager.myself = user);
		callback.onUserCreated();
	}

	@Override
	public void instantiateMyself(final RetrieveUserCallback callback) {
		if (myself == null) {
			remoteRepository.getMyself(user -> {
				myself = user;
				callback.onUserRetrieved(myself);
			});
		} else {
			throw new IllegalStateException("myself was already instantiated");
		}
	}

	@Override
	@NonNull
	public User getMyself() {
		if (myself == null) {
			throw new IllegalStateException("instantiateMyself() needs to be called before to retrieve 'myself'");
		}
		return myself;
	}


}
