package app.mapper;

import app.domain.model.Appointment;
import app.domain.model.SNSUser;
import app.domain.model.VaccineType;
import app.mapper.dto.AppointmentDto;
import app.mapper.dto.SNSUserDTO;
import app.mapper.dto.VaccineTypeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class AppointmentMapperTest {

    @Test
    void testToDto() {
        List<AppointmentDto> result = AppointmentMapper.toDto(List.of(new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "address", "sex", 0L, "email@email.com", LocalDate.of(2022,1, 29), 0L, 0L), LocalDate.of(2022, 5, 29), LocalTime.of(11, 39))));
        Assertions.assertEquals(List.of(new AppointmentDto(new VaccineTypeDto("code", "description", "vaccineTechnology"), new SNSUserDTO("name", "address", "sex", 0L, "email@email.com", LocalDate.of(2022,1, 29), 0L, 0L), LocalDate.of(2022, 5, 29), LocalTime.of(11, 39))), result);
    }
}