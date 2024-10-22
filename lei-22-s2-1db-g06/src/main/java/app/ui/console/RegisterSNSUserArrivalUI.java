package app.ui.console;

import app.controller.RegisterSNSUserArrivalController;
import app.domain.model.SNSUser;
import app.mapper.dto.AppointmentDto;
import app.ui.console.utils.Utils;

import java.util.List;

import static app.ui.console.utils.Utils.*;
import static app.ui.console.utils.Utils.showAndSelectOne;

/**
 * Implements an UI for registering the SNS User arrival time.
 * @author Afonso Costa <1211343@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalUI implements Runnable {
        private RegisterSNSUserArrivalController registerSNSUserArrivalController;

        /**
         * Creates a new UI with an instance of RegisterSNSUserArrivalController.
         */
        public RegisterSNSUserArrivalUI() {
            registerSNSUserArrivalController = new RegisterSNSUserArrivalController();
        }

        /**
         * Method for controlling the flow of the User interaction.
         */
        public void run() {
            int option = 0;
            long snsUserNumber = 0;
            do {
                boolean rightOption = false;
                while (!rightOption) {
                    System.out.println("----Register The Arrival of A SNS User Menu----");
                    System.out.println();
                    System.out.println("1 - Register Arrival of a SNS User.");
                    System.out.println();
                    System.out.println();
                    System.out.println("0 - Cancel");

                    try {
                        option = Integer.valueOf(Utils.readLineFromConsole("Type your option"));
                        rightOption = true;
                    } catch (Exception e) {
                        System.out.println("\nInvalid option.\n");
                        rightOption = false;
                    }
                }

                if (option == 1) {
                    boolean validSNSNumber;
                    do{
                        try {
                            snsUserNumber = readLongFromConsole("Type the SNS User Number");
                            validSNSNumber=SNSUser.checkSNSNumberFormat(snsUserNumber);

                            AppointmentDto appointmentDto = null;
                            try {
                                List<AppointmentDto> list = registerSNSUserArrivalController.checkSNSAppointment(snsUserNumber);
                                if (!list.isEmpty()) {
                                    appointmentDto = (AppointmentDto) showAndSelectOne(list, "----SNS User Appointments For Today----");
                                    if (!(appointmentDto == null)) {
                                        registerSNSUserArrivalController.changeAppointmentStateToWaiting(appointmentDto);
                                        System.out.println("\nThe user's time of arrival has been successfully saved!\n");
                                    }
                                } else {
                                    System.out.println("There are no Appointments for today!");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            if (!confirm("Would you like to Register the Arrival of another SNS User?\n[y/n]")) {
                                option=0;
                            }
                        } catch (Exception e) {
                            validSNSNumber=false;
                            System.out.println(e.getMessage());
                            System.out.println("Please enter a valid SNS User Number!\n");
                        }
                    }
                    while(!validSNSNumber);
                }


            } while (option != 0);
        }
        }
