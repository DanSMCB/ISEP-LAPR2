package app.domain.store;

import app.domain.model.Dose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Saves Dose objects
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class DoseStore implements Serializable {
    /**
     * List containing the doses
     */
    private List<Dose> listDoses;

    /**
     * Create a new Dose Store with an empty dose list
     */
    public DoseStore() {
        listDoses = new ArrayList<>();
    }

    /**
     * Verifies if the dose recived is valid
     * @param dose dose to validate
     * @return dose validation
     */
    public boolean validateDose(Dose dose) {
        return (!listDoses.contains(dose) && validateDoseNumber(dose));
    }

    /**
     * Save the recived dose in the list of doses
     * @param dose dose to add to the list
     */
    public void saveDose(Dose dose) {
        listDoses.add(dose);
    }

    /**
     * Create and return a new instance of a dose
     * @param doseNumber number of the dose in the vaccine administration process
     * @param dosage dosage of the dose
     * @param timeBetweenDoses time (in days) between this dose and the last one
     * @return created dose
     */
    public Dose createDose(int doseNumber, double dosage, int timeBetweenDoses) {
        return new Dose(doseNumber, dosage, timeBetweenDoses);
    }

    /**
     * Verify if only exist one dose of each number
     * @param dose dose to validate
     * @return number validation
     */
    private boolean validateDoseNumber(Dose dose){
        if (dose.getDoseNumber() <= 0) {
            return false;
        }
        if (listDoses.isEmpty())
            return true;
        for (Dose doseInList : listDoses) {
            if (doseInList.getDoseNumber() == dose.getDoseNumber())
                return false;
        }
        return true;
    }

    /**
     * Return the number of doses this dose store
     * @return number of doses
     */
    public int numberOfDoses() {
        return listDoses.size();
    }

    /**
     * Verifies if a dose store has the same data as another dose store.
     * @param o : dose store to be compared to this instance
     * @return boolean : returns true if the object has the same memory address and/or the same attributes. Returns false if those conditions aren't met or if the object is from another class.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoseStore doseStore = (DoseStore) o;
        return Objects.equals(listDoses, doseStore.listDoses);
    }

    /**
     * Find the dose with the given dose number.
     *
     * @param doseNumber The number of the dose you want to find.
     * @return The dose that has the doseNumber that is being passed in.
     */
    public Dose findDose(int doseNumber){
        for (Dose dose:listDoses) {
            if (dose.checkDoseNumber(doseNumber))
                return dose;
        }
        return null;
    }
}
