package com.baldev.putify.helpers;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baldev.putify.services.PutifyFCMService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class VolleyHelperImplementation implements PushNotificationsManager {

	private static PushNotificationsManager instance = new VolleyHelperImplementation();

	public static final String URL_FCM = "https://fcm.googleapis.com/fcm/send";
	public static final String KEY_TO = "to";

	public static PushNotificationsManager getInstance() {
		return instance;
	}

	public void sendPushNotification(Context context, String to, String body, final PushNotificationCallback callback) {
		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(context);
		String url = URL_FCM;

		//JSONObject notificationJson = new DataJson(to, body);
		JSONObject notificationJson = new DataJson(to, body);

		// Request a string response from the provided URL.
		HeaderJsonObjectRequest jsonObjectRequest = new HeaderJsonObjectRequest(Method.POST, url, notificationJson,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d("Volley", "Response is: " + response.toString());
						if (callback != null) {
							if (response.has("failure")) {
								try {
									boolean failed = response.getInt("failure") != 0;
									if (failed) {
										callback.onInvalidRecipient();
									}
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("Volley", "That didn't work!");
			}
		});

		queue.add(jsonObjectRequest);
	}

	public static class NotificationJson extends JSONObject {
		private static final String KEY_NOTIFICATION = "notification";
		private static final String KEY_BODY = "body";
		private static final String KEY_TITLE = "title";

		public NotificationJson(String to, String title, String body) {
			JSONObject content = new JSONObject();
			try {
				content.put(KEY_TITLE, title);
				content.put(KEY_BODY, body);
				this.put(KEY_NOTIFICATION, content);
				this.put(KEY_TO, to);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public static class DataJson extends JSONObject {
		private static final String KEY_DATA = "data";
		private static final String KEY_BODY = PutifyFCMService.KEY_BODY;

		public DataJson(String to, String body) {
			JSONObject content = new JSONObject();
			try {
				content.put(KEY_BODY, body);
				this.put(KEY_DATA, content);
				this.put(KEY_TO, to);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}


	public static class HeaderJsonObjectRequest extends JsonObjectRequest {
		public HeaderJsonObjectRequest(int method, String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener) {
			super(method, url, jsonRequest, listener, errorListener);
		}

		@Override
		public Map<String, String> getHeaders() throws AuthFailureError {
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Authorization", "key=AIzaSyBidDnnrGsfzxAUrtUKatouk6q9xXaE_8Q");
			return headers;
		}
	}
}
