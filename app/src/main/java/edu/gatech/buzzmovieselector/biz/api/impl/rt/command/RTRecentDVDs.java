package edu.gatech.buzzmovieselector.biz.api.impl.rt.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;
import edu.gatech.buzzmovieselector.service.ApiNetwork;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import org.json.JSONObject;

/**
 * Uses the Rotten Tomatoes API to get recent movies
 */
public class RTRecentDVDs implements ApiCommand {
    private final String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?page_limit=10&apikey=" + RTInvoker.API_KEY;

    @Override
    public ApiReceiver<JSONObject> execute(ApiCallback callback) {
        return ApiNetwork.getInstance().apiJSON(url, callback);
    }
}
