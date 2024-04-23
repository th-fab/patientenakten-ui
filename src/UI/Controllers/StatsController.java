package UI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatsController implements Initializable {

    @FXML
    private BorderPane bpStats;
    @FXML
    private Label statsTitleLabel;

    private Pane chartsPage;

    private ChartsController chartsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader chartsLoader = new FXMLLoader(getClass().getResource("/UI/FXML/charts.fxml"));
            chartsPage = chartsLoader.load();
            chartsController = chartsLoader.getController();
            chartsController.setParentController(this);
        } catch (IOException e) {
            System.out.println("Error loading charts");
        }

        bpStats.setCenter(chartsPage);
    }

    public void showGeneralStats() {
        statsTitleLabel.setText("Bewertungen aller Patienten");

        ArrayList<XYChart.Series> series = UI.Helpers.StatsCompiler.compileSeries(UI.Helpers.StatsCompiler.getAllRatings());
        setDataSet(series);
        chartsController.showCharts();
    }

    public void showStatsFor(String patNr) {
        Base.PatientManager pm = Base.PatientManager.getInstance();
        Base.Patient p = pm.findPatient(patNr);

        setTitle(String.format("Bewertungen für: %s %s, %s", p.getPatientNumber(), p.getLastName(), p.getFirstName()));
        ArrayList<XYChart.Series> series = UI.Helpers.StatsCompiler.compileSeries(p.getRatings());
        setDataSet(series);
        chartsController.showCharts();
    }

    public void setDataSet(ArrayList<XYChart.Series> series) {
        chartsController.setCommonData(series.get(0));
        chartsController.setWellbeingData(series.get(1));
        chartsController.setTreatmentData(series.get(2));
        chartsController.setStaffData(series.get(3));
    }

   public ChartsController getChartsController(){
        return this.chartsController;
   }

   public void setTitle(String title) {
        statsTitleLabel.setText(title);
   }
}
