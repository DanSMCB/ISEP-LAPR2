package app.domain.store;

import app.domain.model.Vaccine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineStoreTest {

    @Test
    void validateVaccine() {
        VaccineStore vaccineStore = new VaccineStore();
        Vaccine vaccine = new Vaccine("1234","name","brand", new AdmProcessStore());
        Assertions.assertTrue(vaccineStore.validateVaccine(vaccine));
    }

    @Test
    void createVaccine() {
        VaccineStore vaccineStore = new VaccineStore();
        AdmProcessStore admProcessStore = new AdmProcessStore();
        Vaccine vaccine = new Vaccine("1234","name","brand", admProcessStore);
        Vaccine vaccineToValidate = vaccineStore.createVaccine("1234","name","brand", admProcessStore);
        Assertions.assertEquals(vaccine, vaccineToValidate);
    }
}