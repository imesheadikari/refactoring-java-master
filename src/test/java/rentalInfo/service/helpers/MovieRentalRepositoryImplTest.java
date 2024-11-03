package test.java.rentalInfo.service.helpers;

import main.java.rentalInfo.dto.RentalStatement;
import main.java.rentalInfo.service.helpers.DefaultRentalStatementTextBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link DefaultRentalStatementTextBuilder}.
 */
public class MovieRentalRepositoryImplTest {
    private final DefaultRentalStatementTextBuilder textBuilder = new DefaultRentalStatementTextBuilder();

    @Test
    public void testBuildStatementText() {
        final RentalStatement.RentalRecord rentalRecord_1 = new RentalStatement.RentalRecord("mov01",
                "Matrix", 2, 10, 1);
        final RentalStatement.RentalRecord rentalRecord_2 = new RentalStatement.RentalRecord("mov02",
                "Good Boys", 3, 6, 0);
        final RentalStatement rentalStatement = new RentalStatement("Anne", 16, 1,
                Arrays.asList(rentalRecord_1, rentalRecord_2));
        final String expected = "Rental Record for Anne\n\tMatrix	10.0\n\tGood Boys	6.0\nAmount owed is 16.0\nYou earned 1 frequent points\n";

        assertEquals(expected, textBuilder.buildStatementText(rentalStatement));
    }

    @Test
    public void testBuildStatementText_withNoRentalRecords() {
        final RentalStatement rentalStatement = new RentalStatement("Anne", 0, 0,
                Collections.emptyList());
        final String expected = "Rental Record for Anne\n\nAmount owed is 0.0\nYou earned 0 frequent points\n";

        assertEquals(expected, textBuilder.buildStatementText(rentalStatement));
    }
}
