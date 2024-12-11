package pl.polsl.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a movie in the system, containing attributes such as title, director, country,
 * release year, duration, and the date when the movie was added to the platform.
 * Provides functionality to calculate the difference in days between the release year
 * and the date the movie was added to the platform.
 * 
 * <p>Movies are represented by various attributes such as their ID, type (movie or TV show),
 * cast, rating, and description. This record also includes functionality to calculate the
 * difference in days between the movie's release date and its date of addition to the platform.</p>
 * 
 * @author Karolina
 * @version 3.1
 */
public record Movie (
    /**
     * Unique identifier for the movie in the system. 
     * Example: "s1", "s2", etc.
     *
     * @param showId the unique identifier for the movie.
     */
    String showId,
    /**
     * Type of the content (e.g., movie, series). 
     * This is an enum {@link MovieType} that can either be MOVIE or TV_SHOW.
     *
     * @param type the type of the content (movie or series).
     */
    MovieType type,
    /**
     * Title of the movie.
     * The name by which the movie is identified.
     *
     * @param title the title of the movie.
     */
    String title,
    /**
     * Director of the movie.
     * The person who directed the movie.
     *
     * @param director the director of the movie.
     */
    String director,
    /**
     * Cast of the movie, as a comma-separated list of actor names.
     * This represents the actors featured in the movie.
     *
     * @param cast the cast of the movie.
     */
    String cast,
    /**
     * Country where the movie was produced. 
     * This indicates the country of origin of the movie.
     *
     * @param country the country where the movie was produced.
     */
    String country,
    /**
     * Date the movie was added to the platform, in the format "MMMM d, yyyy".
     * Example: "January 1, 2020".
     *
     * @param dateAdded the date the movie was added to the platform.
     */
    String dateAdded,
    /**
     * Release year of the movie.
     * The year in which the movie was originally released.
     *
     * @param releaseYear the release year of the movie.
     */
    Integer releaseYear,
    /**
     * Age rating of the movie.
     * The rating category (e.g., "PG", "R") given to the movie.
     *
     * @param rating the age rating of the movie.
     */
    String rating,
    /**
     * Duration of the movie or number of seasons if it is a series.
     * This field indicates the movie's length in minutes or the series' season count.
     *
     * @param duration the duration of the movie or the series.
     */
    String duration,
    /**
     * Categories or genres the movie is listed under.
     * This represents the genre(s) the movie falls into (e.g., "Action", "Drama").
     *
     * @param listedIn the categories or genres the movie belongs to.
     */
    String listedIn,
    /**
     * Description or summary of the movie's content.
     * A brief description or summary of the movie.
     *
     * @param description a description or summary of the movie.
     */
    String description){
    
    /**
     * Calculates the difference in days between the movie's release date (January 1 of the release year)
     * and the date it was added to the platform.
     * 
     * <p>If the date cannot be parsed, the method returns {@code Long.MAX_VALUE}.</p>
     *
     * @return the number of days between the release date and the added date,
     *         or {@code Long.MAX_VALUE} if parsing fails.
     */
    public long getReleaseDateDifference() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        LocalDate releaseDate = LocalDate.of(releaseYear, 1, 1);
        LocalDate addedDate;
        try{
            addedDate = LocalDate.parse(dateAdded, formatter);
        } catch(Exception e) {
            System.out.println("Error parsing dateAdded: " + dateAdded);
            return Long.MAX_VALUE;
        }
        return ChronoUnit.DAYS.between(releaseDate, addedDate);
    }
    
    /**
     * Returns a string representation of the movie, including its title, director, release year, country,
     * rating, duration, listed genres, description, and the difference in days between the release date
     * and the date the movie was added to the platform.
     *
     * @return a string describing the movie's main attributes, including release date difference in days.
     */
    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + (releaseYear != null ? releaseYear : "N/A") +
                ", country='" + country + '\'' +
                ", difference=" + getReleaseDateDifference() + " days" +
                ", rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                ", listedIn='" + listedIn + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

