package app.controller;

import app.domain.model.Company;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.*;

/**
 * It's a class that handles with serialization of the app.
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class Serialization implements Serializable {

    /**
     * Save the company data.
     * @param company The company object that you want to save.
     * @param filePath The path to the file you want to save the data to.
     */
    public static void saveCompanyData(Company company, String filePath){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))){
            out.writeObject(company);
        } catch (FileNotFoundException e){
            System.out.println("Problem while saving the app. Binary File not found.");
        } catch (IOException e){
            System.out.println("Problem saving app data.");
        }
    }


    /**
     * Loads the company data.
     *
     * @param filePath The path to the file where the data is stored.
     * @return The method is returning the loaded App.
     */
    public static void loadCompanyData(String filePath){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))){
            Company company = (Company) in.readObject();
            AuthFacade authFacade = App.getInstance().getCompany().getAuthFacade();
            company.setAuthFacade(authFacade);
            App.getInstance().setCompany(company);
        } catch (FileNotFoundException e){
            System.out.println("Problem while loading the app. Binary File not found.");
            System.out.println("If you are running the app for the first time, please ignore this message.");
        } catch (IOException e){
            System.out.println("Problem loading app data.");
        } catch (ClassNotFoundException e) {
            System.out.println("Problem while loading the app. (Class App not found)");
        }
    }

}
