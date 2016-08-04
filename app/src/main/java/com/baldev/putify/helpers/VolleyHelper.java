package com.baldev.putify.helpers;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyHelper {

	public static void sendTestRequest(Context context) {
		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(context);
		String url = "http://www.google.com";

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// Display the first 500 characters of the response string.
						Log.d("Volley", "Response is: " + response.substring(0, 500));
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("Volley", "That didn't work!");
			}
		});
// Add the request to the RequestQueue.
		queue.add(stringRequest);
	}

	public static void sendTestNotification(Context context) {
		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(context);
		String url = "https://fcm.googleapis.com/fcm/send";

		JSONObject jsonObject = new JSONObject();
		JSONObject notificationJson = new JSONObject();
		try {
			notificationJson.put("body", "This is the body");
			notificationJson.put("title", "Title over here!");
			jsonObject.put("notification", notificationJson);
			jsonObject.put("to", "dwyNo9Ax40A:APA91bGCaVaFRGCA6B280R4GXKiszalb4VBCW2wjGQf3dHMixiEj4UTjYjU_Lm41JzG6CFJl9BleN43w6rYntaZihPAF79CWVIfASZ4ZG-t32rO9WG0ayCsRZnRtwz4x80gdzNGBRur-");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Request a string response from the provided URL.
		HeaderJsonObjectRequest jsonObjectRequest = new HeaderJsonObjectRequest(Method.POST, url, jsonObject,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// Display the first 500 characters of the response string.
						Log.d("Volley", "Response is: " + response.toString());
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("Volley", "That didn't work!");
			}
		});

		queue.add(jsonObjectRequest);
	}


	public static class HeaderJsonObjectRequest extends JsonObjectRequest {
		public HeaderJsonObjectRequest(int method, String url, JSONObject jsonRequest, Listener listener, ErrorListener errorListener) {
			super(method, url, jsonRequest, listener, errorListener);
		}

		@Override
		public Map getHeaders() throws AuthFailureError {
			Map headers = new HashMap();
			headers.put("Content-Type", "application/json");
			headers.put("Authorization", "key=AIzaSyBidDnnrGsfzxAUrtUKatouk6q9xXaE_8Q");
			return headers;
		}
	}
}
