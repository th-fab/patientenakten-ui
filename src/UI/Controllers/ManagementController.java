package UI.Controllers;

import Base.Patient;
import Base.PatientManager;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.Optional;

public class ManagementController {
    public Label nummerLabel;
    public Label nameLabel;
    public Label nachnameLabel;
    public MFXButton deletePatientButton;
    public TextField numberTextField;
    public TextField vornameTextField;
    public TextField nachnameTextField;
    public MFXButton buttonCheck;
    public MFXButton buttonAnlegen;
    public TextField textFieldSuchen;
    public MFXButton buttonSuchen;
    @FXML
    public MFXButton viewStatsButton;

    private AdminPanelController apc;
    private boolean isValidNumber;
    private Patient currentPatient;
    private PatientManager pm;



    public void initialize() {
        isValidNumber = false;
        pm = PatientManager.getInstance();
        currentPatient = null;
        deletePatientButton.setDisable(true);
        viewStatsButton.setDisable(true);
        buttonAnlegen.setDisable(true);
        buttonSuchen.setDisable(true);
        textFieldSuchen.textProperty().addListener((observable, oldValue, newValue) -> {
            checkTextSearch(oldValue, newValue);
        });
        numberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            disallowNumbers(oldValue, newValue);
        });
        vornameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            disallowSpecialCharacters(vornameTextField, oldValue, newValue);
        }));
        nachnameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            disallowSpecialCharacters(nachnameTextField, oldValue, newValue);
        }));

        textFieldSuchen.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER && buttonSuchen.isDisabled() == false ) {
                lookupPatient();
            }
        });

        numberTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                Platform.runLater(() -> new Runnable() {
                    @Override
                    public void run() {
                        vornameTextField.requestFocus();
                    }
                });
            }
        });
        vornameTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                Platform.runLater(() -> new Runnable() {
                    @Override
                    public void run() {
                        nachnameTextField.requestFocus();
                    }
                });
            }
        });
        nachnameTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                Platform.runLater(() -> new Runnable() {
                    @Override
                    public void run() {
                        checkData();
                    }
                });
            }
        });

        numberTextField.getStyleClass().add("txt");
        vornameTextField.getStyleClass().add("txt");
        nachnameTextField.getStyleClass().add("txt");
        textFieldSuchen.getStyleClass().add("txt");

        numberTextField.setAlignment(Pos.CENTER);
        vornameTextField.setAlignment(Pos.CENTER);
        nachnameTextField.setAlignment(Pos.CENTER);

        numberTextField.applyCss();
        vornameTextField.applyCss();
        nachnameTextField.applyCss();

    }

    @FXML
    private void createPatient() {
        String vorname = vornameTextField.getText();
        String nummer = numberTextField.getText();
        String nachname = nachnameTextField.getText();

        Patient p = new Patient(nummer, vorname, nachname);
        pm.addPatient(p);

        buttonAnlegen.setDisable(true);
        vornameTextField.setText("");
        numberTextField.setText("");
        nachnameTextField.setText("");
    }

    private String sanitize(String s) {
        return s.replaceAll("\\s+","");
    }

    @FXML
    private void viewStats() {
        apc.showStats(currentPatient.getPatientNumber());
    }
    private boolean showDeletionAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Löschen bestätigen");
        alert.setHeaderText("Patient wirklich löschen?");
        alert.setContentText("Alle gespeicherten Informationen und Dateien werden dabei unwiederruflich gelöscht!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
    @FXML
    private void removePatient() {

        if(showDeletionAlert() == false)
            return;
        pm.deletePatient(currentPatient.getPatientNumber());
        textFieldSuchen.setText("");
        deletePatientButton.setDisable(true);
        viewStatsButton.setDisable(true);
        buttonSuchen.setDisable(true);

        nameLabel.setText("Name");
        nachnameLabel.setText("Nachname");
        nummerLabel.setText("Nummer");
    }

    @FXML
    private void lookupPatient() {
        nummerLabel.setText(currentPatient.getPatientNumber());
        nameLabel.setText((currentPatient.getFirstName()));
        nachnameLabel.setText(currentPatient.getLastName());
        deletePatientButton.setDisable(false);
        if(currentPatient.getRatings() != null && currentPatient.getRatings().isEmpty() == false)
            viewStatsButton.setDisable(false);
        //textFieldSuchen.setText("");
    }

    @FXML
    private void checkData() {
        if(pm.findPatient(numberTextField.getText()) != null || numberTextField.getText().length() < 6) {
            numberTextField.getStyleClass().clear();
            numberTextField.getStyleClass().add("error");
            numberTextField.applyCss();
            return;
        }
        if(vornameTextField.getText().isEmpty()) {
            vornameTextField.getStyleClass().clear();
            vornameTextField.getStyleClass().add("error");
            vornameTextField.applyCss();
            return;
        }
        if(nachnameTextField.getText().isEmpty()) {
            nachnameTextField.getStyleClass().clear();
            nachnameTextField.getStyleClass().add("error");
            nachnameTextField.applyCss();
            return;
        }

        nachnameTextField.setText(sanitize(nachnameTextField.getText()));
        vornameTextField.setText(sanitize(vornameTextField.getText()));
        buttonAnlegen.setDisable(false);
    }

    private void disallowSpecialCharacters(TextField tf, String oldVal, String newVal) {
        tf.getStyleClass().clear();
        tf.getStyleClass().add("txt");
        buttonAnlegen.setDisable(true);
        if(newVal.matches("^[a-zA-Z]+$") || newVal.isEmpty() == true) {
            Platform.runLater(() ->
            {
                tf.setText(newVal);
                tf.positionCaret(newVal.length());

            });

        } else {
            Platform.runLater(() ->
            {

                tf.setText(oldVal);
                tf.positionCaret(oldVal.length());


            });
        }
    }
    @SuppressWarnings("DuplicatedCode")
    private void disallowNumbers(String oldVal, String newVal) {
        numberTextField.getStyleClass().clear();
        numberTextField.getStyleClass().add("txt");
        buttonAnlegen.setDisable(true);
        if((newVal.matches("^[0-9]*$") && newVal.length() <= 6) || newVal.isEmpty()) {

            Platform.runLater(() ->
            {
                numberTextField.setText(newVal);
                numberTextField.positionCaret(newVal.length());

            });

        } else {
            Platform.runLater(() ->
            {
                numberTextField.setText(oldVal);
                numberTextField.positionCaret(oldVal.length());
            });
        }
    }
    @SuppressWarnings("DuplicatedCode")
    private void checkTextSearch(String oldVal, String newVal) {
        deletePatientButton.setDisable(true);
        viewStatsButton.setDisable(true);
        if((newVal.matches("[0-9]*") && newVal.length() <= 6) || newVal.isEmpty()) {
            Platform.runLater(() ->
            {
                textFieldSuchen.setText(newVal);
                textFieldSuchen.positionCaret(newVal.length());

            });

        } else {
            Platform.runLater(() ->
            {
                textFieldSuchen.setText(oldVal);
                textFieldSuchen.positionCaret(oldVal.length());
            });
        }

        try {
            Patient p;
            p = pm.findPatient(newVal);
            if(p != null) {
                buttonSuchen.setDisable(false);
                currentPatient = p;
            } else {
                buttonSuchen.setDisable(true);
            }

        } catch (NumberFormatException ex) {
            buttonSuchen.setDisable(true);
        }

    }
    public void setAPC(AdminPanelController apc) {
        this.apc = apc;
    }
}
