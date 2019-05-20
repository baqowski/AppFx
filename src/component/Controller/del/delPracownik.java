package component.Controller.del;

import java.io.IOException;
import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.Operations;
import component.Hibernate.DAO.PracownikDAO;
import component.Hibernate.Entity.Pracownik;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class delPracownik {

	@FXML
	private ChoiceBox<Pracownik> choiceBox;

	@FXML
	private Button delButton;

	@FXML
	private Button backButton;

	@FXML
	private Label status;

	@FXML
	void backButton(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		Root.getInstance().initRootLayout(stage);

	}

	@FXML
	void delButton(ActionEvent event) {

		try {
			Operations.deleteByID(Pracownik.class, choiceBox.getSelectionModel().getSelectedItem().getId());
			status.setStyle("-fx-text-fill: green;");
			status.setText("Pomyślnie usunięto!");
			initialize();
		} catch (NullPointerException ex) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Nie wybrano rekordu!");
		}

	}

	@FXML
	void choiceBoxKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			System.out.println("Key pressed esc");
			choiceBox.getSelectionModel().clearSelection();
		}
	}

	public void initialize() {
		// TODO Auto-generated method stub
		ObservableList<Pracownik> pracownicy = PracownikDAO.getPracownikList();
		// System.out.println(pracownicy);
		choiceBox.setItems(pracownicy);
	}

}
