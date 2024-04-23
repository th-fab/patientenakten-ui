package UI.Controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class WellbeingController {

    @FXML private ImageView imViewStellar;
    @FXML private ImageView imViewGood;
    @FXML private ImageView imViewMid;
    @FXML private ImageView imViewBad;
    @FXML private ImageView imViewVeryBad;
    @FXML
    public HBox hbox_select;
    @FXML
    public BorderPane bp_sehr_gut;
    @FXML
    public BorderPane bp_gut;
    @FXML
    public BorderPane bp_mittel;
    @FXML
    public BorderPane bp_schlecht;
    @FXML
    public BorderPane bp_sehr_schlecht;
    RatingController parentController;

    private ArrayList<BorderPane> panels;

    private int selectedIndex;

    public void initialize() {
        //noinspection DuplicatedCode
        hbox_select.setAlignment(Pos.CENTER);

        panels = new ArrayList<>();
        panels.add(bp_sehr_gut);
        panels.add(bp_gut);
        panels.add(bp_mittel);
        panels.add(bp_schlecht);
        panels.add(bp_sehr_schlecht);

        selectedIndex = 2;
        selectPanel(bp_mittel);

        for(BorderPane bp : panels) {
            bp.setOnMouseClicked(event -> {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    selectPanel(bp);
                    selectedIndex = panels.indexOf(bp);
                }
            });
        }
        addImage();
    }


    public void resetForm() {
        this.selectedIndex = 2;
        selectPanel(bp_mittel);
    }
    public void selectPanel(BorderPane bp) {
        for( BorderPane bps : panels) {
            if (bps != bp) {
                deselectPanel(bps);
            } else {
                bps.getStyleClass().clear();
                bps.getStyleClass().add("selected-card");
                bps.setOpacity(1);
            }

        }
    }


    public int getSelectedIndex() {
        return this.selectedIndex;
    }
    public void deselectPanel(BorderPane bp) {
        bp.getStyleClass().clear();
        bp.getStyleClass().add("selectorcard");
        bp.setOpacity(0.3);
    }

    public void setParentController(RatingController rtc) {
        this.parentController = rtc;
    }

    public void addImage() {
        try {
            imViewStellar.setImage(new Image(getClass().getResourceAsStream("/UI/img/stellar.PNG"), 100, 100, true, true));
            imViewGood.setImage(new Image(getClass().getResourceAsStream("/UI/img/good.PNG"), 100, 100, true, true));
            imViewMid.setImage(new Image(getClass().getResourceAsStream("/UI/img/medium.PNG"), 100, 100, true, true));
            imViewBad.setImage(new Image(getClass().getResourceAsStream("/UI/img/less_sad.PNG"), 100, 100, true, true));
            imViewVeryBad.setImage(new Image(getClass().getResourceAsStream("/UI/img/sad.PNG"), 100, 100, true, true));

        } catch (Exception e) {
            System.out.println("Error loading image");
        }
    }
}
