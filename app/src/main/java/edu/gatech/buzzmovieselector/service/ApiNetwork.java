package edu.gatech.buzzmovieselector.service;

import android.content.Context;
import android.graphics.Bitmap;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.*;
import org.json.JSONObject;

/**
 * Provides Api networking functionality
 */
public final class ApiNetwork {

    private static ApiNetwork ourInstance = null;
    private static Context apiContext;
    private RequestQueue apiRequestQueue;

    /**
     * Private constructor for Singleton
     *
     * @param context Android application context
     */
    private ApiNetwork(Context context) {
        apiContext = context;
        apiRequestQueue = getApiRequestQueue();
    }

    /**
     * Gets the ApiNetwork instance. This must be called before getInstance()
     *
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
     *
     * @return the ApiNetwork object
     */
    public static ApiNetwork getInstance() {
        return ourInstance;
    }

    /**
     * Gives the Android Volley RequestQueue
     *
     * @return the singleton's RequestQueue instance
     */
    public RequestQueue getApiRequestQueue() {
        if (apiRequestQueue == null) {
            apiRequestQueue = Volley.newRequestQueue(apiContext
                    .getApplicationContext());
        }
        return apiRequestQueue;
    }

    /**
     * Hits an Api expecting a JSON object
     *
     * @param url Api endpoint
     * @return RequestFuture object with JSONObject response
     */
    public RequestFuture<JSONObject> apiJSON(String url) {
        final RequestFuture<JSONObject> jsonFuture = RequestFuture.newFuture();
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method
                .GET, url, jsonFuture, jsonFuture);
        apiRequestQueue.add(jsonRequest);
        return jsonFuture;
    }

    /**
     * Hits an Api expecting a string
     *
     * @param url Api endpoint
     * @return RequestFuture object with String response
     */
    public RequestFuture<String> apiString(String url) {
        final RequestFuture<String> stringFuture = RequestFuture.newFuture();
        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, stringFuture, stringFuture);
        apiRequestQueue.add(stringRequest);
        return stringFuture;
    }

    /**
     * Hits an api expecting a bitmap
     *
     * @param url api endpoint
     * @return object with bitmap response
     */
    public RequestFuture<Bitmap> apiImage(String url) {
        final RequestFuture<Bitmap> bmpFuture = RequestFuture.newFuture();
        final ImageRequest imageRequest = new ImageRequest(url, bmpFuture, 0, 0,
                null, bmpFuture);
        apiRequestQueue.add(imageRequest);
        return bmpFuture;
    }
}
