package edu.gatech.buzzmovieselector.biz.api;

/**
 * Async callback for ApiReceiver
 */
public interface ApiCallback<T extends ApiReceiver> {
    /**
     * onReceive is executed as the callback is called
     *
     * @param receiver ApiReceiver object executing the callback
     */
    void onReceive(T receiver);
}
