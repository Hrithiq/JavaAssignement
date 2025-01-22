/**
 * Represents the login view of the application.
 * This class manages the login interface and authentication process,
 * providing different views based on user types (Customer or Manager).
 */
package in.dwarfb.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import in.dwarfb.inventory.Inventory;
import in.dwarfb.user.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoginView {
    /** The JavaFX Scene object representing the login view */
    private Scene scene;
    /** The primary stage of the application */
    private Stage stage;
    /** Reference to the application's inventory system */
    private Inventory inventory;
    /** Handles notification display and management */
    private Notification notification;
    /** Handles user authentication and management */
    private UserHandler userHandler;

    /**
     * Constructs a LoginView with dummy data for testing.
     * 
     * @param primaryStage the primary stage of the JavaFX application
     */
    public LoginView(Stage primaryStage) {
        stage = primaryStage;
        userHandler = new UserHandler();
        userHandler.setDummyData();
        notification = new Notification();
        inventory = new Inventory(notification);
        inventory.addDummyData();
    }

    /**
     * Constructs a LoginView with existing inventory and notification systems.
     * 
     * @param primaryStage the primary stage of the JavaFX application
     * @param inventory the existing inventory system to use
     * @param notification the existing notification system to use
     */
    public LoginView(Stage primaryStage, Inventory inventory, Notification notification) {
        stage = primaryStage;
        userHandler = new UserHandler();
        userHandler.setDummyData();
        this.notification = notification;
        this.inventory = inventory;
    }

    /**
     * Sets up and displays the login scene.
     * Creates a form with username and password fields, and handles
     * authentication and navigation to appropriate views based on user type.
     */
    public void setScene() {
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button signInButton = new Button("Sign In");
        Label messageLabel = new Label();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.add(signInButton, 1, 2);
        gridPane.add(messageLabel, 0, 2);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Handles the sign-in button click event.
             * Authenticates user credentials and navigates to appropriate view.
             * 
             * @param event the action event triggered by the sign-in button
             */
            public void handle(ActionEvent event) {
                String username = userField.getText();
                String password = passField.getText();
                User user = userHandler.authenticateUser(username, password);
                if (user == null) {
                    messageLabel.setText("Please enter valid credentials");
                } else {
                    if (user.getUserType() == UserType.CUSTOMER) {
                        UserView uV = new UserView(stage, (Customer) user, inventory, notification);
                        uV.setScene();
                    } else if (user.getUserType() == UserType.MANAGER) {
                        ManagerView mV = new ManagerView(stage, inventory, notification);
                        System.out.println("Manager Login notification: " + notification.asArrayList().size());
                        mV.setScene();
                    }
                }
            }
        });

        scene = new Scene(gridPane, 300, 200);
        stage.setTitle("LOGIN PAGE");
        stage.setScene(scene);
        stage.show();
    }
}