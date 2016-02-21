package edu.gatech.buzzmovieselector.service.api.rt.command;

import edu.gatech.buzzmovieselector.service.api.ApiCommand;

/**
 * Factory for commands for the Rotten tomatoes API
 */
public class RTCommandFactory {
    public static ApiCommand getRecentDVDsCommand() {
        return new RTRecentDVDs();
    }

    public static ApiCommand getMovieSearchCommand(String search) {
        return new RTMovieSearch(search);
    }
}
