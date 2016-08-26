package com.baldev.putify.helpers;

import android.content.Context;

public interface PushNotificationsManager {
	void sendPushNotification(Context context, String to, String body, PushNotificationCallback callback);

	interface PushNotificationCallback{
		void onInvalidRecipient();
	}
}
