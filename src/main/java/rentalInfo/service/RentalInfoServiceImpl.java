package main.java.rentalInfo.service;

import main.java.rentalInfo.dto.RentalStatement;
import main.java.rentalInfo.model.MovieCategory;
import main.java.rentalInfo.model.MovieRental;
import main.java.rentalInfo.repository.MovieRentalRepository;
import main.java.rentalInfo.service.helpers.RentalStatementTextBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Rental information service implementation.
 */
public class RentalInfoServiceImpl implements RentalInfoService {
    private final MovieRentalRepository repo;
    private final RentalStatementTextBuilder textBuilder;

    public RentalInfoServiceImpl(final MovieRentalRepository repo, final RentalStatementTextBuilder textBuilder) {
        this.repo = repo;
        this.textBuilder = textBuilder;
    }

    @Override
    public List<RentalStatement> getRentalStatements(final String customerId) {
        return repo.findRentalsByCustomerId(customerId).stream()
                .map(this::mapToRentalStatement).collect(Collectors.toList());
    }

    private RentalStatement mapToRentalStatement(final MovieRental movieRental) {
        final List<RentalStatement.RentalRecord> rentalRecords = movieRental.getRentalItems().stream()
                .map(this::mapToMovieRentalRecord).toList();
        final double totalRental = rentalRecords.stream().mapToDouble(RentalStatement.RentalRecord::rental).sum();
        final int frequentPoints = rentalRecords.stream().mapToInt(RentalStatement.RentalRecord::frequentPoints).sum();

        final RentalStatement rentalStatement = new RentalStatement(movieRental.getCustomer().getName(),
                totalRental, frequentPoints, rentalRecords);
        //Sets the default statement-test
        rentalStatement.setRentalStatementDefaultText(textBuilder.buildStatementText(rentalStatement));
        return rentalStatement;
    }

    private RentalStatement.RentalRecord mapToMovieRentalRecord(final MovieRental.RentalItem rentalItem) {
        int frequentPoints = 1;
        if (MovieCategory.NEW.equals(rentalItem.movie().getCategory()) && rentalItem.period() > 2) {
            frequentPoints = 2;
        }

        return new RentalStatement.RentalRecord(rentalItem.movie().getMovieId(), rentalItem.movie().getTitle(),
                rentalItem.period(), rentalItem.movie().getCategory().getRental(rentalItem.period()), frequentPoints);
    }
}
