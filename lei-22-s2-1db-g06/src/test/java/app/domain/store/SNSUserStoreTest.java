package app.domain.store;

import app.domain.model.SNSUser;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SNSUserStoreTest {

    SNSUserStore snsUserStore = new SNSUserStore();
    String s1 = "10/10/2002";
    LocalDate date1 = LocalDate.parse(s1, DateTimeFormatter.ofPattern("d/M/yyyy"));
    SNSUser snsUser= new SNSUser("name","address","male",123123123,"email@gmail.com",date1,123123123,12312312);
    SNSUser snsUser1 = null;

    SNSUserStoreTest() throws ParseException {
    }

    @Test
    void checkIfNull() {
        assertTrue(snsUserStore.checkIfNull());
        snsUserStore.saveSNSUser(snsUser);
        assertFalse(snsUserStore.checkIfNull());
    }

    @Test
    void getSize() {
        assertEquals(0,snsUserStore.getSize());
        snsUserStore.saveSNSUser(snsUser);
        assertEquals(1,snsUserStore.getSize());
    }

    @Test
    void validateSNSUser() {
        assertTrue(snsUserStore.validateSNSUser(snsUser));
        assertFalse(snsUserStore.validateSNSUser(snsUser1));
    }

    @Test
    void saveSNSUser() {
        assertTrue(snsUserStore.saveSNSUser(snsUser));
        assertFalse(snsUserStore.saveSNSUser(snsUser1));
    }

    @Test
    void checkRepeatedAddress() {
        snsUserStore.saveSNSUser(snsUser);
        assertFalse(snsUserStore.checkRepeatedAddress("address"));
        assertTrue(snsUserStore.checkRepeatedAddress("address1"));
    }

    @Test
    void checkRepeatedPhoneNumber() {
        snsUserStore.saveSNSUser(snsUser);
        assertFalse(snsUserStore.checkRepeatedPhoneNumber(123123123));
        assertTrue(snsUserStore.checkRepeatedPhoneNumber(123123124));
    }

    @Test
    void checkRepeatedEmail() {
        snsUserStore.saveSNSUser(snsUser);
        assertFalse(snsUserStore.checkRepeatedEmail("email@gmail.com"));
        assertTrue(snsUserStore.checkRepeatedAddress("email1@gmail.com"));
    }

    @Test
    void checkRepeatedsnsNumber() {
        snsUserStore.saveSNSUser(snsUser);
        assertFalse(snsUserStore.checkRepeatedsnsNumber(123123123));
        assertTrue(snsUserStore.checkRepeatedsnsNumber(123123124));
    }

    @Test
    void checkRepeatedCitizenCardNumber() {
        snsUserStore.saveSNSUser(snsUser);
        assertFalse(snsUserStore.checkRepeatedCitizenCardNumber(12312312));
        assertTrue(snsUserStore.checkRepeatedCitizenCardNumber(12312313));
    }
}