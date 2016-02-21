package edu.gatech.buzzmovieselector.biz.api.impl.rt;

import edu.gatech.buzzmovieselector.biz.api.ApiCall;
import edu.gatech.buzzmovieselector.biz.api.ApiInvoker;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;

/**
 * Invokes commands that target the Rotten Tomatoes API
 */
public class RTInvoker implements ApiInvoker {
    // Rotten tomatoes API key
    public static final String API_KEY = "yedukp76ffytfuy24zsqk7f5";

    @Override
    public ApiReceiver executeCall(ApiCall call) {
        return call.getApiCommand().execute(call.getApiCallback());
    }
}
