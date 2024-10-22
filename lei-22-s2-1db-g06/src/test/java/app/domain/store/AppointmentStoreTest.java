package app.domain.store;

import app.domain.model.Appointment;
import app.domain.model.SNSUser;
import app.domain.model.VaccineType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class AppointmentStoreTest {


    @Test
    void testGetWaitingList() {
        AppointmentStore appointmentStore = new AppointmentStore();
        LocalDate birthdate = LocalDate.of(2000, 1, 1);
        LocalDate schedule = LocalDate.of(2022, 6, 25);
        Appointment appointment = appointmentStore.createVaccinationAppointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "address", "sex", 919191919, "test@email.com", birthdate, 123456789, 987654321), schedule, LocalTime.of( 10, 30));
        List<Appointment> resultFalse = appointmentStore.getWaitingList(schedule);
        Assertions.assertEquals(List.of(),resultFalse);
        appointment.setStateWaiting();
        List<Appointment> result = appointmentStore.getWaitingList(schedule);
        Assertions.assertEquals(List.of(appointment), result);
    }

    @Test
    void testCreateVaccinationAppointment() {
        AppointmentStore appointmentStore = new AppointmentStore();
        LocalDate schedule = LocalDate.of(2022, 5, 29);
        LocalDate birthdate = LocalDate.of(2000, 1, 1);
        Appointment result = appointmentStore.createVaccinationAppointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "address", "sex", 0L, "test@email.com", birthdate, 123456789, 987654321), schedule, LocalTime.of( 10, 34));
        result.setStateWaiting();
        Assertions.assertEquals(List.of(result),appointmentStore.getWaitingList(schedule));
    }
}