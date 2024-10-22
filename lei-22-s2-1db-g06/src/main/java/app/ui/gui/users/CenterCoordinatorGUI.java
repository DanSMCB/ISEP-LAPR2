package app.ui.gui.users;

import app.controller.App;
import app.controller.Serialization;
import app.domain.shared.Constants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class CenterCoordinatorGUI extends Application implements Runnable{

    @FXML
    private Button performanceButton;
    @FXML
    private Button importDatabutton;

    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.CENTER_COORDINATOR_MAIN_MENU_FILE));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1550, 800);
        stage.setTitle("Center Coordinator");
        stage.setScene(scene);
        stage.show();
    }

    public void selectAnalyseCenterPerformance(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MenuOptions/CenterCoordinator/AnalyseCenterPerformanceUI.fxml"));
        performanceButton.getScene().setRoot(fxmlLoader.load());
    }

    public void selectExportStats(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MenuOptions/CenterCoordinator/ExportStatsMenu.fxml"));
        performanceButton.getScene().setRoot(fxmlLoader.load());
    }

    public void selectImportLegacySystemData(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MenuOptions/CenterCoordinator/ImportLegacySystemDataGUI.fxml"));
        importDatabutton.getScene().setRoot(fxmlLoader.load());
    }

    public void stop() {
        Platform.exit();
        Serialization.saveCompanyData(App.getInstance().getCompany(), Constants.SERIALIZABLE_FILE_PATH);
        System.exit(0);
    }

}
