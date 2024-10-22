package app.ui.console;

import app.controller.CreateVaccinationCenterController;
import app.domain.model.VaccineType;
import app.mapper.dto.HCCDto;
import app.mapper.dto.MVCDto;
import app.ui.console.utils.Utils;

;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Implements an UI for registering a new mass vaccination center
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */
public class CreateVaccinationCenterUI implements Runnable{

    private VaccineType vaccineType;
    private CreateVaccinationCenterController createVaccinationCenterController;
    private String name;
    private String address;
    private long phone_number;
    private String email;
    private long fax_number;
    private String website_address;
    private LocalTime opening_hours;
    private LocalTime closing_hours;
    private int slot_duration;
    private int n_vaccine_p_slot;
    private String aces;
    private String ars;
    private final static int PHONE_NUMBER_DIGITS = 9;
    private final static int FAX_NUMBER_DIGITS = 10;

    /**
     *  Creates a new UI with an instance of CreateMVCenterController.
     */
    public CreateVaccinationCenterUI(){ createVaccinationCenterController =new CreateVaccinationCenterController(); }

    /**
     * Method for controlling the flow of the User interaction.
     */
    public void run(){
        int option=0;
        do{
            System.out.println("\n1 - Register a new mass vaccination center.");
            System.out.println("2 - Register a new healthcare center.");
            System.out.println("3 - Show registered vaccination centers.");
            System.out.println();
            System.out.println("0 - Cancel");

            try{
                option = Integer.valueOf(Utils.readLineFromConsole("Type your option"));

            }catch (Exception e){
                System.out.println("\nInvalid option.\n");
                run();
            }

            if(option==1 || option==2) {
                register(option);
            } else if(option==3){
                if(createVaccinationCenterController.checkIfVacCenterStoreNull()){
                    System.out.println("There are no registered vaccination centers.");
                    System.out.println();
                }else{
                    this.createVaccinationCenterController.printVacCenterStore();
                }
            }
        }while(option!=0);
    }

    public void register(int option0){
        int option;

        do {
            name = Utils.readLineFromConsole("Type the vaccination center's name.");
        }while(!createVaccinationCenterController.validateName(name));

        do {
            address = Utils.readLineFromConsole("Type the vaccination center's address.");
        }while(!createVaccinationCenterController.validateAddress(address));

        do {
            website_address = Utils.readLineFromConsole("Type the vaccination center's website address.");
        }while(!createVaccinationCenterController.validateWebsiteAddress(website_address));

        do {
            phone_number = Utils.readLongFromConsole("Type the vaccination center's phone number (must have " + PHONE_NUMBER_DIGITS + " digits).");
        }while(!createVaccinationCenterController.validatePhoneNumber(phone_number));

        email = Utils.readLineFromConsole("Type the vaccination center's email.");

        do {
            fax_number = Utils.readLongFromConsole("Type the vaccination center's fax number (must have " + FAX_NUMBER_DIGITS + " digits).");
        }while(!createVaccinationCenterController.validateFaxNumber(fax_number));

        opening_hours = Utils.readTimeFromConsole("Type the vaccination center's opening hours (hh:mm).");

        closing_hours = Utils.readTimeFromConsole("Type the vaccination center's closing hours (hh:mm).");

        slot_duration = Utils.readIntegerFromConsole("Type the vaccination center's slot duration (in minutes).");

        n_vaccine_p_slot = Utils.readIntegerFromConsole("Type the vaccination center's number of vaccines per slot.");

        if (option0 == 1){
            do {
                this.createVaccinationCenterController.printVaccineTypeStore();
                option = Utils.readIntegerFromConsole("Choose one vaccine type to be administered by the vaccination center by typing the corresponding index (type \"0\" to leave).\n");
                if(option!=0){
                    vaccineType= createVaccinationCenterController.getVaccineType(option-1);
                }
            }while(createVaccinationCenterController.getVaccineType(option-1)==null && option!=0);
        }else{
            ars = Utils.readLineFromConsole("Type the vaccination center's ARS.");
            aces = Utils.readLineFromConsole("Type the vaccination center's ACES.");
        }

        if(option0==1){
            this.createVaccinationCenterController.createMVCenter(new MVCDto(name,address,phone_number,email,fax_number,website_address,opening_hours,closing_hours,slot_duration,n_vaccine_p_slot,vaccineType));
        }
        else if (option0 == 2){
            this.createVaccinationCenterController.createHCCenter(new HCCDto(name,address,phone_number,email,fax_number,website_address,opening_hours,closing_hours,slot_duration,n_vaccine_p_slot,ars, aces));
        }
        confirmation(option0);
    }

    public void confirmation(int vaccinationCenterType){
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Website address: " + website_address);
        System.out.println("Phone number: " + phone_number);
        System.out.println("Email: " + email);
        System.out.println("Fax number: " + fax_number);
        System.out.println("Opening hours: " + opening_hours.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("Closing hours: " + closing_hours.format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("Slot duration: " + slot_duration);
        System.out.println("Number of vaccines per slot: " + n_vaccine_p_slot);
        if (vaccinationCenterType==1)
            System.out.println("Vaccine type it administers: " + vaccineType.getDescription());
        if(vaccinationCenterType==2) {
            System.out.println("ARS: " + ars);
            System.out.println("ACES: " + aces);
        }
        if(Utils.confirm("Are the results correct? (y/n)")){
            if(vaccinationCenterType==1)
                this.createVaccinationCenterController.saveMVCenter();
            if (vaccinationCenterType==2)
                this.createVaccinationCenterController.saveHCCenter();
            System.out.println("Operation success.");
        } else run();
    }
}
