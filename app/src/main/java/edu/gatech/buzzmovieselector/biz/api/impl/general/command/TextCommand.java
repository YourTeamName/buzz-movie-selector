package edu.gatech.buzzmovieselector.biz.api.impl.general.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.impl.general.receiver.TextReceiver;
import edu.gatech.buzzmovieselector.service.ApiNetwork;

/**
 * General API command for Text requests
 */
public class TextCommand implements ApiCommand {

    private String url;

    public TextCommand(String url) {
        this.url = url;
    }

    @Override
    public TextReceiver execute(ApiCallback callback) {
        return new TextReceiver(ApiNetwork.getInstance().apiString(url), callback);
    }
}
