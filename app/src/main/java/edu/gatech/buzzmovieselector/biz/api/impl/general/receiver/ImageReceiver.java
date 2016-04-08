package edu.gatech.buzzmovieselector.biz.api.impl.general.receiver;

import android.graphics.Bitmap;
import com.android.volley.toolbox.RequestFuture;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;

/**
 * General API receiver for Image requests
 */
public class ImageReceiver extends ApiReceiver<Bitmap, Bitmap> {

    /**
     * Default constructor for an image receiver
     * @param requestFuture the request
     * @param responseCallback the callback for the request
     */
    public ImageReceiver(RequestFuture requestFuture, ApiCallback
        responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public Bitmap getEntity() {
        return getResponse();
    }
}
