package com.baldev.putify.data;


import com.baldev.putify.model.User;

import javax.inject.Singleton;

@Singleton
public class FirebaseRepository implements RemoteRepository {

	@Override
	public User getMyself() {
		return new User("Prueba");
	}
}
