package app.mapper;

import app.domain.model.AdministrationProcess;
import app.domain.model.Dose;
import app.domain.store.DoseStore;
import app.mapper.dto.DoseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoseMapperTest {

    @Test
    void testCreateDose() {
        Dose result = DoseMapper.createDose(new AdministrationProcess(new DoseStore(), 1, 80, 10), new DoseDto(1, 10, 0));
        Assertions.assertEquals(new Dose(1, 10, 0), result);
    }
}
