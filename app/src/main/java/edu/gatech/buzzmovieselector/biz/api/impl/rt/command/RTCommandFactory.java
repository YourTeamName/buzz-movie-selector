package edu.gatech.buzzmovieselector.biz.api.impl.rt.command;

import edu.gatech.buzzmovieselector.biz.api.ApiCommand;
import org.json.JSONObject;

/**
 * Factory for commands for the Rotten tomatoes API
 */
public class RTCommandFactory {

    // cache static commands
    private static ApiCommand recentDVDsCommand = new RTRecentDVDs();
    private static ApiCommand recentMoviesCommand = new RTRecentMovies();

    public static ApiCommand getRecentDVDsCommand() {
        return recentDVDsCommand;
    }

    public static ApiCommand getMovieSearchCommand(String search) {
        return new RTMovieSearch(search);
    }

    public static ApiCommand getRecentMoviesCommand() {
        return recentMoviesCommand;
    }
}
