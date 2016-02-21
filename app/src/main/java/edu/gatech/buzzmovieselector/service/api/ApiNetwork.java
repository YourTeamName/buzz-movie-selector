package edu.gatech.buzzmovieselector.service.api;

import android.content.Context;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.buzzmovieselector.service.api.receiver.ApiJSONReceiver;
import edu.gatech.buzzmovieselector.service.api.receiver.ApiTextReceiver;
import org.json.JSONObject;

/**
 * Provides Api networking functionality
 */
public class ApiNetwork {

    private static ApiNetwork ourInstance = null;
    private RequestQueue apiRequestQueue;
    private static Context apiContext;

    public static ApiNetwork getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ApiNetwork(context);
        }
        return ourInstance;
    }

    // sort of unconventional but commands can't access the app context
    public static ApiNetwork getInstance() {
        return ourInstance;
    }
    public RequestQueue getApiRequestQueue() {
        if (apiRequestQueue == null) {
            apiRequestQueue = Volley.newRequestQueue(apiContext.getApplicationContext());
        }
        return apiRequestQueue;
    }

    private ApiNetwork(Context context) {
        apiContext = context;
        apiRequestQueue = getApiRequestQueue();
    }

    public ApiJSONReceiver apiJSON(String url) {
        return apiJSON(url, null);
    }

    public ApiTextReceiver apiString(String url) {
        return apiString(url, null);
    }

    public ApiJSONReceiver apiJSON(String url, ApiCallback callback) {
        RequestFuture<JSONObject> jsonFuture = RequestFuture.newFuture();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, jsonFuture, jsonFuture);
        ApiJSONReceiver jsonReceiver = new ApiJSONReceiver(jsonFuture);
        apiRequestQueue.add(jsonRequest);
        return jsonReceiver;
    }

    public ApiTextReceiver apiString(String url, ApiCallback callback) {
        RequestFuture<String> stringFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, stringFuture, stringFuture);
        ApiTextReceiver stringReceiver = new ApiTextReceiver(stringFuture);
        apiRequestQueue.add(stringRequest);
        return stringReceiver;
    }
}
