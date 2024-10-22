package app.mapper.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is a DTO for the Appointment class
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class AppointmentDto implements Serializable {

    /**
     * VaccineType of the appointment.
     */
    private VaccineTypeDto vaccineType;

    /**
     * SNSUser of the appointment.
     */
    private SNSUserDTO snsUser;

    /**
     * Date of the appointment.
     */
    private LocalDate date;

    /**
     * Time of the appointment.
     */
    private LocalTime time;

    /**
     * Create a new appointmentDto with the recived vaccineTypeDto, snsUserDto, date and time.
     * @param vaccineType VaccineTypeDto of the appointment
     * @param snsUser SNSUserDto of the appointment
     * @param date Date of the appointment
     * @param time Time of the appointment
     */
    public AppointmentDto(VaccineTypeDto vaccineType, SNSUserDTO snsUser, LocalDate date, LocalTime time) {
        setVaccineType(vaccineType);
        setSnsUser(snsUser);
        setDate(date);
        setTime(time);
    }

    /**
     * This function returns the date of the appointment.
     *
     * @return The date of the appointment.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * This function sets the date of the appointment.
     *
     * @param date The date of the appointment.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * This function returns the time of the appointment.
     *
     * @return The time of the appointment.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * This function sets the time of the appointment to the time passed in.
     *
     * @param time The time of the appointment.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * This function returns the vaccine type of the appointment.
     *
     * @return The vaccineType of the appointment.
     */
    public VaccineTypeDto getVaccineType() {
        return vaccineType;
    }

    /**
     * This function sets the vaccine type of the appointment.
     *
     * @param vaccineType The vaccine type of the appointment.
     */
    public void setVaccineType(VaccineTypeDto vaccineType) {
        this.vaccineType = vaccineType;
    }

    /**
     * This function returns the SNSUser of the appointment.
     *
     * @return The SNSUser of the appointment.
     */
    public SNSUserDTO getSnsUser() {
        return snsUser;
    }

    /**
     * This function sets the SNSUser of the appointment.
     *
     * @param snsUser The SNSUser of the appointment.
     */
    public void setSnsUser(SNSUserDTO snsUser) {
        this.snsUser = snsUser;
    }

    /**
     * This method returns a string representation of the appointment.
     *
     * @return string representation of the appointment.
     */
    @Override
    public String toString() {
        return "Appointment: [" +
                "vaccineType=" + vaccineType +
                ", snsUser name=" + snsUser.getName() +
                ", snsUser number=" + snsUser.getSnsNumber() +
                ", date=" + date.format(DateTimeFormatter.ofPattern("d/M/uuuu")) +
                ", time=" + time.format(DateTimeFormatter.ofPattern("HH:mm")) +
                ']';
    }

    /**
     * Verifies if an appointmentDTO object has the same data as another appointmentDTO object.
     * @param obj : object to be compared to this instance
     * @return boolean : returns true if the object has the same memory address and/or the same attributes. Returns false if those conditions aren't met or if the object is from another class.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AppointmentDto that = (AppointmentDto) obj;
        return getVaccineType().equals(that.getVaccineType()) && getSnsUser().equals(that.getSnsUser()) && getDate().equals(that.getDate()) && getTime().equals(that.getTime());
    }
}
