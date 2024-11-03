package test.java.rentalInfo.service;

import main.java.rentalInfo.dto.RentalStatement;
import main.java.rentalInfo.model.Customer;
import main.java.rentalInfo.model.Movie;
import main.java.rentalInfo.model.MovieCategory;
import main.java.rentalInfo.model.MovieRental;
import main.java.rentalInfo.repository.MovieRentalRepository;
import main.java.rentalInfo.service.RentalInfoServiceImpl;
import main.java.rentalInfo.service.helpers.RentalStatementTextBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link  RentalInfoServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
public class RentalInfoServiceImplTest {
    @Mock
    private MovieRentalRepository repo;

    @Mock
    private RentalStatementTextBuilder textBuilder;

    private RentalInfoServiceImpl rentalInfoService;

    @Test
    public void testGetRentalStatements() {
        final List<MovieRental> movieRentals = new ArrayList<>();
        final Customer customer = new Customer("Anne", "cus01");
        final Movie movie = new Movie("mov01", "Good Boys", MovieCategory.REGULAR);
        final MovieRental.RentalItem rentalItem = new MovieRental.RentalItem(movie, 2);
        movieRentals.add(new MovieRental(customer, List.of(rentalItem)));

        when(repo.findRentalsByCustomerId("cus01")).thenReturn(movieRentals);
        when(textBuilder.buildStatementText(any())).thenReturn("Statement-Text");
        rentalInfoService = new RentalInfoServiceImpl(repo, textBuilder);

        final List<RentalStatement> rentalStatements = rentalInfoService.getRentalStatements("cus01");

        assertEquals(1, rentalStatements.size());
        assertEquals("Anne", rentalStatements.get(0).getCustomerName());
        assertEquals(2.0, rentalStatements.get(0).getTotalRental());
        assertEquals(1, rentalStatements.get(0).getTotalFrequentPoints());

        final RentalStatement.RentalRecord expected_rentalRecord = new RentalStatement.RentalRecord("mov01",
                "Good Boys", 2, 2, 1);
        assertEquals(1, rentalStatements.get(0).getRentedMovies().size());
        assertEquals(expected_rentalRecord, rentalStatements.get(0).getRentedMovies().get(0));
        assertFalse(rentalStatements.get(0).getRentalStatementDefaultText().isEmpty());
    }

    @Test
    public void testGetRentalStatements_whenNoActiveRentals() {
        when(repo.findRentalsByCustomerId("cus01")).thenReturn(Collections.emptyList());
        rentalInfoService = new RentalInfoServiceImpl(repo, textBuilder);

        assertTrue(rentalInfoService.getRentalStatements("cus01").isEmpty());
    }
}
