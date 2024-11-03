package main.java.rentalInfo.service.helpers;

import main.java.rentalInfo.dto.RentalStatement;

import java.text.MessageFormat;

/**
 * Default implementation for {@link RentalStatementTextBuilder}.
 */
public class DefaultRentalStatementTextBuilder implements RentalStatementTextBuilder {
    private final static String rentalTextFormat = """
            \t{0}\t{1,number,0.0}""";

    private final static String statementTextFormat = """
            Rental Record for {0}
            {1}
            Amount owed is {2,number,0.0}
            You earned {3} frequent points
            """;

    public DefaultRentalStatementTextBuilder() {
    }

    @Override
    public String buildStatementText(final RentalStatement statement) {
        final String rentalItemsText = statement.getRentedMovies().stream()
                .map(r -> MessageFormat.format(rentalTextFormat, r.movieTitle(), r.rental()))
                .reduce("", (a, b) -> a + b + "\n").stripTrailing();

        return MessageFormat.format(statementTextFormat, statement.getCustomerName(), rentalItemsText,
                statement.getTotalRental(), statement.getTotalFrequentPoints());
    }
}
