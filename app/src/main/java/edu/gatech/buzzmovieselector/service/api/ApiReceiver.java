package edu.gatech.buzzmovieselector.service.api;

import com.android.volley.toolbox.RequestFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Holds the response of an ApiCommand
 */
abstract public class ApiReceiver {
    private static int API_MAX_WAIT = 5;

    protected ApiResponseType responseType;
    protected RequestFuture responseFuture;

    public ApiReceiver(RequestFuture requestFuture, ApiResponseType responseType) {
        this.responseFuture = requestFuture;
        this.responseType = responseType;
    }

    /**
     * Gets the ApiCommand's response type
     * @return the type of data that was returned
     */
    public ApiResponseType getResponseType() {
        return responseType;
    }

    /**
     * Gives the raw response
     * @return raw response object
     */
    protected Object getRawResponse() {
        try {
            return responseFuture.get(API_MAX_WAIT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gives the correctly casted response
     * @return correctly casted response object
     */
    abstract public Object getResponse();
}
