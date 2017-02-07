package com.baldev.putify.data;

import com.baldev.putify.data.UsersManager.UserCallback;

public interface RemoteRepository {
	void getMyself(UserCallback userCallback);
}
