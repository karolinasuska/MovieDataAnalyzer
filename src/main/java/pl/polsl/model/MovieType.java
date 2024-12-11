package pl.polsl.model;

/**
 * Enum representing the types of media in the application, specifically distinguishing between 
 * movies and TV shows. This enum is used to categorize the type of content stored in the system.
 * 
 * <p>Each value represents a type of media:</p>
 * <ul>
 *   <li>{@link #MOVIE} - Represents a movie.</li>
 *   <li>{@link #TV_SHOW} - Represents a TV show.</li>
 * </ul>
 * 
 * @author Karolina Suska
 * @version 3.1
 */
public enum MovieType{
    /** 
     * Represents a movie. 
     */
    MOVIE("Movie"),
    /** 
     * Represents a TV show. 
     */
    TV_SHOW("TV Show");
    
    /** 
     * The name of the media type. 
     */
    private final String typeName;
    
    /**
     * Constructs a {@code MovieType} with the specified name.
     * 
     * @param typeName the name of the media type (e.g., "Movie" or "TV Show").
     */
    MovieType(String typeName){
        this.typeName = typeName;
    }
    
    /**
     * Retrieves the name of the media type.
     * 
     * @return the name of the media type (e.g., "Movie" or "TV Show").
     */
    public String getTypeName(){
        return typeName;
    }
}