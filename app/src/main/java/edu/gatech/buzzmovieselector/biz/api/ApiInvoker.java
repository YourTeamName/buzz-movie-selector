package edu.gatech.buzzmovieselector.biz.api;

/**
 * Invokes ApiCommands and returns the output
 */
public interface ApiInvoker {
    /**
     * executes a single ApiCommand
     *
     * @param call ApiCommand to be executed
     * @return result of the ApiCommand's execute method
     */
    ApiReceiver executeCall(ApiCall call);
}
