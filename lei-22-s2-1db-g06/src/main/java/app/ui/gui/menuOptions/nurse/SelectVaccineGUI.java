package app.ui.gui.menuOptions.nurse;

import app.controller.RecordAdministrationOfVaccineController;
import app.mapper.dto.VaccineDto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class SelectVaccineGUI {

    @FXML
    private TableView<VaccineDto> vaccineTable;

    @FXML
    private TableColumn<VaccineDto, String> nameColumn;

    @FXML
    private TableColumn<VaccineDto, String> brandColumn;

    @FXML
    private TableColumn<VaccineDto, String> idColumn;


    @FXML
    private Button cancelBtn;

    @FXML
    private Button selectBtn;

    private RecordVaccineAdministrationGUI recordGUI;
    private List<VaccineDto> lVaccineDto;

    private RecordAdministrationOfVaccineController controller;



    public void setlVaccineDto(List<VaccineDto> lVaccineDto){
        this.lVaccineDto = lVaccineDto;
        nameColumn.setCellValueFactory(new PropertyValueFactory<VaccineDto, String>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<VaccineDto, String>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<VaccineDto, String>("brand"));
        vaccineTable.setItems(FXCollections.observableArrayList(lVaccineDto));
    }

    public void setRecordGUI(RecordVaccineAdministrationGUI recordGUI) {
        this.recordGUI = recordGUI;
    }

    public void backToMain(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void cancelHandle(ActionEvent actionEvent) {
        recordGUI.setVaccineMenuCancel(true);
        backToMain(actionEvent);
    }

    public void register(ActionEvent actionEvent) throws IOException {
        try {
            VaccineDto vaccineDto =  lVaccineDto.get(vaccineTable.getSelectionModel().getSelectedIndex());
            controller.findVaccine(vaccineDto);
            recordGUI.setVaccineMenuCancel(false);
            backToMain(actionEvent);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("An error has occurred");
            alert.showAndWait();
        }
    }


    public void setController(RecordAdministrationOfVaccineController controller) {
        this.controller = controller;
    }
}
