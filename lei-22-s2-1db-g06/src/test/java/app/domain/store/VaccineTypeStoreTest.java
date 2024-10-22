package app.domain.store;

import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.mapper.dto.VaccineTypeDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeStoreTest {
    private static VaccineTypeStore vtStoreTest = new VaccineTypeStore();
    private static VaccineTypeStore vtStoreTest2 = new VaccineTypeStore();
    private static List<String> listVaccineTechnologies = List.of("live-attenuated", "inactivated", "subunit", "toxoid", "viral vector", "messenger RNA (mRNA)");
    private static int size = 0;
    private VaccineType vtTest = new VaccineType("11111", "vaccine type 1", "live-attenuated");
    private VaccineType vtTest2 = new VaccineType(" ", "vaccine type 1", "live-attenuated");
    private VaccineType vtTest3 = new VaccineType("11111", "  ", "live-attenuated");
    private VaccineType vtTest4 = new VaccineType("12345", "vaccine type 1", "live-attenuated");
    private VaccineType vtTest5 = new VaccineType("12345", "vaccine type 2", "live-attenuated");


    private VaccineTypeDto vtTestDto = new VaccineTypeDto("11111", "vaccine type 1", "live-attenuated");

    private List<VaccineType> vtListTest = List.of(vtTest);
    private List<VaccineType> vtListTest2 = List.of(vtTest, vtTest4);


    @Test
    void getListVaccineTechnologies() {
        assertEquals(listVaccineTechnologies, VaccineTypeStore.getListVaccineTechnologies());
    }

    @Test
    void getSize() {
        assertEquals(size, vtStoreTest.getSize());
    }

    @Test
    void checkIfNull() {
        assertTrue(new VaccineTypeStore().checkIfNull());
    }

    @Test
    void validateVaccineType() {
        assertFalse(vtStoreTest.validateVaccineType(vtTest2));
        assertFalse(vtStoreTest.validateVaccineType(vtTest3));
        assertTrue(vtStoreTest.validateVaccineType(vtTest));
    }

    @Test
    void addVaccineType() {
        vtStoreTest2.addVaccineType(vtTest);
        assertEquals(vtListTest, vtStoreTest.getVaccineTypes());
    }

    @Test
    void getVaccineType() {
        assertNull(vtStoreTest.getVaccineType(vtStoreTest.getSize()));
        vtStoreTest.addVaccineType(vtTest);
        assertEquals(vtTest, vtStoreTest.getVaccineType(0));
    }

    @Test
    void getVaccineTypes() {
        assertEquals(vtListTest,vtStoreTest.getVaccineTypes());
    }

    @Test
    void createVaccineType() {
        assertEquals(vtTest, vtStoreTest.createVaccineType(vtTestDto));
    }

    @Test
    void saveVaccineType() {
        vtStoreTest.saveVaccineType(vtTest);
        assertEquals(vtListTest,vtStoreTest.getVaccineTypes());
        vtStoreTest.saveVaccineType(vtTest4);
        assertEquals(vtListTest2, vtStoreTest.getVaccineTypes());
    }

    @Test
    void find() {
        assertEquals(vtListTest.get(0),vtStoreTest.find(vtTest));
        assertEquals(vtTest5, vtStoreTest.find(vtTest5));
    }
}