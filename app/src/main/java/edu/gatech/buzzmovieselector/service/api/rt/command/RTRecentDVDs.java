package edu.gatech.buzzmovieselector.service.api.rt.command;

import edu.gatech.buzzmovieselector.service.api.ApiCallback;
import edu.gatech.buzzmovieselector.service.api.ApiCommand;
import edu.gatech.buzzmovieselector.service.api.ApiNetwork;
import edu.gatech.buzzmovieselector.service.api.receiver.ApiJSONReceiver;
import edu.gatech.buzzmovieselector.service.api.rt.RTInvoker;

/**
 * Uses the Rotten Tomatoes API to get recent movies
 */
public class RTRecentDVDs implements ApiCommand {
    private final String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?page_limit=1&" + RTInvoker.API_KEY;

    @Override
    public ApiJSONReceiver execute(ApiCallback callback) {
        return ApiNetwork.getInstance().apiJSON(url, callback);
    }
}
