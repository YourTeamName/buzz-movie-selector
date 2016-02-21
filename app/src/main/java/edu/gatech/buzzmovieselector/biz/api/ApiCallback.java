package edu.gatech.buzzmovieselector.biz.api;

/**
 * Async callback for ApiReceiver
 */
public interface ApiCallback<T> {
    public void onReceive(ApiReceiver<T> receiver);
}
