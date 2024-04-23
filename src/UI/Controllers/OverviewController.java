package UI.Controllers;

import Base.Patient;
import Base.Rating;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OverviewController {

    @FXML
    private Label wellbeingLabel;
    @FXML private Label commonLabel;
    @FXML private Label staffLabel;
    @FXML private Label treatmentLabel;
    @FXML private MFXButton doneButton;
    private int wellbeingRating;
    private int commonRating;
    private int staffRating;
    private int treatmentRating;
    private String feedbackMessage;

    private MainWindowController parentController;
    private RatingController ratingController;

    private Patient currentPatient;
    private Rating currentRating;

    public void initialize() {
        wellbeingRating = 0;
        commonRating = 0;
        staffRating = 0;
        treatmentRating = 0;
    }

    public void updateValues() {
        currentRating = new Rating();
        currentPatient = ratingController.getCurrentPatient();

        wellbeingRating = ratingController.getWellbeingRating();
        commonRating = ratingController.getCommonRating();
        staffRating = ratingController.getStaffRating();
        treatmentRating = ratingController.getTreatmentRating();
        feedbackMessage = ratingController.getFeedbackMessage();

        currentRating.setDate(Rating.getCurrentDateTime());
        currentRating.setStaff(staffRating);
        currentRating.setFeedbackMessage(feedbackMessage);
        currentRating.setWellbeing(wellbeingRating);
        currentRating.setCommon(commonRating);
        currentRating.setTreatment(treatmentRating);

        updateLabel(wellbeingLabel, wellbeingRating);
        updateLabel(commonLabel, commonRating);
        updateLabel(staffLabel, staffRating);
        updateLabel(treatmentLabel, treatmentRating);
    }

    public void setCurrentPatient(Patient p) {
        currentPatient = p;
    }

    private void updateLabel(Label label, int val) {
        switch (val) {
            case 0 -> {
                label.setText("Sehr Gut");
                label.setStyle(getValueColor(0));
            }
            case 1 -> {
                label.setText("Gut");
                label.setStyle(getValueColor(1));
            }
            case 2 -> {
                label.setText("Mittel");
                label.setStyle(getValueColor(2));
            }
            case 3 -> {
                label.setText("Schlecht");
                label.setStyle(getValueColor(3));
            }
            case 4 -> {
                label.setText("Sehr Schlecht");
                label.setStyle(getValueColor(4));
            }
        }
    }

    private String getValueColor(int val) {
        StringBuilder sb = new StringBuilder();

        sb.append("-fx-font-family: Comfortaa SemiBold; ");
        sb.append("-fx-font-size: 27; ");

        switch (val) {
            case 0 -> sb.append("-fx-text-fill: #00b50c; ");
            case 1 -> sb.append("-fx-text-fill: #318a01; ");
            case 2 -> sb.append("-fx-text-fill: #616e02; ");
            case 3 -> sb.append("-fx-text-fill: #6e3402; ");
            case 4 -> sb.append("-fx-text-fill: #8a0101; ");
            default -> sb.append("-fx-text-fill: #000000; ");
        }
        return sb.toString();
    }

    @FXML
    private void submitRating() {
        currentPatient.addRating(currentRating);

        ratingController.resetAll();
        parentController.loadLoginPage();
    }



    public void setParentController(MainWindowController mwc) {
        this.parentController = mwc;
    }
    public void setRatingController(RatingController rtc) {
        ratingController = rtc;
    }
}
