package component.Controller;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import component.App;
import component.Hibernate.DAO.CelDAO;
import component.Hibernate.DAO.MiesiacDAO;
import component.Hibernate.DAO.PracownikDAO;
import component.Hibernate.DAO.ProduktDAO;
import component.Hibernate.Entity.Cel;
import component.Hibernate.Entity.Miesiac;
import component.Hibernate.Entity.Pracownik;
import component.Hibernate.Entity.Produkt;
import component.Hibernate.HibernateUtils;
import org.hibernate.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class Root extends HibernateUtils implements Initializable {

	private static Root instance;
	final KeyCombination kb = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);

	public static Root getInstance() {
		if (instance == null) {
			instance = new Root();
		}
		return instance;
	}

	public void setInstance(Root instance) {
		Root.instance = instance;
	}

	@FXML
	private MenuBar menuBar;

	@FXML
	private Menu pracownikMenu;

	@FXML
	private MenuItem addPracownik;

	@FXML
	private MenuItem delPracownik;

	@FXML
	private MenuItem showPracownik;

	@FXML
	private MenuItem updatePracownik;

	@FXML
	private Menu celMenu;

	@FXML
	private MenuItem addCel;

	@FXML
	private MenuItem delCel;

	@FXML
	private MenuItem showCel;

	@FXML
	private MenuItem updateCel;

	@FXML
	private Menu produktMenu;

	@FXML
	private MenuItem addProdukt;

	@FXML
	private MenuItem delProdukt;

	@FXML
	private MenuItem showProdukt;

	@FXML
	private MenuItem updateProdukt;

	@FXML
	private TextField nazwiskoField;

	@FXML
	private TableView<Cel> tableCelPracownik;

	@FXML
	private TableColumn<Cel, Integer> nrColumn;

	@FXML
	private TableColumn<Cel, String> pracownikColumn;

	@FXML
	private TableColumn<Cel, List<Produkt>> produktColumn;

	@FXML
	private TableColumn<Cel, String> miesiacColumn;

	@FXML
	private TableColumn<Cel, Integer> celColumn;

	@FXML
	private TableColumn<Cel, Integer> wynikColumn;

	@FXML
	private TableColumn<Cel, Integer> saldoColumn;

	@FXML
	private TableView<Cel> tableCelOddzial;

	@FXML
	private TableColumn<Cel, List<Produkt>> produktCelColumn;

	@FXML
	private TableColumn<Cel, String> miesiacCelColumn;

	@FXML
	private TableColumn<Cel, Integer> celOddzialColumn;

	@FXML
	private TableColumn<Cel, Integer> wynikOddzialColumn;

	@FXML
	private TableColumn<Cel, Integer> saldoOddzialColumn;

	@FXML
	private ChoiceBox<String> choiceKwartal;

	@FXML
	private ChoiceBox<Miesiac> choiceMiesiac;

	@FXML
	private ChoiceBox<Produkt> choiceProdukt;

	@FXML
	private ChoiceBox<Pracownik> choicePracownik;

	@FXML
	private Button wykresButton;

	@FXML
	private Button backButton;

	@FXML
	private Button searchButton;

	@FXML
	private Button refreshButton;

	@FXML
	private Label saldoLabel;

	@FXML
	private ImageView imageBack;

	@FXML
	private ImageView imageRefresh;

	@FXML
	private ImageView imageWykres;

	@FXML
	private ImageView searchView;

	@FXML
	private Text text;

	// private Login l;

	private ObservableList<String> kwartalList = FXCollections.observableArrayList("Kwartał 1", "Kwartał 2",
			"Kwartał 3", "Kwartał 4");

	private ObservableList<Pracownik> listPracownik = PracownikDAO.getPracownikList();
	private ObservableList<Produkt> listProdukt = ProduktDAO.getProduktList();
	private ObservableList<Miesiac> listMiesiac = MiesiacDAO.getMiesiacList();
	private ObservableList<Cel> listCelPracownik = CelDAO.getCelPracownikList();
	private ObservableList<Cel> listCeleOdzial = CelDAO.getCelOdzialList();

	// final CustomItem emptyPlaceholder = new CustomItem(null);
	// List<Cel> listCelFromMonth = Operations
	// .selectCelFromConcreteMonth("Luty");

	public ObservableList<Pracownik> getListPracownik() {
		return listPracownik;
	}

	public ObservableList<Produkt> getListProdukt() {
		return listProdukt;
	}

	public ObservableList<Cel> getListCel() {
		return listCelPracownik;
	}

	public ObservableList<Cel> getListCeleOdzial() {
		return listCeleOdzial;
	}

	// Dodawanie pracownika
	@FXML
	void addPracownik(ActionEvent event) throws IOException {

		Stage s = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/component/View/addPracownik.fxml"));
		Scene scene = new Scene(root, 308, 243);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		s.setScene(scene);
		s.show();

	}

	// Usuwanie pracownika
	@FXML
	void delPracownik(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/component/View/delPracownik.fxml"));
		Scene scene = new Scene(root, 300, 200);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	// Wyświetlanie pracownika
	@FXML
	void showPracownik(ActionEvent event) throws IOException {
		Stage s = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/component/View/showPracownik.fxml"));
		Scene scene = new Scene(root, 226, 387);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		s.setScene(scene);
		s.show();
	}

	// aktualizacja pracownika
	@FXML
	void updatePacownik(ActionEvent event) throws IOException {
		Stage s = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/component/View/updatePracownik.fxml"));
		Scene scene = new Scene(root, 300, 219);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		s.setScene(scene);
		s.show();
	}

	// Dodawanie produktu
	@FXML
	void addProdukt(ActionEvent event) throws IOException {

		Stage stage = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/component/View/addProdukt.fxml"));
		Scene scene = new Scene(root, 308, 243);
		// scene.getAccelerators().put(kb, rn);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();

	}

	// Usuwanie produktu
	@FXML
	void delProdukt(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/component/View/delProdukt.fxml"));
		Scene scene = new Scene(root, 300, 200);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	// Wyświetlanie produktu
	@FXML
	void showProdukt(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/component/View/showProdukt.fxml"));
		Scene scene = new Scene(root, 201, 397);
		// scene.getAccelerators().put(kb, rn);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	// Aktualizacja produktu
	@FXML
	void updateProdukt(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader
				.load(getClass().getResource("/component/View/updateProdukt.fxml"));
		Scene scene = new Scene(root, 300, 219);
		stage.setScene(scene);
		stage.show();
	}

	// Dodawanie celu
	@FXML
	void addCel(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/component/View/addCel.fxml"));
		Scene scene = new Scene(root, 400, 300);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	// Usuwanie celu
	@FXML
	void delCel(ActionEvent event) throws IOException, NullPointerException {
		try {
			Stage stage = App.getInstance().getRootStage();
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/component/View/delCel.fxml"));
			Scene scene = new Scene(root, 300, 200);
			// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	// Aktualizacja celu
	@FXML
	void updateCel(ActionEvent event) throws IOException {
		Stage stage = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/component/View/updateCel.fxml"));
		Scene scene = new Scene(root, 437, 266);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void wykresButton(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		BorderPane root = (BorderPane) FXMLLoader
				.load(getClass().getResource("/component/View/wykresKolumnowy.fxml"));
		Scene scene = new Scene(root, 700, 500);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void wykresButtonMouseEntered(MouseEvent event) {
		setActive(imageWykres);
	}

	@FXML
	void wykresButtonMouseExited(MouseEvent event) {
		setUnActive(imageWykres);
	}

	@FXML
	void backButton(ActionEvent event) throws IOException {

		Stage stage = App.getInstance().getRootStage();
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/component/View/logPane.fxml"));
		Scene scene = new Scene(root, 306, 242);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void backButtonMouseEntered(MouseEvent event) {
		System.out.println("mouse entered");
		setActive(imageBack);

	}

	@FXML
	void backButtonMouseExited(MouseEvent event) {
		System.out.println("mouse exited");
		setUnActive(imageBack);
	}

	@FXML
	void backMouseEntered(MouseEvent event) {
		System.out.println("mouse entered");
		// setActive(imageBack);
		imageBack.setOpacity(0.6);
	}

	@FXML
	void refreshButton(ActionEvent event) {
		ObservableList<Cel> refreshCel = CelDAO.getCelPracownikList();
		tableCelPracownik.setItems(refreshCel);
		ObservableList<Cel> refreshCelOddzial = CelDAO.getCelOdzialList();
		tableCelOddzial.setItems(refreshCelOddzial);
		choiceKwartal.getSelectionModel().clearSelection();
		choiceProdukt.getSelectionModel().clearSelection();
		choiceMiesiac.getSelectionModel().clearSelection();
		choicePracownik.getSelectionModel().clearSelection();
		tableCelOddzial.setVisible(true);
		saldoLabel.setText("");
		text.setVisible(false);

	}

	@FXML
	void refreshButtonMouseEntered(MouseEvent event) {
		setActive(imageRefresh);
	}

	@FXML
	void refreshButtonMouseExited(MouseEvent event) {
		setUnActive(imageRefresh);
	}

	@FXML
	void searchButton(ActionEvent event) {
		text.setVisible(false);
		text.setText("");

		if (!choiceKwartal.getSelectionModel().isEmpty() && !choiceMiesiac.getSelectionModel().isEmpty()) {
			System.out.println("Nie mozna szukać jednocześnie po kwartale i miesiącu");
			text.setVisible(true);
			text.setText("Nie mozna szukać jednocześnie po kwartale i miesiącu");

		} else if (!choiceKwartal.getSelectionModel().isEmpty() && !choiceProdukt.getSelectionModel().isEmpty()
				&& choiceMiesiac.getSelectionModel().isEmpty() && choicePracownik.getSelectionModel().isEmpty()) {
			searchByKwartalAndProdukt();
			saldoLabel.setVisible(true);
		} else if (choiceKwartal.getSelectionModel().isEmpty() && !choiceProdukt.getSelectionModel().isEmpty()
				&& !choiceMiesiac.getSelectionModel().isEmpty() && choicePracownik.getSelectionModel().isEmpty()) {
			searchByMiesiacAndProdukt();
			saldoLabel.setVisible(true);
		} else if (choiceKwartal.getSelectionModel().isEmpty() && choiceProdukt.getSelectionModel().isEmpty()
				&& choiceMiesiac.getSelectionModel().isEmpty() && !choicePracownik.getSelectionModel().isEmpty()) {
			searchByPracownik();
			saldoLabel.setVisible(true);
		} else if (!choiceKwartal.getSelectionModel().isEmpty() && choiceProdukt.getSelectionModel().isEmpty()
				&& choiceMiesiac.getSelectionModel().isEmpty() && !choicePracownik.getSelectionModel().isEmpty()) {
			searchByKwartalPracownik();
			saldoLabel.setVisible(true);
		} else if (choiceKwartal.getSelectionModel().isEmpty() && choiceProdukt.getSelectionModel().isEmpty()
				&& !choiceMiesiac.getSelectionModel().isEmpty() && !choicePracownik.getSelectionModel().isEmpty()) {
			searchByPracownikMiesiac();
			saldoLabel.setVisible(true);
		} else if (choiceKwartal.getSelectionModel().isEmpty() && !choiceProdukt.getSelectionModel().isEmpty()
				&& !choiceMiesiac.getSelectionModel().isEmpty() && !choicePracownik.getSelectionModel().isEmpty()) {
			searchByMiesiacPracownikProdukt();
			saldoLabel.setVisible(true);
		} else if (!choiceKwartal.getSelectionModel().isEmpty() && !choiceProdukt.getSelectionModel().isEmpty()
				&& choiceMiesiac.getSelectionModel().isEmpty() && !choicePracownik.getSelectionModel().isEmpty()) {
			searchByKwartalPracownikProdukt();
			saldoLabel.setVisible(true);
		} else if (choiceKwartal.getSelectionModel().isEmpty() && !choiceProdukt.getSelectionModel().isEmpty()
				&& choiceMiesiac.getSelectionModel().isEmpty() && !choicePracownik.getSelectionModel().isEmpty()) {
			searchByProduktPracownik();
			saldoLabel.setVisible(true);
		} else if (choiceKwartal.getSelectionModel().isEmpty() && !choiceProdukt.getSelectionModel().isEmpty()
				&& choiceMiesiac.getSelectionModel().isEmpty() && choicePracownik.getSelectionModel().isEmpty()) {
			searchByProdukt();
			saldoLabel.setVisible(true);
		} else if (!choiceKwartal.getSelectionModel().isEmpty() && choiceProdukt.getSelectionModel().isEmpty()
				&& choiceMiesiac.getSelectionModel().isEmpty() && choicePracownik.getSelectionModel().isEmpty()) {
			searchByKwartal();
			saldoLabel.setVisible(true);
		} else if (choiceKwartal.getSelectionModel().isEmpty() && choiceProdukt.getSelectionModel().isEmpty()
				&& !choiceMiesiac.getSelectionModel().isEmpty() && choicePracownik.getSelectionModel().isEmpty()) {
			searchByMiesiac();
			saldoLabel.setVisible(true);
		}
		// else if ()

	}

	public void updateWynik(Cel old, Cel nowy, Integer value) {
		Session session = HibernateUtils.getCurrentSession();

		try {
			session.getTransaction().begin();

			nowy = new Cel(old.getId(), old.getIdPracownik(), old.getIdProdukt(), old.getIdMiesiac(), old.getWartosc(),
					value);
			nowy.setBilans(nowy.getWynik() - old.getWartosc());

			System.out.println("Zaktualizowao rekord + " + old.toString() + " nowym rekordem " + nowy.toString());

			// Update
			session.update(nowy);
			// status.setText("Dane dodane pomyślnie");
			// Commit the transaction
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	public void updateCel(Cel old, Cel nowy, Integer value) {
		Session session = HibernateUtils.getCurrentSession();

		try {
			session.getTransaction().begin();

			nowy = new Cel(old.getId(), old.getIdPracownik(), old.getIdProdukt(), old.getIdMiesiac(), value,
					old.getWynik());
			nowy.setBilans(old.getWynik() - nowy.getWartosc());

			System.out.println("Zaktualizowao rekord + " + old.toString() + " nowym rekordem " + nowy.toString());

			// Update
			session.update(nowy);
			// status.setText("Dane dodane pomyślnie");
			// Commit the transaction
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
		} finally {
			if (session.isOpen())
				session.close();
		}

	}

	@FXML
	void searchButtonMouseEntered(MouseEvent event) {
		setActive(searchView);
	}

	@FXML
	void searchButtonMouseExited(MouseEvent event) {
		setUnActive(searchView);
	}

	public void initLogPane(Stage s) throws IOException {

		s = App.getInstance().getRootStage();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/baq/myApp/JavaFx/View/logPane.fxml"));
		Scene scene = new Scene(root, 300, 200);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		s.setScene(scene);
		s.show();

	}

	public void initRootLayout(Stage s) throws IOException, NullPointerException {

		s = App.getInstance().getRootStage();
		BorderPane root = (BorderPane) FXMLLoader
				.load(getClass().getResource("/component/View/rootLayout.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/baq/myApp/application.css").toExternalForm());
		s.setScene(scene);
		s.setX(0);
		s.setY(0);
		s.show();

	}

	public void handle(KeyEvent event) {
		if (kb.match(event)) {
			System.out.println("dddd");
			addPracownik.setAccelerator(kb);
		}
	}

	public void addListToTableView() {

		tableCelPracownik.setItems(getListCel());
		System.out.println("Cele oddzialu" + getListCeleOdzial());
		tableCelOddzial.setItems(getListCeleOdzial());
	}

	@FXML
	void choiceKwartalKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ESCAPE) {
			System.out.println("Key pressed esc");
			choiceKwartal.getSelectionModel().clearSelection();
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
	void choiceProduktKeyPressed(KeyEvent event) {
		System.out.println("Mouse pressed");
		if (event.getCode() == KeyCode.ESCAPE) {
			System.out.println("Key pressed esc");
			choiceProdukt.getSelectionModel().clearSelection();
		}
	}

	@FXML
	void choicePracownikKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ESCAPE) {
			System.out.println("Key pressed esc");
			choicePracownik.getSelectionModel().clearSelection();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		saldoLabel.setVisible(false);

		System.out.println("List pracownik " + listPracownik);
		System.out.println("List produkt " + listProdukt);
		System.out.println("kwartal list " + listCelPracownik);
		choiceKwartal.setItems(kwartalList);
		// choiceKwartal.getItems().addAll(kwartalList);
		choiceProdukt.setItems(listProdukt);
		choiceMiesiac.setItems(listMiesiac);
		choicePracownik.setItems(listPracownik);
		tableCelPracownik.setEditable(true);
		wynikColumn.setEditable(true);
		nrColumn.setCellValueFactory(new PropertyValueFactory<Cel, Integer>("id"));
		pracownikColumn.setCellValueFactory(new PropertyValueFactory<Cel, String>("idPracownik"));
		produktColumn.setCellValueFactory(new PropertyValueFactory<Cel, List<Produkt>>("idProdukt"));
		miesiacColumn.setCellValueFactory(new PropertyValueFactory<Cel, String>("idMiesiac"));
		celColumn.setCellValueFactory(new PropertyValueFactory<Cel, Integer>("wartosc"));
		celColumn.setCellFactory(new Callback<TableColumn<Cel, Integer>, TableCell<Cel, Integer>>() {

			@Override
			public TableCell<Cel, Integer> call(TableColumn<Cel, Integer> param) {
				TextFieldTableCell<Cel, Integer> myEditableTableCell = new TextFieldTableCell<Cel, Integer>(
						new IntegerStringConverter()) {

					@Override
					public void commitEdit(Integer val) {
						int index = this.getTableRow().getIndex();
						System.out.println("index" + index);
						Cel cel = (Cel) tableCelPracownik.getItems().get(index);
						Pracownik p = new Pracownik();
						p.setId(cel.getIdPracownik().getId());
						p.setImie(cel.getIdPracownik().getImie());
						p.setNazwisko(cel.getIdPracownik().getNazwisko());
						p.setList(p.getList());
						Produkt pr = new Produkt();
						pr.setId(cel.getIdProdukt().getId());
						pr.setNameProdukt(cel.getIdProdukt().getNameProdukt());
						pr.setList(cel.getIdProdukt().getList());
						Miesiac m = new Miesiac();
						m.setId(cel.getIdMiesiac().getId());
						m.setMiesiac(cel.getIdMiesiac().getMiesiac());

						Cel newCel = new Cel(p, pr, m);
						newCel.setId(cel.getId());
						newCel.setIdPracownik(p);
						newCel.setIdProdukt(pr);
						newCel.setIdMiesiac(m);
						newCel.setWartosc(val);
						String.format("%,d", val);
						newCel.setWynik(cel.getWynik());
						int tmp = newCel.getWynik() - newCel.getWartosc();
						System.out.println(String.format("%,d", tmp));
						System.out.println("tmp" + tmp);
						newCel.setBilans(tmp);
						int columnIndex = cel.getId();
						System.out.println("column Index: " + columnIndex);
						super.commitEdit(val);

						updateCel(cel, newCel, val);
						//ObservableList<Cel> newLst = CelDAO.getCelPracownikList();
						//System.out.println("Po aktualizacji tablei " + listCelPracownik);
						//tableCelPracownik.setItems(newLst);
						ObservableList<Cel> list = FXCollections.observableArrayList();
						list.add(newCel);
						tableCelPracownik.setItems(list);
						ObservableList<Cel> newLstCelOddzial = CelDAO.getCelOdzialList();
						tableCelOddzial.setItems(newLstCelOddzial);

					}

					@Override
					public void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty); // This is mandatory

						if (item == null || empty) { // If the cell is empty
							setText(null);
							setStyle("");
						} else
							setText(String.valueOf(item).format("%,d", item)); // Put the String data in the cell
					}

				};
				return myEditableTableCell;
			}
		});
		wynikColumn.setCellValueFactory(new PropertyValueFactory<Cel, Integer>("wynik"));

		wynikColumn.setCellFactory(new Callback<TableColumn<Cel, Integer>, TableCell<Cel, Integer>>() {

			@Override
			public TableCell<Cel, Integer> call(TableColumn<Cel, Integer> param) {
				TextFieldTableCell<Cel, Integer> myEditableTableCell = new TextFieldTableCell<Cel, Integer>(
						new IntegerStringConverter()) {

					@Override
					public void commitEdit(Integer val) {
						int index = this.getTableRow().getIndex();
						Cel cel = (Cel) tableCelPracownik.getItems().get(index);
						Pracownik p = new Pracownik();
						p.setId(cel.getIdPracownik().getId());
						p.setImie(cel.getIdPracownik().getImie());
						p.setNazwisko(cel.getIdPracownik().getNazwisko());
						p.setList(p.getList());
						Produkt pr = new Produkt();
						pr.setId(cel.getIdProdukt().getId());
						pr.setNameProdukt(cel.getIdProdukt().getNameProdukt());
						pr.setList(cel.getIdProdukt().getList());
						Miesiac m = new Miesiac();
						m.setId(cel.getIdMiesiac().getId());
						m.setMiesiac(cel.getIdMiesiac().getMiesiac());

						Cel newCel = new Cel(p, pr, m);
						newCel.setId(cel.getId());
						newCel.setIdPracownik(p);
						newCel.setIdProdukt(pr);
						newCel.setIdMiesiac(m);
						newCel.setWartosc(cel.getWartosc());
						String.format("%,d", val);
						newCel.setWynik(val);
						int tmp = newCel.getWynik() - newCel.getWartosc();
						System.out.println(String.format("%,d", tmp));
						System.out.println("tmp" + tmp);
						newCel.setBilans(tmp);
						int columnIndex = cel.getId();
						System.out.println("column Index: " + columnIndex);
						super.commitEdit(val);

						updateWynik(cel, newCel, val);
						ObservableList<Cel> list = FXCollections.observableArrayList();
						list.add(newCel);
						tableCelPracownik.setItems(list);
						//ObservableList<Cel> newLst = CelDAO.getCelPracownikList();
						//System.out.println("Po aktualizacji tablei " + listCelPracownik);
						//tableCelPracownik.setItems(newLst);
						ObservableList<Cel> newLstCelOddzial = CelDAO.getCelOdzialList();
						tableCelOddzial.setItems(newLstCelOddzial);

					}

					@Override
					public void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty); // This is mandatory

						if (item == null || empty) { // If the cell is empty
							setText(null);
							setStyle("");
						} else
							setText(String.valueOf(item).format("%,d", item)); // Put the String data in the cell
					}

				};
				return myEditableTableCell;
			}
		});

		saldoColumn.setCellValueFactory(new PropertyValueFactory<Cel, Integer>("bilans"));
		saldoColumn.setCellFactory(column -> {
			return new TableCell<Cel, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty); // This is mandatory

					if (item == null || empty) { // If the cell is empty
						setText(null);
						setStyle("");
					} else { // If the cell is not empty

						setText(String.valueOf(item).format("%,d", item)); // Put the String data in the cell

						Cel c = getTableView().getItems().get(getIndex());

						if (c.getBilans() > 0)
							setTextFill(Color.GREEN); // The text in red
						else if (c.getBilans() < 0)
							setTextFill(Color.RED);
						else if (c.getBilans() == 0)
							setTextFill(Color.BLUE);

					}
				}
			};
		});

		produktCelColumn.setCellValueFactory(new PropertyValueFactory<Cel, List<Produkt>>("idProdukt"));
		miesiacCelColumn.setCellValueFactory(new PropertyValueFactory<Cel, String>("idMiesiac"));
		celOddzialColumn.setCellValueFactory(new PropertyValueFactory<Cel, Integer>("wartosc"));
		celOddzialColumn.setCellFactory(column -> {
			return new TableCell<Cel, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty); // This is mandatory

					if (item == null || empty) { // If the cell is empty
						setText(null);
						setStyle("");
					} else
						setText(String.valueOf(item).format("%,d", item)); // Put the String data in the cell
				}
			};
		});
		wynikOddzialColumn.setCellValueFactory(new PropertyValueFactory<Cel, Integer>("wynik"));
		wynikOddzialColumn.setCellFactory(column -> {
			return new TableCell<Cel, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty); // This is mandatory

					if (item == null || empty) { // If the cell is empty
						setText(null);
						setStyle("");
					} else
						setText(String.valueOf(item).format("%,d", item)); // Put the String data in the cell
				}
			};
		});

		saldoOddzialColumn.setCellValueFactory(new PropertyValueFactory<Cel, Integer>("bilans"));
		saldoOddzialColumn.setCellFactory(column -> {
			return new TableCell<Cel, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty); // This is mandatory

					if (item == null || empty) { // If the cell is empty
						setText(null);
						setStyle("");
					} else { // If the cell is not empty

						setText(String.valueOf(item).format("%,d", item)); // Put the String data in the cell

						Cel c = getTableView().getItems().get(getIndex());

						if (c.getBilans() > 0)
							setTextFill(Color.GREEN); // The text in red
						else if (c.getBilans() < 0)
							setTextFill(Color.RED);
						else if (c.getBilans() == 0)
							setTextFill(Color.BLUE);

					}
				}
			};
		});

		addListToTableView();

	}

	public void getSaldoFromList(ObservableList<Cel> list) {

		int saldo = 0;
		int sumCel = 0;
		int sumWartosc = 0;
		for (Cel c : list) {

			sumCel += c.getWartosc();
			sumWartosc += c.getWynik();
			saldo = sumWartosc - sumCel;

		}

		if (saldo > 0)
			saldoLabel.setStyle("-fx-text-fill: green;");
		else if (saldo == 0)
			saldoLabel.setStyle("-fx-text-fill: blue;");
		else if (saldo < 0)
			saldoLabel.setStyle("-fx-text-fill: #ff6666;");

		saldoLabel.setVisible(true);
		saldoLabel.setText("Saldo: " + String.format("%,d", saldo));
	}

	public void setActive(ImageView iv) {
		iv.setOpacity(0.6);
	}

	public void setUnActive(ImageView iv) {
		iv.setOpacity(1);
	}

	public void searchByPracownik() {
		System.out.println("Wybrano pracownika");
		try {
			ObservableList<Cel> obsList = CelDAO
					.getCelForPracownik(choicePracownik.getSelectionModel().getSelectedItem().getId());

			if (obsList == null) {
				tableCelPracownik.setItems(null);
				// tableCelOddzial.setVisible(false);
			} else {
				System.out.println("0 != " + obsList.size());
				tableCelPracownik.setItems(obsList);
				// tableCelOddzial.setVisible(false);
				getSaldoFromList(obsList);
			}

		} catch (NullPointerException ex) {
			text.setText(ex.getMessage());
			System.out.println(ex.getMessage());
		}

	}

	public void searchByKwartalPracownik() {
		System.out.println("Wybrano pracownika i kwartał");
		try {
			switch (choiceKwartal.getSelectionModel().getSelectedItem()) {
			case "Kwartał 1": {
				System.out.println("Kwartał 1");

				ObservableList<Cel> newobsList = CelDAO.getCelForKwartalPracownik(1, 2, 3,
						choicePracownik.getSelectionModel().getSelectedItem().getId());

				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					saldoLabel.setText(null);
					break;

				} else {
					tableCelPracownik.setItems(newobsList);
					getSaldoFromList(newobsList);
					break;
				}

			}
			case "Kwartał 2": {
				System.out.println("Kwartał 2");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartalPracownik(4, 5, 6,
						choicePracownik.getSelectionModel().getSelectedItem().getId());

				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					saldoLabel.setText(null);
					break;
				} else {

					tableCelPracownik.setItems(newobsList);
					getSaldoFromList(newobsList);
					break;
				}
			}
			case "Kwartał 3": {
				System.out.println("Kwartał 3");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartalPracownik(7, 8, 9,
						choicePracownik.getSelectionModel().getSelectedItem().getId());

				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					tableCelOddzial.setVisible(false);
					saldoLabel.setText(null);
					break;
				} else {

					tableCelPracownik.setItems(newobsList);
					tableCelOddzial.setVisible(false);
					getSaldoFromList(newobsList);
					break;
				}

			}
			case "Kwartał 4": {
				System.out.println("Kwartał 4");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartalPracownik(10, 11, 12,
						choicePracownik.getSelectionModel().getSelectedItem().getId());

				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					tableCelOddzial.setVisible(false);
					saldoLabel.setText(null);
					break;
				} else {

					tableCelPracownik.setItems(newobsList);
					tableCelOddzial.setVisible(false);
					getSaldoFromList(newobsList);
					break;
				}
			}
			}
		} catch (NullPointerException ex) {
			// tableView.setItems(null);
		}

	}

	public void searchByPracownikMiesiac() {
		System.out.println("Wybrano pracownika i miesiąc");
		try {
			ObservableList<Cel> obsList = CelDAO.getCelForMiesiacPracownik(
					choiceMiesiac.getSelectionModel().getSelectedItem().getId(),
					choicePracownik.getSelectionModel().getSelectedItem().getId());

			if (obsList == null) {
				tableCelPracownik.setItems(null);
				// tableCelOddzial.setVisible(false);
			} else {
				System.out.println("0 != " + obsList.size());
				tableCelPracownik.setItems(obsList);
				// tableCelOddzial.setVisible(false);
				getSaldoFromList(obsList);
			}

		} catch (NullPointerException ex) {
			text.setText(ex.getMessage());
			System.out.println(ex.getMessage());
		}

	}

	public void searchByProduktPracownik() {
		System.out.println("Wybrano produkt i pracownika");
		try {
			ObservableList<Cel> obsList = CelDAO.getCelForProduktPracownik(
					choiceProdukt.getSelectionModel().getSelectedItem().getId(),
					choicePracownik.getSelectionModel().getSelectedItem().getId());

			if (obsList == null) {
				tableCelPracownik.setItems(null);
			} else {
				System.out.println("0 != " + obsList.size());
				tableCelPracownik.setItems(obsList);
				getSaldoFromList(obsList);
			}

		} catch (NullPointerException ex) {
			text.setText(ex.getMessage());
			System.out.println(ex.getMessage());
		}

	}

	public void searchByKwartalAndProdukt() {

		System.out.println("Wybrano produkt i kwartal");
		Label placeholder = new Label();
		placeholder.setText("Brak danych");

		try {
			switch (choiceKwartal.getSelectionModel().getSelectedItem()) {
			case "Kwartał 1": {
				System.out.println("Kwartał 1");
				List<Object[]> objListCelPracownik = CelDAO.getCelPracownikForKwartalProdukt(1, 2, 3,
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				List<Object[]> objListCelOddzial = CelDAO.getCelOddzialForKwartalProdukt(1, 2, 3,
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				List<Cel> listCelPracownik = new ArrayList<Cel>();
				List<Cel> listCelOddzial = new ArrayList<Cel>();
				ObservableList<Cel> newobsListPracownik = null;
				ObservableList<Cel> newobsListOddzial = null;
				for (Object[] o : objListCelPracownik) {
					listCelPracownik.add((Cel) o[0]);
					newobsListPracownik = FXCollections.observableArrayList(listCelPracownik);
				}
				for (Object[] o : objListCelOddzial) {
					Produkt p = (Produkt) o[0];
					Miesiac m = (Miesiac) o[1];
					Long wartosc = (Long) o[2];
					Long wynik = (Long) o[3];
					Long bilans = (Long) o[4];
					Cel c = new Cel();
					c.setIdProdukt(p);
					c.setIdMiesiac(m);
					c.setWartosc(wartosc.intValue());
					c.setWynik(wynik.intValue());
					c.setBilans(bilans.intValue());
					listCelOddzial.add(c);
					newobsListOddzial = FXCollections.observableArrayList(listCelOddzial);
				}

				if (newobsListPracownik == null || newobsListOddzial == null) {
					tableCelPracownik.setItems(null);
					tableCelOddzial.setItems(null);
					tableCelPracownik.setPlaceholder(placeholder);
					saldoLabel.setText(null);
					break;
				} else {
					System.out.println("0 != " + newobsListPracownik.size());
					tableCelPracownik.setItems(newobsListPracownik);
					tableCelOddzial.setItems(newobsListOddzial);
					getSaldoFromList(newobsListPracownik);
					break;
				}

			}
			case "Kwartał 2": {
				System.out.println("Kwartał 2");
				List<Object[]> objListCelPracownik = CelDAO.getCelPracownikForKwartalProdukt(4, 5, 6,
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				List<Object[]> objListCelOddzial = CelDAO.getCelOddzialForKwartalProdukt(4, 5, 6,
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				List<Cel> listCelPracownik = new ArrayList<Cel>();
				List<Cel> listCelOddzial = new ArrayList<Cel>();
				ObservableList<Cel> newobsListPracownik = null;
				ObservableList<Cel> newobsListOddzial = null;
				for (Object[] o : objListCelPracownik) {
					listCelPracownik.add((Cel) o[0]);
					newobsListPracownik = FXCollections.observableArrayList(listCelPracownik);
				}
				for (Object[] o : objListCelOddzial) {
					Produkt p = (Produkt) o[0];
					Miesiac m = (Miesiac) o[1];
					Long wartosc = (Long) o[2];
					Long wynik = (Long) o[3];
					Long bilans = (Long) o[4];
					Cel c = new Cel();
					c.setIdProdukt(p);
					c.setIdMiesiac(m);
					c.setWartosc(wartosc.intValue());
					c.setWynik(wynik.intValue());
					c.setBilans(bilans.intValue());
					listCelOddzial.add(c);
					newobsListOddzial = FXCollections.observableArrayList(listCelOddzial);
				}

				if (newobsListPracownik == null || newobsListOddzial == null) {
					tableCelPracownik.setItems(null);
					tableCelOddzial.setItems(null);
					tableCelPracownik.setPlaceholder(placeholder);
					saldoLabel.setText(null);
					break;
				} else {
					System.out.println("0 != " + newobsListPracownik.size());
					tableCelPracownik.setItems(newobsListPracownik);
					tableCelOddzial.setItems(newobsListOddzial);
					getSaldoFromList(newobsListPracownik);
					break;
				}
			}
			case "Kwartał 3": {
				System.out.println("Kwartał 3");
				List<Object[]> objListCelPracownik = CelDAO.getCelPracownikForKwartalProdukt(7, 8, 9,
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				List<Object[]> objListCelOddzial = CelDAO.getCelOddzialForKwartalProdukt(7, 8, 9,
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				List<Cel> listCelPracownik = new ArrayList<Cel>();
				List<Cel> listCelOddzial = new ArrayList<Cel>();
				ObservableList<Cel> newobsListPracownik = null;
				ObservableList<Cel> newobsListOddzial = null;
				for (Object[] o : objListCelPracownik) {
					listCelPracownik.add((Cel) o[0]);
					newobsListPracownik = FXCollections.observableArrayList(listCelPracownik);
				}
				for (Object[] o : objListCelOddzial) {
					Produkt p = (Produkt) o[0];
					Miesiac m = (Miesiac) o[1];
					Long wartosc = (Long) o[2];
					Long wynik = (Long) o[3];
					Long bilans = (Long) o[4];
					Cel c = new Cel();
					c.setIdProdukt(p);
					c.setIdMiesiac(m);
					c.setWartosc(wartosc.intValue());
					c.setWynik(wynik.intValue());
					c.setBilans(bilans.intValue());
					listCelOddzial.add(c);
					newobsListOddzial = FXCollections.observableArrayList(listCelOddzial);
				}

				if (newobsListPracownik == null || newobsListOddzial == null) {
					tableCelPracownik.setItems(null);
					tableCelOddzial.setItems(null);
					tableCelPracownik.setPlaceholder(placeholder);
					saldoLabel.setText(null);
					break;
				} else {
					System.out.println("0 != " + newobsListPracownik.size());
					tableCelPracownik.setItems(newobsListPracownik);
					tableCelOddzial.setItems(newobsListOddzial);
					getSaldoFromList(newobsListPracownik);
					break;
				}

			}
			case "Kwartał 4": {
				System.out.println("Kwartał 4");
				List<Object[]> objListCelPracownik = CelDAO.getCelPracownikForKwartalProdukt(10, 11, 12,
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				List<Object[]> objListCelOddzial = CelDAO.getCelOddzialForKwartalProdukt(10, 11, 12,
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				List<Cel> listCelPracownik = new ArrayList<Cel>();
				List<Cel> listCelOddzial = new ArrayList<Cel>();
				ObservableList<Cel> newobsListPracownik = null;
				ObservableList<Cel> newobsListOddzial = null;
				for (Object[] o : objListCelPracownik) {
					listCelPracownik.add((Cel) o[0]);
					newobsListPracownik = FXCollections.observableArrayList(listCelPracownik);
				}
				for (Object[] o : objListCelOddzial) {
					Produkt p = (Produkt) o[0];
					Miesiac m = (Miesiac) o[1];
					Long wartosc = (Long) o[2];
					Long wynik = (Long) o[3];
					Long bilans = (Long) o[4];
					Cel c = new Cel();
					c.setIdProdukt(p);
					c.setIdMiesiac(m);
					c.setWartosc(wartosc.intValue());
					c.setWynik(wynik.intValue());
					c.setBilans(bilans.intValue());
					listCelOddzial.add(c);
					newobsListOddzial = FXCollections.observableArrayList(listCelOddzial);
				}

				if (newobsListPracownik == null || newobsListOddzial == null) {
					tableCelPracownik.setItems(null);
					tableCelOddzial.setItems(null);
					tableCelPracownik.setPlaceholder(placeholder);
					saldoLabel.setText(null);
					break;
				} else {
					System.out.println("0 != " + newobsListPracownik.size());
					tableCelPracownik.setItems(newobsListPracownik);
					tableCelOddzial.setItems(newobsListOddzial);
					getSaldoFromList(newobsListPracownik);
					break;
				}

			}

			}
		} catch (NullPointerException ex) {
			// tableView.setItems(null);
		}

	}

	public void searchByKwartal() {
		System.out.println("Wybrano kwartał");
		try {
			switch (choiceKwartal.getSelectionModel().getSelectedItem()) {
			case "Kwartał 1": {
				System.out.println("Kwartał 1");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartal(1, 2, 3);
				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					saldoLabel.setText("Brak danych");
					break;
				} else {
					tableCelPracownik.setItems(newobsList);
					getSaldoFromList(newobsList);
					break;
				}
			}
			case "Kwartał 2": {
				System.out.println("Kwartał 2");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartal(4, 5, 6);
				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					saldoLabel.setText("Brak danych");
					break;
				} else {
					tableCelPracownik.setItems(newobsList);
					getSaldoFromList(newobsList);
					break;
				}
			}
			case "Kwartał 3": {
				System.out.println("Kwartał 3");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartal(7, 8, 9);
				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					saldoLabel.setText("Brak danych");
					break;
				} else {
					tableCelPracownik.setItems(newobsList);
					getSaldoFromList(newobsList);
					break;
				}
			}
			case "Kwartał 4": {
				System.out.println("Kwartał 4");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartal(10, 11, 12);
				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					saldoLabel.setText("Brak danych");
					break;
				} else {
					tableCelPracownik.setItems(newobsList);
					getSaldoFromList(newobsList);
					break;
				}
			}
			}
		} catch (NullPointerException ex) {
			// tableView.setItems(null);
		}
	}

	public void searchByProdukt() {
		System.out.println("Wybrano produkt");
		try {
			ObservableList<Cel> obsList = CelDAO
					.getCelForProdukt(choiceProdukt.getSelectionModel().getSelectedItem().getId());

			if (obsList == null) {
				tableCelPracownik.setItems(null);
			} else {
				System.out.println("0 != " + obsList.size());
				tableCelPracownik.setItems(obsList);
				getSaldoFromList(obsList);
			}

		} catch (NullPointerException ex) {
			text.setText(ex.getMessage());
			System.out.println(ex.getMessage());
		}

	}

	public void searchByMiesiac() {
		System.out.println("Wybrano miesiac");
		try {
			ObservableList<Cel> obsList = CelDAO
					.getCelForMiesiac(choiceMiesiac.getSelectionModel().getSelectedItem().getId());

			if (obsList == null) {
				tableCelPracownik.setItems(null);
				saldoLabel.setText("Brak danych");
			} else {
				tableCelPracownik.setItems(obsList);
				getSaldoFromList(obsList);
			}

		} catch (NullPointerException ex) {
			text.setText(ex.getMessage());
			System.out.println(ex.getMessage());
		}

	}

	public void searchByMiesiacAndProdukt() {

		System.out.println("Wybrano produkt i miesiąc");
		try {

			Label placeholder = new Label();
			placeholder.setText("Brak danych");

			ObservableList<Cel> newobsListPracownik = CelDAO.getCelPracownikForMiesiacProdukt(
					choiceMiesiac.getSelectionModel().getSelectedItem().getId(),
					choiceProdukt.getSelectionModel().getSelectedItem().getId());

			List<Object[]> objListCelOddzial = CelDAO.getCelOddzialForMiesiacProdukt(
					choiceMiesiac.getSelectionModel().getSelectedItem().getId(),
					choiceProdukt.getSelectionModel().getSelectedItem().getId());

			List<Cel> listCelOddzial = new ArrayList<Cel>();
			ObservableList<Cel> newobsListOddzial = null;

			for (Object[] o : objListCelOddzial) {
				Produkt p = (Produkt) o[0];
				Miesiac m = (Miesiac) o[1];
				Long wartosc = (Long) o[2];
				Long wynik = (Long) o[3];
				Long bilans = (Long) o[4];
				Cel c = new Cel();
				c.setIdProdukt(p);
				c.setIdMiesiac(m);
				c.setWartosc(wartosc.intValue());
				c.setWynik(wynik.intValue());
				c.setBilans(bilans.intValue());
				listCelOddzial.add(c);
				newobsListOddzial = FXCollections.observableArrayList(listCelOddzial);
			}
			System.out.println("przed if");

			if (newobsListPracownik == null || newobsListOddzial == null) {
				tableCelPracownik.setItems(null);
				tableCelOddzial.setItems(null);
				// tableCelPracownik.setPlaceholder(placeholder);
				// tableCelOddzial.setPlaceholder(placeholder);
				saldoLabel.setText(null);

			} else {
				System.out.println("0 != " + newobsListPracownik.size());
				System.out.println("za else");
				tableCelPracownik.setItems(newobsListPracownik);
				tableCelOddzial.setItems(newobsListOddzial);
				getSaldoFromList(newobsListPracownik);
				// text.setText("Znaleziono");
			}

		} catch (NullPointerException ex) {
			// text.setText("Znaleziono ");
			// tableView.setItems(null);

		}

	}

	public void searchByMiesiacPracownikProdukt() {
		System.out.println("Wybrano miesiac, pracownika, produkt");
		try {
			ObservableList<Cel> obsList = CelDAO.getCelForMiesiacPracownikProdukt(
					choiceMiesiac.getSelectionModel().getSelectedItem().getId(),
					choicePracownik.getSelectionModel().getSelectedItem().getId(),
					choiceProdukt.getSelectionModel().getSelectedItem().getId());

			if (obsList == null) {
				tableCelPracownik.setItems(null);
				// tableCelOddzial.setVisible(false);
			} else {
				System.out.println("0 != " + obsList.size());
				tableCelPracownik.setItems(obsList);
				// tableCelOddzial.setVisible(false);
				getSaldoFromList(obsList);
			}

		} catch (NullPointerException ex) {
			text.setText(ex.getMessage());
			System.out.println(ex.getMessage());
		}

	}

	public void searchByKwartalPracownikProdukt() {
		System.out.println("Wybrano kwartal pracownika produkt");
		Label placeholder = new Label();
		placeholder.setText("Brak danych");

		try {
			switch (choiceKwartal.getSelectionModel().getSelectedItem()) {
			case "Kwartał 1": {
				System.out.println("Kwartał 1");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartalPracownikProdukt(1, 2, 3,
						choicePracownik.getSelectionModel().getSelectedItem().getId(),
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					// tableCelOddzial.setVisible(false);
					saldoLabel.setText(null);
					break;

				} else {
					tableCelPracownik.setItems(newobsList);
					// tableCelOddzial.setVisible(false);
					getSaldoFromList(newobsList);
					break;
				}

			}
			case "Kwartał 2": {
				System.out.println("Kwartał 2");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartalPracownikProdukt(4, 5, 6,
						choicePracownik.getSelectionModel().getSelectedItem().getId(),
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					// tableCelOddzial.setVisible(false);
					saldoLabel.setText(null);
					break;

				} else {
					tableCelPracownik.setItems(newobsList);
					// tableCelOddzial.setVisible(false);
					getSaldoFromList(newobsList);
					break;
				}
			}
			case "Kwartał 3": {
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartalPracownikProdukt(7, 8, 9,
						choicePracownik.getSelectionModel().getSelectedItem().getId(),
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					// tableCelOddzial.setVisible(false);
					saldoLabel.setText(null);
					break;

				} else {
					tableCelPracownik.setItems(newobsList);
					// tableCelOddzial.setVisible(false);
					getSaldoFromList(newobsList);
					break;
				}
			}
			case "Kwartał 4": {
				System.out.println("Kwartał 4");
				ObservableList<Cel> newobsList = CelDAO.getCelForKwartalPracownikProdukt(10, 11, 12,
						choicePracownik.getSelectionModel().getSelectedItem().getId(),
						choiceProdukt.getSelectionModel().getSelectedItem().getId());

				if (newobsList == null) {
					tableCelPracownik.setItems(null);
					// tableCelOddzial.setVisible(false);
					saldoLabel.setText(null);
					break;

				} else {
					tableCelPracownik.setItems(newobsList);
					// tableCelOddzial.setVisible(false);
					getSaldoFromList(newobsList);
					break;
				}
			}

			}
		} catch (NullPointerException ex) {
			// tableView.setItems(null);
		}

	}
}
