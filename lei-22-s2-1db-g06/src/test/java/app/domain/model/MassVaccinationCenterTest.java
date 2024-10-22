package app.domain.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class MassVaccinationCenterTest {
    String s1 = "07:00";
    String s2="23:00";
    LocalTime date1 = LocalTime.parse(s1, DateTimeFormatter.ofPattern("HH:mm"));
    LocalTime date2 = LocalTime.parse(s2, DateTimeFormatter.ofPattern("HH:mm"));
    VaccineType vtTest = new VaccineType("11111", "vaccine type 1", "live-attenuated");
    MassVaccinationCenter mvc1 = new MassVaccinationCenter("name","address",123456789,"email@gmail.com",1234567890,"website_Address",date1,date2,10,10,vtTest);

    MassVaccinationCenterTest() throws ParseException {
    }

}