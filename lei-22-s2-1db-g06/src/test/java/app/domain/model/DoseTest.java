package app.domain.model;

import app.domain.store.DoseStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoseTest {

    @Test
    void testEquals() {
        Dose dose1 = new Dose(1, 10, 20);
        Dose dose2 = new Dose(1, 10, 20);
        Dose dose3 = new Dose(2, 15, 20);
        Assertions.assertTrue(dose1.equals(dose2));
        Assertions.assertFalse(dose1.equals(dose3));
    }

    @Test
    void getDoseNumber() {
        int dosenumber = 1;
        Dose dose = new Dose(dosenumber, 10, 20);
        Assertions.assertEquals(dose.getDoseNumber(), dosenumber);
    }

    @Test
    void getDosage() {
        int dosage = 10;
        Dose dose = new Dose(1, dosage, 20);
        Assertions.assertEquals(dose.getDosage(), dosage);
    }

    @Test
    void getTimeBetweenDoses() {
        int time = 10;
        Dose dose = new Dose(1, 10, time);
        Assertions.assertEquals(dose.getTimeBetweenDoses(), time);
    }
}