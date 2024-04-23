package UI.Controllers;

import Base.Patient;
import Base.PatientManager;
import Base.Rating;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

public class LoginController {
    @FXML
    MFXTextField loginText;
    @FXML
    MFXButton loginButton;
    @FXML
    Label labelAnmeldung;
    private PatientManager pm;
    private Patient currentPatient;

    private RatingController rtc;
    private OverviewController ovc;
    private MainWindowController parentMainWindowController;

    public void initialize() {
        //xE2\x9C\x94
        //byte[] emojiByteCode = new byte[]{(byte)0xE2, (byte)0x9C, (byte)0x94};
        //String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
        //loginButton.setText(emoji + " Anmeldung");
        pm = PatientManager.getInstance();
        currentPatient = null;


        loginText.setAlignment(Pos.CENTER);
        loginButton.setDisable(true);
        loginText.textProperty().addListener((observable, oldValue, newValue) -> checkText(oldValue, newValue));
        loginText.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER && loginButton.isDisabled() == false ) {
                loadRatingPage();
            }
        });
    }

    public void loadRatingPage() {
        resetForm();
        parentMainWindowController.loadRatingPage();
        rtc.resetAll();
        rtc.setCurrentPatient(currentPatient);
        ovc.setCurrentPatient(currentPatient);
    }

    public void setOverviewController(OverviewController overviewController) {
        ovc = overviewController;
    }
    public void setRatingController(RatingController rtc) {
        this.rtc = rtc;
    }
    public void setParentController(MainWindowController mainWindowController) {
        this.parentMainWindowController = mainWindowController;
    }

    public void resetForm() {
        loginText.setText("");
        loginButton.setDisable(true);
    }

    private void checkText(String oldVal, String newVal) {
        if((newVal.matches("[0-9]*") && newVal.length() <= 6) || newVal.isEmpty() == true ) {
            Platform.runLater(() ->
            {
                loginText.setText(newVal);
                loginText.positionCaret(newVal.length());

            });

        } else {
            Platform.runLater(() ->
            {
                loginText.setText(oldVal);
                loginText.positionCaret(oldVal.length());
            });
        }

        try {
            Patient p;
            p = pm.findPatient(newVal);
            //int nbr = Integer.parseInt(newVal);
            if(p != null) {
                loginButton.setDisable(false);
                currentPatient = p;
            } else {
                loginButton.setDisable(true);
            }

        } catch (NumberFormatException ex) {
            loginButton.setDisable(true);
        }

    }

}
