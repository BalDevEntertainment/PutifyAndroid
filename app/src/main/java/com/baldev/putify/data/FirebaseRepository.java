package com.baldev.putify.data;


import com.baldev.putify.BuildConfig;
import com.baldev.putify.data.UsersManager.UserCallback;
import com.baldev.putify.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Singleton;

@Singleton
public class FirebaseRepository implements RemoteRepository {

	private static final String KEY_DATABASE = BuildConfig.DATABASE;
	private static final String KEY_USERS = "users";

	private final FirebaseDatabase firebaseDatabase;
	private final DatabaseReference rootRef;
	private final DatabaseReference usersRef;
	private final String myToken;

	protected FirebaseRepository() {
		this.firebaseDatabase = FirebaseDatabase.getInstance();
		this.myToken = FirebaseInstanceId.getInstance().getToken();

		this.rootRef = this.firebaseDatabase.getReference(KEY_DATABASE);
		this.usersRef = this.rootRef.child(KEY_USERS);
	}

	@Override
	public void getMyself(UserCallback callback) {
		usersRef.child(myToken).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				User user = dataSnapshot.getValue(User.class);
				if(user != null){
					user.setMessagesToken(myToken);
				}
				callback.onUserRetrieved(user);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}
}