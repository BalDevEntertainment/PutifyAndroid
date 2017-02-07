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
	public void createNewUser(User user) {
		//FirebaseUsersManager.myself = firebaseDatabaseHelper.createNewUser(user);
	}

	@Override
	public void instantiateMyself(final UserCallback callback) {
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
