package edu.gatech.buzzmovieselector.biz.api;

import android.graphics.Bitmap;

/**
 * Interface that allows API commands to be executed by the API invoker
 */
public interface ApiCommand {
    /**
     * Executes the ApiCommand and returns the result
     * @return result from calling the API
     */
    public ApiReceiver execute(ApiCallback callback);
}
