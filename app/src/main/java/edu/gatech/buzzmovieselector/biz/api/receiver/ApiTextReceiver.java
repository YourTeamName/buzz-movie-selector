package edu.gatech.buzzmovieselector.biz.api.receiver;

import com.android.volley.toolbox.RequestFuture;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;
import edu.gatech.buzzmovieselector.biz.api.ApiResponseType;

/**
 * ApiReceiver for JSON objects
 */
public class ApiTextReceiver extends ApiReceiver{

    public ApiTextReceiver(RequestFuture<String> responseFuture) {
        super(responseFuture, ApiResponseType.TEXT);
    }
    public ApiTextReceiver(RequestFuture<String> responseFuture, ApiCallback callback) {
        super(responseFuture, ApiResponseType.TEXT, callback);
    }

    @Override
    public String getResponse() {
        return (String) getRawResponse();
    }
}
