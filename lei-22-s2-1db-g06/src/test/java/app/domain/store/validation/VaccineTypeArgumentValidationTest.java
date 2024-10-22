package app.domain.store.validation;

import app.domain.model.VaccineType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VaccineTypeArgumentValidationTest {

    private VaccineType vtTest = new VaccineType("11111", "vaccine type 1", "live-attenuated");
    private VaccineType vtTest2 = new VaccineType("*    ", "vaccine type 1", "live-attenuated");
    private VaccineType vtTest3 = new VaccineType("11111", "  ", "live-attenuated");
    private VaccineType vtTest4 = new VaccineType("11111", "", "live-attenuated");


    @Test
    void codeValidation() {
        assertTrue(VaccineTypeArgumentValidation.codeValidation(vtTest));
        assertFalse(VaccineTypeArgumentValidation.codeValidation(vtTest2));
    }

    @Test
    void descriptionValidation() {
        assertTrue(VaccineTypeArgumentValidation.descriptionValidation(vtTest));
        assertFalse(VaccineTypeArgumentValidation.descriptionValidation(vtTest3));
        assertFalse(VaccineTypeArgumentValidation.descriptionValidation(vtTest4));
    }
}