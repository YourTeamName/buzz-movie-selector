package edu.gatech.buzzmovieselector.biz.api.impl.general.receiver;

import com.android.volley.toolbox.RequestFuture;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;

/**
 * General API receiver for Text requests
 */
public class TextReceiver extends ApiReceiver<String, String> {

    /**
     * Text receiver command
     * @param requestFuture the request
     * @param responseCallback callback for the receiver
     */
    public TextReceiver(RequestFuture requestFuture, ApiCallback
        responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public String getEntity() {
        return getResponse();
    }
}
