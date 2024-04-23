package UI.Controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("DuplicatedCode")
public class MainWindowController {

    @FXML private BorderPane bp;
    @FXML
    private Label label;
    @FXML
    private Pane loginTile;
    @FXML
    private Pane ratingTile;
    @FXML
    private Pane doneTile;
    @FXML
    private Label loginLabel;
    @FXML
    private Label ratingLabel;
    @FXML
    private Label doneLabel;
    @FXML Pane mainWindowContentPane;

    private Pane ratingPage;
    private Pane loginPage;
    private Pane overviewPage;
    public HashMap<String, Pane> panes;
    private LoginController loginController;
    private RatingController ratingController;
    private OverviewController overviewController;

    public void initialize() {

        try {

            FXMLLoader loginPageLoader = new FXMLLoader(getClass().getResource("/UI/FXML/login.fxml"));
            loginPage = loginPageLoader.load();
            loginController = loginPageLoader.getController();
            loginController.setParentController(this);
            System.out.println("login loaded");

            FXMLLoader ratingPageLoader = new FXMLLoader(getClass().getResource("/UI/FXML/rating.fxml"));
            ratingPage = ratingPageLoader.load();
            ratingController = ratingPageLoader.getController();
            ratingController.setParentController(this);
            System.out.println("rating loaded");

            FXMLLoader overviewPageLoader = new FXMLLoader(getClass().getResource("/UI/FXML/overview.fxml"));
            overviewPage = overviewPageLoader.load();
            overviewController = overviewPageLoader.getController();
            overviewController.setParentController(this);
            overviewController.setRatingController(ratingController);
            System.out.println("overview loaded");

        } catch (IOException e) {
            System.out.println("error loading files");
        }

        panes = new HashMap<>();
        panes.put("login", loginTile);
        panes.put("rating", ratingTile);
        panes.put("done", doneTile);

        loadLoginPage();
    }

    public void setActivePane(String tile) {

        Pane activePane = panes.get(tile);
        activePane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-radius: 10");
        Label l = (Label)activePane.getChildren().get(0);
        l.setStyle("-fx-text-fill: #000000; -fx-font-family: Comfortaa SemiBold; -fx-font-size: 30;");

        //setting all other tiles inactive
        Iterator entries = panes.entrySet().iterator();

        while(entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();

            String key = (String)entry.getKey();
            Pane val = (Pane)entry.getValue();

            if(!key.equals(tile)) {
                setInactive(key);
            }
        }
    }

    public void setInactive(String tile) {
        Pane activePane = panes.get(tile);
        activePane.setStyle("-fx-background-color: #20738a; -fx-background-radius: 10; -fx-border-radius: 10;");
        Label l = (Label)activePane.getChildren().get(0);
        l.setStyle("-fx-text-fill: WHITE; -fx-font-family: Comfortaa SemiBold; -fx-font-size: 30;");
    }

    public void loadRatingPage() {
        bp.setCenter(ratingPage);
        setActivePane("rating");
        fadeIn(ratingPage);
        ratingController.viewWellbeing();
    }

    public void loadLoginPage() {
        loginController.resetForm();
        loginController.setRatingController(ratingController);
        loginController.setOverviewController(overviewController);
        bp.setCenter(loginPage);
        fadeIn(loginPage);
        setActivePane("login");
    }

    public void loadOverviewPage() {
        bp.setCenter(overviewPage);
        fadeIn(overviewPage);
        overviewController.updateValues();
        setActivePane("done");
    }

    public void fadeIn(Node target) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), target);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}