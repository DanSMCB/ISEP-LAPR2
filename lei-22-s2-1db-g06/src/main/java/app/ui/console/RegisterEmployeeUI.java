package app.ui.console;
import app.controller.RegisterEmployeeController;
import app.domain.model.Company;
import app.domain.model.Role;
import app.mapper.dto.EmployeeDTO;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Random;
import java.util.Scanner;

import static app.domain.store.RoleStore.getListOfRole;
import static app.ui.console.utils.Utils.*;

/**
 * Implements an UI for registering a new employee
 * @author Afonso Costa <1211343@isep.ipp.pt>
 */
public class RegisterEmployeeUI implements Runnable {
    private RegisterEmployeeController registerEmployeeController;

    /**
     * Creates a new UI with an instance of RegisterEmployeeController.
     */
    public RegisterEmployeeUI() {
        registerEmployeeController = new RegisterEmployeeController();
    }

    /**
     * Method for controlling the flow of the User interaction.
     */
    public void run() {
        int option = 0;
        String name;
        String address;
        String phoneNumber=null;
        Email emailAddress = null;
        String citizenCardNumber;
        do {
            boolean rightOption = false;
            while (!rightOption) {
                System.out.println("----Register an employee Menu----");
                System.out.println();
                System.out.println("1 - Register a new employee.");
                System.out.println();
                System.out.println();
                System.out.println("0 - Cancel");

                try {
                    option = Integer.valueOf(Utils.readLineFromConsole("Type your option"));
                    rightOption=true;
                } catch (Exception e) {
                    System.out.println("\nInvalid option.\n");
                    rightOption=false;
                }
            }

            if (option == 1) {
                EmployeeDTO employeeDTO = null;
                System.out.println();
                Role role = null;
                boolean error2;
                 do{
                    try {
                        role = (Role) showAndSelectOne(getListOfRole(), "----- Roles -----\n");
                        error2=false;
                    } catch (Exception e) {
                        System.out.println("Invalid option.");
                        error2=true;
                    }
                }while(error2);
                System.out.println();
                name = readLineFromConsole("Type the employee's name");
                System.out.println();
                address = readLineFromConsole("Type the employee's address");
                System.out.println();
                boolean error3;
                 do {

                    phoneNumber = readLineFromConsole("Type the employee's phone number");
                    error3 = false;
                    if (phoneNumber.length() < 9 && !(phoneNumber.matches((("[1-9+/()]"))))) {
                        System.out.println("Please enter a valid phone number\n");
                        error3 = true;
                    }
                }while (error3);
                System.out.println();
                boolean error;
                do {
                    try {
                        emailAddress = new Email(readLineFromConsole("Type the employee's email address"));
                        error=false;
                    } catch (Exception emailException) {
                        System.out.println(emailException.getMessage() + "\n");
                        error=true;
                    }
                }while(error);
                System.out.println();
                citizenCardNumber = readLineFromConsole("Type the employee's citizen card number");
                String password = String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1) + 100000000));
                employeeDTO = new EmployeeDTO(name, ("emp"+ new Random().nextInt(999999)), password, address, phoneNumber, emailAddress.getEmail(), citizenCardNumber, role);
                if (confirm("New Employee: \n\nEmployee Id: " + employeeDTO.employeeId + "\nEmployee Password: " + employeeDTO.password + "\n\nRole: " + employeeDTO.role + "\nName: " + employeeDTO.name + "\nAddress: " + employeeDTO.address + "\nPhone Number: " + employeeDTO.phoneNumber + "\nEmail: " + employeeDTO.emailAddress + "\nCitizen Card Number: " + employeeDTO.ccNumber + "\n\nSave? [y/n]")) {
                    registerEmployeeController.createEmployee(employeeDTO);
                    registerEmployeeController.saveEmployee();
                    registerEmployeeController.createUser(password);
                    System.out.println("\nEmployee Saved Successfully!\n");
                }
            }

        } while (option != 0);
    }
}
