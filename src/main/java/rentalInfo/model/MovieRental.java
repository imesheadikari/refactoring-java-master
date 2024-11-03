package main.java.rentalInfo.model;

import java.util.List;

/**
 * Represent a renting of {@link Movie}s.
 */
public class MovieRental {
    public record RentalItem(Movie movie, int period) {}

    private final Customer customer;
    private final List<RentalItem> rentalItems;

    public MovieRental(final Customer customer, final List<RentalItem> rentalItems) {
        this.customer = customer;
        this.rentalItems = rentalItems;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public List<RentalItem> getRentalItems() {
        return this.rentalItems;
    }
}
