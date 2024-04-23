package UI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartsController implements Initializable {

    @FXML
    private BarChart<?, ?> commonChart;
    @FXML
    private BarChart<?, ?> staffChart;
    @FXML
    private BarChart<?, ?> treatmentChart;
    @FXML
    private BarChart<?, ?> wellbeingChart;


    private XYChart.Series commonData;
    private XYChart.Series wellbeingData;
    private XYChart.Series treatmentData;
    private XYChart.Series staffData;

    private StatsController parentController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonChart.setTitle("Allgemeine Zufriedenheit");
        wellbeingChart.setTitle("Wohlbefinden");
        treatmentChart.setTitle("Behandlung");
        staffChart.setTitle("Personal");

    }

    public void showCharts() {
        commonChart.getData().clear();
        wellbeingChart.getData().clear();
        treatmentChart.getData().clear();
        staffChart.getData().clear();

        commonChart.getData().add(commonData);
        wellbeingChart.getData().add(wellbeingData);
        treatmentChart.getData().add(treatmentData);
        staffChart.getData().add(staffData);
    }

    public void setParentController(StatsController stc) {
        this.parentController = stc;
    }

    public void setCommonData(XYChart.Series commonData) {
        this.commonData = commonData;
    }

    public void setWellbeingData(XYChart.Series wellbeingData) {
        this.wellbeingData = wellbeingData;
    }

    public void setTreatmentData(XYChart.Series treatmentData) {
        this.treatmentData = treatmentData;
    }

    public void setStaffData(XYChart.Series staffData) {
        this.staffData = staffData;
    }
}
