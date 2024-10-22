package app.ui.console;

import app.controller.ConsultWaitingRoomController;
import app.domain.model.VaccinationCenter;
import app.mapper.dto.AppointmentDto;
import app.mapper.dto.SNSUserDTO;

;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * It's a UI class that displays the waiting room for a given vaccination center
 *
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class ConsultWaitingRoomUI implements Runnable {

    /**
     * Controller of this UI.
     */
    private ConsultWaitingRoomController controller;
    /**
     * Vaccination center that this UI is working on.
     */
    private VaccinationCenter vaccinationCenter;
    /**
     * Constructor that creates a new instance of ConsultWaitingRoomUI.
     */
    public ConsultWaitingRoomUI(VaccinationCenter vaccinationCenter) {
        this.controller = new ConsultWaitingRoomController();
        this.vaccinationCenter = vaccinationCenter;
    }

    /**
     * It prints the waiting list of a vaccination center
     */
    @Override
    public void run() {
        List<AppointmentDto> waitingListDTO = controller.getWaitingListDTO(vaccinationCenter);
        if (!waitingListDTO.isEmpty()) {
            System.out.println("\n\nWaiting room for the Vaccination Center by order of arrival: " + vaccinationCenter.getName() + "\n");
            int i = 1;
            for (AppointmentDto app : waitingListDTO) {
                SNSUserDTO snsUser = app.getSnsUser();
                System.out.printf("Arrival %s: ", i);
                System.out.print("Name: " + snsUser.getName());
                System.out.print(snsUser.getSex() != null ? ("; Sex: " + snsUser.getSex()) : "");
                System.out.print("; Birthdate: " + snsUser.getBirthDate().format(DateTimeFormatter.ofPattern("d/M/uuuu")));
                System.out.print("; SNS User Number: " + snsUser.getSnsNumber());
                System.out.print("; Phone Number: " + snsUser.getPhoneNumber());
                System.out.println();
                i++;
            }
        } else {
            System.out.printf("The waiting room for the %s is empty.", vaccinationCenter.getName());
        }
    }
}
