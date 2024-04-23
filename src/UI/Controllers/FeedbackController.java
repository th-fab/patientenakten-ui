package UI.Controllers;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class FeedbackController {
    String feedbackMessage;
    @FXML
    private TextArea feedbackText;
    public RatingController parentController;
    public void initialize() {
        feedbackText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                feedbackMessage = feedbackText.getText().replace(System.getProperty("line.separator"), "");
                //text = text.replace(System.getProperty("line.separator"), "");
                parentController.ratingDone();
                event.consume();
            }
        });

    }

    public void setParentController(RatingController rtc) {
        this.parentController = rtc;
    }

    public String getFeedbackMessage() {
        feedbackMessage = feedbackText.getText().replace(System.getProperty("line.separator"), "");
        return feedbackMessage;
    }

    public void resetForm() {
        feedbackText.setText("");
    }

    public void focusText() {
        Platform.runLater(() -> {
            feedbackText.requestFocus();
        });
    }
}
