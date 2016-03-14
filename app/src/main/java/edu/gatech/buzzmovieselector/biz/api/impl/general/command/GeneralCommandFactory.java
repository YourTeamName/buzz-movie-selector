package edu.gatech.buzzmovieselector.biz.api.impl.general.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCommand;

/**
 * Factory for general API commands
 */
public class GeneralCommandFactory {

    /**
     * Gives the API command for a general image request
     *
     * @param url request url
     * @return ImageCommand with url parameter
     */
    public static ApiCommand getImageCommand(String url) {
        return new ImageCommand(url);
    }

    /**
     * Gives the API command for a general JSON request
     *
     * @param url request url
     * @return JSONCommand with url parameter
     */
    public static ApiCommand getJSONCommand(String url) {
        return new JSONCommand(url);
    }

    /**
     * Gives the API command for a general text request
     *
     * @param url request url
     * @return TextCommand with url parameter
     */
    public static ApiCommand getTextCommand(String url) {
        return new TextCommand(url);
    }
}
