package edu.gatech.buzzmovieselector.dao;

import com.j256.ormlite.dao.Dao;
import edu.gatech.buzzmovieselector.entity.Movie;

/**
 * Movie DAO which has an Integer id (movie.id)
 */
public interface MovieDao extends Dao<Movie, Integer> {
}
