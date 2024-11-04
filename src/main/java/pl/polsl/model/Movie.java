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
 * @author Karolina Suska
 * @version 2.1
 */
public class Movie {
    
    /** 
     * Unique identifier for the movie in the system. 
     */
    private final String showId;
    /** 
     * Type of the content (e.g., movie, series). 
     */
    private final String type;
    /** 
     * Title of the movie. 
     */
    private final String title;
    /** 
     * Director of the movie. 
     */
    private final String director;
    /** 
     * Cast of the movie, as a comma-separated list of actor names. 
     */
    private final String cast;
    /** 
     * Country where the movie was produced. 
     */
    private final String country;
    /** 
     * Date the movie was added to the platform, in the format "MMMM d, yyyy". 
     */
    private final String dateAdded;
    /** 
     * Release year of the movie. 
     */
    private final Integer releaseYear;
    /** 
     * Age rating of the movie. 
     */
    private final String rating;
    /** 
     * Duration of the movie or number of seasons if it is a series. 
     */
    private final String duration;
    /** 
     * Categories or genres the movie is listed under. 
     */
    private final String listedIn;
    /** Description or summary of the movie's content. */
    private final String description;
    
    
    /**
     * Constructs a Movie instance with the specified attributes.
     * 
     * @param showId Unique identifier of the movie.
     * @param type Type of the content (e.g., movie, series).
     * @param title Title of the movie.
     * @param director Director of the movie.
     * @param cast Cast of the movie.
     * @param country Production country of the movie.
     * @param dateAdded Date when the movie was added to the platform.
     * @param releaseYear Release year of the movie.
     * @param rating Age rating of the movie.
     * @param duration Duration of the movie or number of seasons if a series.
     * @param listedIn Categories the movie is listed under.
     * @param description Summary of the movie's content.
     */
    public Movie(String showId, String type, String title, String director, String cast, String country,
                String dateAdded, Integer releaseYear, String rating,  String duration, String listedIn, String description) {
       
        this.showId = showId;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.dateAdded = dateAdded;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.duration = duration;
        this.listedIn = listedIn;
        this.description = description;
    }
    
    /**
     * Retrieves the unique identifier of the movie.
     *
     * @return the movie's unique identifier.
     */
    public String getId() {
        return showId;
    }
    /**
     * Retrieves the type of the movie (e.g., movie or series).
     *
     * @return the type of the movie.
     */
    public String getType() {
        return type;
    }
    /**
     * Retrieves the title of the movie.
     *
     * @return the movie's title.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Retrieves the director of the movie.
     *
     * @return the movie's director.
     */
    public String getDirector() {
        return director;
    }
    /**
     * Retrieves the cast of the movie.
     *
     * @return the cast of the movie.
     */
    public String getCast() {
        return cast;
    }
    
    /**
     * Retrieves the country where the movie was produced.
     *
     * @return the production country of the movie.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Retrieves the date when the movie was added to the platform.
     *
     * @return the date the movie was added, in the format "MMMM d, yyyy".
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     * Retrieves the release year of the movie.
     *
     * @return the release year of the movie.
     */
    public int getReleaseYear() {
        return releaseYear;
    }
    /**
     * Retrieves the age rating of the movie.
     *
     * @return the age rating of the movie.
     */
    public String getRating() {
        return rating;
    }
    /**
     * Retrieves the duration of the movie or the number of seasons if it is a series.
     *
     * @return the duration or number of seasons of the movie/series.
     */
    public String getDuration() {
        return duration;
    }
    /**
     * Retrieves the categories or genres under which the movie is listed.
     *
     * @return the categories or genres of the movie.
     */
    public String getListedIn() {
        return listedIn;
    }
    /**
     * Retrieves the description or summary of the movie's content.
     *
     * @return the description of the movie.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Calculates the difference in days between the movie's release date (January 1 of the release year)
     * and the date it was added to the platform.
     * 
     * <p>If the date cannot be parsed, the method returns {@code Long.MAX_VALUE}.</p>
     *
     * @return the number of days between the release date and the added date, or {@code Long.MAX_VALUE} if parsing fails.
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
     * Returns a string representation of the movie, including title, director, release year, country,
     * rating, duration, and a description. Also includes the difference in days between the release date
     * and the date the movie was added to the platform.
     *
     * @return a string describing the movie's main attributes.
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
