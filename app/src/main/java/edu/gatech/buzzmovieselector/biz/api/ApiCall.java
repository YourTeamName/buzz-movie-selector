package edu.gatech.buzzmovieselector.biz.api;

/**
 * ApiCall pairs a command with a callback
 */
public class ApiCall {
    private ApiCommand apiCommand;
    private ApiCallback apiCallback;

    public ApiCall(ApiCommand command) {
        this(command, null);
    }

    public ApiCall(ApiCommand command, ApiCallback callback) {
        this.apiCommand = command;
        this.apiCallback = callback;
    }

    public ApiCommand getApiCommand() {
        return apiCommand;
    }

    public ApiCallback getApiCallback() {
        return apiCallback;
    }
}
