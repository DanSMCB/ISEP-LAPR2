package app.mapper;

import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.store.AdmProcessStore;
import app.domain.store.VaccineStore;
import app.mapper.dto.VaccineDto;
import app.mapper.dto.VaccineTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements methods for mapping Vaccine DTOs to Vaccine and vice-versa.
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class VaccineMapper {

    /**
     * Create and return a new instance of a vaccine using Vaccine DTO information
     * @param vaccineDto vaccine Dto with the atributes to create the vaccine
     * @param vaccineStore vaccine store to create the vaccine
     * @return created vaccine
     */
    public static Vaccine createVaccine(VaccineDto vaccineDto, VaccineStore vaccineStore) {
        String id = vaccineDto.getId();
        String name = vaccineDto.getName();
        String brand = vaccineDto.getBrand();
        AdmProcessStore admProcessStore = new AdmProcessStore();
        return vaccineStore.createVaccine(id,name,brand,admProcessStore);
    }

    public static List<VaccineDto> toDto(List<Vaccine> lVaccines) {
        List<VaccineDto> lVaccinesDto = new ArrayList<>();
        for (Vaccine v : lVaccines)
        {
            String vaccineId = v.getId();
            String name = v.getName();
            String brand = v.getBrand();
            VaccineDto vDto = new VaccineDto(vaccineId, name, brand);
            lVaccinesDto.add(vDto);
        }
        return lVaccinesDto;

    }

}
