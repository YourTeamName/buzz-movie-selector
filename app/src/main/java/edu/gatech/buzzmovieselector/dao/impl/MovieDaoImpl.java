package edu.gatech.buzzmovieselector.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import edu.gatech.buzzmovieselector.dao.MovieDao;
import edu.gatech.buzzmovieselector.entity.Movie;

import java.sql.SQLException;

public class MovieDaoImpl extends BaseDaoImpl<Movie, Integer>
    implements MovieDao {

    // this constructor must be defined
    public MovieDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Movie.class);
    }

}
