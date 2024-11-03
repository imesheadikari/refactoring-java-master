package main.java.rentalInfo.repository;

import main.java.rentalInfo.model.MovieRental;

import java.util.List;

/**
 * Service interface for movie-rental repository.
 */
public interface MovieRentalRepository {
    /**
     * Find all active {@link MovieRental}s for given customer.
     *
     * @param customerId customer id
     * @return {@link MovieRental}s
     */
    List<MovieRental> findRentalsByCustomerId(final String customerId);
}
