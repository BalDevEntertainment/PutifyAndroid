package com.baldev.putify.data;


import com.baldev.putify.model.User;

public interface UsersManager {

	void createNewUser(final User user, final CreateUserCallback callback);

	User getMyself();

	void instantiateMyself(RetrieveUserCallback callback);

	interface RetrieveUserCallback {
		void onUserRetrieved(User user);
	}

	interface CreateUserCallback {
		void onUserCreated();
	}
}
