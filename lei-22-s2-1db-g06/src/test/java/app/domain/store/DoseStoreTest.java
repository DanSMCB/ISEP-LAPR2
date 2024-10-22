package app.domain.store;

import app.domain.model.AdministrationProcess;
import app.domain.model.Dose;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoseStoreTest {

    @Test
    void validateDose() {
        DoseStore doseStore = new DoseStore();
        Dose dose1 = new Dose(-1, 20, 2);
        Dose dose2 = new Dose(1, -1, 2);
        Dose dose3 = new Dose(1, 20, -1);
        doseStore.saveDose(dose1);
        doseStore.saveDose(dose2);
        doseStore.saveDose(dose3);
        Assertions.assertFalse(doseStore.validateDose(dose1));
        Assertions.assertFalse(doseStore.validateDose(dose2));
        Assertions.assertFalse(doseStore.validateDose(dose3));
    }

    @Test
    void createDose() {
        DoseStore doseStore = new DoseStore();
        Dose dose = doseStore.createDose(3, 20, 30);
        Dose expectedDose = new Dose(3,20, 30);
        Assertions.assertEquals(expectedDose, dose);
    }


    @Test
    void numberOfDoses() {
        int numberOfDoses = 1;
        DoseStore doseStore = new DoseStore();
        Dose dose = doseStore.createDose(3, 20, 30);
        doseStore.saveDose(dose);
        Assertions.assertEquals(doseStore.numberOfDoses(), numberOfDoses);
    }

    @Test
    void testEquals() {
        DoseStore doseStore1 = new DoseStore();
        DoseStore doseStore2 = new DoseStore();
        DoseStore doseStore3 = new DoseStore();
        Dose dose = new Dose(1,10,20);
        doseStore1.saveDose(dose);
        doseStore2.saveDose(dose);
        Assertions.assertTrue(doseStore1.equals(doseStore2));
        Assertions.assertFalse(doseStore1.equals(doseStore3));
    }
}