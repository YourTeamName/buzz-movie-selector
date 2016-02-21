package edu.gatech.buzzmovieselector.biz.api.impl.rt.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import org.json.JSONObject;

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

    public static ApiCommand getRecentMoviesCommand() {
        return new RTRecentMovies();
    }
}
