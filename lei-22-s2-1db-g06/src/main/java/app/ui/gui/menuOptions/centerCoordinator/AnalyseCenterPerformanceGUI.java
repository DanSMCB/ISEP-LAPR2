package app.ui.gui.menuOptions.centerCoordinator;
import app.domain.shared.Constants;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import app.controller.AnalyseCenterPerformanceController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class AnalyseCenterPerformanceGUI extends Application{

    @FXML
    private DatePicker dateInput;
    @FXML
    private TextField timeIntervalField;
    @FXML
    private Button doBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField sumLabel;
    @FXML
    private TextField firstDate;
    @FXML
    private TextField secondDate;
    @FXML
    private ListView<Integer> differenceList;
    @FXML
    private ListView<Integer> maxSublist;

    @FXML
    private Label execTime;

    private AnalyseCenterPerformanceController controller;

    private LocalDate dayToCheck;

    public AnalyseCenterPerformanceGUI() throws Exception {
        controller=new AnalyseCenterPerformanceController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AnalyseCenterPerformanceGUI.class.getResource("fxml/MenuOptions/CenterCoordinator/AnalyseCenterPerformanceUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Analyse Center Performance");
        stage.setScene(scene);
        stage.show();
    }

    public boolean inputTimeInterval(ActionEvent actionEvent) {
        String text = timeIntervalField.getText();
        int ti = 0;
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Time interval not valid");
        try {
            ti = Integer.parseInt(text);
            boolean valid = controller.setTimeInterval(ti);
            if (!valid) {
                throw new Exception("The inputed time interval must be divisible by 720");
            }
        } catch (Exception e) {
            error.setContentText(e.getMessage());
            error.showAndWait();
            return false;
        }
        return true;
    }

    public boolean inputDate(ActionEvent actionEvent) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Date not valid");
        try {
            this.dayToCheck=(dateInput.getValue());
            if (dateInput.getValue().isAfter(LocalDate.now())) {
                throw new Exception("The day to check can not be a future day");
            }
        }catch (Exception e) {
            error.setContentText(e.getMessage());
            error.showAndWait();
            return false;
        }
        return true;
    }

    public void checkPressed(ActionEvent actionEvent) {
        if (inputTimeInterval(actionEvent) && inputDate(actionEvent)) {
            try {
                differenceList.setItems(FXCollections.observableArrayList(Arrays.asList(convert(controller.getArrivalNumberList(this.dayToCheck)))));
                long startTime = System.nanoTime();
                int[] maxSumSublist =controller.getMaxSumSublist();
                long endTime = System.nanoTime();
                execTime.setText((endTime - startTime) +" nanoseconds");
                maxSublist.setItems(FXCollections.observableArrayList(Arrays.asList(convert(maxSumSublist))));
                int[] indexes = controller.findMaxSumSublistIndexes();
                firstDate.setText(getTimeByIndex(indexes[0]).toString());
                secondDate.setText(getTimeByIndex(indexes[1]+1).toString());
                sumLabel.setText(String.valueOf(controller.sumSublist()));
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error");
                error.setContentText(e.getMessage());
                error.showAndWait();
            }
        }
    }

    public void cancelPressed(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.CENTER_COORDINATOR_MAIN_MENU_FILE));
        cancelBtn.getScene().setRoot(fxmlLoader.load());
    }

    private int[] convert(Integer[] list) {
        int lenght = list.length;
        int[] array = new int[lenght];
        for (int i = 0; i < lenght; i++) {
            array[i] = list[i];
        }
        return array;
    }

    private Integer[] convert(int[] array) {
        int lenght = array.length;
        Integer[] list = new Integer[lenght];
        for (int i = 0; i < lenght; i++) {
            list[i] = array[i];
        }
        return list;
    }

    private LocalTime getTimeByIndex(int index) {
        return LocalTime.of(8,0).plusMinutes((long) Integer.parseInt(timeIntervalField.getText()) *index);
    }
}
