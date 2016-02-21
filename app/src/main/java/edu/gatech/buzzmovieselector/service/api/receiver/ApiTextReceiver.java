package edu.gatech.buzzmovieselector.service.api.receiver;

import com.android.volley.toolbox.RequestFuture;
import edu.gatech.buzzmovieselector.service.api.ApiReceiver;
import edu.gatech.buzzmovieselector.service.api.ApiResponseType;

/**
 * ApiReceiver for JSON objects
 */
public class ApiTextReceiver extends ApiReceiver{

    public ApiTextReceiver(RequestFuture<String> responseFuture) {
        super(responseFuture, ApiResponseType.TEXT);
    }

    @Override
    public String getResponse() {
        return (String) getRawResponse();
    }
}
