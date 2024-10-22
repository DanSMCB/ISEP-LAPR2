package app.mapper.dto;

import app.domain.model.VaccineType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeDtoTest {

    private static VaccineTypeDto vtDtoTest = new VaccineTypeDto("11111", "vaccine type 1", "live-attenuated");
    private static VaccineTypeDto vtDtoTest2 = null;
    private static VaccineType vtDtoTest3 = new VaccineType("11111", "vaccine type 1", "live-attenuated");
    private static VaccineTypeDto vtDtoTest4 = new VaccineTypeDto("11111", "vaccine type 1", "live-attenuated");
    private static VaccineTypeDto vtDtoTest5 = new VaccineTypeDto("12345", "vaccine type 1", "live-attenuated");
    private static VaccineTypeDto vtDtoTest6 = new VaccineTypeDto("11111", "vaccine type 2", "live-attenuated");
    private static VaccineTypeDto vtDtoTest7 = new VaccineTypeDto("11111", "vaccine type 1", "inactivated");


    @Test
    void getCode() { assertEquals("11111", vtDtoTest.getCode());}

    @Test
    void getDescription() { assertEquals("vaccine type 1", vtDtoTest.getDescription()); }

    @Test
    void getVaccineTechnology() { assertEquals("live-attenuated", vtDtoTest.getVaccineTechnology()); }

    @Test
    void testEquals() {
        assertEquals(vtDtoTest, vtDtoTest);
        assertNotEquals(vtDtoTest, vtDtoTest2);
        assertNotEquals(vtDtoTest, vtDtoTest3);
        assertEquals(vtDtoTest, vtDtoTest4);
        assertNotEquals(vtDtoTest, vtDtoTest5);
        assertNotEquals(vtDtoTest, vtDtoTest6);
        assertNotEquals(vtDtoTest, vtDtoTest7);
    }
}