package UI.Controllers;

import Base.Patient;
import Base.PatientManager;
import Base.Rating;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DashboardController {
    @FXML
    private Label dateLabel;
    @FXML
    private Label patientsLabel;
    @FXML
    private Label allRatingsLabel;
    @FXML
    private Label todayRatingsLabel;
    @FXML
    private MFXButton refreshButton;

    private PatientManager pm;

    public void initialize() {
        updateLabels();
    }

    @FXML
    private void updateLabels() {
        clearLabels();

        PatientManager pm = PatientManager.getInstance();

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        dateLabel.setText(date);

        int currentPatients = pm.getAmountOfPatient();
        patientsLabel.setText(String.valueOf(currentPatients));

        int amountTotalRatings = pm.getTotalAmountRatings();
        allRatingsLabel.setText(String.valueOf(amountTotalRatings));

        ArrayList<Patient> allPatients = pm.getPatients();
        int amountRatingsToday = 0;
        for(Patient p : allPatients) {
            String lastRatingDate = p.getDateStringOfLastRating();
            if(date.equals(lastRatingDate)) {
                amountRatingsToday += 1;
            }
        }

        todayRatingsLabel.setText(String.valueOf(amountRatingsToday));
    }

    private void clearLabels() {
        dateLabel.setText("");
        patientsLabel.setText("");
        allRatingsLabel.setText("");
        todayRatingsLabel.setText("");
    }

}
