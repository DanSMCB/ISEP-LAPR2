package app.mapper;

import app.domain.model.VaccineType;
import app.mapper.dto.VaccineTypeDto;
import java.util.ArrayList;
import java.util.List;
/**
 * Implements methods for mapping VaccineType DTOs to VaccineType and vice-versa.
 * @author Inês Costa <1210814@isep.ipp.pt>, Afonso Costa <1211343@isep.ipp.pt>, Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class VaccineTypeMapper {
    /**
     * Transforms a list of VaccineType into a list of VaccineTypeDto
     * @param listVT : list of VaccineType
     * @return listVTDto : list of VaccineTypeDto
     */
    public static List<VaccineTypeDto> toDto(List<VaccineType> listVT){
        List<VaccineTypeDto> listVTDto = new ArrayList<>();
        for (VaccineType vt : listVT)
        {
            String code = vt.getCode();
            String description = vt.getDescription();
            String vaccineType = vt.getVaccineTechnology();
            VaccineTypeDto vtDto = new VaccineTypeDto(code, description, vaccineType);
            listVTDto.add(vtDto);
        }
        return listVTDto;
    }

    /**
     * It takes a VaccineType object and returns a VaccineTypeDto object
     *
     * @param vt the VaccineType object that we want to convert to a VaccineTypeDto object
     * @return A new VaccineTypeDto object.
     */
    public static VaccineTypeDto toDto(VaccineType vt){
            String code = vt.getCode();
            String description = vt.getDescription();
            String vaccineType = vt.getVaccineTechnology();
        return new VaccineTypeDto(code, description, vaccineType);
    }

    /**
     * Transforms a VaccineTypeDto into a VaccineType
     * @param vtDto : VaccinetypeDto
     * @return VaccineType with the same data as vtDto
     */
    public static VaccineType toModel(VaccineTypeDto vtDto){
        String code = vtDto.getCode();
        String description = vtDto.getDescription();
        String vaccineTechnology = vtDto.getVaccineTechnology();
        return new VaccineType(code, description, vaccineTechnology);
    }
}