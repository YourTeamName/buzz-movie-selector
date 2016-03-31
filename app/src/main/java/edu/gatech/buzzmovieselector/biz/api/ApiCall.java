package edu.gatech.buzzmovieselector.biz.api;

/**
 * ApiCall pairs a command with a callback
 */
public class ApiCall {
    private ApiCommand apiCommand;
    private ApiCallback apiCallback;

    /**
     * Constructor for a call
     *
     * @param command Command for call
     */
    public ApiCall(ApiCommand command) {
        this(command, null);
    }

    /**
     * Constructor for call
     *
     * @param command  Command for the call
     * @param callback Callback for the call
     */
    public ApiCall(ApiCommand command, ApiCallback callback) {
        this.apiCommand = command;
        this.apiCallback = callback;
    }

    /**
     * Getter for the command
     *
     * @return the command
     */
    public ApiCommand getApiCommand() {
        return apiCommand;
    }

    /**
     * Getter for the callback
     *
     * @return the callback
     */
    public ApiCallback getApiCallback() {
        return apiCallback;
    }
}
