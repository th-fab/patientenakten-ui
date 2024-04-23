package UI;

import Base.FileSystem;
import Base.Patient;
import Base.PatientManager;
import Base.Rating;
import UI.Controllers.AdminPanelController;
import UI.Controllers.MainWindowController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    private FXMLLoader loader;
    private Parent user;
    private Parent admin;
    private AdminPanelController adminCtrl;
    private MainWindowController ctrl;
    @Override
    public void start(Stage primaryStage) throws Exception {

        initBase();

        loader = new FXMLLoader();
        user = loader.load(getClass().getResource("/UI/FXML/mainWindow.fxml"));
        ctrl = loader.getController();

        KeyCodeCombination loadAdminPageCombination = new KeyCodeCombination(
                KeyCode.A,
                KeyCombination.SHORTCUT_DOWN,
                KeyCombination.SHIFT_DOWN,
                KeyCombination.ALT_DOWN
        );

        primaryStage.setTitle("rate-clinic");
        primaryStage.setScene(new Scene(user, 1280, 960));
        //primaryStage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("CTRL+Q"));
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setMinHeight(960);
        primaryStage.setMinWidth(1280);
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/UI/img/cross.png")));
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(loadAdminPageCombination.match(event)) {
                loadAdminPage();
                primaryStage.setScene(new Scene(admin, 1280, 960));
                primaryStage.show();
                FadeTransition ft = new FadeTransition(Duration.millis(1000), admin);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                event.consume();
            }
        });
        primaryStage.show();
    }

    public void loadAdminPage() {
        try {
            FXMLLoader adminPageLoader = new FXMLLoader(getClass().getResource("/UI/FXML/adminPanel.fxml"));
            admin = adminPageLoader.load();
            adminCtrl = adminPageLoader.getController();
        } catch (Exception e) {
            System.out.println("Error loaing admin file");
        }
    }

    public void initBase() {
        String root = System.getProperty("user.dir");

        new FileSystem(root);
        PatientManager patientManager = PatientManager.getInstance();


    }

    public static void main(String[] args) {
        launch(args);
    }
}