package app.ui.console;

import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *Implements an UI for registered administrators to interact with the system.
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{

    public AdminUI() {}
    /**
     * Method for controlling the flow of the Administrator interaction.
     */
    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a vaccination center", new CreateVaccinationCenterUI()));
        options.add(new MenuItem("Specify a new vaccine type", new SpecifyNewVaccineTypeUI()));
        options.add(new MenuItem("Get list of employees according to selected role", new GetListUI()));
        options.add(new MenuItem("Register a new Employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("Register a new Vaccine", new CreateVaccineUI()));
        options.add(new MenuItem("Register SNS Users", new RegisterSNSUserUI()));

        int option = 0;
        do
        {
            try {
                option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");
            }catch (Exception e){
                System.out.println("\nInvalid option\n");
                run();
            }

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
