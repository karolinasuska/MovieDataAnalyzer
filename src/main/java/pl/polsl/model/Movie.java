package pl.polsl.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 
 * Represents a movie in the system with attributes such as title, director, country, 
 * release year, duration, and date when the movie was added to the platform.
 * Also provides functionality to calculate the difference in days between the release 
 * year and the date the movie was added.
 * 
 * @author Karolina Suska
 * @version 2.1
 * 
 */
public class Movie {
    
    /** 
     * Unique identifier for the movie in the system. 
     */
    private final String showId;
    
    private final String type;
    /** 
     * Title of the movie. 
     */
    private final String title;
    /** 
     * Director of the movie. 
     */
    private final String director;
    
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
    
    private final String rating;
    /** 
     * Duration of the movie or number of seasons if it is a series. 
     */
    private final String duration;
    
    private final String listedIn;
    private final String description;
    
    
    
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
    
    public String getListedIn() {
        return listedIn;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * Calculates the difference in days between the movie's release date (January 1 of the release year)
     * and the date it was added to the platform.
     * 
     * <p>If the date cannot be parsed, the method returns {@code Long.MAX_VALUE}.
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
     * Returns a string representation of the movie, including title, director, year, country, 
     * and the difference in days between the release date and the date added.
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
