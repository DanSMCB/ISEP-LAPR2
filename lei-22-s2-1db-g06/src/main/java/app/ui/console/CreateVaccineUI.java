package app.ui.console;

import app.controller.CreateVaccineController;
import app.mapper.dto.AdmProcessDto;
import app.mapper.dto.DoseDto;
import app.mapper.dto.VaccineDto;
import app.mapper.dto.VaccineTypeDto;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Objects;

import static app.ui.console.utils.Utils.readLineFromConsole;

public class CreateVaccineUI implements Runnable {


    private CreateVaccineController createVaccineController;

    public CreateVaccineUI() {
        this.createVaccineController = new CreateVaccineController();
    }
    @Override
    public void run() {
        boolean addVaccine = true;
        do {
            register();
            addVaccine = Utils.confirm("Do you want to create another vaccine[y/n]?");
        } while (addVaccine);
    }

    public void register() {
        List<VaccineTypeDto> listVTDto = createVaccineController.getListOfVaccineTypes();
        if (listVTDto.isEmpty()) {
            System.out.println("There is no vaccine types created");
        } else {
            VaccineTypeDto vtDto = null;
            boolean vtSucess;
            do {
                try {
                    vtDto = selectVtDto(listVTDto);
                    vtSucess = true;
                } catch (Exception e) {
                    System.out.println("Invalid option\n");
                    vtSucess = false;
                }
            } while (!vtSucess);

            if (vtDto != null) {
                boolean vSucess;
                do {
                    String name = Utils.readLineFromConsole("Type the Vaccine Name");
                    String brand = Utils.readLineFromConsole("Type the Vaccine Brand");
                    String id = Utils.readLineFromConsole("Type the Vaccine ID");
                    vSucess = createVaccineController.createVaccine(new VaccineDto(id, name, brand), vtDto);
                    if (!vSucess) {
                        System.out.println("Something went wrong, please try again");
                    }
                } while (!vSucess);
                boolean continueCreateAdmp = true;
                int nrAdmp = 0;
                while (continueCreateAdmp) {
                    nrAdmp++;
                    boolean admpSucess;
                    int numberOfDoses = 0;
                    int maximumAge = 0;
                    int minimumAge = 0;
                    do {
                        System.out.println("\nAdministration Process nr. " + nrAdmp);
                        boolean error;
                        do {
                            try {
                                numberOfDoses = Integer.parseInt(Objects.requireNonNull(readLineFromConsole("Type the number of doses")));
                                error = false;
                            } catch (Exception e) {
                                System.out.println("Invalid argument introduced");
                                error = true;
                            }
                        } while (error);

                        do {
                            try {
                                maximumAge = Integer.parseInt(Objects.requireNonNull(readLineFromConsole("Type the maximum age (in years) to this administration process")));
                                error = false;
                            } catch (Exception e) {
                                System.out.println("Invalid argument introduced");
                                error = true;
                            }
                        } while (error);

                        do {
                            try {
                                minimumAge = Integer.parseInt(Objects.requireNonNull(readLineFromConsole("Type the minimum age (in years) to this administration process")));
                                error = false;
                            } catch (Exception e) {
                                System.out.println("Invalid argument introduced");
                                error = true;
                            }
                        } while (error);

                        admpSucess = createVaccineController.createAdmProcess(new AdmProcessDto(numberOfDoses, maximumAge, minimumAge));
                        if (!admpSucess) {
                            System.out.println("Something went wrong, please try again");
                        }
                    } while (!admpSucess);
                    int dosesCreated = 0;
                    while (dosesCreated != numberOfDoses) {
                        boolean doseSucess;
                        do {
                            System.out.println("\nDose nr. " + (dosesCreated + 1));
                            int doseNumber = dosesCreated + 1;
                            double dosage = 0;
                            int timeBetweenDoses = 0;
                            boolean error;

                            do {
                                try {
                                    dosage = Double.parseDouble(Objects.requireNonNull(readLineFromConsole("Type the dosage (in ml)")));
                                    error = false;
                                } catch (Exception e) {
                                    System.out.println("Invalid argument introduced");
                                    error = true;
                                }
                            } while (error);

                            if (dosesCreated != 0) {
                                do {
                                    try {
                                        timeBetweenDoses = Integer.parseInt(Objects.requireNonNull(readLineFromConsole("Type the time (in days) between this dose and the last one")));
                                        error = false;
                                    } catch (Exception e) {
                                        System.out.println("Invalid argument introduced");
                                        error = true;
                                    }
                                } while (error);
                            }

                            doseSucess = createVaccineController.createDose(new DoseDto(doseNumber, dosage, timeBetweenDoses));
                            if (!doseSucess) {
                                System.out.println("Something went wrong, please try again");
                            }
                        } while (!doseSucess);
                        dosesCreated++;
                    }
                    continueCreateAdmp = Utils.confirm("Pretend to add another administration process[y/n]?");
                }
                createVaccineController.saveVaccine();
                System.out.println("Vaccine created sucessfuly!");
            }
        }
    }

    private VaccineTypeDto selectVtDto(List<VaccineTypeDto> listVTDto) {
        return (VaccineTypeDto) Utils.showAndSelectOne(listVTDto, "-----Choose the vaccine type to create the vaccine-----");
    }
}
