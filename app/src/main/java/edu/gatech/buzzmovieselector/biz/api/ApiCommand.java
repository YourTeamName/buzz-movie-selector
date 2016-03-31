package edu.gatech.buzzmovieselector.biz.api;

/**
 * Interface that allows API commands to be executed by the API invoker
 */
public interface ApiCommand {
    /**
     * Executes the ApiCommand and returns the result
     * @param callback the callback for the command
     * @return result from calling the API
     */
    ApiReceiver execute(ApiCallback callback);
}
