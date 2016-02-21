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

    /**
     * Gets the ApiNetwork instance. This must be called before getInstance()
     * @param context Android context to use
     * @return the ApiNetwork object
     */
    public static ApiNetwork getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ApiNetwork(context);
        }
        return ourInstance;
    }

    /**
     * Gets the ApiNetwork instance
     * @return the ApiNetwork object
     */
    public static ApiNetwork getInstance() {
        return ourInstance;
    }

    /**
     * Gives the Android Volley RequestQueue
     * @return the singleton's RequestQueue instance
     */
    public RequestQueue getApiRequestQueue() {
        if (apiRequestQueue == null) {
            apiRequestQueue = Volley.newRequestQueue(apiContext.getApplicationContext());
        }
        return apiRequestQueue;
    }

    /**
     * Private constructor for Singleton
     * @param context Android application context
     */
    private ApiNetwork(Context context) {
        apiContext = context;
        apiRequestQueue = getApiRequestQueue();
    }

    /**
     * Hits an Api expecting a JSON object
     * @param url Api endpoint
     * @param callback Callback to execute when finished
     * @return ApiReceiver object with JSONObject response
     */
    public ApiReceiver<JSONObject> apiJSON(String url, ApiCallback callback) {
        RequestFuture<JSONObject> jsonFuture = RequestFuture.newFuture();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, jsonFuture, jsonFuture);
        ApiReceiver<JSONObject> jsonReceiver = new ApiReceiver<>(jsonFuture, callback);
        apiRequestQueue.add(jsonRequest);
        return jsonReceiver;
    }

    /**
     * Hits an Api expecting a string
     * @param url Api endpoint
     * @param callback Callback to execute when finished
     * @return ApiReceiver object with String response
     */
    public ApiReceiver<String> apiString(String url, ApiCallback callback) {
        RequestFuture<String> stringFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, stringFuture, stringFuture);
        ApiReceiver<String> stringReceiver = new ApiReceiver<>(stringFuture, callback);
        apiRequestQueue.add(stringRequest);
        return stringReceiver;
    }
}
