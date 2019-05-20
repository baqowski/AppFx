package component;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class App extends Application {

	private static Stage rootStage;
	private static App instance;
	

	public static App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}

	public  Stage getRootStage() {
		
		return rootStage;
	}

	public void setRootStage(Stage rootStage) {
		App.rootStage = rootStage;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			showLoginPane(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showLoginPane(Stage primaryStage) throws IOException {

		App.rootStage = primaryStage;
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/component/View/logPane.fxml"));
		Scene scene = new Scene(root, 306, 242);
		//scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}