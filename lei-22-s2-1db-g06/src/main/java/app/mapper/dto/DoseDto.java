package app.mapper.dto;

import java.io.Serializable;

/**
 * DTO for Dose objects.
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class DoseDto implements Serializable {
    /**
     * Number of the dose in the vaccine administration process
     */
    private int doseNumber;
    /**
     * Dosage of the dose
     */
    private double dosage;
    /**
     * Time (in days) between this dose and the last one
     */
    private int timeBetweenDoses;
    /**
     * Create a new dose DTO with the recived atributes
     * @param doseNumber number of the dose in the vaccine administration process
     * @param dosage dosage of the dose
     * @param timeBetweenDoses time (in days) between this dose and the last one
     */
    public DoseDto(int doseNumber, double dosage, int timeBetweenDoses) {
        this.doseNumber = doseNumber;
        this.dosage = dosage;
        this.timeBetweenDoses = timeBetweenDoses;
    }
    /**
     * Return the dose number of the vaccine
     * @return dose number
     */
    public int getDoseNumber() {
        return doseNumber;
    }
    /**
     * Return the dosage of the dose
     * @return vaccine dosage
     */
    public double getDosage() {
        return dosage;
    }
    /**
     * Return time (in days) between doses
     * @return time between doses
     */
    public int getTimeBetweenDoses() {
        return timeBetweenDoses;
    }

    /**
     * Set the dose number of the vaccine
     * @param doseNumber dose number of the vaccine
     */
    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    /**
     * Set the dosage of the dose
     * @param dosage dose dosage
     */
    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    /**
     * Set the time (in days) between doses
     * @param timeBetweenDoses time between doses
     */
    public void setTimeBetweenDoses(int timeBetweenDoses) {
        this.timeBetweenDoses = timeBetweenDoses;
    }

    /**
     * Returns String containing dose DTO information.
     * @return String containing class instance data (dose number, dosage and time between doses)
     */
    @Override
    public String toString() {
        return "DoseDto{" +
                "doseNumber=" + doseNumber +
                ", dosage=" + dosage +
                ", timeBetweenDoses=" + timeBetweenDoses +
                '}';
    }
}
