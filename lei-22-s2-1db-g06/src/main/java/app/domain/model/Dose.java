package app.domain.model;


import java.io.Serializable;
import java.util.Objects;

/**
 * Creates and retrieves information from Doses
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class Dose implements Serializable {
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
     * Create a new dose with the recived atributes
     * @param doseNumber number of the dose in the vaccine administration process
     * @param dosage dosage of the dose
     * @param timeBetweenDoses time (in days) between this dose and the last one
     */
    public Dose(int doseNumber, double dosage, int timeBetweenDoses) {
        this.doseNumber = doseNumber;
        this.dosage = dosage;
        this.timeBetweenDoses = timeBetweenDoses;
    }

    /**
     * Verifies if a dose has the same data as another dose.
     * @param obj : object to be compared to this instance
     * @return boolean : returns true if the object has the same memory address and/or the same attributes. Returns false if those conditions aren't met or if the object is from another class.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dose dose = (Dose) obj;
        return doseNumber == dose.doseNumber && Double.compare(dose.dosage, dosage) == 0 && timeBetweenDoses == dose.timeBetweenDoses;
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
     * @return dose dosage
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
     * Returns String containing dose information.
     * @return String containing class instance data (dose number, dosage and time between doses)
     */
    @Override
    public String toString() {
        return "Dose{" +
                "doseNumber=" + doseNumber +
                ", dosage=" + dosage +
                ", timeBetweenDoses=" + timeBetweenDoses +
                '}';
    }

    /**
     * This function checks if the dose number is equal to the dose number passed in as a parameter.
     *
     * @param doseNumber The dose number of the vaccine.
     * @return A boolean value.
     */
    public boolean checkDoseNumber(int doseNumber) {
        return this.doseNumber == doseNumber;
    }
}
