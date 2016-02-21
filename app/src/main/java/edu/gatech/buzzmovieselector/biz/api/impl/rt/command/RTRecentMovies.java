package edu.gatech.buzzmovieselector.biz.api.impl.rt.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;

/**
 * Uses the Rotten Tomatoes API to get recent movies
 */
public class RTRecentMovies implements ApiCommand {
    @Override
    public ApiReceiver execute(ApiCallback callback) {
        return null;
    }
}
