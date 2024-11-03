package main.java.rentalInfo.service;

import main.java.rentalInfo.dto.RentalStatement;

import java.util.List;

/**
 * Service interface for movie rental information.
 */
public interface RentalInfoService {
    /**
     * Returns active movie rentals for the given customer id.
     *
     * @param customerId customer identification
     * @return {@link RentalStatement}s
     */
    List<RentalStatement> getRentalStatements(final String customerId);
}
