package component.Controller.add;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;





import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.CelDAO;
import component.Hibernate.DAO.MiesiacDAO;
import component.Hibernate.DAO.PracownikDAO;
import component.Hibernate.DAO.ProduktDAO;
import component.Hibernate.Entity.Cel;
import component.Hibernate.Entity.Miesiac;
import component.Hibernate.Entity.Pracownik;
import component.Hibernate.Entity.Produkt;
import component.Hibernate.HibernateUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addCel extends HibernateUtils implements Initializable {

	@FXML
	private ChoiceBox<Miesiac> choiceMiesiac;

	@FXML
	private ChoiceBox<Pracownik> choicePracownik;

	@FXML
	private ChoiceBox<Produkt> choiceProdukt;

	@FXML
	private TextField wartoscField;

	@FXML
	private TextField kwotaField;

	@FXML
	private Button backButton;

	@FXML
	private Button addButton;

	@FXML
	private Label myLabel;

	ObservableList<Cel> cele;

	@FXML
	void backButton(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		Root.getInstance().initRootLayout(stage);

	}

	@FXML
	void addCel(ActionEvent event) {

		try {
			Cel c = validate();

			boolean isCel = CelDAO.isCelExists(c);

			if (isCel) {
				myLabel.setStyle("-fx-text-fill: red;");
				myLabel.setText("Isntnieje juz taki cel! Wykonaj aktualizacje rekordu");

			} else {
				//Session session = HibernateUtils.getCurrentSession();
				getCurrentSession().getTransaction().begin();
				getCurrentSession().save(c);
				myLabel.setStyle("-fx-text-fill: green;");
				myLabel.setText("Dane dodane pomyślnie");
				getCurrentSession().getTransaction().commit();
				getCurrentSession().close();

			}

		} catch (NumberFormatException ex) {
			myLabel.setStyle("-fx-text-fill: red;");
			myLabel.setText("Błędny format wprowadzonych danych");
		}

		// int count = Operations.breakCelu();

		// System.out.println("count " + count);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stu

		ObservableList<Pracownik> empList = PracownikDAO.getPracownikList();
		ObservableList<Produkt> productList = ProduktDAO.getProduktList();
		cele = CelDAO.getCelPracownikList();
		ObservableList<Miesiac> miesiacList = MiesiacDAO.getMiesiacList();
		System.out.println("Lista pracownikow " + empList);
		System.out.println("Lista produktow " + productList);
		System.out.println("Lista celi " + cele);
		choiceMiesiac.setItems(miesiacList);
		choicePracownik.setItems(empList);
		choiceProdukt.setItems(productList);
	}

	public Cel validate() {

		Pracownik idPracownik = choicePracownik.getSelectionModel().getSelectedItem();
		Produkt idProdukt = choiceProdukt.getSelectionModel().getSelectedItem();
		Miesiac idMiesiac = choiceMiesiac.getSelectionModel().getSelectedItem();
		String kwota = kwotaField.getText();
		String wartosc = wartoscField.getText();

		if (choiceMiesiac.getSelectionModel().isEmpty()) {
			myLabel.setStyle("-fx-text-fill: red;");
			myLabel.setText("Nie wybrano miesiąca ");
		} else if (choicePracownik.getSelectionModel().isEmpty()) {
			myLabel.setStyle("-fx-text-fill: red;");
			myLabel.setText("Nie wybrano pracownika ");
		} else if (choiceProdukt.getSelectionModel().isEmpty()) {
			myLabel.setStyle("-fx-text-fill: red;");
			myLabel.setText("Nie wybrano produktu ");
		} else if (!choiceMiesiac.getSelectionModel().isEmpty() && !choicePracownik.getSelectionModel().isEmpty()
				&& !choiceProdukt.getSelectionModel().isEmpty() && !wartoscField.getText().isEmpty()
				&& !kwotaField.getText().isEmpty()) {
			Cel c = new Cel(idPracownik, idProdukt, idMiesiac, Integer.parseInt(kwota), Integer.parseInt(wartosc));
			return c;

		} else if (!choiceMiesiac.getSelectionModel().isEmpty() && !choicePracownik.getSelectionModel().isEmpty()
				&& !choiceProdukt.getSelectionModel().isEmpty() && wartoscField.getText().isEmpty()
				&& kwotaField.getText().isEmpty()) {

			Cel c = new Cel(idPracownik, idProdukt, idMiesiac);
			return c;
		}
		return null;
	}
}
