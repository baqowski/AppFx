package component.Controller.update;

import java.io.IOException;

import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.PracownikDAO;
import component.Hibernate.Entity.Pracownik;
import component.Hibernate.HibernateUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class updatePracownik extends HibernateUtils {

	@FXML
	private ChoiceBox<Pracownik> choiceBox;

	@FXML
	private TextField imieField;

	@FXML
	private TextField nazwiskoField;

	@FXML
	private Button updateButton;

	@FXML
	private Button backButton;

	@FXML
	private Label status;

	@FXML
	void backButton(ActionEvent event) throws IOException, Exception {
		Stage stage = App.getInstance().getRootStage();
		Root.getInstance().initRootLayout(stage);
	}

	@FXML
	void updateButton(ActionEvent event) {
		if (choiceBox.getSelectionModel().isEmpty()) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Nie wybrano rekordu do aktualizacji");
		} else if (imieField.getText().isEmpty()) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Uzupełnij pole imie");
		} else if (nazwiskoField.getText().isEmpty()) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Uzupełnij pole nazwisko");
		} else {

			getCurrentSession().getTransaction().begin();
			Pracownik p = new Pracownik(choiceBox.getSelectionModel().getSelectedItem().getId(),
					imieField.getText(), nazwiskoField.getText());

			getCurrentSession().update(p);
			status.setStyle("-fx-text-fill: green;");
			status.setText("Pomyślnie zaktualizowano!");
			getCurrentSession().getTransaction().commit();
			getCurrentSession().close();

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
		choiceBox.setItems(pracownicy);

	}
}
