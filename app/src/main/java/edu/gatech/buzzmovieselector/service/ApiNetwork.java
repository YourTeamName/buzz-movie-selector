package edu.gatech.buzzmovieselector.service;

import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;
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

    public ApiReceiver<JSONObject> apiJSON(String url, ApiCallback callback) {
        RequestFuture<JSONObject> jsonFuture = RequestFuture.newFuture();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, jsonFuture, jsonFuture);
        ApiReceiver<JSONObject> jsonReceiver = new ApiReceiver<>(jsonFuture, callback);
        apiRequestQueue.add(jsonRequest);
        return jsonReceiver;
    }

    public ApiReceiver<String> apiString(String url, ApiCallback callback) {
        RequestFuture<String> stringFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, stringFuture, stringFuture);
        ApiReceiver<String> stringReceiver = new ApiReceiver<>(stringFuture, callback);
        apiRequestQueue.add(stringRequest);
        return stringReceiver;
    }
}
