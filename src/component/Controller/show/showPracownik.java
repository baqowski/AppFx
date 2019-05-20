package component.Controller.show;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import component.App;
import component.Controller.Root;
import component.Hibernate.DAO.PracownikDAO;
import component.Hibernate.Entity.Pracownik;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class showPracownik implements Initializable {
	
    @FXML
    private Button backButton;
    
    @FXML
    private TableView<Pracownik> tablePracownik;
    
    @FXML
    private TableColumn<Pracownik, Integer> idColumn;

    @FXML
    private TableColumn<Pracownik, String> imieColumn;

    @FXML
    private TableColumn<Pracownik, String> nazwiskoColumn;

    @FXML
    void backButton(ActionEvent event) throws IOException {
    	Stage stage = App.getInstance().getRootStage();
		Root.getInstance().initRootLayout(stage);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<Pracownik> pracownicy = PracownikDAO.getPracownikList();
		idColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, Integer>("id"));
		imieColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, String>("imie"));
		nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Pracownik, String>("nazwisko"));
		tablePracownik.setItems(pracownicy);
		

		
	}

	
	
}
