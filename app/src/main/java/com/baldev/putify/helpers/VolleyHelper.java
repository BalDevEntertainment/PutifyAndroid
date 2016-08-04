package com.baldev.putify.helpers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
}
