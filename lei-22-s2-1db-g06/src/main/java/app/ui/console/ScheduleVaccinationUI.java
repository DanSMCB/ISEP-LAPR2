package app.ui.console;

import app.controller.ScheduleVaccinationController;
import app.mapper.dto.*;
import app.ui.console.utils.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import static app.domain.store.VaccineTypeStore.getListVaccineTechnologies;
import static app.ui.console.utils.Utils.*;

/**
 * Implements an UI for scheduling new vaccination appointments.
 * @author José Barbosa <>, Inês Costa <1210814@isep.ipp.pt>
 */
public class ScheduleVaccinationUI implements Runnable{

    private final ScheduleVaccinationController scheduleVaccinationController;

    /**
     * Creates a new UI with an instance of SpecifyNewVaccineTypeController.
     */
    public ScheduleVaccinationUI() {scheduleVaccinationController = new ScheduleVaccinationController();}
    
    /**
     * It creates a file called SMS.txt, writes the content of the SMS to the file, and closes the file
     *
     * @param smsDto The object that contains the SMS content.
     */
    public void writeToTXT(SMSDto smsDto) throws IOException {
        File file = new File("SMS.txt");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write(smsDto.getSmsContent());
        br.close();
        fr.close();
    }
    /**
     * This method takes in two lists of DTOs, and returns a list of DTOs
     *
     * @param mvcDtoList A list of MVC DTOs
     * @param hccDtoList A list of HCCDto objects
     * @return A list of VCDto objects.
     */
    public List<VCDto> addVcDtoList(List<MVCDto> mvcDtoList, List<HCCDto> hccDtoList){
        List<VCDto> vcDtoList = new ArrayList<>();
            vcDtoList.addAll(hccDtoList);
            vcDtoList.addAll(mvcDtoList);
        return vcDtoList;
    }

    /**
     * Method for controlling the flow of the User interaction.
     */
    public void run() {
        int option;
        List<VaccineTypeDto> listVTDto;
        do {
            option = 0;
            System.out.println("\n\nVaccination Appointment Menu:");
            System.out.println("1 - Schedule a new vaccination appointment.");
            System.out.println();
            System.out.println("0 - Cancel");

            try {
                option = Integer.parseInt(Objects.requireNonNull(readLineFromConsole("Type your option")));
                if (option == 1) {
                    System.out.println();
                    System.out.println("Suggested vaccine Type according to the current outbreak: " + scheduleVaccinationController.getOngoingOutbreak() + "\n");
                    boolean validVT;
                    if(confirm("Do you wish to schedule an appointment for this vaccine type? [y/n]")){
                        scheduleVaccinationController.setOngoingVaccineType();
                        validVT = true;
                    }else{
                        VaccineTypeDto vaccineTypeDto = (VaccineTypeDto) showAndSelectOne(scheduleVaccinationController.getListOfVaccineTypes(), "----- Available Vaccine Types -----");
                        if (vaccineTypeDto != null) {
                            scheduleVaccinationController.selectVaccineType(vaccineTypeDto);
                            validVT = true;
                        } else {
                            validVT = false;
                        }
                    }
                    if (validVT) {
                        boolean appointmentExists;
                        if (scheduleVaccinationController.getIsSnsLoggedIn()){
                            appointmentExists = scheduleVaccinationController.checkSnsUserAppointment();
                        }else{
                            appointmentExists = scheduleVaccinationController.checkSnsUserAppointment(Long.parseLong(readLineFromConsole("Type the SNS User number")));
                        }
                        if (appointmentExists){
                            System.out.println("There is already a vaccination appointment scheduled for this vaccine type. Returning to menu.\n");
                        }else{
                            List<MVCDto> mvcDtoList = scheduleVaccinationController.getMassVaccinationCenters();
                            List<HCCDto> hccDtoList = scheduleVaccinationController.getHealthcareCenters();
                            List<VCDto> centers = addVcDtoList(mvcDtoList, hccDtoList);
                            if (!centers.isEmpty()) {
                                VCDto vcDto = (VCDto) showAndSelectOne(centers, "----- Available Vaccination Centers -----");
                                if (vcDto != null) {
                                    scheduleVaccinationController.toModel(vcDto);
                                    boolean result = false;
                                    while(!result) {
                                        try {
                                            LocalDate today = LocalDate.now();
                                            LocalDate day = Utils.readDateFromConsole("Type the desired date (dd-MM-yyyy).\n");
                                            while(day.isBefore(today))
                                                day = (Utils.readDateFromConsole("Invalid date.\nPlease provide a valid date that is later than today's date (dd-MM-yyyy).\n"));
                                            if (scheduleVaccinationController.createAppointment(day, Utils.readTimeFromConsole("Type the desired schedule (hh:mm).\n"))) {
                                                System.out.println("\nAppointment created sucessfully!\n");
                                                result = true;
                                                if (confirm("Would you like the system to send an SMS with details on this appointment? [y/n]\n")) {
                                                    SMSDto smsDto = scheduleVaccinationController.generateSMS();
                                                    try {
                                                        writeToTXT(smsDto);
                                                        System.out.println("\nSMS generated sucessfully!\n\n");
                                                        System.out.println("---------------------------------------------------------------------------\n" + smsDto.getSmsContent() +
                                                                "---------------------------------------------------------------------------\n");
                                                    } catch (IOException e2) {
                                                        throw new RuntimeException(e2);
                                                    }
                                                }
                                            } else
                                                System.out.println("\nNo vacancy. Please select another date/schedule.\n");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                            result = false;
                                        }
                                    }
                                }
                            } else {
                                System.out.println("\nThere are no vaccinations centers to this vaccine type!\n");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("\nInvalid option.\n");
            }
        }while (option != 0) ;
    }
}