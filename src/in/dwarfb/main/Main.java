package in.dwarfb.main;

import in.dwarfb.gui.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point for the inventory management system application.
 * This class initializes the JavaFX application and sets up the login interface.
 */
public class Main extends Application {
    
    /**
     * Main method that launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the primary stage with the login view.
     * This method is called by the JavaFX runtime.
     * 
     * @param primaryStage The main application window
     */
    @Override
    public void start(Stage primaryStage) {
        LoginView lgnView = new LoginView(primaryStage);
        lgnView.setScene();
    }
}