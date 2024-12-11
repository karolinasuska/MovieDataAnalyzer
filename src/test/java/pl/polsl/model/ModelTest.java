package pl.polsl.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
/**
 *
 * @author Karolina
 * @version 4.0
 */
public class ModelTest {
    
    @Test
    public void testGetMovieById() {
        try {
            Model model = new Model();
            Movie movie = model.getMovieById("s1");
            assertNotNull(movie);
            assertEquals("Dick Johnson Is Dead", movie.title());
        } catch (InvalidMovieIdException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testGetCountryWithMostMovies() {
        try {
            Model model = new Model();  // Może zgłosić wyjątek
            String mostMoviesCountry = model.getCountryWithMostMovies();
            assertEquals("United States", mostMoviesCountry);
        } catch (InvalidMovieIdException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testGetMoviesWithReleaseDifferences() {
        try {
            Model model = new Model();
            List<String> releaseDifferences = model.getMoviesWithReleaseDifferences();
            assertTrue(!releaseDifferences.isEmpty());
        } catch (InvalidMovieIdException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    
}
