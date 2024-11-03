package main.java.rentalInfo.repository;

import main.java.rentalInfo.model.Customer;
import main.java.rentalInfo.model.Movie;
import main.java.rentalInfo.model.MovieCategory;
import main.java.rentalInfo.model.MovieRental;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRentalRepositoryImpl implements MovieRentalRepository {
    private final List<Movie> movies = new ArrayList<>();
    private final List<MovieRental> movieRentals = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    {
        movies.add(new Movie("F001", "You've Got Mail", MovieCategory.REGULAR));
        movies.add(new Movie("F002", "Matrix", MovieCategory.REGULAR));
        movies.add(new Movie("F003", "Cars", MovieCategory.CHILDREN));
        movies.add(new Movie("F004", "Fast & Furious X", MovieCategory.NEW));

        customers.add(new Customer("C. U. Stomer", "CUS01"));

        final MovieRental movieRental_1 = new MovieRental(customers.get(0),
                Arrays.asList(
                        new MovieRental.RentalItem(movies.get(0), 3),
                        new MovieRental.RentalItem(movies.get(1), 1)
                ));

        movieRentals.add(movieRental_1);
    }

    @Override
    public List<MovieRental> findRentalsByCustomerId(final String customerId) {
        return movieRentals.stream().filter(r -> r.getCustomer().getIdentification().equals(customerId))
                .collect(Collectors.toList());
    }
}
