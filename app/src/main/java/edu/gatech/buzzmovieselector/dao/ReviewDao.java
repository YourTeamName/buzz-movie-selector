package edu.gatech.buzzmovieselector.dao;

import com.j256.ormlite.dao.Dao;
import edu.gatech.buzzmovieselector.entity.Review;

/**
 * Review DAO which has an Integer id (review.id)
 */
public interface ReviewDao extends Dao<Review, Integer> {
}
