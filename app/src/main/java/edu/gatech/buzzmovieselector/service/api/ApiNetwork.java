package edu.gatech.buzzmovieselector.service.api;

import android.content.Context;
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

    public ApiReceiver getApiJSON(String url) {
        RequestFuture<JSONObject> jsonFuture = RequestFuture.newFuture();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, jsonFuture, jsonFuture);
        ApiReceiver jsonReceiver = new ApiJSONReceiver(jsonFuture);
        apiRequestQueue.add(jsonRequest);
        return jsonReceiver;
    }

    public ApiReceiver getApiString(String url) {
        RequestFuture<String> stringFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, stringFuture, stringFuture);
        ApiReceiver stringReceiver = new ApiTextReceiver(stringFuture);
        apiRequestQueue.add(stringRequest);
        return stringReceiver;
    }
}
