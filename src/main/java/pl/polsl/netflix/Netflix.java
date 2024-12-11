package pl.polsl.netflix;

import pl.polsl.controller.*;

/**
 * The main entry point for the Netflix application.
 * This class initializes the Controller, which manages the interaction
 * between the model and the view, following the MVC design pattern.
 * 
 * <p>The application allows users to explore movies, view details such as 
 * release dates, and determine the country with the most movies available.</p>
 * 
 * <p>Command-line arguments can be used to perform specific operations on 
 * movies, such as retrieving details based on a movie ID.</p>
 * 
 * @author Karolina Suska
 * @version 3.1
 */
public class Netflix {
    /**
     * The main method that starts the Netflix application.
     * It creates an instance of the Controller, passing any command-line
     * arguments to it for processing.
     * 
     * @param args Command-line arguments passed to the application.
     *             The first argument can be the movie ID which the
     *             application will use to perform operations related to movies.
     *             If no movie ID is provided, the application will start without 
     *             any specific operations.
     */
        public static void main(String[] args) {
        Controller controller = new Controller(args);
    }
}
