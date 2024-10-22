package app.ui.gui.menuOptions.centerCoordinator;

import app.controller.CheckAndExportVaccinationStatisticsController;
import app.domain.shared.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.LocalDate;

public class ExportStatsGUI {
    @FXML
    private TextField fileName;
    @FXML
    private DatePicker initialDate;
    @FXML
    private DatePicker finalDate;
    private LocalDate firstDay;
    private LocalDate lastDay;
    @FXML
    private Button cancelBt;
    private String name;


    private CheckAndExportVaccinationStatisticsController controller;
    public ExportStatsGUI() throws Exception {
        controller=new CheckAndExportVaccinationStatisticsController();
    }

    public String inputFileName(ActionEvent actionEvent){
         return name=fileName.getText();


    }
    public void inputInitialDate(ActionEvent actionEvent){
        this.firstDay=initialDate.getValue();
    }
    public void exportClick(ActionEvent actionEvent){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        try {
            inputFileName(actionEvent);
            exportToCSVFile(inputFileName(actionEvent));
        } catch (Exception e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public boolean initialDateSelection(ActionEvent actionEvent) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Date not valid");
        try {
            this.firstDay=(initialDate.getValue());
            if (initialDate.getValue().isAfter(LocalDate.now())) {
                error.setContentText("The day to check can not be a future day");
                error.showAndWait();

            } else {
                if(lastDay!=null && firstDay.isAfter(lastDay)){
                    error.setContentText("The initial date can not be after the final date");
                    error.showAndWait();
                }
            }


        }catch (Exception e) {
            error.setContentText("Invalid Date Format");
            error.showAndWait();
            return false;
        }
        return true;
    }
    public boolean finalDateSelection(ActionEvent actionEvent) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Date not valid");
        try {
            this.lastDay=(finalDate.getValue());
            if (finalDate.getValue().isAfter(LocalDate.now())) {
                error.setContentText("The final date can not be a future day");
                error.showAndWait();

            } else {
                if(firstDay!=null && lastDay.isBefore(firstDay)){
                    error.setContentText("The final date can not be before the initial date");
                    error.showAndWait();
                }
            }


        }catch (Exception e) {
            error.setContentText("Invalid Date Format");
            error.showAndWait();
            return false;
        }
        return true;
    }
    public void cancelPressed(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.CENTER_COORDINATOR_MAIN_MENU_FILE));
        cancelBt.getScene().setRoot(fxmlLoader.load());
    }
    public void exportToCSVFile(String name) throws Exception {
       controller.getCCVaccinationCenter();
       int[]fullVaccinatedArray=controller.getVaccinationStatistics(firstDay,lastDay);
            File file= new File(name);
            FileWriter fr= new FileWriter(file,true);
            BufferedWriter br= new BufferedWriter(fr);
            for (int i = 0; i <fullVaccinatedArray.length ; i++) {
                br.write(firstDay.plusDays(i).toString()+"; ");
                br.write(String.valueOf(fullVaccinatedArray[i]));
                br.write("\n");
            }
            br.close();
            fr.close();
            Alert confirmation=new Alert(Alert.AlertType.INFORMATION);
            confirmation.setHeaderText("Information");
            confirmation.setContentText("Operation was successful");
            confirmation.showAndWait();

        }

    }


