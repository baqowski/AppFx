package component.Controller.update;

import java.io.IOException;


import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.ProduktDAO;
import component.Hibernate.Entity.Produkt;
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

public class updateProdukt extends HibernateUtils {

	@FXML
	private ChoiceBox<Produkt> choiceProdukt;

	@FXML
	private TextField nazwaField;

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

		//initialize();
		// session.close();
		Produkt p = validate();
		p.setId(choiceProdukt.getSelectionModel().getSelectedItem().getId());
		System.out.println(p.toString());
		if (ProduktDAO.isProduktExists(p)) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Produkt o takiej nazwie istnieje!");
			// session.close();
		} else {
			getCurrentSession().getTransaction().begin();
			System.out.println("Nowy rekord " + p.getId() + " " + p.toString());
			getCurrentSession().update(p);
			getCurrentSession().getTransaction().commit();
			getCurrentSession().close();
			status.setStyle("-fx-text-fill: green;");
			status.setText("Pomyślnie zaktualizowano!");
			choiceProdukt.getSelectionModel().clearSelection();
			initialize();
			

		}

	}

	@FXML
	void choiceProduktKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			System.out.println("Key pressed esc");
			choiceProdukt.getSelectionModel().clearSelection();
		}
	}

	public void initialize() {
		// TODO Auto-generated method stub
		ObservableList<Produkt> listProdukt = ProduktDAO.getProduktList();
		if (listProdukt != null)
		choiceProdukt.setItems(listProdukt);

	}

	private Produkt validate() {

		if (choiceProdukt.getSelectionModel().isEmpty()) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Nie wybrano produktu, który będzie aktualizowany!");
		} else if (nazwaField.getText().isEmpty()) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Uzupełnij pole nowa nazwa! ");
		} else {
			Produkt p = new Produkt(nazwaField.getText());
			return p;

		}
		return null;

	}
	
	

}
