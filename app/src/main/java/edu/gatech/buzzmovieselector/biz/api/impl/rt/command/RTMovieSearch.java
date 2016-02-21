package edu.gatech.buzzmovieselector.biz.api.impl.rt.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;

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
