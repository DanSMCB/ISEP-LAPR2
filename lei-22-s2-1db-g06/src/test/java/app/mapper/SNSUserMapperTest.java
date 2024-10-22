package app.mapper;

import app.domain.model.SNSUser;
import app.mapper.dto.SNSUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

class SNSUserMapperTest {



    @Test
    void testToDto() {
        SNSUserDTO result = SNSUserMapper.toDto(new SNSUser("name", "address", "sex", 0L, "email@email.com", LocalDate.of(2022, 5, 29), 0L, 0L));
        Assertions.assertEquals(new SNSUserDTO("name", "address", "sex", 0L, "email@email.com", LocalDate.of(2022, 5, 29), 0L, 0L), result);
    }
}
