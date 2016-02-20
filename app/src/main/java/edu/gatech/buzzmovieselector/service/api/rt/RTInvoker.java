package edu.gatech.buzzmovieselector.service.api.rt;

import edu.gatech.buzzmovieselector.service.api.ApiCommand;
import edu.gatech.buzzmovieselector.service.api.ApiInvoker;
import edu.gatech.buzzmovieselector.service.api.ApiReceiver;

/**
 * Invokes commands that target the Rotten Tomatoes API
 */
public class RTInvoker implements ApiInvoker {
    // Rotten tomatoes API key
    public static final String API_KEY = "yedukp76ffytfuy24zsqk7f5";

    @Override
    public ApiReceiver executeCommand(ApiCommand command) {
        return command.execute();
    }
}
