package app.mapper;

import app.domain.model.AdministrationProcess;
import app.domain.model.Vaccine;
import app.domain.store.DoseStore;
import app.mapper.dto.AdmProcessDto;

/**
 * Implements methods for mapping AdministrationProcess DTOs to AdministrationProcess and vice-versa.
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class AdmProcessMapper {

    /**
     * Create and return an instance of an administration process using Administration Process DTO information
     * @param vaccine vaccine to create the administration process
     * @param admpDto administration process Dto with the atributes to create the administration process
     * @return
     */
    public static AdministrationProcess createAdmProcess(Vaccine vaccine, AdmProcessDto admpDto) {
        int numberOfDoses = admpDto.getNumberOfDoses();
        int maximumAge = admpDto.getMaximumAge();
        int minimumAge = admpDto.getMinimumAge();
        DoseStore doseStore = new DoseStore();
        return vaccine.createAdmProcess(doseStore,numberOfDoses,maximumAge,minimumAge);
    }

}
