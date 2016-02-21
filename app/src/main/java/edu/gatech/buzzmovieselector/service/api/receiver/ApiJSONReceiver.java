package edu.gatech.buzzmovieselector.service.api.receiver;

import com.android.volley.toolbox.RequestFuture;
import edu.gatech.buzzmovieselector.service.api.ApiReceiver;
import edu.gatech.buzzmovieselector.service.api.ApiResponseType;
import org.json.JSONObject;

/**
 * ApiReceiver for JSON objects
 */
public class ApiJSONReceiver extends ApiReceiver{

    public ApiJSONReceiver(RequestFuture<JSONObject> responseFuture) {
        super(responseFuture, ApiResponseType.JSON);
    }

    @Override
    public JSONObject getResponse() {
        return (JSONObject) getRawResponse();
    }
}
