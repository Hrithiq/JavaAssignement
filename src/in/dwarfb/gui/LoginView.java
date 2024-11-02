package in.dwarfb.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import in.dwarfb.inventory.Inventory;
import in.dwarfb.user.User;
import in.dwarfb.user.UserHandler;
import in.dwarfb.user.UserType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class LoginView {
    private Scene scene;
    private Stage stage;
    private Inventory inventory;
    private Notification notification;
    private UserHandler userHandler;
    public LoginView(Stage primaryStage){
        stage = primaryStage;
        userHandler = new UserHandler();
        userHandler.setDummyData();
        notification = new Notification();
        inventory = new Inventory(notification);
        inventory.addDummyData();
    }
    public LoginView(Stage primaryStage, Inventory inventory, Notification notification){
        stage = primaryStage;
        userHandler = new UserHandler();
        userHandler.setDummyData();
        this.notification = notification;
        this.inventory = inventory;
    }
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
            public void handle(ActionEvent event) {
                String username = userField.getText();
                String password = passField.getText();
                User user = userHandler.authenticateUser(username,password);
                if (user == null) {
                    messageLabel.setText("Please enter your username.");
                } else {
                    if(user.getUserType() == UserType.CUSTOMER){
                        UserView uV = new UserView(stage, inventory, notification);
                        uV.setScene();
                    }else if(user.getUserType() == UserType.MANAGER){
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
