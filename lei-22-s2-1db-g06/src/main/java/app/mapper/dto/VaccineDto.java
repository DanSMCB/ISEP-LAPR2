package app.mapper.dto;

import java.io.Serializable;

/**
 * DTO for Vaccine objects.
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class VaccineDto implements Serializable {
    /**
     * Number of identification of the vaccine
     */
    private String id;

    /**
     * Name of the vaccine
     */
    private String name;

    /**
     * Brand of the vaccine
     */
    private String brand;

    /**
     * Creates a new instance of a vaccine with the recived attributes
     * @param id number of identification of the vaccine
     * @param name name of the vaccine
     * @param brand brand of the vaccine
     */
    public VaccineDto(String id, String name, String brand) {
        this.id = id.trim();
        this.name = name.trim();
        this.brand = brand.trim();
    }

    /**
     * Returns the number of identification of the vaccine
     * @return vaccine id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the vaccine
     * @return vaccine name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the brand of the vaccine
     * @return vaccine brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set the number of identification of the vaccine
     * @param id vaccine id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the name of the vaccine
     * @param name vaccine name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the brand of the vaccine
     * @param brand vaccine brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns String containing vaccine DTO information.
     * @return String containing class instance data (id, name and brand)
     */
    @Override
    public String toString() {
        return "VaccineDto{" +
                "id='" + id +
                ", name='" + name +
                ", brand='" + brand +
                '}';
    }
}
