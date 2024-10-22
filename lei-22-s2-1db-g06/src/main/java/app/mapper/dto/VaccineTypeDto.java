package app.mapper.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for VaccineType objects.
 * @author Inês Costa <1210814@isep.ipp.pt>
 */
public class VaccineTypeDto implements Serializable {
    private String code;
    private String description;
    private String vaccineTechnology;

    /**
     * Creates a new VaccineTypeDto with attributes code, description and vaccineTechnology.
     * @param code : vaccine type code
     * @param description : vaccine type description
     * @param vaccineTechnology : vaccine type vaccine technology
     */
    public VaccineTypeDto(String code, String description, String vaccineTechnology){
        this.code = code;
        this.description = description;
        this.vaccineTechnology = vaccineTechnology;
    }
    /**
     * A getter method for the code attribute.
     * @return code
     * */
    public String getCode(){ return code; };
    /**
     * A getter method for the description attribute.
     * @return description
     * */
    public String getDescription(){ return description; };
    /**
     * A getter method for the vaccine technology attribute.
     * @return vaccineTechnology
     * */
    public String getVaccineTechnology(){return vaccineTechnology;}

    /**
     * Verifies if a vaccine type DTO has the same data as another vaccine type DTO.
     * @param vtDto : vaccine type DTO to be compared to this instance
     * @return boolean : returns true if the object has the same memory address and/or the same attributes. Returns false if those conditions aren't met or if the object is from another class.
     */
    @Override
    public boolean equals(Object vtDto) {
        if (this == vtDto) return true;
        if (vtDto == null || getClass() != vtDto.getClass()) return false;
        VaccineTypeDto that = (VaccineTypeDto) vtDto;
        return Objects.equals(code, that.code) && Objects.equals(description, that.description) && Objects.equals(vaccineTechnology, that.vaccineTechnology);
    }

    /**
     * Returns String containing vaccine type DTO information.
     * @return String containing class instance data (code, description and vaccine technology)
     */
    public String toString() {
        return "[Code:'" + code + '\'' +
                ", Description:'" + description + '\'' +
                ", Vaccine technology:'" + vaccineTechnology + '\'' +
                ']';
    }
}