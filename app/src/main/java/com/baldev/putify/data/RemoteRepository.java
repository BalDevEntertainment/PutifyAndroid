package com.baldev.putify.data;

import com.baldev.putify.data.UsersManager.CreateUserCallback;
import com.baldev.putify.data.UsersManager.RetrieveUserCallback;
import com.baldev.putify.model.User;

public interface RemoteRepository {
	void createUser(User user, CreateUserCallback createUsercallback);
	void getMyself(RetrieveUserCallback retrieveUserCallback);
}
