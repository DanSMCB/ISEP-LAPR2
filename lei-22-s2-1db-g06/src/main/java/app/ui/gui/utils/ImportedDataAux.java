package app.ui.gui.utils;

import javafx.beans.property.SimpleStringProperty;

public class ImportedDataAux {
    private SimpleStringProperty snsUserNumber;
    private SimpleStringProperty snsUserName;
    private SimpleStringProperty vaccineType;
    private SimpleStringProperty vaccineName;
    private SimpleStringProperty doseNumber;
    private SimpleStringProperty lotNumber;
    private SimpleStringProperty scheduledDateTime;
    private SimpleStringProperty arrivalDateTime;
    private SimpleStringProperty dateOfAdministration;
    private SimpleStringProperty leavingDateTime;

    public ImportedDataAux(String snsUserNumber, String snsUserName, String vaccineType, String vaccineName, String doseNumber, String lotNumber, String scheduledDateTime, String arrivalDateTime,
                           String dateOfAdministration, String leavingDateTime){
        this.snsUserNumber = new SimpleStringProperty(snsUserNumber);
        this.snsUserName = new SimpleStringProperty(snsUserName);
        this.vaccineType = new SimpleStringProperty(vaccineType);
        this.vaccineName = new SimpleStringProperty(vaccineName);
        this.doseNumber = new SimpleStringProperty(doseNumber);
        this.lotNumber = new SimpleStringProperty(lotNumber);
        this.scheduledDateTime = new SimpleStringProperty(scheduledDateTime);
        this.arrivalDateTime = new SimpleStringProperty(arrivalDateTime);
        this.dateOfAdministration = new SimpleStringProperty(dateOfAdministration);
        this.leavingDateTime = new SimpleStringProperty(leavingDateTime);
    }

    public ImportedDataAux(String snsUserNumber, String snsUserName, String vaccineType, String scheduleTime) {
        this.snsUserNumber = new SimpleStringProperty(snsUserNumber);
        this.snsUserName = new SimpleStringProperty(snsUserName);
        this.vaccineType = new SimpleStringProperty(vaccineType);
        this.scheduledDateTime = new SimpleStringProperty(scheduleTime);
    }

    public String getSnsUserNumber() {
        return snsUserNumber.get();
    }

    public SimpleStringProperty snsUserNumberProperty() {
        return snsUserNumber;
    }

    public void setSnsUserNumber(String snsUserNumber) {
        this.snsUserNumber.set(snsUserNumber);
    }

    public String getSnsUserName() {
        return snsUserName.get();
    }

    public SimpleStringProperty snsUserNameProperty() {
        return snsUserName;
    }

    public void setSnsUserName(String snsUserName) {
        this.snsUserName.set(snsUserName);
    }

    public String getVaccineType() {
        return vaccineType.get();
    }

    public SimpleStringProperty vaccineTypeProperty() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType.set(vaccineType);
    }

    public String getVaccineName() {
        return vaccineName.get();
    }

    public SimpleStringProperty vaccineNameProperty() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName.set(vaccineName);
    }

    public String getDoseNumber() {
        return doseNumber.get();
    }

    public SimpleStringProperty doseNumberProperty() {
        return doseNumber;
    }

    public void setDoseNumber(String doseNumber) {
        this.doseNumber.set(doseNumber);
    }

    public String getLotNumber() {
        return lotNumber.get();
    }

    public SimpleStringProperty lotNumberProperty() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber.set(lotNumber);
    }

    public String getScheduledDateTime() {
        return scheduledDateTime.get();
    }

    public SimpleStringProperty scheduledDateTimeProperty() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(String scheduledDateTime) {
        this.scheduledDateTime.set(scheduledDateTime);
    }

    public String getArrivalDateTime() {
        return arrivalDateTime.get();
    }

    public SimpleStringProperty arrivalDateTimeProperty() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime.set(arrivalDateTime);
    }

    public String getDateOfAdministration() {
        return dateOfAdministration.get();
    }

    public SimpleStringProperty dateOfAdministrationProperty() {
        return dateOfAdministration;
    }

    public void setDateOfAdministration(String dateOfAdministration) {
        this.dateOfAdministration.set(dateOfAdministration);
    }

    public String getLeavingDateTime() {
        return leavingDateTime.get();
    }

    public SimpleStringProperty leavingDateTimeProperty() {
        return leavingDateTime;
    }

    public void setLeavingDateTime(String leavingDateTime) {
        this.leavingDateTime.set(leavingDateTime);
    }
}
