package app.mapper;

import app.domain.model.VaccineType;
import app.mapper.dto.VaccineTypeDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeMapperTest {

    private static VaccineTypeDto vtDtoTest = new VaccineTypeDto("11111", "vaccine type 1", "live-attenuated");
    private static VaccineTypeDto vtDtoTest2 = new VaccineTypeDto("12345", "vaccine type 2", "live-attenuated");

    private static VaccineType vtTest = new VaccineType("11111", "vaccine type 1", "live-attenuated");
    private static VaccineType vtTest2 = new VaccineType("12345", "vaccine type 2", "live-attenuated");

    private static List<VaccineTypeDto> dtoListTest = List.of(vtDtoTest,vtDtoTest2);
    private static List<VaccineType> listTest = List.of(vtTest,vtTest2);

    @Test
    void toDto() {
        assertTrue(dtoListTest.equals(VaccineTypeMapper.toDto(listTest)));
    }

    @Test
    void toModel() {
        assertEquals(vtTest,VaccineTypeMapper.toModel(vtDtoTest));
    }
}