package main.java.rentalInfo.dto;

import java.util.List;

/**
 * A statement indicating the movie-rental.
 */
public class RentalStatement {
    private final String customerName;
    private final double totalRental;
    private final int totalFrequentPoints;
    private final List<RentalRecord> rentedMovies;
    private String rentalStatementDefaultText;

    /**
     * Represents an item of the rental.
     *
     * @param movieId        movie id
     * @param movieTitle     movie title
     * @param rentalPeriod   period of rental for this item
     * @param rental         rental to be paid
     * @param frequentPoints bonus points earned for this item
     */
    public record RentalRecord(String movieId, String movieTitle, int rentalPeriod, double rental,
                               int frequentPoints) {
    }

    public RentalStatement(final String customerName, final double totalRental, final int totalFrequentPoints,
                           final List<RentalRecord> rentedMovies) {
        this.customerName = customerName;
        this.totalRental = totalRental;
        this.totalFrequentPoints = totalFrequentPoints;
        this.rentedMovies = rentedMovies;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public double getTotalRental() {
        return this.totalRental;
    }

    public int getTotalFrequentPoints() {
        return this.totalFrequentPoints;
    }

    public List<RentalRecord> getRentedMovies() {
        return this.rentedMovies;
    }

    public String getRentalStatementDefaultText() {
        return this.rentalStatementDefaultText;
    }

    /**
     * Returns the default String presentation of this rental statement.
     *
     * @param rentalStatementDefaultText rental statement as a String
     */
    public void setRentalStatementDefaultText(final String rentalStatementDefaultText) {
        this.rentalStatementDefaultText = rentalStatementDefaultText;
    }
}
