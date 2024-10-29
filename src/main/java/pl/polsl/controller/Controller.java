package pl.polsl.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import pl.polsl.view.*;
import pl.polsl.model.*;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * Manages the interactions between the {@link Model} and {@link View} classes.
 * This controller initializes the application, handles user input, and updates 
 * the view based on model data.
 * 
 * <p>Responsibilities include:
 * <ul>
 *   <li>Setting up the initial view state based on command-line arguments, if provided.</li>
 *   <li>Handling events triggered by the view's buttons to display the country with the most movies 
 *       and calculate the date difference for specific movies.</li>
 * </ul>
 * 
 * @author Karolina Suska
 * @version 1.2
 * 
 */
public class Controller  {
    
    /** The model instance containing application data and business logic. */
    private Model model;
    /** The view instance responsible for displaying the GUI. */
    private View view;
    
    /**
     * Constructs a {@code Controller} instance, initializes the model and view, and handles initial setup.
     * If a movie ID is provided as a command-line argument, attempts to retrieve the movie and calculate 
     * the difference in days between its release date and the date added.
     * 
     * @param args an array of command-line arguments, where the first argument is a movie ID.
     */
    public Controller(String[] args) {
        this.model = new Model();
        this.view = new View(model);
        this.viewEvent();
        
        if (args.length > 0) {
            String movieId = args[0];
            view.setMovieIdInput(movieId);
            handleMovieIdInput(movieId);
            
        } else {
            JOptionPane.showMessageDialog(view.getFrame(), "No Movie ID provided.");
        }
    }
    
    /**
     * Sets up event handling for buttons within the view.
     * <ul>
     *   <li>Configures the {@code showCountryButton} to display the country with the most movies
     *       when clicked.</li>
     *   <li>Configures the {@code calculateDateDiffButton} to calculate the release date difference
     *       based on the entered movie ID.</li>
     * </ul>
     * <p>If the movie ID entered is invalid or empty, displays an error message in a dialog box.
     */
    private void viewEvent(){
       view.showCountryButton.setAction(new AbstractAction("Show Country") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String countryWithMostMovies = model.getCountryWithMostMovies();
                view.updateCountryLabel(countryWithMostMovies);
            }
        });
        
        view.calculateDateDiffButton.addActionListener(arg0 -> {
            String movieId = view.getMovieIdInput();
            handleMovieIdInput(movieId);
        });
        
        setKeyboardShortcuts();
    }
    
    
    
    private void handleMovieIdInput(String movieId) {
        if (movieId.isEmpty()) {
            view.showErrorDialog("Movie ID cannot be empty.");
            return;
        }
        try {
            Movie movie = model.getMovieById(movieId);

            if (movie != null) {
                long difference = movie.getReleaseDateDifference();
                view.setDifferenceArea("Difference in days: " + difference + " days.");
            } else {
                view.setDifferenceArea("Movie not found!");
            }
        } catch (InvalidMovieIdException e) {
            view.showErrorDialog(e.getMessage());
        } catch (Exception e) {
            view.showErrorDialog("An unexpected error occurred: " + e.getMessage());
        }
    }
    
    private void setKeyboardShortcuts() {
        view.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_DOWN_MASK), "showCountry");
    
    view.getFrame().getRootPane().getActionMap().put("showCountry", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String countryWithMostMovies = model.getCountryWithMostMovies();
            view.updateCountryLabel(countryWithMostMovies);
        }
    });

    // Rejestracja skrótu klawiszowego Alt + D dla przycisku calculateDateDiffButton
    view.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_DOWN_MASK), "calculateDateDiff");
    
    view.getFrame().getRootPane().getActionMap().put("calculateDateDiff", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String movieId = view.getMovieIdInput();
            handleMovieIdInput(movieId);
        }
    });
    }
}