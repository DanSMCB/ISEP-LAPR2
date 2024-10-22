package app.ui.gui.menuOptions.nurse;

import app.controller.App;
import app.controller.RecordAdministrationOfVaccineController;
import app.controller.Serialization;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;
import app.mapper.dto.AppointmentDto;
import app.mapper.dto.SMSDto;
import app.ui.gui.utils.ImportedDataAux;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RecordVaccineAdministrationGUI extends Application implements Runnable {


    @FXML
    private TableView<ImportedDataAux> waitingListTable;
    @FXML
    private TableColumn<ImportedDataAux, String> snsNumberColumn;

    @FXML
    private TableColumn<ImportedDataAux, String> snsNameColumn;

    @FXML
    private TableColumn<ImportedDataAux, String> snsVaccineTypeColumn;

    @FXML
    private TableColumn<ImportedDataAux, String> scheduleTimeColumn;

    @FXML
    private Button confirmBtn;
    @FXML
    private Button CancelBtn;

    @FXML
    private Button refresh;

    private List<AppointmentDto> waitingList;
    private RecordAdministrationOfVaccineController controller;

    private boolean vaccineMenuCancel;

    private static VaccinationCenter vc;

    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.NURSE_RECORD_VACC_ADMN_FILE));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Record Vaccine Administration");
        stage.setScene(scene);
        stage.show();


    }

    public void refreshHandle(ActionEvent actionEvent) {
        waitingList = controller.getWaitingListDTO();
        if (waitingList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("The waiting list is empty!");
            alert.showAndWait();
        } else {
            try {
                snsNumberColumn.setCellValueFactory(new PropertyValueFactory<ImportedDataAux, String>("snsUserNumber"));
                snsNameColumn.setCellValueFactory(new PropertyValueFactory<ImportedDataAux, String>("snsUserName"));
                snsVaccineTypeColumn.setCellValueFactory(new PropertyValueFactory<ImportedDataAux, String>("vaccineType"));
                scheduleTimeColumn.setCellValueFactory(new PropertyValueFactory<ImportedDataAux, String>("scheduledDateTime"));
                waitingListTable.setItems(convertForTable(waitingList));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("An error has occurred");
                alert.showAndWait();
            }

        }
    }

    private ObservableList<ImportedDataAux> convertForTable(List<AppointmentDto> waitingList) {
        List<ImportedDataAux> converted = new ArrayList<>();
        for (AppointmentDto appDto : waitingList) {
            converted.add(new ImportedDataAux(String.valueOf(appDto.getSnsUser().getSnsNumber()), appDto.getSnsUser().getName(), appDto.getVaccineType().getDescription(), appDto.getTime().format(DateTimeFormatter.ofPattern("HH:mm"))));
        }
        return FXCollections.observableArrayList(converted);
    }
    public RecordVaccineAdministrationGUI() {
        controller = new RecordAdministrationOfVaccineController(vc);
    }

    public RecordVaccineAdministrationGUI(VaccinationCenter vaccinationCenter) {
        vc=vaccinationCenter;
        controller = new RecordAdministrationOfVaccineController(vc);
    }

    public void setVaccineMenuCancel(boolean vaccineMenuCancel) {
        this.vaccineMenuCancel = vaccineMenuCancel;
    }

    public void register(ActionEvent actionEvent) throws IOException {
        try{
            AppointmentDto appointmentDto = waitingList.get(waitingListTable.getSelectionModel().getSelectedIndex());
            controller.getSNSUser(appointmentDto);
            controller.findAppointment(appointmentDto);
            int currentDose = controller.getCurrentDose();
            if (currentDose == 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MenuOptions.Nurse/SelectVaccine.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);
                ((SelectVaccineGUI)fxmlLoader.getController()).setlVaccineDto(controller.getVaccineList(appointmentDto));
                ((SelectVaccineGUI)fxmlLoader.getController()).setController(controller);
                ((SelectVaccineGUI)fxmlLoader.getController()).setRecordGUI(this);
                stage.setTitle("An error has occurred");
                stage.showAndWait();

            } else {
                controller.getVaccine();


            }
            if (!vaccineMenuCancel) {
                TextInputDialog td = new TextInputDialog();
                td.setContentText("insert the lot number");
                td.setHeaderText("Lot Number");
                td.setTitle("Type Lot Number");
                td.showAndWait();
                boolean lotNumberValid;
                do {
                    String result = td.getResult();
                    lotNumberValid = td.getResult().matches("\\d\\d[A-Z]\\d-\\d\\d");
                    String waitingTime = App.getInstance().getProperties().getProperty("SnsUserWaitingTime");
                    if (lotNumberValid) {
                        controller.saveVaccineAdministration(result, LocalTime.now().plusMinutes(Long.parseLong(waitingTime)));
                        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                        alert1.setHeaderText("Saved!");
                        alert1.setContentText("The vaccination administration has been saved successfully!");
                        alert1.setTitle("Confirmation");
                        alert1.showAndWait();
                        writeToTXT(controller.generateSMS());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setContentText("Wrong lot number format! (00A00-00)");
                        alert.showAndWait();
                        td.showAndWait();
                    }
                } while (!lotNumberValid);

            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("An error has occurred");
            alert.showAndWait();
        }

    }

    @Override
    public void stop() {
        Platform.exit();
        Serialization.saveCompanyData(App.getInstance().getCompany(), Constants.SERIALIZABLE_FILE_PATH);
        System.exit(0);
    }

    public void writeToTXT(SMSDto smsDto) {
        Timer timer = new Timer();
        String waitingTime = App.getInstance().getProperties().getProperty("SnsUserWaitingTime");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                File file = new File("WAITINGSMS.txt");
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file, true);
                    BufferedWriter br = new BufferedWriter(fr);
                    br.write(smsDto.getSmsContent());
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("An error has occurred");
                    alert.showAndWait();
                }

            }
        }, Long.parseLong(waitingTime)*60*1000);

    }


}
