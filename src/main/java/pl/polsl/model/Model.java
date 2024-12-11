package pl.polsl.model;

import javax.swing.event.SwingPropertyChangeSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.InputStream;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the Model component of the application, following the MVC design pattern.
 * This class manages movie data, providing functionalities such as loading movie data from a CSV file,
 * retrieving specific movies by ID, and finding the country with the most movies.
 * It also supports property change notifications for GUI updates.
 * 
 * <p>Functionalities include:</p>
 * <ul>
 *   <li>Loading movies from a CSV file.</li>
 *   <li>Retrieving movies by ID.</li>
 *   <li>Calculating release date differences for each movie.</li>
 *   <li>Identifying the country with the most movies.</li>
 * </ul>
 * 
 * @author Karolina Suska
 * @version 3.1
 */
@Getter
@Setter
public class Model {
    /** 
     * Supports property change notifications for listeners in the Swing framework.
     */
    private final SwingPropertyChangeSupport swingPropChangeFirer;
    /** 
     * A list that stores Movie objects managed by the Model.
     */
    @Getter
    private final List<Movie> movies = new ArrayList<>();
    
    /**
     * Constructs a {@code Model} instance, initializes the list of movies, and loads the movie data
     * from a CSV file.
     * 
     * @throws InvalidMovieIdException if an error occurs during loading or parsing the movie data.
     */
    public Model() throws InvalidMovieIdException {
        swingPropChangeFirer = new SwingPropertyChangeSupport(this);
        loadMoviesFromCsv();
    }
    
    /**
     * Loads movie data from a CSV file and populates the {@link movies} list with {@link Movie} objects.
     * The CSV file should contain information about each movie such as ID, type, title, director, cast, 
     * country, date added, release year, rating, duration, listed genres, and description.
     * 
     * <p>If any line in the CSV file is invalid or contains missing data, it will be skipped and an error
     * message will be generated.</p>
     * 
     * @throws InvalidMovieIdException if there is an error reading the CSV file or if any required field is invalid.
     */
    private void loadMoviesFromCsv() throws InvalidMovieIdException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("netflix_titles.csv");
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
         CSVReader csvReader = new CSVReader(inputStreamReader)) {
            
            if (inputStream == null) {
                throw new InvalidMovieIdException("CSV file not found.");
            }
            
            csvReader.readNext();
            String[] row;
            
            while((row = csvReader.readNext()) != null) {
                if(row.length >= 7)
                {
                    String id = row[0].trim();
                    String typeString = row[1].trim();
                    String title = row[2].trim();
                    String director = row[3].trim();
                    String cast = row[4].trim();
                    String country = row[5].trim();
                    String dateAdded = row[6].trim();
                    String releaseYearStr = row[7].trim();
                    String rating = row[8].trim();
                    String duration = row[9].trim();
                    String listedIn = row[10].trim();
                    String description = row[11].trim();
                    
                    MovieType type;
                    try {
                        type = MovieType.valueOf(typeString.toUpperCase().replace(" ", "_"));
                    } catch (IllegalArgumentException e) {
                        type = MovieType.MOVIE;
                    }
                
                    int releaseYear = 0;
                    
                    try {
                        if (!releaseYearStr.isEmpty()) {
                            releaseYear = Integer.parseInt(releaseYearStr);
                        }
                    } catch (NumberFormatException e) {
                        throw new InvalidMovieIdException("Invalid release year: " + releaseYearStr);
                    } 
                    
                    if (duration.isEmpty()) {
                        duration = "N/A"; 
                    }

                    movies.add(new Movie(id, type, title, director, cast, country, dateAdded,
                            releaseYear, rating, duration,listedIn, description));
                }else {
                    throw new InvalidMovieIdException("Skipping invalid line: " + String.join(",", row));
                }
            }
        }catch (IOException | CsvValidationException e) {
            throw new InvalidMovieIdException("Error reading the CSV file: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves a Movie object by its ID.
     *
     * @param id The ID of the movie to be retrieved, expected in the format 's' followed by a number (e.g., 's1').
     * @return The Movie object with the specified ID, or null if no such movie is found.
     * @throws InvalidMovieIdException if the ID format is invalid.
     */
    public Movie getMovieById(String id)  throws InvalidMovieIdException {
        if(!id.matches("^s\\d+$")) {
            throw new InvalidMovieIdException("Invalid movie ID format. Expected format: 's' followed by a number, e.g., 's1'");
        }
        
        for (Movie movie : movies) {
            if (movie.showId().equals(id)) { 
                return movie;
            }
        }
        return null;     
    }
    
    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            return null;
        }
    }
    
    public void sortMoviesByDateAdded(boolean ascending) {
    movies.sort((movie1, movie2) -> {
        LocalDate date1 = parseDate(movie1.dateAdded());
        LocalDate date2 = parseDate(movie2.dateAdded());
        
        if (date1 == null && date2 == null) {
            return 0;
        } else if (date1 == null) {
            return 1;
        } else if (date2 == null) {
            return -1;
        } else {
            return ascending ? date1.compareTo(date2) : date2.compareTo(date1);
        }
    });
}

    
    /**
     * Finds the country with the highest number of movies in the list.
     *
     * @return The country with the most movies; returns "Unknown" if no valid country is found.
     */
    public String getCountryWithMostMovies() {
        return movies.stream()
                .filter(movie -> movie.country() != null && !movie.country().isBlank())
                .collect(Collectors.groupingBy(Movie::country, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");
    }
    
    /**
     * Generates a list of movie titles along with their release date differences.
     *
     * @return A list of strings, each containing a movie title and its release date difference in days.
     */
    public List<String> getMoviesWithReleaseDifferences() {
        return movies.stream()
                .map(movie -> String.format("%s: %d days", movie.title(), movie.getReleaseDateDifference()))
                .collect(Collectors.toList());
    }
}
       