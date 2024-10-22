package app.domain.model;

import app.domain.store.DoseStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministrationProcessTest {

    @Test
    void createDose() {
        AdministrationProcess admp = new AdministrationProcess(new DoseStore(), 3,80,0);
        Dose dose = admp.createDose(3, 20, 30);
        Dose expectedDose = new Dose(3,20, 30);
        Assertions.assertEquals(expectedDose, dose);
    }

    @Test
    void validateDose() {
        DoseStore doseStore = new DoseStore();
        Dose dose1 = new Dose(-1, 20, 2);
        Dose dose2 = new Dose(1, -1, 2);
        Dose dose3 = new Dose(1, 20, -1);
        doseStore.saveDose(dose1);
        doseStore.saveDose(dose2);
        doseStore.saveDose(dose3);
        AdministrationProcess admp = new AdministrationProcess(doseStore, 3,80,0);
        Assertions.assertFalse(admp.validateDose(dose1));
        Assertions.assertFalse(admp.validateDose(dose2));
        Assertions.assertFalse(admp.validateDose(dose3));
    }

    @Test
    void getNumberOfDoses() {
        int numberOfDoses = 2;
        AdministrationProcess admp = new AdministrationProcess(new DoseStore(),numberOfDoses, 10,20);
        Assertions.assertEquals(admp.getNumberOfDoses(), numberOfDoses);
    }

    @Test
    void getMaximumAge() {
        int maximumAge = 10;
        AdministrationProcess admp = new AdministrationProcess(new DoseStore(),2, maximumAge,20);
        Assertions.assertEquals(admp.getMaximumAge(), maximumAge);
    }

    @Test
    void getMinimumAge() {
        int minimumAge = 20;
        AdministrationProcess admp = new AdministrationProcess(new DoseStore(),2, 10,minimumAge);
        Assertions.assertEquals(admp.getMinimumAge(), minimumAge);
    }

    @Test
    void getDoseStore() {
        DoseStore doseStore = new DoseStore();
        AdministrationProcess admp = new AdministrationProcess(doseStore, 2, 10, 20);
        Assertions.assertEquals(admp.getDoseStore(), doseStore);
    }

    @Test
    void testEquals() {
        AdministrationProcess admp1 = new AdministrationProcess(new DoseStore(), 2, 10, 20);
        AdministrationProcess admp2 = new AdministrationProcess(new DoseStore(), 2, 10, 20);
        AdministrationProcess admp3 = new AdministrationProcess(new DoseStore(), 3, 10, 20);
        Assertions.assertTrue(admp1.equals(admp2));
        Assertions.assertFalse(admp1.equals(admp3));
    }

    @Test
    void validateNumberOfDoses() {
        int numberOfDoses = -1, numberOfDoses2 = 2;
        DoseStore doseStore = new DoseStore();
        doseStore.saveDose(new Dose(1, 20, 2));
        doseStore.saveDose(new Dose(2, 20, 2));
        AdministrationProcess admp1 = new AdministrationProcess(doseStore, numberOfDoses, 10, 20);
        AdministrationProcess admp2 = new AdministrationProcess(doseStore, numberOfDoses2, 10, 20);
        Assertions.assertTrue(admp2.validateNumberOfDoses());
        Assertions.assertFalse(admp1.validateNumberOfDoses());
    }
}