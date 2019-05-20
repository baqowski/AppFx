package component.Controller.add;

import java.io.IOException;



import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.ProduktDAO;
import component.Hibernate.Entity.Produkt;
import component.Hibernate.HibernateUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static component.Hibernate.HibernateUtils.getCurrentSession;

public class addProdukt extends HibernateUtils {

	@FXML
	private TextField nazwaField;

	@FXML
	private Button addButton;

	@FXML
	private Label status;

	@FXML
	private Button backButton;

	@FXML
	void addProdukt(ActionEvent event) {

		if (nazwaField.getText().isEmpty()) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Uzupełnij pole nazwa produktu!");
		} else {
			Produkt p = new Produkt(nazwaField.getText());
			boolean produktExist = ProduktDAO.isProduktExists(p);
			if (produktExist) {
				status.setStyle("-fx-text-fill: red;");
				status.setText("Taki produkt istnieje!");
				getCurrentSession().close();		
			} else {
				getCurrentSession().getTransaction().begin();
				status.setStyle("-fx-text-fill: green;");
				status.setText("Dane dodane pomyślnie");
				getCurrentSession().save(p);
				// Commit the transaction
				getCurrentSession().getTransaction().commit();
			}
		}

	}

	@FXML
	void backButton(ActionEvent event) throws IOException, NullPointerException {
		Stage stage = App.getInstance().getRootStage();
		Root.getInstance().initRootLayout(stage);
	}
}
