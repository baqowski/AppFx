package component.Controller.update;

import java.io.IOException;

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
import org.hibernate.Transaction;
import javafx.collections.FXCollections;
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

public class updateCel extends HibernateUtils {

	@FXML
	private ChoiceBox<Cel> choiceCel;

	@FXML
	private ChoiceBox<Miesiac> choiceMiesiac;

	@FXML
	private ChoiceBox<Pracownik> choicePracownik;

	@FXML
	private ChoiceBox<Produkt> choiceProdukt;

	@FXML
	private Label myLabel;

	@FXML
	private TextField kwotaField;

	@FXML
	private TextField wartoscField;

	@FXML
	private Button upadateButton;

	@FXML
	private Button backButton;

	
	@FXML
	void updateCel(ActionEvent event) {
		try {
			Transaction tx = getCurrentSession().beginTransaction();
			Cel c = new Cel();
			c.setId(choiceCel.getSelectionModel().getSelectedItem().getId());
			c.setIdProdukt(choiceProdukt.getSelectionModel().getSelectedItem());
			c.setIdPracownik(choicePracownik.getSelectionModel().getSelectedItem());
			c.setIdMiesiac(choiceMiesiac.getSelectionModel().getSelectedItem());
			c.setWartosc(Integer.parseInt(kwotaField.getText()));
			c.setWynik(Integer.parseInt(wartoscField.getText()));
			c.setBilans(c.getWynik() - c.getWartosc());
			getCurrentSession().update(c);
			myLabel.setStyle("-fx-text-fill: green;");
			myLabel.setText("Pomyślnie zaktualizowano!");
			tx.commit();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			// // Rollback in case of an error occurred.
			myLabel.setStyle("-fx-text-fill: red;");
			myLabel.setText("Nie wybrano rekordu!");
			getCurrentSession().getTransaction().rollback();
		} finally {
			getCurrentSession().close();
		}
	}

	@FXML
	void backButton(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		Root.getInstance().initRootLayout(stage);
	}
	
	  @FXML
	    void choiceCelKeyPressed(KeyEvent event) {

		  if (event.getCode() == KeyCode.ESCAPE) {
				System.out.println("Key pressed esc");
				choiceCel.getSelectionModel().clearSelection();
			}
	    }

	  

	    @FXML
	    void choiceMiesiacKeyPressed(KeyEvent event) {
	    	 if (event.getCode() == KeyCode.ESCAPE) {
					System.out.println("Key pressed esc");
					choiceMiesiac.getSelectionModel().clearSelection();
				}
		    
	    }

	  

	    @FXML
	    void choicePracownikKeyPressed(KeyEvent event) {
	    	 if (event.getCode() == KeyCode.ESCAPE) {
					System.out.println("Key pressed esc");
					choicePracownik.getSelectionModel().clearSelection();
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
		// TODO Auto-generated method stu
		ObservableList<Pracownik> empList = PracownikDAO.getPracownikList();
		ObservableList<Produkt> productList = ProduktDAO.getProduktList();
		ObservableList<Miesiac> miesiacList = MiesiacDAO.getMiesiacList();
		ObservableList<Cel> celList = CelDAO.getCelPracownikList();
		choiceMiesiac.setItems(miesiacList);
		choicePracownik.setItems(empList);
		choiceProdukt.setItems(productList);
		choiceCel.setItems(celList);
	}

}
