package app.mapper.dto;

import app.domain.model.Dose;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoseDtoTest {

    @Test
    void getDoseNumber() {
        int dosenumber = 1;
        DoseDto dose = new DoseDto(dosenumber, 10, 20);
        Assertions.assertEquals(dose.getDoseNumber(), dosenumber);
    }

    @Test
    void getDosage() {
        int dosage = 10;
        DoseDto dose = new DoseDto(1, dosage, 20);
        Assertions.assertEquals(dose.getDosage(), dosage);
    }

    @Test
    void getTimeBetweenDoses() {
        int time = 10;
        DoseDto dose = new DoseDto(1, 10, time);
        Assertions.assertEquals(dose.getTimeBetweenDoses(), time);
    }

    @Test
    void setDoseNumber() {
        DoseDto dose = new DoseDto(1, 10, 10);
        int toChange = 10;
        dose.setDoseNumber(toChange);
        Assertions.assertEquals(toChange, dose.getDoseNumber());
    }

    @Test
    void setDosage() {
        DoseDto dose = new DoseDto(1, 10, 10);
        int toChange = 10;
        dose.setDosage(toChange);
        Assertions.assertEquals(toChange, dose.getDosage());
    }

    @Test
    void setTimeBetweenDoses() {
        DoseDto dose = new DoseDto(1, 10, 10);
        int toChange = 10;
        dose.setTimeBetweenDoses(toChange);
        Assertions.assertEquals(toChange, dose.getTimeBetweenDoses());
    }
}