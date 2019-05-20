package component.Controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


import component.Hibernate.DAO.CelDAO;
import component.Hibernate.Entity.Cel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.Line;

public class Wykres<X, Y> implements Initializable {



	@FXML
	private BarChart<String, Number> barChart;

	@FXML
	private CategoryAxis xBar;

	@FXML
	private NumberAxis yBar;

	@FXML
	private ChoiceBox<Cel> celChoice;

	@FXML
	private Button addButton;

	@FXML
	private Button clearButton;

	ObservableList<Cel> celList = CelDAO.getCelPracownikList();


	@FXML
	void addButton(ActionEvent event) {
		Cel c = celChoice.getSelectionModel().getSelectedItem();

		XYChart.Series<String, Number> seriesBarChart = new XYChart.Series<String, Number>();

		

		seriesBarChart.getData().add(new XYChart.Data<String, Number>(c.getIdMiesiac().getMiesiac(), c.getWynik()));

	

		seriesBarChart.setName(c.getIdPracownik().toString() +  " " + c.getIdProdukt().toString() + " " + c.getIdMiesiac().toString());

		

		barChart.getData().add(seriesBarChart);
	//	barChart.getData().add(seriesBarChart2);
	//	barChart.getData().add(seriesBarChart3);
		// lineChart.getData().add(seriesLineChart);

		// borderPane.getChildren().add(lineChart);

	}

	@FXML
	void clearButton(ActionEvent event) {

		barChart.getData().clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		celChoice.setItems(celList);
		// add();

	}
}
