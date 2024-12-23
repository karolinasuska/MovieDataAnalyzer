package pl.polsl.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import pl.polsl.model.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Represents the graphical user interface for the Netflix Analyzer application.
 * Provides controls to interact with the data model and display information
 * such as movies and calculated release date differences.
 * 
 * <p>This class is responsible for:</p>
 * <ul>
 *   <li>Setting up and displaying the main application frame.</li>
 *   <li>Providing a table for displaying movie information and various controls for user interaction.</li>
 *   <li>Handling the visual representation of user input and output fields.</li>
 * </ul>
 * 
 * @author Karolina Suska
 * @version 3.1
 */
public class View {
    /** 
     * Main application frame for displaying the Netflix Analyzer interface.
     */
    private JFrame frame;
    /** 
     * Panel containing control buttons and input fields. 
     */
    private JPanel controlPanel;
    /** 
     * The data model providing movie information.
     */
    private Model model;
    /** 
     * Button to trigger the display of the country with the most movies. 
     */
    public JButton showCountryButton;
    /** 
     * Label displaying the country with the most movies. 
     */
    public JLabel countryLabel;
    /** 
     * Table displaying the list of movies. 
     */
    public JTable movieTable; 
    /** 
     * Table model used to populate movie information in movieTable. 
     */
    public DefaultTableModel tableModel;
    /** 
     * Text area displaying calculated release date differences. 
     */
    public JTextArea differenceArea;
    /** 
     * Text field to input movie ID for calculations.
     */
    private JTextField movieIdInput; 
    /** 
     * Button to calculate and display the release date difference for a selected movie. 
     */
    public JButton calculateDateDiffButton; 
    
    public JButton sortByDateButton;
    
    public JComboBox<String> sortOrderComboBox; 
    
    /**
     * Constructs a View for the Netflix Analyzer application, initializing the GUI components.
     *
     * @param model the data model containing movie information.
     */
    public View(Model model) {
        this.model = model;
        prepareGUI();
        displayMovies();
    }
    
    /**
     * Retrieves the main application frame.
     *
     * @return the main JFrame of the application.
     */
    public JFrame getFrame() {
        return frame;
    }
    
    /**
     * Updates the country label with the specified country name.
     *
     * @param country the name of the country to display in the country label.
     */
    public void updateCountryLabel(String country) {
        this.countryLabel.setText("Country with most movies: " + country);
    }
    
    /**
     * Retrieves the movie ID input provided by the user.
     *
     * @return the text from the movie ID input field.
     */
    public String getMovieIdInput() {
        return movieIdInput.getText(); 
    }
    
    /**
     * Sets the movie ID input field with a specified movie ID.
     *
     * @param movieId the movie ID to set in the input field.
     */
    public void setMovieIdInput(String movieId) {
        movieIdInput.setText(movieId);
    }

    /**
     * Sets the text content in the difference area to display calculated differences.
     *
     * @param text the text to display in the difference area.
     */
    public void setDifferenceArea(String text) {
        differenceArea.setText(text); 
    }
    
    /**
     * Initializes and sets up the graphical user interface (GUI) for the application.
     * This includes creating the main application frame, configuring panels, setting layouts,
     * and adding components like buttons, labels, tables, and text areas.
     * 
     * <p>The layout is organized as follows:</p>
     * <ul>
     *   <li>A {@code BorderLayout} for the main frame, dividing the GUI into center, south, and east sections.</li>
     *   <li>A {@code GridBagLayout} for the control panel to allow flexible positioning of input fields,
     *       labels, and buttons.</li>
     * </ul>
     * 
     * <p>Components added to the GUI include:</p>
     * <ul>
     *   <li>A table for displaying movie data in the center of the frame, with columns for movie attributes.</li>
     *   <li>A control panel at the bottom containing buttons, labels, and input fields for user interactions.</li>
     *   <li>A text area on the right side of the frame, displaying calculated release date differences.</li>
     * </ul>
     * 
     * <p>Insets are set to create spacing between components within the control panel, and a titled border is used for
     * the text area to enhance readability.</p>
     */
    private void prepareGUI(){
        
        frame = new JFrame("Netflix Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 700));
        frame.setLayout(new BorderLayout());
        
        
        controlPanel = new JPanel(); 
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
        String[] columnNames = {"Show ID", "Title", "Director", "Country", "Date Added", "Release Year", "Duration"};
        tableModel = new DefaultTableModel(columnNames, 0);
        movieTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(movieTable); 
        

        showCountryButton = new JButton("Show Country");
        showCountryButton.setMnemonic(KeyEvent.VK_C);
        showCountryButton.setToolTipText("Click to show the country with the most movies.(Alt + C)");
        showCountryButton.getAccessibleContext().setAccessibleDescription("Button to display the country with the most movies.");
        
        
        countryLabel = new JLabel("Country with most movies: ");
        countryLabel.getAccessibleContext().setAccessibleDescription("Displays the country with the most movies based on the dataset.");

       
        movieIdInput = new JTextField(10); 
        movieIdInput.setToolTipText("Enter the Movie ID to calculate the release date difference.");
        movieIdInput.getAccessibleContext().setAccessibleDescription("Text field for entering the Movie ID to calculate the release date difference.");
        
        
        calculateDateDiffButton = new JButton("Calculate Date Difference"); 
        calculateDateDiffButton.setMnemonic(KeyEvent.VK_D);
        calculateDateDiffButton.setToolTipText("Click to calculate the release date difference for the entered Movie ID. (Alt + D)");
        calculateDateDiffButton.getAccessibleContext().setAccessibleDescription("Button to calculate the release date difference.");
    
        
        differenceArea = new JTextArea(3, 30); 
        differenceArea.setEditable(false);
        differenceArea.setBorder(BorderFactory.createTitledBorder("Release Date Differences"));
        differenceArea.getAccessibleContext().setAccessibleDescription("Displays calculated release date differences for movies.");
        
        movieTable.setToolTipText("Movie data table. Use the table to view movie details.");
        movieTable.getAccessibleContext().setAccessibleDescription("Table displaying movie data including show ID, title, director, country, date added, release year, and duration.");
  
         // Sort by Date button configuration
        sortByDateButton = new JButton("Sort by Date Added");
        sortByDateButton.setMnemonic(KeyEvent.VK_S);
        sortByDateButton.setToolTipText("Click to sort movies by date added. (Alt + S)");
        sortByDateButton.getAccessibleContext().setAccessibleDescription("Button to sort movies by date added.");
        
        String[] sortOrderOptions = {"Ascending", "Descending"};
        sortOrderComboBox = new JComboBox<>(sortOrderOptions);
        
        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new GridBagLayout());
        sortPanel.setBorder(BorderFactory.createTitledBorder("Sort by Date Added")); // Dodanie ramki tytułowej
        
        GridBagConstraints sortGbc = new GridBagConstraints();
        sortGbc.fill = GridBagConstraints.HORIZONTAL;
        sortGbc.insets = new Insets(5, 5, 5, 5);

        
        sortGbc.gridx = 0;
        sortGbc.gridy = 0;
        sortPanel.add(sortByDateButton, sortGbc);
    
        sortGbc.gridx = 1;
        sortPanel.add(sortOrderComboBox, sortGbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(showCountryButton, gbc);
        
        gbc.gridx = 1;
        controlPanel.add(countryLabel, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        controlPanel.add(sortPanel, gbc);  // Dodanie panelu z ramką do głównego panelu
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(new JLabel("Enter Movie ID: "), gbc);
        
        gbc.gridx = 1;
        controlPanel.add(movieIdInput, gbc);

        gbc.gridx = 2;
        controlPanel.add(calculateDateDiffButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2; // Ustawić differenceArea pod kontrolkami
        gbc.gridwidth = 3; // Rozciągnąć na wszystkie kolumny
        controlPanel.add(differenceArea, gbc);
        
        
        frame.add(scrollPane, BorderLayout.CENTER); 
        frame.add(controlPanel, BorderLayout.SOUTH); 
        
        frame.setVisible(true);
    }
    
    
    /**
     * Populates the movie table with movie data from the model.
     */
    public void displayMovies() {
        tableModel.setRowCount(0); 
        for (Movie movie : model.getMovies()) {
            Object[] rowData = {
                movie.showId(),
                movie.title(),
                movie.director(),
                movie.country(),
                movie.dateAdded(),
                movie.releaseYear(),
                movie.duration()
            };
            tableModel.addRow(rowData); 
        }
    }
    
    /**
     * Displays release date differences for movies in the difference area.
     */
    public void displayReleaseDateDifferences() {
        differenceArea.setText(""); 
        for (String movieDifference : model.getMoviesWithReleaseDifferences()) {
            differenceArea.append(movieDifference + "\n"); 
        }
    }
    
    /**
     * Shows an error dialog with the specified message.
     *
     * @param message the error message to display in the dialog box.
     */
    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
   
}
