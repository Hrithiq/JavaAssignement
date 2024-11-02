package in.dwarfb.main;

import in.dwarfb.gui.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main
 */
public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) {
        LoginView lgnView = new LoginView(primaryStage);
        lgnView.setScene();
	}
}
