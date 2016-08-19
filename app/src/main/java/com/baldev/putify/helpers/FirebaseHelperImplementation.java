package com.baldev.putify.helpers;

import com.baldev.putify.helpers.MessagesManager.TokenCallback;
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

	public static final String FCM_TOKEN = "FCMTOKEN";
	public static final String MISSING = "Missing";
	private static final String REFERENCE_USERS = "users";
	private static final String REFERENCE_MESSAGES = "messages";
	private static final String KEY_TOKEN = "firebaseToken";
	private static final String KEY_MESSAGE = "message";
	private static FirebaseHelperImplementation ourInstance = new FirebaseHelperImplementation();
	private FirebaseDatabase database;
	private DatabaseReference usersReference;
	private DatabaseReference messagesReference;
	private DatabaseReference myMessagesReference; //TODO see how to avoid null
	private String myToken;
	private FirebaseTokenCallback tokenCallback;

	public static FirebaseHelper getInstance() {
		return ourInstance;
	}

	private FirebaseHelperImplementation() {
		this.myToken = FirebaseInstanceId.getInstance().getToken();
	}

	@Override
	public void registerFCMToken() {
		this.myToken = FirebaseInstanceId.getInstance().getToken();
		this.setupReferences();
	}

	@Override
	public void notifyTokenRegistration() {
		if (tokenCallback != null) {
			tokenCallback.onTokenRetrieved(this.myToken);
		}
	}

	@Override
	public void askForToken(FirebaseTokenCallback callback) {
		this.tokenCallback = callback;
		FirebaseInstanceId.getInstance().getToken();
	}

	@Override
	public String getMyToken() {
		return myToken;
	}

	@Override
	public void getRandomToken(final FirebaseTokenCallback callback) {
		this.usersReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Collection<DataSnapshot> collection = makeCollection(dataSnapshot.getChildren());
				DataSnapshot randomElement = random(collection);
				String token = randomElement.getKey();
				callback.onTokenRetrieved(token);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				callback.onError();
			}
		});
	}

	@Override
	public void sendMessage(String to, String message) {
		DatabaseReference receiverMessagesReference = messagesReference.child(to);
		DatabaseReference newMessage = receiverMessagesReference.push();
		newMessage.child(KEY_MESSAGE).setValue(message);
	}


	@Override
	public void registerListenerForMessages(final FirebaseMessageListener listener) {
		this.myMessagesReference.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				Object message = dataSnapshot.child("message").getValue();
				if (message != null) {
					listener.onNewMessage(message.toString());
				}
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

	private void setupReferences() {
		this.database = FirebaseDatabase.getInstance();
		this.usersReference = this.database.getReference(REFERENCE_USERS);
		this.messagesReference = this.database.getReference(REFERENCE_MESSAGES);
		if (this.myToken != null && !this.myToken.equals("")) {
			this.myMessagesReference = this.messagesReference.child(myToken);
			DatabaseReference userToken = this.usersReference.child(myToken);
			userToken.child(KEY_TOKEN).setValue(myToken);
		}
	}

	private Collection<DataSnapshot> makeCollection(Iterable<DataSnapshot> iter) {
		Collection<DataSnapshot> list = new ArrayList<>();
		for (DataSnapshot item : iter) {
			if (!item.getKey().equals(this.myToken)) {
				list.add(item);
			}
		}
		return list;
	}

	private static <T> T random(Collection<T> coll) {
		int num = (int) (Math.random() * coll.size());
		for (T t : coll) if (--num < 0) return t;
		throw new AssertionError();
	}
}
