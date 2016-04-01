package edu.gatech.buzzmovieselector.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import edu.gatech.buzzmovieselector.dao.ReviewDao;
import edu.gatech.buzzmovieselector.entity.Review;

import java.sql.SQLException;

public class ReviewDaoImpl extends BaseDaoImpl<Review, Integer>
    implements ReviewDao {

    // this constructor must be defined
    public ReviewDaoImpl(ConnectionSource connectionSource)
        throws SQLException {
        super(connectionSource, Review.class);
    }

}
