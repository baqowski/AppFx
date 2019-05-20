package component.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import component.App;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login implements Initializable {

	private final String login = "root";
	private final String haslo = "root";
	// private final BooleanProperty pressedProperty;

	final KeyCombination altP = new KeyCodeCombination(KeyCode.P, KeyCombination.ALT_DOWN);

	@FXML
	private ImageView loginView;

	@FXML
	private Label label;

	@FXML
	private TextField loginField;

	@FXML
	private Button loginButton;

	@FXML
	private PasswordField pswdField;

	 @FXML
	    void loginMouseEntered(MouseEvent event) {
		 System.out.println("mouse entered");
		 loginView.setOpacity(0.6);
	    }
	 
	  @FXML
	    void loginMouseExited(MouseEvent event) {
		  loginView.setOpacity(1);
	    }

	@FXML
	void loginViewClicked(MouseEvent event) throws IOException, InstantiationException, IllegalAccessException {

		if ((loginField.getText().contains(login)) && (pswdField.getText().contains(haslo))) {

			label.setStyle("-fx-text-fill: green;");
			label.setText("Logowanie...");
			// KeyEvent = new KeyEvent(null, haslo, haslo, null, false, false, false,
			// false);
			// enterPressed();
			Stage stage = App.getInstance().getRootStage();
			BorderPane root = (BorderPane) FXMLLoader
					.load(getClass().getResource("/component/View/rootLayout.fxml"));
			Scene scene = new Scene(root);
			
			//stage.setFullScreenExitKeyCombination(altP);
			//stage.setFullScreen(true);
			stage.setX(0);
			stage.setY(0);
			stage.setScene(scene);
			stage.show();
		} else {
			label.setStyle("-fx-text-fill: red;");
			label.setText("Błędny login lub hasło");
			System.out.println("Błąd logowania");
		}

	}

	private void installEventHandler(final Node keyNode) {
		// handler for enter key press / release events, other keys are
		// handled by the parent (keyboard) node handler
		final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					if ((loginField.getText().contains(login)) && (pswdField.getText().contains(haslo))) {
						label.setStyle("-fx-text-fill: green;");
						label.setText("Logowanie...");

						Stage stage = App.getInstance().getRootStage();
						
						BorderPane root;
						Scene scene = null;
						try {
							root = (BorderPane) FXMLLoader
									.load(getClass().getResource("/baq/myApp/JavaFx/View/RootLayout.fxml"));
							scene = new Scene(root);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						stage.setScene(scene);
						stage.setX(0);
						stage.setY(0);
						stage.show();
					} else {
						label.setStyle("-fx-text-fill: red;");
						label.setText("Błędny login lub hasło");
					}
					keyEvent.consume();
				}
			}
		};

		// keyNode.setOnKeyPressed(keyEventHandler);
		// keyNode.setOnKeyReleased(keyEventHandler);
	}

	@FXML
	void keyPressed(KeyEvent event) {
		System.out.println("tekst");
		loginButton.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				System.out.println("Enter");
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	
	}
}
