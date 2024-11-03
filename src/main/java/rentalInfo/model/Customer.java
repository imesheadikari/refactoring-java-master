package main.java.rentalInfo.model;

/**
 * Represents the holder of a movie-rental.
 */
public class Customer {
    private final String name;
    private final String identification;

    public Customer(final String name, final String identification) {
        this.name = name;
        this.identification = identification;
    }

    public String getName() {
        return this.name;
    }

    public String getIdentification() {
        return this.identification;
    }
}
