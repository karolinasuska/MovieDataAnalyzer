package pl.polsl.model;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
 * @version 2.1
 */
public class Model {
    /** 
     * Supports property change notifications for listeners in the Swing framework.
     */
    private final SwingPropertyChangeSupport swingPropChangeFirer;
    /** 
     * A list that stores Movie objects managed by the Model.
     */
    private final List<Movie> movies;
    
    /**
     * Constructs a Model instance, initializing the list of movies
     * and loading data from the default CSV file.
     */
    public Model(){
        swingPropChangeFirer = new SwingPropertyChangeSupport(this);
        movies = new ArrayList<>();
        loadMoviesFromCsv();
    }
    
    /**
     * Loads movie data from a CSV file and populates the movies list.
     * Each line in the CSV file should contain fields for ID, type, title, director,
     * cast, country, date added, release year, rating, duration, listed genres, and description.
     * 
     * <p>If any fields are missing or contain incorrect data, the line will be skipped with an error message.</p>
     */
    private void loadMoviesFromCsv() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("netflix_titles.csv");
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
         CSVReader csvReader = new CSVReader(inputStreamReader)) {
            
            if (inputStream == null) {
                System.err.println("CSV file not found.");
                return;
            }
            
            csvReader.readNext();
            String[] row;
            
            while((row = csvReader.readNext()) != null) {
                if(row.length >= 7)
                {
                    String id = row[0].trim();
                    String type = row[1].trim();
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
                    
                    int releaseYear = 0;
                    
                    try {
                        if (!releaseYearStr.isEmpty()) {
                            releaseYear = Integer.parseInt(releaseYearStr);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing release year: " + e.getMessage());
                    } 
                    
                    if (duration.isEmpty()) {
                        duration = "N/A"; // lub null, w zależności od potrzeb
                    }

                    movies.add(new Movie(id, type, title, director, cast, country, dateAdded,
                            releaseYear, rating, duration,listedIn, description));
                }else {
                    System.err.println("Skipping invalid line: " + String.join(",", row));
                }
            }
        }catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }catch (NumberFormatException e) {
            System.err.println("Error parsing release year or duration: " + e.getMessage());
        }catch (CsvValidationException e) {
            System.err.println("CSV validation error: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves the list of movies.
     *
     * @return A list of Movie objects.
     */
    public List<Movie> getMovies() {
        return movies;
    }
    
    /**
     * Adds a PropertyChangeListener to the model to listen for property changes.
     *
     * @param prop The PropertyChangeListener to be added.
     */
    public void addListener(PropertyChangeListener prop){
        swingPropChangeFirer.addPropertyChangeListener(prop);
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
            if (movie.getId().equals(id)) { 
                return movie;
            }
        }
        return null;     
    }
    
    /**
     * Finds the country with the highest number of movies in the list.
     *
     * @return The country with the most movies; returns "Unknown" if no valid country is found.
     */
    public String getCountryWithMostMovies() {
        Map<String, Integer> countryCount = new HashMap<>();

        for (Movie movie : movies) { 
            String country = movie.getCountry();

            if (country != null && !country.equals("Unknown") && !country.trim().isEmpty()) {
                countryCount.put(country, countryCount.getOrDefault(country, 0) + 1);
            }
        }

        String mostFrequentCountry = "Unknown";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : countryCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentCountry = entry.getKey();
            }
        }

        return mostFrequentCountry;
    }
    
    /**
     * Generates a list of movie titles along with their release date differences.
     *
     * @return A list of strings, each containing a movie title and its release date difference in days.
     */
    public List<String> getMoviesWithReleaseDifferences() {
        List<String> moviesWithDifferences = new ArrayList<>();
        for (Movie movie : movies) {
            moviesWithDifferences.add(movie.getTitle() + ": " + movie.getReleaseDateDifference() + " days");
        }
        return moviesWithDifferences;
    }
}
       
    