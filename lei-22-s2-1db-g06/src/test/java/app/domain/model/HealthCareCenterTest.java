package app.domain.model;

import app.domain.store.AppointmentStore;
import app.mapper.dto.VCDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;



class HealthCareCenterTest {

    @Test
    void testCheckNameFormat() {
        boolean result = HealthCareCenter.checkNameFormat("name");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCheckAddressFormat() {
        boolean result = HealthCareCenter.checkAddressFormat("address");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCheckPhoneNumberFormat() {
        boolean result = HealthCareCenter.checkPhoneNumberFormat(111111111);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCheckFaxNumberFormat() {
        boolean result = HealthCareCenter.checkFaxNumberFormat(1111111111);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCheckWebsiteAddressFormat() {
        boolean result = HealthCareCenter.checkWebsiteAddressFormat("website_address");
        Assertions.assertEquals(true, result);
    }

}

