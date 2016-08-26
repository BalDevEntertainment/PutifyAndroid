package com.baldev.putify.helpers;

import com.baldev.putify.BuildConfig;
import com.baldev.putify.model.Message;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.Collection;

public class FirebaseDatabaseHelperImplementation implements FirebaseDatabaseHelper {
	private static final String KEY_DATABASE = BuildConfig.DATABASE;
	private static final String KEY_USERS = "users";
	private static final String KEY_MESSAGES = "messages";
	private static final String KEY_TOKEN = "firebaseToken";
	private static final String KEY_MESSAGE = "message";
	private static FirebaseDatabaseHelperImplementation ourInstance = new FirebaseDatabaseHelperImplementation();
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference database;
	private DatabaseReference usersReference;
	private DatabaseReference messagesReference;
	private DatabaseReference myMessagesReference; //TODO see how to avoid null
	private String myToken;
	private FirebaseTokenCallback tokenCallback;

	public static FirebaseDatabaseHelper getInstance() {
		return ourInstance;
	}

	private FirebaseDatabaseHelperImplementation() {
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
		this.usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Collection<DataSnapshot> collection = makeCollection(dataSnapshot.getChildren());
				try {
					DataSnapshot randomElement = random(collection);
					String token = randomElement.getKey();
					callback.onTokenRetrieved(token);
				} catch (AssertionError error) {
					callback.onError("There's only one Putify user");
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				callback.onError(databaseError.getMessage());
			}
		});
	}

	@Override
	public void saveMessage(Message message) {
		DatabaseReference receiverMessagesReference = messagesReference.child(message.getRecipient());
		DatabaseReference newMessage = receiverMessagesReference.push().child(KEY_MESSAGE);
		newMessage.setValue(message);

	}

	@Override
	public void registerListenerForMessages(final FirebaseMessageListener listener) {
		this.myMessagesReference.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				Object messageBody = dataSnapshot.child(KEY_MESSAGE).child(Message.KEY_BODY).getValue();
				Object messageTimestamp = dataSnapshot.child(KEY_MESSAGE).child(Message.KEY_TIMESTAMP).getValue();
				if (messageBody != null && messageTimestamp != null) {
					String to = myMessagesReference.getKey();
					String body = messageBody.toString();
					long timestamp = Long.valueOf(messageTimestamp.toString());
					listener.onNewMessage(to, body, timestamp);
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

	@Override
	public void invalidateToken(String token) {
		DatabaseReference userReference = this.usersReference.child(token);
		if (userReference != null) {
			userReference.removeValue();
		}
	}

	private void setupReferences() {
		if (this.firebaseDatabase == null) {
			this.firebaseDatabase = FirebaseDatabase.getInstance();
			this.firebaseDatabase.setPersistenceEnabled(true);
		}
		this.database = this.firebaseDatabase.getReference(KEY_DATABASE);
		this.usersReference = this.database.child(KEY_USERS);
		this.messagesReference = this.database.child(KEY_MESSAGES);
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
