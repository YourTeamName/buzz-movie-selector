package edu.gatech.buzzmovieselector.biz.api.impl.rt.receiver;

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

    public RTMovieListReceiver(RequestFuture requestFuture, ApiCallback responseCallback) {
        super(requestFuture, responseCallback);
    }

    @Override
    public Movie[] getEntity() {
        ArrayList<Movie> parsedMovies = new ArrayList<>();
        JSONObject moviesList = getResponse();
        try {
            JSONArray movieList = moviesList.getJSONArray("movies");
            for (int i = 0; i < movieList.length(); i++) {
                JSONObject movieJ = movieList.getJSONObject(i);
                double rating = (double) movieJ.getJSONObject("ratings").getInt("audience_score") / 100.;
                Movie movie = new Movie(movieJ.getString("title"), movieJ.getInt("year"), rating);
                parsedMovies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Movie[] movieArray = new Movie[parsedMovies.size()];
        movieArray = (Movie[]) parsedMovies.toArray(movieArray);
        return movieArray;
    }
}
