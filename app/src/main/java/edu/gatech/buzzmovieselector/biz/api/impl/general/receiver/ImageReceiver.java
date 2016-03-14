package edu.gatech.buzzmovieselector.biz.api.impl.general.receiver;

import android.graphics.Bitmap;
import com.android.volley.toolbox.RequestFuture;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;

/**
 * General API receiver for Image requests
 */
public class ImageReceiver extends ApiReceiver<Bitmap, Bitmap> {

    public ImageReceiver(RequestFuture requestFuture, ApiCallback
            responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public Bitmap getEntity() {
        return getResponse();
    }
}
