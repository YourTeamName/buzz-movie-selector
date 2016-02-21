package edu.gatech.buzzmovieselector.service.api.rt.command;

import edu.gatech.buzzmovieselector.service.api.ApiCallback;
import edu.gatech.buzzmovieselector.service.api.ApiCommand;
import edu.gatech.buzzmovieselector.service.api.ApiReceiver;

/**
 * Uses the Rotten Tomatoes API to search for a movie
 */
public class RTMovieSearch implements ApiCommand {

    private String url;

    public RTMovieSearch(String search) {

    }
    public ApiReceiver execute(ApiCallback callback) {

        return null;
    }
}
