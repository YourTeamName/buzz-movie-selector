package edu.gatech.buzzmovieselector.biz.api;

/**
 * Invokes ApiCommands and returns the output
 */
public interface ApiInvoker {
    /**
     * executes a single ApiCommand
     * @param command ApiCommand to be executed
     * @return result of the ApiCommand's execute method
     */
    public ApiReceiver executeCommand(ApiCommand command);
}
