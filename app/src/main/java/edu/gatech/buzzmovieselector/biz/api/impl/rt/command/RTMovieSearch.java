package edu.gatech.buzzmovieselector.biz.api.impl.rt.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.service.ApiNetwork;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * Uses the Rotten Tomatoes API to search for a movie
 */
public class RTMovieSearch implements ApiCommand {

    private String url;

    public RTMovieSearch(String search) {
        String query = URLEncoder.encode(search);
        url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q=" + query + "&page_limit=10&apikey=" + RTInvoker.API_KEY;
    }
    public ApiReceiver<JSONObject> execute(ApiCallback callback) {
        return ApiNetwork.getInstance().apiJSON(url, callback);
    }
}
