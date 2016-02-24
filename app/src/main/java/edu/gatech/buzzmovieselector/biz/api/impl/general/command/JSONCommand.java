package edu.gatech.buzzmovieselector.biz.api.impl.general.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.impl.general.receiver.JSONReceiver;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.RTInvoker;
import edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver.RTMovieListReceiver;
import edu.gatech.buzzmovieselector.service.ApiNetwork;

import java.net.URLEncoder;

/**
 * General API command for JSON requests
 */
public class JSONCommand implements ApiCommand {

    private String url;

    public JSONCommand(String url) {
        this.url = url;
    }

    @Override
    public JSONReceiver execute(ApiCallback callback) {
        return new JSONReceiver(ApiNetwork.getInstance().apiJSON(url), callback);
    }
}
