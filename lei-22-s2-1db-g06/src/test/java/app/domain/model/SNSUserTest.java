package app.domain.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SNSUserTest {

    String s1 = "10/10/2002";
    LocalDate date1 = LocalDate.parse(s1, DateTimeFormatter.ofPattern("d/M/yyyy"));
    SNSUser snsUser = new SNSUser("name","address","male",123123123,"email@gmail.com",date1,123123123,12312312);

    SNSUserTest() throws ParseException {
    }

    @Test
    void getName() {
        assertEquals("name",snsUser.getName());
    }

    @Test
    void setName() {
        snsUser.setName("name1");
        assertEquals("name1",snsUser.getName());
    }

    @Test
    void getAddress() {
        assertEquals("address",snsUser.getAddress());
    }

    @Test
    void setAddress() {
        snsUser.setAddress("address1");
        assertEquals("address1",snsUser.getAddress());
    }

    @Test
    void getSex() {
        assertEquals("male",snsUser.getSex());
    }

    @Test
    void setSex() {
        snsUser.setSex("female");
        assertEquals("female",snsUser.getSex());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(123123123,snsUser.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        snsUser.setPhoneNumber(123123124);
        assertEquals(123123124,snsUser.getPhoneNumber());
    }

    @Test
    void getEmail() {
        assertEquals("email@gmail.com",snsUser.getEmail());
    }

    @Test
    void setEmail() {
        snsUser.setEmail("email1@gmail.com");
        assertEquals("email1@gmail.com",snsUser.getEmail());
    }

    @Test
    void getBirthDate() {
        assertEquals(date1,snsUser.getBirthDate());
    }

    @Test
    void setBirthDate() throws ParseException {
        String s2 = "10/10/2000";
        LocalDate date2 = LocalDate.parse(s2, DateTimeFormatter.ofPattern("d/M/yyyy"));
        snsUser.setBirthDate(date2);
        assertEquals(date2,snsUser.getBirthDate());
    }

    @Test
    void getSnsNumber() {
        assertEquals(123123123,snsUser.getSnsNumber());
    }

    @Test
    void setSnsNumber() {
        snsUser.setSnsNumber(123123124);
        assertEquals(123123124,snsUser.getSnsNumber());
    }

    @Test
    void getCitizenCardNumber() {
        assertEquals(12312312,snsUser.getCitizenCardNumber());
    }

    @Test
    void setCitizenCardNumber() {
        snsUser.setCitizenCardNumber(123123123);
        assertEquals(123123123,snsUser.getCitizenCardNumber());
    }

    @Test
    void checkNameFormat() {
        String name = "";
        String name1 = "name";
        assertFalse(SNSUser.checkNameFormat(name));
        assertTrue(SNSUser.checkNameFormat(name1));
    }

    @Test
    void checkAddressFormat() {
        String address = "";
        String address1 = "address";
        assertFalse(SNSUser.checkAddressFormat(address));
        assertTrue(SNSUser.checkAddressFormat(address1));
    }

    @Test
    void checkSexFormat() {
        String sex = "";
        String sex1 = "male";
        String sex2 = "female";
        String sex3 = "abc";
        assertTrue(SNSUser.checkSexFormat(sex));
        assertTrue(SNSUser.checkSexFormat(sex1));
        assertTrue(SNSUser.checkSexFormat(sex2));
        assertFalse(SNSUser.checkSexFormat(sex3));
    }

    @Test
    void checkPhoneNumberFormat() {
        long phoneNumber = 1231231231;
        long phoneNumber1 = 123123123;
        assertFalse(SNSUser.checkPhoneNumberFormat(phoneNumber));
        assertTrue(SNSUser.checkPhoneNumberFormat(phoneNumber1));
    }

    @Test
    void checkSNSNumberFormat() {
        long snsNumber = 1231231231;
        long snsNumber1 = 123123123;
        assertFalse(SNSUser.checkSNSNumberFormat(snsNumber));
        assertTrue(SNSUser.checkSNSNumberFormat(snsNumber1));
    }

    @Test
    void checkCitizenCardNumberFormat() {
        long citizenCardNumber = 123123123;
        long citizenCardNumber1 = 12312312;
        assertFalse(SNSUser.checkCitizenCardNumberFormat(citizenCardNumber));
        assertTrue(SNSUser.checkCitizenCardNumberFormat(citizenCardNumber1));
    }

    @Test
    void testGetTodayAppointment() {
        LocalTime localTime = LocalTime.now();
        snsUser.getSnsUserAppointmentStore().addAppointment(new Appointment(new VaccineType("vaccine", "vaccine", "vaccine"), snsUser, LocalDate.now(), localTime));
        snsUser.getSnsUserAppointmentStore().addAppointment(new Appointment(new VaccineType("vaccine", "vaccine", "vaccine"), snsUser, date1, localTime));
        assertEquals(snsUser.getTodayAppointment(), List.of(new Appointment(new VaccineType("vaccine", "vaccine", "vaccine"), snsUser, LocalDate.now(), localTime)));
        assertNotEquals(snsUser.getTodayAppointment(), List.of(new Appointment(new VaccineType("vaccine", "vaccine", "vaccine"), snsUser, LocalDate.now(),localTime), new VaccineType("vaccine", "vaccine", "vaccine"), snsUser, date1, LocalTime.now()));
    }

    @Test
    void getAge() {
        SNSUser snsUserTest=new SNSUser("Rui","Rua das Flores","male",123456789,"user1@sem2.pt",LocalDate.of(2003,3,21),2345678,12345678);
        long age=19;
        long ageTest=snsUserTest.getAge();
        assertEquals(age,ageTest);
    }
}