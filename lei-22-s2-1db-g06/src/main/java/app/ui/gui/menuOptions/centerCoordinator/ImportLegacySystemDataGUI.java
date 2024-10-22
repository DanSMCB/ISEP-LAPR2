package app.ui.gui.menuOptions.centerCoordinator;

import app.controller.ImportLegacySystemDataController;
import app.domain.shared.Constants;
import app.ui.gui.utils.ImportedDataAux;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;

public class ImportLegacySystemDataGUI {
    private ImportLegacySystemDataController controller;
    @FXML
    private Button sort_data_btn1;
    @FXML
    private Button sort_data_btn2;
    @FXML
    private Button sort_data_btn3;
    @FXML
    private Button sort_data_btn4;
    @FXML
    private TableView<ImportedDataAux> data_table;
    @FXML
    private TableColumn<ImportedDataAux,String> snsUserNumberCol;
    @FXML
    private TableColumn<ImportedDataAux,String> snsUserNameCol;
    @FXML
    private TableColumn<ImportedDataAux,String> vaccineTypeCol;
    @FXML
    private TableColumn<ImportedDataAux,String> vaccineNameCol;
    @FXML
    private TableColumn<ImportedDataAux,String> doseNumberCol;
    @FXML
    private TableColumn<ImportedDataAux,String> lotNumberCol;
    @FXML
    private TableColumn<ImportedDataAux,String> scheduledDateTimeCol;
    @FXML
    private TableColumn<ImportedDataAux,String> arrivalDateTimeCol;
    @FXML
    private TableColumn<ImportedDataAux,String> dateOfAdministrationCol;
    @FXML
    private TableColumn<ImportedDataAux,String> leavingDateTimeCol;

    @FXML
    private File file;

    @FXML
    private Button confirm_button;

    @FXML
    private TextField file_path_field;

    @FXML
    private Button choose_file_button;
    @FXML
    private Button cancelBt;

    public ImportLegacySystemDataGUI() throws Exception {
        controller= new ImportLegacySystemDataController();
    }

    @FXML
    // This method is responsible for handling the events that occur when the user clicks on the buttons of the GUI.
    void handleClick(ActionEvent event) throws Exception {
        if (event.getSource()==choose_file_button){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load File");
            fileChooser.getExtensionFilters().setAll(
                    new FileChooser.ExtensionFilter("Input Files", "*.csv"),
                    new FileChooser.ExtensionFilter("CSV", "*.csv")
            );

            file = fileChooser.showOpenDialog(choose_file_button.getScene().getWindow());
            if (file != null) {
                confirm_button.setDisable(false);
                file_path_field.setText(file.getPath());
            }
        }
        else if (event.getSource()==confirm_button){
            controller.saveFileContent(file);
            controller.saveData();
            snsUserNumberCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("snsUserNumber"));
            snsUserNameCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("snsUserName"));
            vaccineTypeCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("vaccineType"));
            vaccineNameCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("vaccineName"));
            doseNumberCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("doseNumber"));
            lotNumberCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("lotNumber"));
            scheduledDateTimeCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("scheduledDateTime"));
            arrivalDateTimeCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("arrivalDateTime"));
            dateOfAdministrationCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("dateOfAdministration"));
            leavingDateTimeCol.setCellValueFactory(new PropertyValueFactory<ImportedDataAux,String>("leavingDateTime"));
            ObservableList<ImportedDataAux> data = FXCollections.observableArrayList(controller.getImportedDataAuxList());
            data_table.setItems(data);
        }else if(event.getSource()==sort_data_btn1){
            ObservableList<ImportedDataAux> orderedOutputList = FXCollections.observableArrayList(controller.sortByArrivalTime(0));
            data_table.getItems().clear();
            data_table.setItems(orderedOutputList);
        }else if(event.getSource()==sort_data_btn2){
            ObservableList<ImportedDataAux> orderedOutputList = FXCollections.observableArrayList(controller.sortByArrivalTime(1));
            data_table.getItems().clear();
            data_table.setItems(orderedOutputList);
        }else if(event.getSource()==sort_data_btn3){
            ObservableList<ImportedDataAux> orderedOutputList = FXCollections.observableArrayList(controller.sortByLeavingTime(0));
            data_table.getItems().clear();
            data_table.setItems(orderedOutputList);
        }else if(event.getSource()==sort_data_btn4){
            ObservableList<ImportedDataAux> orderedOutputList = FXCollections.observableArrayList(controller.sortByLeavingTime(1));
            data_table.getItems().clear();
            data_table.setItems(orderedOutputList);
        }
    }

    /**
     * When the cancel button is pressed, load the main menu scene.
     */
    public void cancelPressed() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.CENTER_COORDINATOR_MAIN_MENU_FILE));
        cancelBt.getScene().setRoot(fxmlLoader.load());
    }
}