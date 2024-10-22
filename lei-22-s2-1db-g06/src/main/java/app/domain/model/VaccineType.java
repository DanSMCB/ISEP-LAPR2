package app.domain.model;

import app.domain.store.VaccineStore;
import app.mapper.VaccineMapper;
import app.mapper.dto.VaccineDto;
import app.mapper.dto.VaccineTypeDto;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;
/**
 * Creates vaccine types and retrieves information from vaccine types.
 * @author Daniel Braga <1200801@isep.ipp.pt>, Inês Costa <1210814@isep.ipp.pt>, Rodrigo Peireso <1211345@isep.ipp.pt>, Afonso Costa <1211343@isep.ipp.pt>
 */
public class VaccineType implements Serializable {
    private String code;
    private String description;
    private String vaccineTechnology;

    /**
     * Creates a new VaccineType with attributes code, description and vaccineTechnology.
     * @param code : vaccine type code
     * @param description : vaccine type description
     * @param vaccineTechnology : vaccine type vaccine technology
     */
    private VaccineStore vaccineStore;

    public VaccineType(String code, String description, String vaccineTechnology) {
        this.code = code;
        this.description = description;
        this.vaccineTechnology = vaccineTechnology;
        this.vaccineStore = new VaccineStore();
    }

    /**
     * Returns vaccine type's code.
     * @return code : vaccine type code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets this vaccine type's code.
     * @param code : vaccine type code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns vaccine type's description.
     * @return description : vaccine type description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets this vaccine type's description
     * @param description : vaccine type description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return vaccine type's vaccine technology
     * @return vaccineTechnology : vaccine type vaccine technology
     */
    public String getVaccineTechnology() {
        return vaccineTechnology;
    }

    /**
     * Verifies if a vaccine type has the same data as another vaccine type.
     * @param vt : vaccine type to be compared to this instance
     * @return boolean : returns true if the object has the same memory address and/or the same attributes. Returns false if those conditions aren't met or if the object is from another class.
     */
    @Override
    public boolean equals(Object vt) {
        if (this == vt) return true;
        if (vt == null || getClass() != vt.getClass()) return false;
        VaccineType that = (VaccineType) vt;
        return Objects.equals(code, that.code) && Objects.equals(description, that.description) && Objects.equals(vaccineTechnology, that.vaccineTechnology);
    }

    /**
     * Returns String containing vaccine type information.
     * @return String containing class instance data (code, description and vaccine technology)
     */
    @Override
    public String toString() {
        return "[Code:'" + code + '\'' +
                ", Description:'" + description + '\'' +
                ", Vaccine technology:'" + vaccineTechnology + '\'' +
                ']';
    }

    /**
     * Save the recived vaccine in the vaccine store
     * @param vaccine vaccine to be stored
     */
    public void saveVaccine(Vaccine vaccine) {
        vaccineStore.saveVaccine(vaccine);
    }

    /**
     * Validate the recived vaccine
     * @param vaccine vaccine to validate
     * @return vaccine validation
     */
    public boolean validateVaccine(Vaccine vaccine) {
        return vaccineStore.validateVaccine(vaccine);
    }

    /**
     * Create and return a new instance of a vaccine using Vaccine DTO information
     * @param vaccineDto vaccine Dto with the atributes to create the vaccine
     * @return created vaccine
     */
    public Vaccine createVaccine(VaccineDto vaccineDto) {
        return VaccineMapper.createVaccine(vaccineDto, vaccineStore);
    }
    /**
     * This function checks if the code, description and vaccine technology of the vaccine type are the same as the vacccine type DTO
     *
     * @param vtDto VaccineTypeDto object
     * @return A boolean value of the comparison.
     */
    public boolean checkVaccineType(VaccineTypeDto vtDto){
        String codeToCompare=vtDto.getCode();
        String descriptionToCompare=vtDto.getDescription();
        String vaccineTechnologyToCompare= vtDto.getVaccineTechnology();

        return codeToCompare.equals(this.getCode()) && descriptionToCompare.equals(this.getDescription()) && vaccineTechnologyToCompare.equals(this.getVaccineTechnology());
    }

    /**
     * It compares an VaccineType with an VaccineTypeDto.
     *
     * @param vt The VaccineTypeDto object to compare to.
     * @return A boolean value.
     */
    public boolean equals(VaccineTypeDto vt) {
        if (vt == null) return false;
        return Objects.equals(code, vt.getCode()) && Objects.equals(description, vt.getDescription()) && Objects.equals(vaccineTechnology, vt.getVaccineTechnology());
    }

    /**
     * This function returns the vaccine store
     *
     * @return The vaccineStore is being returned.
     */
    public VaccineStore getVaccineStore() {
        return vaccineStore;
    }
}
