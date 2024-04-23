package UI.Controllers;


import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("DuplicatedCode")
public class AdminPanelController {
    @FXML
    public Pane adminPanelContentPane;
    public Pane statsTile;
    public Label statsLabel;
    public Pane managementTile;
    public Label managementLabel;
    public BorderPane bp;
    public Pane dashboardTile;
    public Label dashboardLabel;

    private Pane dashboardPage;
    private Pane managementPage;
    private Pane statsPage;

    private DashboardController dbc;
    private ManagementController mmc;
    private StatsController stc;

    private HashMap<String, Pane> panes;

    public void initialize() {
        try {
            FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("/UI/FXML/dashboard.fxml"));
            dashboardPage = dashboardLoader.load();
            dbc = dashboardLoader.getController();

            FXMLLoader managementLoader = new FXMLLoader(getClass().getResource("/UI/FXML/management.fxml"));
            managementPage = managementLoader.load();
            mmc = managementLoader.getController();
            mmc.setAPC(this);

            FXMLLoader statsLoader = new FXMLLoader(getClass().getResource("/UI/FXML/stats.fxml"));
            statsPage = statsLoader.load();
            stc = statsLoader.getController();



        } catch (Exception e) {
            System.out.println("Error loading admin files");
        }

        dashboardTile.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                showDashboard();
            }
        });
        managementTile.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                showManagement();
            }
        });
        statsTile.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                showStats();
            }
        });

        panes = new HashMap<>();
        panes.put("dashboard", dashboardTile);
        panes.put("management", managementTile);
        panes.put("stats", statsTile);

        showDashboard();
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
    public void showDashboard() {
        bp.setCenter(dashboardPage);
        fadeIn(dashboardPage);
        setActivePane("dashboard");
    }

    public void showStats() {
        bp.setCenter(statsPage);
        fadeIn(statsPage);
        setActivePane("stats");
        stc.showGeneralStats();
    }

    public void showStats(String patNr) {
        bp.setCenter(statsPage);
        fadeIn(statsPage);
        setActivePane("stats");
        stc.showStatsFor(patNr);
    }



    public void showManagement() {
        bp.setCenter(managementPage);
        fadeIn(managementPage);
        setActivePane("management");
    }

    public void fadeIn(Node target) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), target);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

}
