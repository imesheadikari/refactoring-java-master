package main.java;

import main.java.rentalInfo.repository.MovieRentalRepositoryImpl;
import main.java.rentalInfo.service.RentalInfoService;
import main.java.rentalInfo.service.RentalInfoServiceImpl;
import main.java.rentalInfo.service.helpers.DefaultRentalStatementTextBuilder;

public class Main {
  private static final RentalInfoService rentalInfoService = new RentalInfoServiceImpl(new MovieRentalRepositoryImpl(),
          new DefaultRentalStatementTextBuilder());

  public static void main(String[] args) {
    final String result = rentalInfoService.getRentalStatements("CUS01").get(0).getRentalStatementDefaultText();
    final String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    if (!result.equals(expected)) {
      throw new AssertionError("Expected: " + System.lineSeparator() + String.format(expected) + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
    }

    System.out.println("Success");
  }
}
