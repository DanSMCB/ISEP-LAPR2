package app.ui;

import app.controller.App;
import app.controller.Serialization;
import app.domain.shared.Constants;
import app.ui.console.MainMenuUI;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

//Teste
public class Main {

    public static void main(String[] args)
    {
        try
        {
            MainMenuUI menu = new MainMenuUI();
            Serialization.loadCompanyData(Constants.SERIALIZABLE_FILE_PATH);
            menu.run();
            Serialization.saveCompanyData(App.getInstance().getCompany(), Constants.SERIALIZABLE_FILE_PATH);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
