package app.controller;

import app.algorithm.BubbleSort;
import app.algorithm.QuickSort;
import app.domain.model.*;
import app.domain.store.AdmProcessStore;
import app.domain.store.EmployeeStore;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccineTypeStore;
import app.ui.gui.utils.ImportedDataAux;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.Email;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ImportLegacySystemDataController {

    Properties props = App.getInstance().getProperties();
    int key = Integer.parseInt((String)props.get("Sorting.Algorithm"));
    private Company company;
    private VaccinationCenter vaccinationCenter;
    private SNSUserStore snsUserStore;
    private VaccineTypeStore vtStore;
    private List<List<String>> importedList = new ArrayList<>();
    private List<ImportedDataAux> importedDataAuxList = new ArrayList<>();
    private List<List<String>> fileContentList;

    public ImportLegacySystemDataController() throws Exception {
        this.company = App.getInstance().getCompany();
        getCCVaccinationCenter();
        this.snsUserStore=null;
        this.vtStore=null;
        this.importedList=new ArrayList<>();
        this.importedDataAuxList =new ArrayList<>();
        this.fileContentList=new ArrayList<>();
    }


    /**
     * This function gets the vaccination center of the current center coordinator
     */
    public void getCCVaccinationCenter() throws Exception {
        App app=App.getInstance();
        UserSession userSession=app.getCurrentUserSession();
        Email email=userSession.getUserId();
        EmployeeStore employeeStore=company.getEmployeeStore();
        CenterCoordinator cc=(CenterCoordinator) employeeStore.findEmployeeByEmail(email.getEmail());
        vaccinationCenter=cc.getVaccinationCenter();
    }
    
    /**
     * It reads the file, creates a SNSUser, a VaccineType, a Vaccine, an AdministrationProcess, a Dose, a Slot, and an
     * Appointment, and adds the Appointment to the SNSUser
     */
    public void saveData() throws Exception {
        snsUserStore = company.getSnsUserStore();
        vtStore = company.getVTStore();
        int doseNumber = 1;
        for (List<String> line : fileContentList) {
            SNSUser snsUser = snsUserStore.findSnsUserByNumber(Long.parseLong(line.get(0)));
            VaccineType vt = vtStore.findVaccineType(line.get(1));
            Vaccine vaccine = vtStore.findVaccine(line.get(1));
            AdmProcessStore admpStore = vaccine.getAdmProcessStore();
            int age = snsUser.getAge();
            AdministrationProcess admp = admpStore.getAdmProcess(age);
            if (line.get(2).equalsIgnoreCase("primeira"))
                doseNumber = 1;
            else if (line.get(2).equalsIgnoreCase("segunda"))
                doseNumber = 2;
            else if (line.get(2).equalsIgnoreCase("terceira"))
                doseNumber = 3;
            Dose dose = admp.findDose(doseNumber);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:m");
            Slot sl = vaccinationCenter.findSlot(LocalDateTime.parse(line.get(4),formatter).toLocalDate(),LocalDateTime.parse(line.get(4),formatter).toLocalTime());
            sl.validateSlot();
            Appointment appointment = vaccinationCenter.createVaccinationAppointment(vt,snsUser,LocalDateTime.parse(line.get(4),formatter).toLocalDate(),LocalDateTime.parse(line.get(4),formatter).toLocalTime(),
                    LocalDateTime.parse(line.get(5),formatter), LocalDateTime.parse(line.get(7),formatter));
            appointment.addVaccineAdministration(line.get(3),LocalDateTime.parse(line.get(6),formatter),vaccine,admp,dose);
            snsUser.addAppointment(appointment);
            sl.addScheduledVaccine();
            addToImportedList(line.get(0),snsUser.getName(), vt.getDescription(),line.get(1), String.valueOf(doseNumber),line.get(3),line.get(4),line.get(5),line.get(6),line.get(7));
        }
    }
    /**
     * It sorts the imported data by arrival time
     *
     * @param sortingOrder 0 for ascending, 1 for descending
     * @return A list of ImportedDataAux objects.
     */
    public List<ImportedDataAux> sortByArrivalTime(int sortingOrder){
        int index = 7;
        if(key==0&&sortingOrder==0){
            return orderedImportedDataAuxList(BubbleSort.bubbleSort(importedList,index));
        }else if(key==1&&sortingOrder==0){
            return orderedImportedDataAuxList(QuickSort.quickSort(importedList,0,importedList.size()-1,index));
        }else if(key==1&&sortingOrder==1){
            List<List<String>> orderedOutputList = BubbleSort.bubbleSort(importedList,index);
            Collections.reverse(orderedOutputList);
            return orderedImportedDataAuxList(orderedOutputList);
        }else if(key==0&&sortingOrder==1){
            List<List<String>> orderedOutputList = QuickSort.quickSort(importedList,0,importedList.size()-1,index);
            Collections.reverse(orderedOutputList);
            return orderedImportedDataAuxList(orderedOutputList);
        }
        return null;
    }

    /**
     * It sorts the imported data by leaving time, using the algorithm and sorting order specified by the user
     *
     * @param sortingOrder 0 for ascending, 1 for descending
     * @return A list of ImportedDataAux objects.
     */
    public List<ImportedDataAux> sortByLeavingTime(int sortingOrder){
        int index = 9;
        if(key==0&&sortingOrder==0){
            return orderedImportedDataAuxList(BubbleSort.bubbleSort(importedList,index));
        }else if(key==1&&sortingOrder==0){
            return orderedImportedDataAuxList(QuickSort.quickSort(importedList,0,importedList.size()-1,index));
        }else if(key==1&&sortingOrder==1){
            List<List<String>> orderedOutputList = BubbleSort.bubbleSort(importedList, index);
            Collections.reverse(orderedOutputList);
            return orderedImportedDataAuxList(orderedOutputList);
        }else if(key==0&&sortingOrder==1){
            List<List<String>> orderedOutputList = QuickSort.quickSort(importedList,0,importedList.size()-1,index);
            Collections.reverse(orderedOutputList);
            return orderedImportedDataAuxList(orderedOutputList);
        }
        return null;
    }

    /**
     * This function returns a list of imported data
     *
     * @return A list of ImportedDataAux objects.
     */
    public List<ImportedDataAux> getImportedDataAuxList() {
        return importedDataAuxList;
    }

    /**
     * This function adds a line to the importedList and importedDataAuxList
     *
     * @param snsUserNumber The number of the user in the SNS system.
     * @param snsUserName The name of the person who received the vaccine
     * @param vaccineType The type of vaccine (e.g. "Vacina BCG")
     * @param vaccineName The name of the vaccine
     * @param doseNumber dose number
     * @param lotNumber lot number of the vaccine
     * @param scheduledDateTime the date and time the user was scheduled to receive the vaccine
     * @param arrivalDateTime The date and time the patient arrived at the health center.
     * @param dateOfAdministration the date the vaccine was administered
     * @param leavingDateTime the time the patient left the health center
     */
    public void addToImportedList(String snsUserNumber, String snsUserName, String vaccineType, String vaccineName, String doseNumber, String lotNumber, String scheduledDateTime, String arrivalDateTime,
                                  String dateOfAdministration, String leavingDateTime) {
        List<String> importedLine = List.of(snsUserNumber,snsUserName,vaccineType,vaccineName,doseNumber,lotNumber,scheduledDateTime,arrivalDateTime,dateOfAdministration,leavingDateTime);
        this.importedList.add(importedLine);
        this.importedDataAuxList.add(new ImportedDataAux(snsUserNumber,snsUserName,vaccineType,vaccineName,doseNumber,lotNumber,scheduledDateTime,arrivalDateTime,dateOfAdministration,leavingDateTime));

    }

    /**
     * It reads a file line by line, and if the line is valid, it adds it to a list of lists
     *
     * @param file The file to be read.
     */
    public void saveFileContent(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String firstLine = br.readLine();
        String currentLine = br.readLine();
        while (currentLine != null){
            try{
                List<String> lineRecord = List.of(currentLine.split(";"));
                this.fileContentList.add(lineRecord);
            }catch(Exception e){
                System.out.println("Line with invalid data. Skipping...");
            }
            currentLine=br.readLine();
        }
        br.close();
    }

    /**
     * It takes a list of lists of strings, and returns a list of ImportedDataAux objects
     *
     * @param orderedOutputList The list of lists of strings that we want to convert to a list of ImportedDataAux objects.
     * @return A list of ImportedDataAux objects.
     */
    public List<ImportedDataAux> orderedImportedDataAuxList(List<List<String>> orderedOutputList){
        List<ImportedDataAux> orderedImportedDataAuxList = new ArrayList<>();
        for (List<String> importedDataAux: orderedOutputList) {
            orderedImportedDataAuxList.add(new ImportedDataAux(importedDataAux.get(0),importedDataAux.get(1),importedDataAux.get(2),importedDataAux.get(3),importedDataAux.get(4),importedDataAux.get(5),importedDataAux.get(6),
                    importedDataAux.get(7),importedDataAux.get(8),importedDataAux.get(9)));
        }
        return orderedImportedDataAuxList;
    }
}
