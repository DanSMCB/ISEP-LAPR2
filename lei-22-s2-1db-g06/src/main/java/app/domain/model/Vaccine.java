package app.domain.model;

import app.domain.store.AdmProcessStore;
import app.domain.store.DoseStore;
import app.mapper.dto.VaccineDto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Creates and retrieves information from Vaccines
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class Vaccine implements Serializable {
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
     * Store containing the administration Processes of this vaccine
     */
    private AdmProcessStore admProcessStore;

    /**
     * Creates a new instance of a vaccine with the recived attributes
     * @param id number of identification of the vaccine
     * @param name name of the vaccine
     * @param brand brand of the vaccine
     */
    public Vaccine(String id, String name, String brand, AdmProcessStore admProcessStore) {
        this.id = id.trim();
        this.name = name.trim();
        this.brand = brand.trim();
        this.admProcessStore = admProcessStore;
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
     * Returns the store containing the administration Processes of this vaccine
     * @return administration process store
     */
    public AdmProcessStore getAdmProcessStore() {
        return admProcessStore;
    }

    /**
     * Verifies if the administration process recived is valid
     * @param admp administration process to validate
     * @return administration process validation
     */
    public boolean validateAdmProcess(AdministrationProcess admp) {
        return admProcessStore.validateAdmProcess(admp);
    }

    /**
     * Save the recived administration process in the administration processes store
     * @param admp administration processes to add to the administration processes store
     */
    public void saveAdmProcess(AdministrationProcess admp) {
        admProcessStore.saveAdmProcess(admp);
    }

    /**
     * Create and return a new instance of an administration process
     * @param doseStore store containing the doses of this administration process
     * @param numberOfDoses number of doses the adminstration process administers
     * @param maximumAge maximum age of people that will follow this administration process
     * @param minimumAge minimum age of people that will follow this administration process
     * @return created adminisatration process
     */
    public AdministrationProcess createAdmProcess(DoseStore doseStore, int numberOfDoses, int maximumAge, int minimumAge) {
        return admProcessStore.createAdmProcess(doseStore, numberOfDoses, maximumAge, minimumAge);
    }

    /**
     * Returns String containing vaccine information.
     * @return String containing class instance data (id, name and brand)
     */
    @Override
    public String toString() {
        return "Vaccine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    /**
     * Verify if two instances of Vaccine are equal
     * @param o Object to be comparated
     * @return validation
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccine vaccine = (Vaccine) o;
        return id.equals(vaccine.getId()) && name.equals(vaccine.getName()) && brand.equals(vaccine.getBrand()) && admProcessStore.equals(vaccine.admProcessStore);
    }

    public boolean equals(VaccineDto vaccine) {
        return id.equals(vaccine.getId()) && name.equals(vaccine.getName()) && brand.equals(vaccine.getBrand());
    }


    /**
     * This function gets the administration process for a given age
     *
     * @param age The age of the patient.
     * @return Administration process for the given age
     */
    public AdministrationProcess getAdmProcess(int age) throws Exception {
        AdministrationProcess admp=admProcessStore.getAdmProcess(age);
        return admp;
    }

    /**
     * This function checks if the vaccine name passed in is the same as the vaccine name of the object
     *
     * @param vaccineName The name of the vaccine to check.
     * @return The method is returning a boolean value.
     */
    public boolean checkVaccineName(String vaccineName){
        return vaccineName.equalsIgnoreCase(name);
    }
}
