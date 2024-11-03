package main.java.rentalInfo.model;

public class Movie {
    private final String movieId;
    private final String title;
    private final MovieCategory category;

    public Movie(final String movieId, final String title, final MovieCategory category) {
        this.movieId = movieId;
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return this.title;
    }

    public MovieCategory getCategory() {
        return this.category;
    }

    public String getMovieId() {
        return this.movieId;
    }
}
