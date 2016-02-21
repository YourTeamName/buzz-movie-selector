package edu.gatech.buzzmovieselector.service.api;

import android.os.AsyncTask;
import android.util.Log;
import com.android.volley.toolbox.RequestFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

enum ApiResult {
    SUCCESS,
    FAIL,
    NOT_DONE;

    @Override
    public String toString() {
        switch (this) {
            case SUCCESS: return "Success";
            case FAIL: return "Failed";
            case NOT_DONE: return "Not Done";
            default:
                throw new IllegalArgumentException();
        }
    }
}

/**
 * Holds the response of an ApiCommand
 */
abstract public class ApiReceiver {

    private class AsyncFutureTask extends AsyncTask<RequestFuture, Integer, Object> {
        @Override
        protected void onPreExecute() {
            responseStatus = ApiResult.NOT_DONE;
        }

        @Override
        protected Object doInBackground(RequestFuture... params) {
            RequestFuture future = params[0];
            try {
                return future.get(API_MAX_WAIT, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                responseStatus = ApiResult.FAIL;
                e.printStackTrace();
            } catch (ExecutionException e) {
                responseStatus = ApiResult.FAIL;
                e.printStackTrace();
            } catch (TimeoutException e) {
                responseStatus = ApiResult.FAIL;
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
        }
    }

    private class FutureThread implements Runnable {
        @Override
        public void run() {
            AsyncFutureTask futureTask = new AsyncFutureTask();
            try {
                responseStatus = ApiResult.SUCCESS;
                responseData = futureTask.execute(responseFuture).get(API_MAX_WAIT, TimeUnit.SECONDS);
                if (responseCallback != null) {
                    responseCallback.onReceive(getResponse());
                }
                Log.v("run", responseData.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    private static final int API_MAX_WAIT = 5;

    protected ApiResponseType responseType;
    protected RequestFuture responseFuture;
    private volatile Object responseData;
    private ApiCallback responseCallback = null;
    private volatile ApiResult responseStatus = ApiResult.NOT_DONE;

    public ApiReceiver(RequestFuture requestFuture, ApiResponseType responseType) {
        this(requestFuture, responseType, null);
    }

    public ApiReceiver(RequestFuture requestFuture, ApiResponseType responseType, ApiCallback responseCallback) {
        this.responseFuture = requestFuture;
        this.responseType = responseType;
        this.responseCallback = responseCallback;
        startRetrieve();
    }

    /**
     * sets a callback to be executed when the value of the future is retrieved
     * @param callback ApiCallback class to execute
     */
    public void setResponseCallback(ApiCallback callback) {
        responseCallback = callback;
    }

    /**
     * Gets the ApiCommand's response type
     * @return the type of data that was returned
     */
    public ApiResponseType getResponseType() {
        return responseType;
    }

    /**
     * Starts async thread to retrieve value of future
     */
    private void startRetrieve() {
        Thread retrieveThread = new Thread(new FutureThread());
        retrieveThread.start();
    }

    /**
     * Gives the raw response
     * @return raw response object
     */
    protected Object getRawResponse() {
        return responseData;
    }

    /**
     * Gives the correctly casted response
     * @return correctly casted response object
     */
    abstract public Object getResponse();

    public Object getSyncRawResponse() {
        while (responseStatus == ApiResult.NOT_DONE) {
            // Log.v("syncrawresponse", responseStatus.toString());
        }
        Log.v("syncrawresponse2", responseStatus.toString());
        return getRawResponse();
    }


}
