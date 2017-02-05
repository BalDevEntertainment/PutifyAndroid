package com.baldev.putify.data;

import com.baldev.putify.helpers.FirebaseDatabaseHelper;
import com.baldev.putify.helpers.FirebaseDatabaseHelper.UserCallback;
import com.baldev.putify.helpers.FirebaseDatabaseHelperImplementation;
import com.baldev.putify.model.User;
import com.google.firebase.database.ValueEventListener;

public class FirebaseUsersManager implements UsersManager {

	private static final FirebaseDatabaseHelper firebaseDatabaseHelper = FirebaseDatabaseHelperImplementation.getInstance();
	private static final FirebaseUsersManager instance = new FirebaseUsersManager();

	private static User myself;

	private FirebaseUsersManager() {
	}

	public static FirebaseUsersManager getInstance() {
		return instance;
	}

	@Override
	public void initialize(UsersManagerInitializationListener listener){
		firebaseDatabaseHelper.getMyself(user -> {
			myself = user;
			listener.onInitializationCompleted();
		});
	}

	@Override
	public void createNewUser(User user) {
		FirebaseUsersManager.myself = firebaseDatabaseHelper.createNewUser(user);
	}

	@Override
	public User getMyself() {
		return myself;
	}

}
