package com.baldev.putify.data;


import com.baldev.putify.model.User;

public interface UsersManager {

	void createNewUser(User user);

	User getMyself();

	void instantiateMyself(UserCallback callback);

	interface UserCallback {
		void onUserRetrieved(User user);
	}
}
