package app.domain.store;

import app.domain.model.AdministrationProcess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Saves Administration Process objects
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class AdmProcessStore implements Serializable {
    /**
     * List containing administration processes
     */
    private List<AdministrationProcess> listAdmp;


    /**
     * Creates a new Administartion Process Store with an empty list
     */
    public AdmProcessStore() {
        listAdmp = new ArrayList();
    }

    /**
     * Verifies if the administration process recived is valid
     *
     * @param admp administration process to validate
     * @return administration process validation
     */
    public boolean validateAdmProcess(AdministrationProcess admp) {
        return (!listAdmp.contains(admp) && validateAgeGroup(admp) && admp.validateNumberOfDoses());
    }

    /**
     * Validate the age group of an administration process
     *
     * @param admp administration process to validate
     * @return age group validation
     */
    private boolean validateAgeGroup(AdministrationProcess admp) {
        if (!(admp.getMinimumAge() >= 0 && admp.getMaximumAge() > 0 && admp.getMaximumAge() >= admp.getMinimumAge()))
            return false;
        if (listAdmp.isEmpty())
            return true;
        for (AdministrationProcess admpInList : listAdmp) {
            if (!(((admp.getMaximumAge() > admpInList.getMaximumAge()) || (admp.getMaximumAge() < admpInList.getMinimumAge())) && ((admp.getMinimumAge() > admpInList.getMaximumAge()) || (admp.getMinimumAge() < admpInList.getMinimumAge())))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Save the recived administration process in the list of administration process
     *
     * @param admp administration process to add to the list
     */
    public void saveAdmProcess(AdministrationProcess admp) {
        listAdmp.add(admp);
    }

    /**
     * Create and return a new instance of an administration process
     *
     * @param doseStore     dose store containing the doses of this administration process
     * @param numberOfDoses number of doses the adminstration process administers
     * @param maximumAge    maximum age of people that will follow this administration process
     * @param minimumAge    minimum age of people that will follow this administration process
     * @return created administration process
     */
    public AdministrationProcess createAdmProcess(DoseStore doseStore, int numberOfDoses, int maximumAge, int minimumAge) {
        return new AdministrationProcess(doseStore, numberOfDoses, maximumAge, minimumAge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmProcessStore that = (AdmProcessStore) o;
        return Objects.equals(listAdmp, that.listAdmp);
    }

    /**
     * "This function returns the administration process for a given age."
     *
     * @param age The age of the SNS user
     * @return The administration process that is available for the SNS user age.
     */
    public AdministrationProcess getAdmProcess(int age) throws Exception {
        if (!(listAdmp == null)) {
            for (AdministrationProcess admp : listAdmp) {
                if (age >= admp.getMinimumAge() && age <= admp.getMaximumAge()) {
                    return admp;
                }
            }
        }
        throw new Exception("There no administration processes available for the SNS user age");
    }
}
