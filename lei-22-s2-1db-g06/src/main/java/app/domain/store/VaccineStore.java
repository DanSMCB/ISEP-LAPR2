package app.domain.store;

import app.domain.model.Vaccine;
import app.mapper.dto.VaccineDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves Vaccine objects
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class VaccineStore implements Serializable {
    public VaccineStore() {
        this.listVaccine = new ArrayList<>();
    }

    /**
     * List containing the existing vaccines
     */
    private List<Vaccine> listVaccine;

    /**
     * Verifies if the vaccine recived is valid
     * @param vaccine vaccine to validate
     * @return vaccine validation
     */
    public boolean validateVaccine(Vaccine vaccine) {
        return ((!listVaccine.contains(vaccine)) && validateName(vaccine) && validateId(vaccine) && validateBrand(vaccine));
    }

    /**
     * Validate the vaccine brand
     * @param vaccine vaccine to validate
     * @return vaccine brand validation
     */
    private boolean validateBrand(Vaccine vaccine) {
        return (vaccine.getBrand() != null && !(vaccine.getBrand().equals("")));
    }

    /**
     * Validate the identification code of a vaccine
     * @param vaccine vaccine to validate
     * @return vaccine id validation
     */
    private boolean validateId(Vaccine vaccine) {
        if (vaccine.getId() != null && !(vaccine.getId().equals(""))) {
            for (Vaccine vaccineInList : listVaccine) {
                if ((vaccineInList.getId().equals(vaccine.getId())))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Validate the name of a vaccine
     * @param vaccine vaccine to validate
     * @return vaccine name validation
     */
    private boolean validateName(Vaccine vaccine) {
        if (vaccine.getName() != null && !(vaccine.getName().equals(""))) {
            for (Vaccine vaccineInList : listVaccine) {
                if ((vaccineInList.getName().equals(vaccine.getName())))
                   return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Save the recived vaccine in the list of vaccines
     * @param vaccine vaccine to add to the list
     */
    public void saveVaccine(Vaccine vaccine) {
        listVaccine.add(vaccine);
    }

    /**
     * Create and return a new instance of a vaccine
     * @param id number of identification of the vaccine
     * @param name name of the vaccine
     * @param brand brand of the vaccine
     * @param admProcessStore store containing the administration Processes of this vaccine
     * @return created vaccine
     */
    public Vaccine createVaccine(String id, String name, String brand, AdmProcessStore admProcessStore) {
        return new Vaccine(id, name, brand, admProcessStore);
    }

    /**
     * This function loops through the list of vaccines and returns the vaccine with the name that matches the name
     * passed in as a parameter
     *
     * @param vaccineName The name of the vaccine that you want to find.
     * @return The vaccine that matches the vaccineName
     */
    public Vaccine findVaccine(String vaccineName){
        for (Vaccine vaccine : listVaccine) {
            if (vaccine.checkVaccineName(vaccineName))
                return vaccine;
        }
        return null;
    }

    public Vaccine findVaccine(VaccineDto vaccineDto) {
        for (Vaccine vaccine : listVaccine) {
            if (vaccine.equals(vaccineDto))
                return vaccine;
        }
        return null;
    }

    public List<Vaccine> getVaccineList() {
        return listVaccine;
    }
}
