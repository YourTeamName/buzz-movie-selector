package edu.gatech.buzzmovieselector.biz.api.impl.general.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.impl.general.receiver.JSONReceiver;
import edu.gatech.buzzmovieselector.service.ApiNetwork;

/**
 * General API command for JSON requests
 */
public class JSONCommand implements ApiCommand {

    private String url;

    /**
     * Default constructor for a json command
     * @param url the url for the command
     */
    public JSONCommand(String url) {
        this.url = url;
    }

    @Override
    public JSONReceiver execute(ApiCallback callback) {
        return new JSONReceiver(ApiNetwork.getInstance().apiJSON(url),
            callback);
    }
}
