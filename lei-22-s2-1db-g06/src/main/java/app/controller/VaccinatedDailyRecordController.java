package app.controller;

import app.domain.model.Company;
import app.domain.model.HealthCareCenter;
import app.domain.model.MassVaccinationCenter;
import app.domain.model.VaccinationCenter;
import app.domain.store.HCCenterStore;
import app.domain.store.MVCenterStore;

import java.time.LocalDate;

/**
 * Controller for UI/Domain interaction in the creation of the daily vaccination reports
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public class VaccinatedDailyRecordController {

    private Company company;
    private MVCenterStore mvcStore;
    private HCCenterStore hcCenterStore;

    public VaccinatedDailyRecordController(){
        this(App.getInstance().getCompany());
        this.mvcStore = this.company.getMVCenterStore();
        this.hcCenterStore = this.company.getHCCenterStore();
    }

    /**
     * Create an instance of Vaccinated Daily Record Controller with a given company
     * @param company
     */
    public VaccinatedDailyRecordController(Company company){
        this.company = company;
    }

    /**
     * Returns the mass vaccination center store
     * @return MVCenterStore
     */
    public MVCenterStore getMVCenterStore(){
        return company.getMVCenterStore();
    }

    /**
     * Returns the healthcare center store
     * @return HCCenterStore
     */
    public HCCenterStore getHCCenterStore(){
        return company.getHCCenterStore();
    }

    /**
     * Returns the number of vaccinated users of a mass vaccination center
     * @return n_vaccinated
     */
    public int getNumberOfVaccinated(VaccinationCenter vaccinationCenter){
        return vaccinationCenter.getNumberOfVaccinatedCurrentDay();
    }

//    /**
//     * Returns the number of vaccinated users of a healthcare center
//     * @return n_vaccinated
//     */
//    public int getNumberOfVaccinated(HealthCareCenter vaccinationCenter){
//        return vaccinationCenter.getNumberOfVaccinatedCurrentDay();
//    }
}
