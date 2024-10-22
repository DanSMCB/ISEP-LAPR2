package app.ui.console;

import app.controller.SpecifyNewVaccineTypeController;
import app.mapper.dto.VaccineTypeDto;
import java.util.Objects;
import static app.domain.store.VaccineTypeStore.getListVaccineTechnologies;
import static app.ui.console.utils.Utils.*;

/**
 * Implements an UI for registering new vaccine types and seeing the vaccine types already registered in the system.
 * @author Inês Costa <1210814@isep.ipp.pt>
 */
public class SpecifyNewVaccineTypeUI implements Runnable{

    private final SpecifyNewVaccineTypeController specifyNewVaccineTypeController;

    /**
     * Creates a new UI with an instance of SpecifyNewVaccineTypeController.
     */
    public SpecifyNewVaccineTypeUI(){
        specifyNewVaccineTypeController =new SpecifyNewVaccineTypeController();
    }

    /**
     * Method for controlling the flow of the User interaction.
     */
    public void run() {
        int option;
        String code = null;
        String description = null;
        do {
            option = 0;
            System.out.println("1 - Specify a new vaccine type.");
            System.out.println("2 - List registered vaccine types.");
            System.out.println();
            System.out.println("0 - Cancel");

            try {
                option = Integer.parseInt(Objects.requireNonNull(readLineFromConsole("Type your option")));

            } catch (Exception e) {
                System.out.println("\nInvalid option.\n");
                run();
            }

            if (option == 1) {
                VaccineTypeDto vtDto = null;
                System.out.println();
                code = readLineFromConsole("Type the vaccine type code (5 alphanumeric characters)");
                System.out.println();
                description = readLineFromConsole("Type the vaccine type description");
                System.out.println();
                String vaccineTechnology = (String) showAndSelectOne(getListVaccineTechnologies(), "----- Vaccine Technologies -----");
                vtDto = new VaccineTypeDto(code, description, vaccineTechnology);
                if (specifyNewVaccineTypeController.createVaccineType(vtDto) && confirm("New Vaccine Type:\nCode: " + vtDto.getCode() + "\nDescription: " + vtDto.getDescription() +
                        "\nVaccine Technology: " + vtDto.getVaccineTechnology() + "\n\nSave? [y/n]")){

                    specifyNewVaccineTypeController.saveVaccineType();
                    System.out.println("\n\n-----Returning to Vaccine Type creation menu-----\n");
                }
            }
            if (option == 2){
                specifyNewVaccineTypeController.printVaccineTypeStore();
            }
        }while (option != 0) ;
    }
}