package app.mapper;

import app.domain.model.AdministrationProcess;
import app.domain.model.Dose;
import app.mapper.dto.DoseDto;

/**
 * Implements methods for mapping Dose DTOs to Dose's and vice-versa.
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class DoseMapper {

    /**
     * Create and return a new instance of a dose using Dose DTO information
     * @param admp administration process to create the dose
     * @param doseDto dose Dto with the atributes to create the dose
     * @return created dose
     */
    public static Dose createDose(AdministrationProcess admp, DoseDto doseDto) {
        int doseNumber = doseDto.getDoseNumber();
        double dosage = doseDto.getDosage();
        int timeBetweenDoses = doseDto.getTimeBetweenDoses();
        return admp.createDose(doseNumber, dosage, timeBetweenDoses);
    }

}
