package component.Controller.show;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.ProduktDAO;
import component.Hibernate.Entity.Produkt;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class showProdukt implements Initializable {

    @FXML
    private TableView<Produkt> produktTable;

    @FXML
    private TableColumn<Produkt, Integer> idColumn;

    @FXML
    private TableColumn<Produkt, String> nameColumn;

    @FXML
    private Button backButton;

    @FXML
    void backButton(ActionEvent event) throws IOException {
    	Stage stage = App.getInstance().getRootStage();
		Root.getInstance().initRootLayout(stage);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<Produkt> produkty = ProduktDAO.getProduktList();
		System.out.println(produkty);
		idColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Integer>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Produkt, String>("nameProdukt"));
		produktTable.setItems(produkty);
	}
}
