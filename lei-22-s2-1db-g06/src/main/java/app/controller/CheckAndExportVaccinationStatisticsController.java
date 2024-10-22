package app.controller;

import app.domain.model.CenterCoordinator;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.store.EmployeeStore;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;

public class CheckAndExportVaccinationStatisticsController {
    private Company company;
    private VaccinationCenter vaccinationCenter;

    public CheckAndExportVaccinationStatisticsController(){
        company=App.getInstance().getCompany();

    }
    /**
     * This function gets the vaccination center of the current center coordinator
     */
    public void getCCVaccinationCenter() throws Exception {
        App app=App.getInstance();
        UserSession userSession=app.getCurrentUserSession();
        Email email=userSession.getUserId();
        EmployeeStore employeeStore=company.getEmployeeStore();
        CenterCoordinator cc=(CenterCoordinator) employeeStore.findEmployeeByEmail(email.getEmail());
        vaccinationCenter=cc.getVaccinationCenter();
    }

    /**
     * This function returns an array of integers that represents the number of fully vaccinated people in each age group
     *
     * @param initialDate The initial date of the interval
     * @param finalDate The final date of the interval.
     * @return The number of fully vaccinated people in a given period of time.
     */
    public int[] getVaccinationStatistics(LocalDate initialDate,LocalDate finalDate) throws Exception {
        return vaccinationCenter.getNumberOfFullyVaccinated(initialDate,finalDate);

    }

}
