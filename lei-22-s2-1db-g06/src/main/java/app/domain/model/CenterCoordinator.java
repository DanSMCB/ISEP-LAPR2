package app.domain.model;

import app.domain.shared.Constants;
import app.domain.store.RoleStore;

import java.io.Serializable;

public class CenterCoordinator extends Employee implements Serializable {

    /**
     * The Vaccination Center the Center Coordinator works on.
     */
    private VaccinationCenter vaccinationCenter;

    /**
     * Builds an instance of CenterCoordinator receiving the id, password, name, role, employee id, address, phone number, citizen card number and Vaccination Center he works on
     *
     * @param id                The Email address of a given Employee
     * @param name              The name of a given Employee
     * @param employeeId        The id that identifies the employee in the Company
     * @param address           The address of a given Employee
     * @param phoneNumber       The phone Number of a given Employee
     * @param ccNumber          The citizen card number of a given Employee
     * @param vaccinationCenter The Vaccination Center the Center Coordinator works on
     */
    public CenterCoordinator(String id, String name, String employeeId, String address, String phoneNumber, String ccNumber, VaccinationCenter vaccinationCenter) throws Exception {
        super(id, name, RoleStore.getRole(Constants.ROLE_CENTER_COORDINATOR), employeeId, address, phoneNumber, ccNumber);
        this.vaccinationCenter=vaccinationCenter;
    }

    /**
     * This function returns the vaccination center the center coordinator works on
     *
     * @return The vaccination center.
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * This function sets the vaccination center of the center coordinator
     *
     * @param vaccinationCenter The vaccination center of the center coordinator.
     */
    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }
}
