package edu.gatech.buzzmovieselector.biz.api.impl.general.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import edu.gatech.buzzmovieselector.biz.api.impl.general.receiver.ImageReceiver;
import edu.gatech.buzzmovieselector.service.ApiNetwork;

/**
 * General API command for Image requests
 */
public class ImageCommand implements ApiCommand {

    private String url;

    public ImageCommand(String url) {
        this.url = url;
    }

    @Override
    public ImageReceiver execute(ApiCallback callback) {
        return new ImageReceiver(ApiNetwork.getInstance().apiImage(url),
                callback);
    }
}
