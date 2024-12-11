/**
 * Contains the data model classes for the Netflix application.
 * 
 * <p>This package defines the core data entities and business logic of the application.
 * It includes essential data structures and operations for managing movies, such as 
 * retrieving movie information, calculating date differences, and handling specific 
 * exceptions related to movies.</p>
 * 
 * <p>Key classes in this package:</p>
 * <ul>
 *   <li>{@link Movie} - Represents a movie entity with properties such as title, director, release year, and other details.</li>
 *   <li>{@link Model} - Maintains a list of movies and provides functionality for filtering, searching, and analyzing movie data.</li>
 *   <li>{@link InvalidMovieIdException} - Custom exception class for handling errors when movie IDs do not match the expected format.</li>
 *   <li>{@link MovieType} - Enum representing the type of media, distinguishing between movies and TV shows.</li>
 * </ul>
 * 
 * @author Karolina Suska
 * @version 3.1
 */
package pl.polsl.model;
