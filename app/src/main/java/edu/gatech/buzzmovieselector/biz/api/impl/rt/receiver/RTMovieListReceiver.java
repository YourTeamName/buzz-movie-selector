package edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver;

import android.util.Log;
import com.android.volley.toolbox.RequestFuture;
import edu.gatech.buzzmovieselector.biz.api.ApiCallback;
import edu.gatech.buzzmovieselector.biz.api.ApiReceiver;
import edu.gatech.buzzmovieselector.entity.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Receives json objects from ApiCalls and makes them into Movie objects
 */
public class RTMovieListReceiver extends ApiReceiver<JSONObject, Movie[]> {
    public static final int MAXIMUM_SCORE = 100;

    /**
     * Default constructor
     *
     * @param requestFuture    the request
     * @param responseCallback callback for the receiver
     */
    public RTMovieListReceiver(RequestFuture requestFuture, ApiCallback
        responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public Movie[] getEntity() {
        final ArrayList<Movie> parsedMovies = new ArrayList<>();
        final JSONObject moviesList = getResponse();
        try {
            final JSONArray movieList = moviesList.getJSONArray("movies");
            for (int i = 0; i < movieList.length(); i++) {
                final JSONObject movieJ = movieList.getJSONObject(i);
                final double rating = (double) movieJ.getJSONObject("ratings")
                    .getInt("audience_score") / MAXIMUM_SCORE;
                final String thumbUrl = movieJ.getJSONObject("posters")
                    .getString("thumbnail");
                final Movie movie = new Movie(movieJ.getInt("id"), movieJ
                    .getString("title"), movieJ.getInt("year"), rating,
                    thumbUrl);
                parsedMovies.add(movie);
            }
        } catch (JSONException e) {
            Log.e("RTMovieListReceiver Error", "Can't get JSONObject", e);
        }
        Movie[] movieArray = new Movie[parsedMovies.size()];
        movieArray = parsedMovies.toArray(movieArray);
        return movieArray;
    }
}
