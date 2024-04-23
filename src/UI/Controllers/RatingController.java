package UI.Controllers;

import Base.Patient;
import Base.Rating;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class RatingController {

    public Label currentPageLabel;

    private Pane wellbeingPage;
    private Pane treatmentPage;
    private Pane staffPage;
    private Pane commonPage;
    private Pane feedbackPage;


    private WellbeingController wbc;
    private TreatmentController tmc;
    private StaffController stc;
    private CommonController cmc;
    private FeedbackController fbc;

    private int currentID = 0;

    @FXML
    MFXProgressBar progressBar;
    @FXML
    Button buttonNext;
    @FXML
    Button buttonPrevious;
    @FXML
    BorderPane bp_rating;

    private Patient currentPatient;
    private Rating currentRating;
    private MainWindowController parentMainWindowController;

    public void initialize() {

        progressBar.setProgress(0);
        currentID = 1;
        currentRating = new Rating();
        currentPatient = null;

        try {
            System.out.println("loading wellbeing");
            FXMLLoader wbLoader = new FXMLLoader(getClass().getResource("/UI/FXML/wellbeing.fxml"));
            wellbeingPage = wbLoader.load();
            wbc = wbLoader.getController();
            wbc.setParentController(this);

            FXMLLoader tmLoader = new FXMLLoader(getClass().getResource("/UI/FXML/treatment.fxml"));
            treatmentPage = tmLoader.load();
            tmc = tmLoader.getController();
            tmc.setParentController(this);

            FXMLLoader cmLoader = new FXMLLoader(getClass().getResource("/UI/FXML/common.fxml"));
            commonPage = cmLoader.load();
            cmc = cmLoader.getController();
            cmc.setParentController(this);

            FXMLLoader fbLoader = new FXMLLoader(getClass().getResource("/UI/FXML/feedback.fxml"));
            feedbackPage = fbLoader.load();
            fbc = fbLoader.getController();
            fbc.setParentController(this);

            FXMLLoader stLoader = new FXMLLoader(getClass().getResource("/UI/FXML/staff.fxml"));
            staffPage = stLoader.load();
            stc = stLoader.getController();
            stc.setParentController(this);

            wbc.resetForm();

        } catch (IOException ioException) {
            System.out.println("Error loading wellbeing.fxml");
        }



    }

    public void disablePreviousButton() {
        buttonPrevious.setDisable(true);
    }

    public void enablePreviousButton() {
        buttonPrevious.setDisable(false);
    }

    public int getWellbeingRating() {
        return wbc.getSelectedIndex();
    }

    public int getCommonRating() {
        return cmc.getSelectedIndex();
    }

    public int getStaffRating() {
        return stc.getSelectedIndex();
    }

    public int getTreatmentRating() {
        return tmc.getSelectedIndex();
    }

    public String getFeedbackMessage() {
        return fbc.getFeedbackMessage();
    }

    public Patient getCurrentPatient() {
        return this.currentPatient;
    }
    public void viewNext() {
        switch (currentID) {
            case 1 -> viewCommon();
            case 2 -> viewStaff();
            case 3 -> viewTreatment();
            case 4 -> viewFeedback();
            case 5 -> ratingDone();
        }
    }

    public void viewPrevious() {
        switch (currentID) {
            case 1 -> viewFeedback();
            case 2 -> viewWellbeing();
            case 3 -> viewCommon();
            case 4 -> viewStaff();
            case 5 -> viewTreatment();
        }
    }

    public void ratingDone() {
        parentMainWindowController.loadOverviewPage();
    }

    public void resetAll() {
        wbc.resetForm();
        cmc.resetForm();
        stc.resetForm();
        tmc.resetForm();
        fbc.resetForm();
        currentPatient = null;
        currentRating = new Rating();
    }

    public void setCurrentPatient(Patient patient) {
        this.currentPatient = patient;
    }

    public void viewWellbeing() {
        disablePreviousButton();
        bp_rating.setCenter(wellbeingPage);
        fadeIn(wellbeingPage);
        currentPageLabel.setText("Schritt 1/5");
        progressBar.setProgress(0.2);
        currentID = 1;
    }

    public void viewCommon() {
        enablePreviousButton();
        bp_rating.setCenter(commonPage);
        fadeIn(commonPage);
        currentPageLabel.setText("Schritt 2/5");
        currentID = 2;
        progressBar.setProgress(0.4);
    }

    public void viewStaff() {
        bp_rating.setCenter((staffPage));
        fadeIn(staffPage);
        currentPageLabel.setText("Schritt 3/5");
        currentID = 3;
        progressBar.setProgress(0.6);
    }

    public void viewTreatment() {
        bp_rating.setCenter(treatmentPage);
        fadeIn(treatmentPage);
        currentPageLabel.setText("Schritt 4/5");
        currentID = 4;
        progressBar.setProgress(0.8);
    }

    public void viewFeedback() {
        bp_rating.setCenter(feedbackPage);
        fadeIn(feedbackPage);
        currentPageLabel.setText("Schritt 5/5");
        currentID = 5;
        fbc.focusText();
        progressBar.setProgress(1);
    }

    public void setParentController(MainWindowController controller) {
        this.parentMainWindowController = controller;
    }

    public void fadeIn(Node target) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), target);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
