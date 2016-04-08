package edu.gatech.buzzmovieselector.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import edu.gatech.buzzmovieselector.dao.MovieDao;
import edu.gatech.buzzmovieselector.entity.Movie;

import java.sql.SQLException;

public class MovieDaoImpl extends BaseDaoImpl<Movie, Integer>
    implements MovieDao {

    /**
     * Constructor for movie dao that must be defined
     * @param connectionSource the connection source
     * @throws SQLException a database exception
     */
    public MovieDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Movie.class);
    }

}
