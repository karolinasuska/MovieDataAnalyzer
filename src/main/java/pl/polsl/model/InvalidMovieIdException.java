package pl.polsl.model;

/**
 * 
 * Custom exception class to handle errors related to invalid movie IDs in the application.
 * This exception is thrown when an invalid movie ID format is encountered, for example,
 * when the ID does not follow the expected pattern or format.
 * 
 * <p>For instance, this exception might be used in methods that retrieve movies
 * by ID, ensuring that only properly formatted IDs are processed.</p>
 * 
 * @author Karolina Suska
 * @version 1.2
 * 
 */
public class InvalidMovieIdException extends Exception {
    
    /**
     * Constructs an {@code InvalidMovieIdException} with a specified error message.
     * 
     * @param message the detail message explaining the cause of the exception, typically
     *                indicating that the movie ID format is invalid or not recognized.
     */
    public InvalidMovieIdException(String message) {
        super(message);
    }
}
