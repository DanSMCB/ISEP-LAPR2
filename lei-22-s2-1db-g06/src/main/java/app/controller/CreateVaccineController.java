package app.controller;

import app.domain.model.*;
import app.domain.store.VaccineTypeStore;
import app.mapper.AdmProcessMapper;
import app.mapper.DoseMapper;
import app.mapper.VaccineTypeMapper;
import app.mapper.dto.AdmProcessDto;
import app.mapper.dto.DoseDto;
import app.mapper.dto.VaccineDto;
import app.mapper.dto.VaccineTypeDto;

import java.util.List;

/**
 * Controller for UI/Domain interaction in the creation of new vaccines
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class CreateVaccineController {

    private Company company;

    private VaccineType selectedVt;
    private Vaccine vaccine;

    private AdministrationProcess admp;

    /**
     * Create an instance of Create Vaccine Controller with the active company
     */
    public CreateVaccineController() {
        this.company=App.getInstance().getCompany();
    }

    /**
     * Create an instance of Create Vaccine Controller with a given company
     * @param company
     */
    public CreateVaccineController(Company company) {
        this.company=company;
    }

    /**
     * Create and return a list of Vaccine Types in DTO
     * @return list of Vaccine Types DTO
     */
    public List<VaccineTypeDto> getListOfVaccineTypes() {
        VaccineTypeStore vtStore = company.getVTStore();
        List<VaccineType> listVT = vtStore.getVaccineTypes();
        return VaccineTypeMapper.toDto(listVT);
    }

    /**
     * Create and validate a new instance of Vaccine using Vaccine DTO information
     * @param vaccineDto vaccine Dto with the atributes to create the vaccine
     * @param vtDto vaccine type Dto to create
     * @return vaccine validated
     */
    public boolean createVaccine(VaccineDto vaccineDto, VaccineTypeDto vtDto) {
        selectedVt = VaccineTypeMapper.toModel(vtDto);
        vaccine = company.createVaccine(vaccineDto, selectedVt);
        return company.validateVaccine(vaccine, selectedVt);
    }

    /**
     * Create and validate a new instance of Administration Process using Administration Process DTO information
     * @param admpDto administration process Dto with the atributes to create the administration process
     * @return administration process validated
     */
    public boolean createAdmProcess(AdmProcessDto admpDto) {
        admp = AdmProcessMapper.createAdmProcess(vaccine, admpDto);
        if (vaccine.validateAdmProcess(admp)) {
            vaccine.saveAdmProcess(admp);
            return true;
        }
        return false;
    }

    /**
     * Create and validate a new instance of a Dose using Dose DTO information
     * @param doseDto dose Dto with the atributes to create the dose
     * @return dose validated
     */
    public boolean createDose(DoseDto doseDto) {
        Dose dose = DoseMapper.createDose(admp, doseDto);
        if (admp.validateDose(dose)) {
            admp.saveDose(dose);
            return true;
        }
        return false;
    }

    /**
     * Save the created vaccine
     */
    public void saveVaccine() {
        company.saveVaccine(vaccine, selectedVt);
    }
}
