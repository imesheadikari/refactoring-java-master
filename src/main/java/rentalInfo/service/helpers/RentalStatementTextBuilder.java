package main.java.rentalInfo.service.helpers;

import main.java.rentalInfo.dto.RentalStatement;

/**
 * {@link RentalStatement} text builder.
 */
public interface RentalStatementTextBuilder {
    /**
     * Build String presentation of a given {@link RentalStatement}
     *
     * @param statement {@link RentalStatement}
     * @return statement as a String
     */
    String buildStatementText(final RentalStatement statement);
}
