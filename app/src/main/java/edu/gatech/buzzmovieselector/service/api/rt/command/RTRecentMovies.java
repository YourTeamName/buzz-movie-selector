package edu.gatech.buzzmovieselector.service.api.rt.command;

import edu.gatech.buzzmovieselector.service.api.ApiCallback;
import edu.gatech.buzzmovieselector.service.api.ApiCommand;
import edu.gatech.buzzmovieselector.service.api.ApiReceiver;

/**
 * Uses the Rotten Tomatoes API to get recent movies
 */
public class RTRecentMovies implements ApiCommand {
    @Override
    public ApiReceiver execute(ApiCallback callback) {
        return null;
    }
}
