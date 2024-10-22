package app.mapper;

import app.domain.model.Vaccine;
import app.domain.store.AdmProcessStore;
import app.domain.store.VaccineStore;
import app.mapper.dto.VaccineDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VaccineMapperTest {

    @Test
    void testCreateVaccine() {
        Vaccine result = VaccineMapper.createVaccine(new VaccineDto("id", "name", "brand"), new VaccineStore());
        Assertions.assertEquals(new Vaccine("id", "name", "brand", new AdmProcessStore()), result);
    }

}