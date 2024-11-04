package pl.polsl.model;

/**
 * Custom exception class to handle errors related to invalid movie IDs in the application.
 * This exception is thrown when a movie ID does not follow the expected pattern or format.
 * 
 * <p>For example, this exception can be used in methods that retrieve movies by ID, 
 * ensuring that only properly formatted IDs are processed.</p>
 * 
 * @author Karolina Suska
 * @version 2.1
 */
public class InvalidMovieIdException extends Exception {
    
    /**
     * Constructs an {@code InvalidMovieIdException} with a specified error message.
     * 
     * @param message a detailed message explaining the cause of the exception, 
     *                typically indicating that the movie ID format is invalid 
     *                or not recognized.
     */
    public InvalidMovieIdException(String message) {
        super(message);
    }
}
