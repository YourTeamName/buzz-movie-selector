package edu.gatech.buzzmovieselector.biz.api.impl.general.receiver;

import com.android.volley.toolbox.RequestFuture;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;
import org.json.JSONObject;

/**
 * General API receiver for JSON requests
 */
public class JSONReceiver extends ApiReceiver<JSONObject, JSONObject> {

    public JSONReceiver(RequestFuture requestFuture, ApiCallback
            responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public JSONObject getEntity() {
        return getResponse();
    }
}
