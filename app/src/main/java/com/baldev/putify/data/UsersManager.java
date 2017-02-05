package com.baldev.putify.data;


import com.baldev.putify.model.User;

public interface UsersManager {

	void initialize(UsersManagerInitializationListener listener);

	void createNewUser(User user);

	User getMyself();

	interface UsersManagerInitializationListener{
		void onInitializationCompleted();
	}

}
