package app.mapper.dto;

import java.io.Serializable;
/**
 * DTO for AdministrationProcess objects.
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class AdmProcessDto implements Serializable {
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
     * Creates a new administration process with recived atributes
     * @param numberOfDoses Number of doses the adminstration process administers
     * @param maximumAge Maximum age of people that will follow this administration process
     * @param minimumAge Minimum age of people that will follow this administration process
     */
    public AdmProcessDto(int numberOfDoses, int maximumAge, int minimumAge) {
        this.numberOfDoses = numberOfDoses;
        this.maximumAge = maximumAge;
        this.minimumAge = minimumAge;
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
     * Set the number of doses the administration process administers
     * @param numberOfDoses number of doses
     */
    public void setNumberOfDoses(int numberOfDoses) {
        this.numberOfDoses = numberOfDoses;
    }
    /**
     * Set the maximum age of people that will follow this administration process
     * @param maximumAge maximum age
     */
    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }
    /**
     * Set the minimum age of people that will follow this administration process
     * @param minimumAge minimum age
     */
    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    /**
     * Returns String containing administration process DTO information.
     * @return String containing class instance data (number of doses, maximum age and minimum age)
     */
    @Override
    public String toString() {
        return "AdmProcessDto{" +
                "numberOfDoses=" + numberOfDoses +
                ", maximumAge=" + maximumAge +
                ", minimumAge=" + minimumAge +
                '}';
    }
}
