package component.Controller.del;

import java.io.IOException;


import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.CelDAO;
import component.Hibernate.DAO.Operations;
import component.Hibernate.Entity.Cel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class delCel {

	@FXML
	private ChoiceBox<Cel> celChoice;

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
			Operations.deleteByID(Cel.class, celChoice.getSelectionModel().getSelectedItem().getId());
			status.setStyle("-fx-text-fill: green;");
			status.setText("Pomyślnie usunięto!");
			initialize();
			celChoice.getSelectionModel().clearSelection();
		

	}

	@FXML
	void celChoiceKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ESCAPE) {
			System.out.println("Key pressed esc");
			celChoice.getSelectionModel().clearSelection();
		}
	}

	public void initialize(){
		// TODO Auto-generated method stub
		ObservableList<Cel> cele = CelDAO.getCelPracownikList();
		celChoice.setItems(cele);
		
			

	}
	
	

}