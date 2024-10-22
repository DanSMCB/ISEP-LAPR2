package app.mapper;

import app.domain.model.AdministrationProcess;
import app.domain.model.Vaccine;
import app.domain.store.AdmProcessStore;
import app.domain.store.DoseStore;
import app.mapper.dto.AdmProcessDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdmProcessMapperTest {

    @Test
    void testCreateAdmProcess() {
        AdministrationProcess result = AdmProcessMapper.createAdmProcess(new Vaccine("id", "name", "brand", new AdmProcessStore()), new AdmProcessDto(1, 80, 0));
        Assertions.assertEquals(new AdministrationProcess(new DoseStore(), 1, 80, 0), result);
    }
}
