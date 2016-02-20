package edu.gatech.buzzmovieselector.service.api;

/**
 * Interface that allows API commands to be executed by the API invoker
 */
public interface ApiCommand {
    /**
     * Executes the ApiCommand and returns the result
     * @return result from calling the API
     */
    public ApiReceiver execute();
}
