package app.domain.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;



import static org.junit.jupiter.api.Assertions.*;

class VaccinationCenterTest {

    @Test
    void checkNameFormat() {
        String name="";
        assertFalse(VaccinationCenter.checkNameFormat(name));
        String name1="name";
        assertTrue(VaccinationCenter.checkNameFormat(name1));
        String name2="   ";
        assertFalse(VaccinationCenter.checkNameFormat(name2));
    }

    @Test
    void checkAddressFormat() {
        String address="";
        assertFalse(VaccinationCenter.checkAddressFormat(address));
        String address1="address";
        assertTrue(VaccinationCenter.checkAddressFormat(address1));
        String address2="   ";
        assertFalse(VaccinationCenter.checkAddressFormat(address2));
    }

    @Test
    void checkPhoneNumberFormat() {
        long phoneNumber=12345678;
        assertFalse(VaccinationCenter.checkPhoneNumberFormat(phoneNumber));
        long phoneNumber1=123456789;
        assertTrue(VaccinationCenter.checkPhoneNumberFormat(phoneNumber1));
    }

    @Test
    void checkFaxNumberFormat() {
        long faxNumber=123456789;
        assertFalse(VaccinationCenter.checkFaxNumberFormat(faxNumber));
        long faxNumber1=1234567890;
        assertTrue(VaccinationCenter.checkFaxNumberFormat(faxNumber1));
    }

    @Test
    void checkWebsiteAddressFormat() {
        String website_address="";
        assertFalse(VaccinationCenter.checkWebsiteAddressFormat(website_address));
        String website_address1="website address";
        assertTrue(VaccinationCenter.checkWebsiteAddressFormat(website_address1));
        String website_address2="   ";
        assertFalse(VaccinationCenter.checkWebsiteAddressFormat(website_address2));
    }


}