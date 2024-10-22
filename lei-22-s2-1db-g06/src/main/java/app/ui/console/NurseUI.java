package app.ui.console;


import app.controller.NurseController;
import app.domain.model.VaccinationCenter;
import app.mapper.dto.VCDto;
import app.ui.console.utils.Utils;
import app.ui.gui.menuOptions.nurse.RecordVaccineAdministrationGUI;

import java.util.ArrayList;
import java.util.List;

/**
 * UI class to be used by Nurses
 *
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class NurseUI implements Runnable{

    /**
     * Controller of this UI.
     */
    private NurseController controller;

    /**
     * Constructor that creates a new instance of NurseUI.
     */
    public NurseUI() {
        this.controller = new NurseController();
    };

    /**
     * Method for controlling the flow of the Nurse interaction.
     */
    public void run()
    {
        VaccinationCenter selectedVc = null;

        List<VCDto> vaccineCenters = controller.getVaccinationCenters();
        if (!vaccineCenters.isEmpty()) {
            int optionVC = 0;
            do
            {
                try {

                    optionVC = Utils.showAndSelectIndex(vaccineCenters, "\n\nSelect a vaccination center to work on:");

                    if ((optionVC >= 0) && (optionVC < vaccineCenters.size())) {
                        try {
                            selectedVc = controller.toModel(vaccineCenters.get(optionVC));

                            List<MenuItem> options = new ArrayList<>();
                            options.add(new MenuItem("Consult the waiting room", new ConsultWaitingRoomUI(selectedVc)));
                            options.add(new MenuItem("Record Vaccine Administration", new RecordVaccineAdministrationGUI(selectedVc)));

                            int option = 0;
                            do {
                                try {
                                    option = Utils.showAndSelectIndex(options, "\n\nNurse Menu:");
                                    if ((option >= 0) && (option < options.size())) {
                                        options.get(option).run();
                                    }
                                } catch (Exception e) {
                                    System.out.println("\nInvalid option\n");
                                }
                            }
                            while (option != -1);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }catch (Exception e){
                    System.out.println("\nInvalid option\n");
                }
            }
            while (optionVC != -1);
        } else {
            System.out.println("There are no vaccination centers created");
        }
    }
}
