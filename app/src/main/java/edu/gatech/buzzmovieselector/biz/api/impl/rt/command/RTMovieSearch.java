package edu.gatech.buzzmovieselector.biz.api.impl.rt.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver
    .RTMovieListReceiver;
import edu.gatech.buzzmovieselector.service.ApiNetwork;

import java.net.URLEncoder;

/**
 * Uses the Rotten Tomatoes API to search for a movie
 */
public class RTMovieSearch implements ApiCommand {

    private String url;

    /**
     * Constructor for movie search
     * @param search the string to search for
     */
    public RTMovieSearch(String search) {
        @SuppressWarnings("deprecation") final String query = URLEncoder.encode(search);
        url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="
            + query + "&page_limit=10&apikey=" + RTInvoker.API_KEY;
    }

    @Override
    public RTMovieListReceiver execute(ApiCallback callback) {
        return new RTMovieListReceiver(ApiNetwork.getInstance().apiJSON(url),
            callback);
    }
}
