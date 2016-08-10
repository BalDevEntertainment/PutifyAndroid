package com.baldev.putify.helpers;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.Collection;

public class FirebaseHelperImplementation implements FirebaseHelper {

	private static FirebaseHelperImplementation ourInstance = new FirebaseHelperImplementation();
	private static final String REFERENCE_USERS = "users";
	private static final String KEY_TOKEN = "firebaseToken";
	private FirebaseDatabase database;
	private DatabaseReference usersReference;
	private String refreshedToken;

	public static FirebaseHelper getInstance() {
		return ourInstance;
	}

	private FirebaseHelperImplementation() {
		this.setupReferences();
	}

	private void setupReferences() {
		database = FirebaseDatabase.getInstance();
		usersReference = database.getReference(REFERENCE_USERS);
	}

	@Override
	public void registerCurrentFCMToken() {
		refreshedToken = FirebaseInstanceId.getInstance().getToken();
		Log.i("FCMTOKEN", refreshedToken != null ? refreshedToken : "Missing");
		registerFCMToken(refreshedToken);
	}

	@Override
	public void registerFCMToken(String token) {
		DatabaseReference userToken = usersReference.child(token);
		userToken.child(KEY_TOKEN).setValue(token);
	}

	@Override
	public void getRandomToken(final TokenCallback callback) {
		usersReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Collection<DataSnapshot> collection = makeCollection(dataSnapshot.getChildren());
				DataSnapshot randomElement = random(collection);
				String token = randomElement.getKey();
				callback.onTokenRetrieved(token);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}

	public Collection<DataSnapshot> makeCollection(Iterable<DataSnapshot> iter) {
		Collection<DataSnapshot> list = new ArrayList<>();
		for (DataSnapshot item : iter) {
			if (!item.getKey().equals(refreshedToken)) {
				list.add(item);
			}
		}
		return list;
	}

	public static <T> T random(Collection<T> coll) {
		int num = (int) (Math.random() * coll.size());
		for (T t : coll) if (--num < 0) return t;
		throw new AssertionError();
	}


}
