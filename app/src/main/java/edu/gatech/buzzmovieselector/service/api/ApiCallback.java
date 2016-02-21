package edu.gatech.buzzmovieselector.service.api;

/**
 * Async callback for ApiReceiver
 */
public interface ApiCallback {
    public void onReceive(Object result);
}
