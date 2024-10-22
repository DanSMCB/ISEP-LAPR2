package app.controller;

import app.domain.model.Company;
import app.domain.model.MassVaccinationCenter;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.store.MVCenterStore;
import app.domain.store.VaccineTypeStore;
import app.mapper.VaccineTypeMapper;
import app.mapper.dto.MVCDto;
import app.mapper.dto.VaccineTypeDto;

/**
 * Controller for UI/Domain interaction in the creation of new mass vaccination centers
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public class CreateMVCenterController {

    private Company company;
    private MVCenterStore mvcStore;
    private MassVaccinationCenter mvc;
    private VaccineTypeStore vtStore;

    private VaccineType vt;


    /**
     * Create an instance of Create Mass Vaccination Center Controller with the active company
     */
    public CreateMVCenterController(){
        this(App.getInstance().getCompany());
        this.mvcStore = this.company.getMVCenterStore();
        this.vtStore = this.company.getVTStore();
    }

    /**
     * Create an instance of Create Mass Vaccination Center Controller with a given company
     * @param company
     */
    public CreateMVCenterController(Company company){
        this.company = company;
    }

    /**
     * Create an instance of Create Vaccine Controller with the active company
     */
    public boolean checkIfVacCenterStoreNull(){
        return mvcStore.checkIfNull();
    }

    /**
     * Prints the mass vaccination center store with all the registered mass vaccination centers
     */
    public void printVacCenterStore(){mvcStore.printVaccinationStore();}

    /**
     * Prints the vaccine type store with all the active vaccine types
     */
    public void printVaccineTypeStore(){
        vtStore.printVaccineTypeStore();}

    /**
     * Returns a vaccine type with the given index
     * @param index
     * @return vaccine type
     */
    public VaccineType getVaccineType(int index){
        return vtStore.getVaccineType(index);
    }

    /**
     * Create and validate a new instance of a mass vaccination center using mvc DTO information
     * @param mvcDto mvc Dto with the attributes to create the mass vaccination center
     * @return dose validated
     */
    public boolean createMVCenter(MVCDto mvcDto){
        this.mvc = this.mvcStore.createMVCenter(mvcDto);
        return this.mvcStore.validateMVCenter(mvc);
    }

    /**
     * Validate the typed name for the mass vaccination center
     * @param name of the mass vaccination center
     * @return name validated
     */
    public boolean validateName(String name){
        return VaccinationCenter.checkNameFormat(name);
    }

    /**
     * Validate the typed address for the mass vaccination center
     * @param address of the mass vaccination center
     * @return address validated
     */
    public boolean validateAddress(String address){
        return VaccinationCenter.checkAddressFormat(address);
    }

    /**
     * Validate the typed phone number for the mass vaccination center
     * @param phoneNumber of the mass vaccination center
     * @return phone number validated
     */
    public boolean validatePhoneNumber(long phoneNumber){
        return VaccinationCenter.checkPhoneNumberFormat(phoneNumber);
    }

    /**
     * Validate the typed fax number for the mass vaccination center
     * @param faxNumber of the mass vaccination center
     * @return fax number validated
     */
    public boolean validateFaxNumber(long faxNumber){
        return VaccinationCenter.checkFaxNumberFormat(faxNumber);
    }

    /**
     * Validate the typed website address for the mass vaccination center
     * @param websiteAddress of the mass vaccination center
     * @return website address validated
     */
    public boolean validateWebsiteAddress(String websiteAddress){
        return VaccinationCenter.checkWebsiteAddressFormat(websiteAddress);
    }

//    public boolean validateEmail(String email){
//        return VaccinationCenter.checkEmailFormat(email);
//    }

    public void associateVaccineType(VaccineTypeDto vtDto){
        this.vt = VaccineTypeMapper.toModel(vtDto);
        VaccineType mvcVT = company.getVTStore().find(vt);
        this.mvc.setVaccineType(mvcVT);
    }

    /**
     * Save the created mass vaccination center
     */
    public void saveMVCenter(){
        mvcStore.saveMVCenter(mvc);
    };

}
