package com.baldev.putify.helpers;

import android.content.Context;

public interface VolleyHelper {
	void sendPushNotification(Context context, String to, String title, String body);
}
