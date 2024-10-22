package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class AppointmentTest {

    @Test
    void testCompareTo() {
        Appointment appointment = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        Appointment appointment1 = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        Appointment appointment2 = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        Appointment appointment3 = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        LocalTime localTime = LocalTime.of(10,0);
        appointment.setSNSUserArrivalTime(localTime);
        appointment1.setSNSUserArrivalTime(LocalTime.of(localTime.getHour()+1, localTime.getMinute()));
        appointment2.setSNSUserArrivalTime(LocalTime.of(localTime.getHour()-1, localTime.getMinute()));;
        appointment3.setSNSUserArrivalTime(LocalTime.of(localTime.getHour(), localTime.getMinute()));;
        int result1 = appointment.compareTo(appointment1);
        int result2 = appointment.compareTo(appointment2);
        Assertions.assertEquals(-1, result1);
        Assertions.assertEquals(1, result2);
    }

    @Test
    void testCheckStateToDo() {
        Appointment appointment = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        boolean result = appointment.checkStateToDo();
        Assertions.assertTrue(result);
    }

    @Test
    void testCheckStateWaiting() {
        Appointment appointment = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        appointment.setStateWaiting();
        boolean result = appointment.checkStateWaiting();
        Assertions.assertTrue(result);
    }

    @Test
    void testCheckStateDone() {
        Appointment appointment = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        appointment.setStateDone();
        boolean result = appointment.checkStateDone();
        Assertions.assertTrue(result);
    }

    @Test
    void testSetStateToDo() {
        Appointment appointment = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        appointment.setStateToDo();
        Assertions.assertTrue(appointment.checkStateToDo());
    }

    @Test
    void testSetStateWaiting() {
        Appointment appointment = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        appointment.setStateWaiting();
        Assertions.assertTrue(appointment.checkStateWaiting());
    }

    @Test
    void testSetStateDone() {
        Appointment appointment = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        appointment.setStateDone();
        Assertions.assertTrue(appointment.checkStateDone());
    }

    @Test
    void testCheckIfToday() {
        Appointment appointment = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.now(), LocalTime.now());
        Appointment appointment1 = new Appointment(new VaccineType("code", "description", "vaccineTechnology"), new SNSUser("name", "adress", "sex", 919191919, "email@email.com", LocalDate.now(), 123456789, 123456789), LocalDate.of(2021, 1, 1), LocalTime.now());
        Assertions.assertTrue(appointment.checkIfToday());
        Assertions.assertFalse(appointment1.checkIfToday());
    }
}

