package app.domain.model;

import app.domain.store.DoseStore;

import java.io.Serializable;

/**
 * Creates and retrieves information from Administration Processes
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class AdministrationProcess implements Serializable {

    private AdministrationProcess admp;
    /**
     * Number of doses the adminstration process administers
     */
    private int numberOfDoses;
    /**
     * Maximum age of people that will follow this administration process
     */
    private int maximumAge;
    /**
     * Minimum age of people that will follow this administration process
     */
    private int minimumAge;
    /**
     * Store containing the doses of this administration process
     */
    private DoseStore doseStore;

    /**
     * Creates a new administration process with recived atributes
     * @param doseStore Dose store containing the doses of this administration process
     * @param numberOfDoses Number of doses the adminstration process administers
     * @param maximumAge Maximum age of people that will follow this administration process
     * @param minimumAge Minimum age of people that will follow this administration process
     */
    public AdministrationProcess(DoseStore doseStore,int numberOfDoses, int maximumAge, int minimumAge) {
        this.doseStore = doseStore;
        this.numberOfDoses = numberOfDoses;
        this.maximumAge = maximumAge;
        this.minimumAge = minimumAge;
    }

    /**
     * Create and return a new instance of a dose
     * @param doseNumber number of the dose in the vaccine administration process
     * @param dosage dosage of the dose
     * @param timeBetweenDoses time (in days) between this dose and the last one
     * @return created dose
     */
    public Dose createDose(int doseNumber, double dosage, int timeBetweenDoses) {
        return doseStore.createDose(doseNumber, dosage, timeBetweenDoses);
    }

    /**
     * Verifies if the dose recived is valid
     * @param dose dose to validate
     * @return dose validation
     */
    public boolean validateDose(Dose dose) {
        return doseStore.validateDose(dose);
    }

    /**
     * Save the recived dose in the dose store
     * @param dose dose to add to dose store
     */
    public void saveDose(Dose dose) {
        doseStore.saveDose(dose);
    }

    /**
     * Returns the number of doses the administration process administers
     * @return number of doses
     */
    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    /**
     * Returns the maximum age of people that will follow this administration process
     * @return maximum age
     */
    public int getMaximumAge() {
        return maximumAge;
    }

    /**
     * Returns the minimum age of people that will follow this administration process
     * @return minimum age
     */
    public int getMinimumAge() {
        return minimumAge;
    }

    /**
     * Returns the Store containing the doses of this administration process
     * @return dose store
     */
    public DoseStore getDoseStore() {
        return doseStore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministrationProcess that = (AdministrationProcess) o;
        return numberOfDoses == that.numberOfDoses && maximumAge == that.maximumAge && minimumAge == that.minimumAge && doseStore.equals(that.doseStore);
    }

    /**
     * Validate the number of doses of an administration process
     * @return number of doses validation
     */
    public boolean validateNumberOfDoses() {
        return (numberOfDoses > 0);
    }

    /**
     * Returns String containing administration process information.
     * @return String containing class instance data (number of doses, maximum age and minimum age)
     */
    @Override
    public String toString() {
        return "AdministrationProcess{" +
                "numberOfDoses=" + numberOfDoses +
                ", maximumAge=" + maximumAge +
                ", minimumAge=" + minimumAge +
                '}';
    }

    /**
     * Find a dose by its number.
     *
     * @param doseNumber The number of the dose you want to find.
     * @return The dose being returned.
     */
    public Dose findDose(int doseNumber){
        return doseStore.findDose(doseNumber);
    }

    /**
     * This function checks if the age is within the minimum and maximum age of the admp
     *
     * @param age The age of the person
     * @return A boolean value.
     */
    public boolean checkAdmpAge(int age) {
            if (admp.minimumAge >= age && admp.getMaximumAge() <= age) {
                return true;
        } else return false;
    }
}
