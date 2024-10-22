package app.domain.store;

import app.domain.model.MassVaccinationCenter;
import app.domain.model.VaccineType;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.*;

class MVCenterStoreTest {
    String s1 = "07:00";
    String s2="23:00";
    LocalTime date1 = LocalTime.parse(s1, DateTimeFormatter.ofPattern("HH:mm"));
    LocalTime date2 = LocalTime.parse(s2, DateTimeFormatter.ofPattern("HH:mm"));
    VaccineType vtTest = new VaccineType("11111", "vaccine type 1", "live-attenuated");
    MassVaccinationCenter mvc1 = new MassVaccinationCenter("name","address",123456789,"email@gmail.com",1234567890,"website_Address",date1,date2,10,10,vtTest);
    MassVaccinationCenter mvc2 = null;

    MVCenterStore mvCenterStore = new MVCenterStore();

    MVCenterStoreTest() throws ParseException {
    }


    @Test
    void getSize() {
        assertEquals(0,mvCenterStore.getSize());
        mvCenterStore.saveMVCenter(mvc1);
        assertEquals(1,mvCenterStore.getSize());
    }

    @Test
    void checkIfNull() {
        MVCenterStore center = new MVCenterStore();
        assertTrue(center.checkIfNull());
    }

    @Test
    void validateMVCenter() {
        assertFalse(mvCenterStore.validateMVCenter(mvc2));
        assertTrue(mvCenterStore.validateMVCenter(mvc1));
    }

    @Test
    void saveMVCenter() {
        assertFalse(mvCenterStore.validateMVCenter(mvc2));
        assertTrue(mvCenterStore.validateMVCenter(mvc1));
    }
}