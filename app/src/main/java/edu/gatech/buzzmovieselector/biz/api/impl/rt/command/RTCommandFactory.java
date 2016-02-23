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

    /**
     * Produces the ApiCommand for recent DVDs
     * @return instance of RTRecentDVDs
     */
    public static ApiCommand getRecentDVDsCommand() {
        return recentDVDsCommand;
    }

    /**
     * Produces the ApiCommand for searching movies
     * @param search
     * @return instance of RTMovieSearch
     */
    public static ApiCommand getMovieSearchCommand(String search) {
        return new RTMovieSearch(search);
    }

    /**
     * Produces the ApiCommand for recent Movies
     * @return instance of RTRecentMovies
     */
    public static ApiCommand getRecentMoviesCommand() {
        return recentMoviesCommand;
    }
}
