package app.ui.console;

import app.controller.ScheduleVaccinationController;
import app.domain.model.SNSUser;
import app.domain.model.VaccineType;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SNSUserUI implements Runnable {
    public SNSUserUI(){}
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Schedule vaccination appointment.", new ScheduleVaccinationUI()));

        int option = 0;
        do
        {
            try {
                option = Utils.showAndSelectIndex(options, "\n\nSNS User Menu:");
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

