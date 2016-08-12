package com.baldev.putify.helpers;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
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
	private static final String REFERENCE_MESSAGES = "messages";
	private static final String KEY_TOKEN = "firebaseToken";
	private static final String KEY_MESSAGE = "message";
	private static final String KEY_TO = "to";
	private FirebaseDatabase database;
	private DatabaseReference usersReference;
	private DatabaseReference messagesReference;
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
		messagesReference = database.getReference(REFERENCE_MESSAGES);
	}

	@Override
	public void registerCurrentFCMToken() {
		refreshedToken = FirebaseInstanceId.getInstance().getToken();
		Log.i("FCMTOKEN", refreshedToken != null ? refreshedToken : "Missing");
		if(refreshedToken != null){
			registerFCMToken(refreshedToken);
		}
	}

	@Override
	public void registerFCMToken(String token) {
		DatabaseReference userToken = usersReference.child(token);
		userToken.child(KEY_TOKEN).setValue(token);
	}

	@Override
	public void getRandomToken(final FirebaseTokenCallback callback) {
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

	@Override
	public void sendMessage(String to, String message) {
		DatabaseReference newMessage = messagesReference.push();
		newMessage.child(KEY_MESSAGE).setValue(message);
		newMessage.child(KEY_TO).setValue(to);
	}

	@Override
	public void registerListenerForMessages(final FirebaseMessageListener listener) {
		messagesReference.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				listener.onNewMessage(dataSnapshot.child("message").getValue().toString());
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {

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
