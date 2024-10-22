package app.domain.model;

import app.mapper.dto.VaccineTypeDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeTest {
    private VaccineType vtTest = new VaccineType("11111", "vaccine type 1", "live-attenuated");
    private static VaccineType vtTest2 = null;
    private static VaccineTypeDto vtTest3 = new VaccineTypeDto("11111", "vaccine type 1", "live-attenuated");
    private static VaccineType vtTest4 = new VaccineType("11111", "vaccine type 1", "live-attenuated");
    private static VaccineType vtTest5 = new VaccineType("12345", "vaccine type 1", "live-attenuated");
    private static VaccineType vtTest6 = new VaccineType("11111", "vaccine type 2", "live-attenuated");
    private static VaccineType vtTest7 = new VaccineType("11111", "vaccine type 1", "inactivated");

    @Test
    void testEquals() {
        assertEquals(vtTest, vtTest);
        assertNotEquals(vtTest, vtTest2);
        assertNotEquals(vtTest, vtTest3);
        assertEquals(vtTest, vtTest4);
        assertNotEquals(vtTest, vtTest5);
        assertNotEquals(vtTest, vtTest6);
        assertNotEquals(vtTest, vtTest7);
    }

    @Test
    void testToString() {
        assertEquals("[Code:'" + "11111" + '\'' + ", Description:'" + "vaccine type 1" + '\'' + ", Vaccine technology:'" + "live-attenuated" + '\'' + ']',vtTest.toString());
    }

    @Test
    void getCode() {
        assertEquals("11111", vtTest.getCode());
    }

    @Test
    void setCode() {
        vtTest.setCode("12345");
        assertEquals("12345", vtTest.getCode());
    }

    @Test
    void getDescription() {
        assertEquals("vaccine type 1", vtTest.getDescription());
    }

    @Test
    void setDescription() {
        vtTest.setDescription("vaccine type 2");
        assertEquals("vaccine type 2", vtTest.getDescription());
    }

    @Test
    void getVaccineTechnology() {
        assertEquals("live-attenuated", vtTest.getVaccineTechnology());
    }
}