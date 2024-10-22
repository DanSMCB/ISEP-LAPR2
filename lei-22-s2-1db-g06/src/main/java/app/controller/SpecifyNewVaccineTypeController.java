package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineType;
import app.domain.store.VaccineTypeStore;
import app.mapper.dto.VaccineTypeDto;

/**
 * Controller for UI/Domain interaction in the creation of new vaccine types.
 * @author Inês Costa <1210814@isep.ipp.pt>
 */
public class SpecifyNewVaccineTypeController {
    private Company company;
    private VaccineTypeDto vtDto;
    private VaccineType vt;
    private VaccineTypeStore vtStore;

    /**
     * Creates new instance of SpecifyNewVaccineTypeController.
     */
    public SpecifyNewVaccineTypeController() {
        this(App.getInstance().getCompany());
    }
    /**
     * Creates new instance of SpecifyNewVaccineTypeController.
     */
    public SpecifyNewVaccineTypeController(Company company) {
        this.company = company;
        this.vtDto = null;
        this.vt = null;
        this.vtStore = company.getVTStore();
    }

    /**
     * Saves a new vaccine type.
     */
    public void saveVaccineType(){
        vtStore.saveVaccineType(vt);
    }

    /**
     * Creates and validates a new vaccine type.
     * @param vtDto : vaccine type DTO
     * @return vtStore.validateVaccineType(vt) : boolean that indicates state of validity of new vaccine type
     */
    public boolean createVaccineType(VaccineTypeDto vtDto){
        this.vt = vtStore.createVaccineType(vtDto);
        return vtStore.validateVaccineType(vt);
    }

    /**
     * Prints list of vaccine types already registered in the system.
     */
    public void printVaccineTypeStore() {
        vtStore.printVaccineTypeStore();
    }
}
