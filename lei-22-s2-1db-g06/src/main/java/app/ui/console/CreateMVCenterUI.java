package app.ui.console;

import app.controller.CreateMVCenterController;
import app.domain.model.VaccineType;
import app.mapper.dto.MVCDto;
import app.ui.console.utils.Utils;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * Implements an UI for registering a new mass vaccination center
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */
public class CreateMVCenterUI implements Runnable{

    private VaccineType vaccineType;
    private CreateMVCenterController createMVCenterController;

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

    private final static int PHONE_NUMBER_DIGITS = 9;
    private final static int FAX_NUMBER_DIGITS = 10;

    /**
     *  Creates a new UI with an instance of CreateMVCenterController.
     */
    public CreateMVCenterUI(){ createMVCenterController =new CreateMVCenterController(); }

    /**
     * Method for controlling the flow of the User interaction.
     */
    public void run(){
        int option=0;
        do{
            System.out.println("1 - Register a new vaccination center.");
            System.out.println("2 - Show registered vaccination centers.");
            System.out.println();
            System.out.println();
            System.out.println("0 - Cancel");

            try{
                option = Integer.valueOf(Utils.readLineFromConsole("Type your option"));

            }catch (Exception e){
                System.out.println("\nInvalid option.\n");
                run();
            }

            if(option==1){
                register();
            } else if(option==2){
                if(createMVCenterController.checkIfVacCenterStoreNull()){
                    System.out.println("There hasn't been any registered vaccination centers yet.");
                    System.out.println();
                }else{
                    this.createMVCenterController.printVacCenterStore();
                }
            }
        }while(option!=0);
    }

    public void register(){
        int option;
        do {
            this.createMVCenterController.printVaccineTypeStore();
            option = Utils.readIntegerFromConsole("Choose one vaccine type to be administered by the vaccination center by typing the corresponding index (type \"0\" to leave).\n");
            if(option!=0){
                vaccineType= createMVCenterController.getVaccineType(option-1);
            }
        }while(createMVCenterController.getVaccineType(option-1)==null && option!=0);

        do {
            name = Utils.readLineFromConsole("Type the mass vaccination center's name.");
        }while(!createMVCenterController.validateName(name));

        do {
            address = Utils.readLineFromConsole("Type the mass vaccination center's address.");
        }while(!createMVCenterController.validateAddress(address));

        do {
            website_address = Utils.readLineFromConsole("Type the mass vaccination center's website address.");
        }while(!createMVCenterController.validateWebsiteAddress(website_address));

        do {
            phone_number = Utils.readLongFromConsole("Type the mass vaccination center's phone number (must have " + PHONE_NUMBER_DIGITS + " digits).");
        }while(!createMVCenterController.validatePhoneNumber(phone_number));

//        do {
            email = Utils.readLineFromConsole("Type the mass vaccination center's email.");
//        }while(!createMVCenterController.validateEmail(email));

        do {
            fax_number = Utils.readLongFromConsole("Type the mass vaccination center's fax number (must have " + FAX_NUMBER_DIGITS + " digits).");
        }while(!createMVCenterController.validateFaxNumber(fax_number));

        opening_hours = Utils.readTimeFromConsole("Type the mass vaccination center's opening hours (hh:mm).");

        closing_hours = Utils.readTimeFromConsole("Type the mass vaccination center's closing hours (hh:mm).");

        slot_duration = Utils.readIntegerFromConsole("Type the mass vaccination center's slot duration (in minutes).");

        n_vaccine_p_slot = Utils.readIntegerFromConsole("Type the mass vaccination center's number of vaccines per slot.");

        this.createMVCenterController.createMVCenter(new MVCDto(name,address,phone_number,email,fax_number,website_address,opening_hours,closing_hours,slot_duration,n_vaccine_p_slot,vaccineType));
        confirmation();
    }

    public void confirmation(){
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Website address: " + website_address);
        System.out.println("Phone number: " + phone_number);
        System.out.println("Email: " + email);
        System.out.println("Fax number: " + fax_number);
        System.out.println("Opening hours: " + DateFormat.getTimeInstance().format(opening_hours));
        System.out.println("Closing hours: " + DateFormat.getTimeInstance().format(closing_hours));
        System.out.println("Slot duration: " + slot_duration);
        System.out.println("Number of vaccines per slot: " + n_vaccine_p_slot);
        System.out.println("Vaccine type it administers: " + vaccineType.getDescription());

        if(Utils.confirm("Are the results correct? (s/n)")){
            this.createMVCenterController.saveMVCenter();
            System.out.println("Operation success.");
        } else register();
    }
}
