package app.ui.console;

import app.controller.ScheduleVaccinationController;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistUI implements Runnable{

    public ReceptionistUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register the Arrival of a SNS User. ", new RegisterSNSUserArrivalUI()));
        options.add(new MenuItem("Schedule vaccination appointment. ", new ScheduleVaccinationUI()));

        int option = 0;
        do
        {
            try {
                option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

                if ( (option >= 0) && (option < options.size()))
                {
                    options.get(option).run();
                }
            } catch (Exception e) {
                System.out.println("Invalid option\n");
            }
        }
        while (option != -1 );
    }
}
