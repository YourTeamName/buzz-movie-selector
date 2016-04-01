package edu.gatech.buzzmovieselector.biz.api;

import android.os.AsyncTask;
import com.android.volley.toolbox.RequestFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

enum ApiResult {
    /**
     * success
     */
    SUCCESS,
    /**
     * fail
     */
    FAIL,
    /**
     * not done
     */
    NOT_DONE;

    @Override
    public String toString() {
        switch (this) {
            case SUCCESS:
                return "Success";
            case FAIL:
                return "Failed";
            case NOT_DONE:
                return "Not Done";
            default:
                throw new IllegalArgumentException();
        }
    }
}

/**
 * Holds the response of an ApiCommand
 */
public abstract class ApiReceiver<T, V> {

    private static final int API_MAX_WAIT = 5;
    private RequestFuture responseFuture;
    private T responseData;
    private ApiCallback responseCallback = null;
    private ApiResult responseStatus = ApiResult.NOT_DONE;

    /**
     * Constructor for a receiver
     *
     * @param requestFuture    The request
     * @param responseCallback The callback for the command
     */
    protected ApiReceiver(RequestFuture requestFuture,
        ApiCallback responseCallback) {
        this.responseFuture = requestFuture;
        this.responseCallback = responseCallback;
        startRetrieve();
    }

    /**
     * sets a callback to be executed when the value of the future is retrieved
     *
     * @param callback ApiCallback class to execute
     */
    public void setResponseCallback(ApiCallback callback) {
        responseCallback = callback;
    }

    /**
     * Gets the ApiReceiver's response status
     *
     * @return the status of the receiver
     */
    public ApiResult getResponseStatus() {
        return responseStatus;
    }

    /**
     * Starts async thread to retrieve value of future
     */
    private void startRetrieve() {
        final Thread retrieveThread = new Thread(new FutureThread());
        retrieveThread.start();
    }

    /**
     * Gives the correctly casted response
     *
     * @return correctly casted response object
     */
    protected T getResponse() {
        return responseData;
    }

    /**
     * Gives the response converted to an Entity
     *
     * @return converted entity object
     */
    public abstract V getEntity();

    private class AsyncFutureTask extends AsyncTask<RequestFuture, Integer,
        Object> {
        @Override
        protected void onPreExecute() {
            responseStatus = ApiResult.NOT_DONE;
        }

        @Override
        protected Object doInBackground(RequestFuture... params) {
            final RequestFuture future = params[0];
            try {
                return future.get(API_MAX_WAIT, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException |
                TimeoutException e) {
                responseStatus = ApiResult.FAIL;
                e.printStackTrace();
            }
            return null;
        }
    }

    private class FutureThread implements Runnable {
        @Override
        public void run() {
            final AsyncFutureTask futureTask = new AsyncFutureTask();
            try {
                responseData = (T) futureTask.execute(responseFuture).get();
                if (responseCallback != null) {
                    responseCallback.onReceive(ApiReceiver.this);
                }
                responseStatus = ApiResult.SUCCESS;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
