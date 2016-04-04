package edu.gatech.buzzmovieselector.biz.api.impl.rt.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver
    .RTMovieListReceiver;
import edu.gatech.buzzmovieselector.service.ApiNetwork;

/**
 * Uses the Rotten Tomatoes API to get recent movies
 */
public class RTRecentMovies implements ApiCommand {

    @Override
    public RTMovieListReceiver execute(ApiCallback callback) {
        String url = "http://api.rottentomatoes.com/api/public/v1" +
            ".0/lists/movies/in_theaters.json?page_limit=10&apikey=" +
            RTInvoker.API_KEY;
        return new RTMovieListReceiver(ApiNetwork.getInstance().apiJSON(url),
            callback);
    }
}
