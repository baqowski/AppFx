package component.Controller.add;

import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.PracownikDAO;
import component.Hibernate.Entity.Pracownik;
import component.Hibernate.HibernateUtils;
import component.MyValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import org.hibernate.HibernateException;




public class addPracownik extends HibernateUtils implements MyValidator {



	@FXML
	private TextField imieField;

	@FXML
	private TextField nazwiskoField;

	@FXML
	private Button backButton;

	@FXML
	private Button addButton;

	@FXML
	private Label status;

	@FXML
	void backButton(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		// stage.setFullScreen(true);
		Root.getInstance().initRootLayout(stage);
	}

	@FXML
	void addButton(ActionEvent event) throws IOException {

		try {

			Pracownik p = (Pracownik) validate();

			if (PracownikDAO.isPracownikExists(p)) {
				status.setStyle("-fx-text-fill: red;");
				status.setText("Taki pracownik juz istnieje!");

			} else {

				getCurrentSession().getTransaction().begin();
				getCurrentSession().save(p);
				status.setStyle("-fx-text-fill: green;");
				status.setText("Dane dodane pomyślnie");
				getCurrentSession().getTransaction().commit();
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
			getCurrentSession().beginTransaction().rollback();

		}
	}

	@Override
	public Object validate() {
		// TODO Auto-generated method stub
		if (imieField.getText().isEmpty()) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Uzupełnij pole imię! ");
		} else if (nazwiskoField.getText().isEmpty()) {
			status.setStyle("-fx-text-fill: red;");
			status.setText("Uzupełnij pole nazwisko! ");
		} else {

			Pracownik p = new Pracownik(imieField.getText(), nazwiskoField.getText());
			return p;

		}
		return null;

	}
}