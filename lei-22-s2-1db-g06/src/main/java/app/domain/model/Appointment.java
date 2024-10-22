package app.domain.model;

import app.mapper.dto.AppointmentDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Appointment class represents an appointment for a vaccination
 *
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>, Afonso Costa <1211343@isep.ipp.pt>, José Barbosa <1211359@isep.ipp.pt>
 */
public class Appointment implements Comparable<Appointment>, Serializable {

    /**
     * VaccineType of the appointment.
     */
    private VaccineType vaccineType;

    /**
     * SNSUser of the appointment.
     */
    private SNSUser snsUser;

    /**
     * Date of the appointment.
     */
    private LocalDate date;

    /**
     * Time of the appointment.
     */
    private LocalTime time;

    /**
     * Time of arrival of the user.
     */
    private LocalTime snsUserArrivalTime;

    /**
     * Time of leaving of the user.
     */
    private LocalTime snsUserLeaveTime;


    /**
     * Object that stores information relating to the vaccine administration that occurred in this appointment.
     */
    private VaccineAdministration vaccineAdministration;

    /**
     * The compareTo() method compares two objects of the appointment class and returns a negative integer, zero, or a positive
     * integer as this appointment time of arrival is less than, equal to, or greater than the specified object's time of arrival.
     *
     * @param appointment The appointment to compare to.
     * @return Negative integer, zero, or a positive integer as this appointment time of arrival is less than, equal to, or greater than the specified object's time of arrival.
     */
    @Override
    public int compareTo(Appointment appointment) {
        return snsUserArrivalTime.compareTo(appointment.getSNSUserArrivalTime());
    }

    /**
     * If the day, month, and year of the date object are the same as the day, month, and year of the date object that
     * represents today, then return true, otherwise return false.
     *
     * @return A boolean value.
     */
    public boolean checkIfToday() {
        LocalDate dateToday = LocalDate.now();
        return dateToday.isEqual(date);
    }

    /**
     * Possible states of the appointment. An appointment can be in TODO, WAITING, and DONE state.
     */
    private enum State {
        TODO,
        WAITING,
        DONE;
    }

    /**
     * State of the appointment.
     */
    private State state;

    /**
     * Create a new appointment with the recived vaccineType, snsUser, date and time.
     * @param vaccineType VaccineType of the appointment
     * @param snsUser SNSUser of the appointment
     * @param date Date of the appointment
     * @param time Time of the appointment
     */
    public Appointment(VaccineType vaccineType, SNSUser snsUser, LocalDate date, LocalTime time) {
        setVaccineType(vaccineType);
        setSnsUser(snsUser);
        setDate(date);
        setTime(time);
        setStateToDo();
    }

    /**
     * Create a new appointment with the recived vaccineType, snsUser, date and time.
     * @param vaccineType VaccineType of the appointment
     * @param snsUser SNSUser of the appointment
     * @param date Date of the appointment
     * @param time Time of the appointment
     * @param arrivalTime Time of User arrival
     * @param leavingTime Time of User departure
     */
    public Appointment(VaccineType vaccineType, SNSUser snsUser, LocalDate date, LocalTime time, LocalDateTime arrivalTime, LocalDateTime leavingTime) {
        setVaccineType(vaccineType);
        setSnsUser(snsUser);
        setDate(date);
        setTime(time);
        setStateDone();
        setSNSUserArrivalTime(arrivalTime.toLocalTime());
        setSNSUserLeaveTime(leavingTime.toLocalTime());
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
    public VaccineType getVaccineType() {
        return vaccineType;
    }

    /**
     * This function sets the vaccine type of the appointment.
     *
     * @param vaccineType The vaccine type of the appointment.
     */
    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    /**
     * This function returns the SNSUser of the appointment.
     *
     * @return The SNSUser of the appointment.
     */
    public SNSUser getSnsUser() {
        return snsUser;
    }

    /**
     * This function sets the SNSUser of the appointment.
     *
     * @param snsUser The SNSUser of the appointment.
     */
    public void setSnsUser(SNSUser snsUser) {
        this.snsUser = snsUser;
    }

    /**
     * Check if the state is TODO.
     *
     * @return A boolean value.
     */
    public boolean checkStateToDo() {
        return state == State.TODO;
    }

    /**
     * Check if the state is WAITING.
     *
     * @return A boolean value.
     */
    public boolean checkStateWaiting() {
        return state == State.WAITING;
    }

    /**
     * Check if the state is DONE.
     *
     * @return A boolean value.
     */
    public boolean checkStateDone() {
        return state == State.DONE;
    }

    /**
     * This function sets the state of the appointment to TODO.
     */
    public void setStateToDo() {
        this.state = State.TODO;
    }

    /**
     * This function sets the state of the appointment to WAITING.
     */
    public void setStateWaiting() {
        this.state = State.WAITING;
    }

    /**
     * This function sets the state of the appointment to DONE.
     */
    public void setStateDone(){
        this.state = State.DONE;
    }

    /**
     * This function returns the time at which the SNS user arrived at the vaccination center
     *
     * @return The arrival time of the user.
     */
    public LocalTime getSNSUserArrivalTime() {
        return snsUserArrivalTime;
    }

    /**
     * This function sets the arrival time of the user at the vaccination center.
     *
     * @param snsUserArrivalTime The time the user arrived at the vaccination center.
     */
    public void setSNSUserArrivalTime(LocalTime snsUserArrivalTime) {
        this.snsUserArrivalTime = snsUserArrivalTime;
    }

    /**
     * This function returns the time at which the SNS user leaved the vaccination center
     *
     * @return The leave time of the user.
     */
    public LocalTime getSNSUserLeaveTime() {
        return snsUserLeaveTime;
    }
    /**
     * This function sets the leave time of the user at the vaccination center.
     *
     * @param snsUserLeaveTime The time the user leaved at the vaccination center.
     */
    public void setSNSUserLeaveTime(LocalTime snsUserLeaveTime) {
        this.snsUserLeaveTime = snsUserLeaveTime;
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
                ", snsUser=" + snsUser +
                ", date=" + date.format(DateTimeFormatter.ofPattern("d/M/uuuu")) +
                ", time=" + time.format(DateTimeFormatter.ofPattern("HH:mm")) +
                ']';
    }

    /**
     * This function returns true if the vaccine type of the vaccine record matches the vaccine type passed in as a
     * parameter
     *
     * @param vaccineType The vaccine type to compare against.
     * @return A boolean value.
     */
    public boolean vaccineTypeMatches(VaccineType vaccineType) {
        VaccineType vtCompare = this.getVaccineType();
        if (vtCompare == vaccineType) {
            return true;
        }
        return false;
    }

    /**
     * It compares an Appointment with an AppointmentDto.
     *
     * @param appointmentDto The appointmentDto object to compare to.
     * @return The method returns a boolean value.
     */
    public boolean equals(AppointmentDto appointmentDto) {
        if (appointmentDto == null) return false;
        return (getVaccineType().equals(appointmentDto.getVaccineType())) && (getSnsUser().equals(appointmentDto.getSnsUser())) && Objects.equals(getDate(), appointmentDto.getDate()) && Objects.equals(getTime(), appointmentDto.getTime());
    }

    /**
     * Verifies if an Appointment has the same data as another Appointment.
     * @param obj : object to be compared to this instance
     * @return boolean : returns true if the object has the same memory address and/or the same attributes. Returns false if those conditions aren't met or if the object is from another class.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment that = (Appointment) obj;
        return Objects.equals(getVaccineType(), that.getVaccineType()) && Objects.equals(getSnsUser(), that.getSnsUser()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(snsUserArrivalTime, that.snsUserArrivalTime) && state == that.state;
    }

    /**
     * This function creates a new vaccine administration and saves it in this appointment
     *
     * @param lotNumber The lot number of the vaccine
     * @param dateOfAdministration The date and time the vaccine was administered.
     * @param vaccine The vaccine that was administered.
     * @param admp AdministrationProcess
     * @param dose The dose of the vaccine that was administered.
     */
    public void addVaccineAdministration(String lotNumber, LocalDateTime dateOfAdministration, Vaccine vaccine, AdministrationProcess admp, Dose dose){
        VaccineAdministration vaccineAdministration = new VaccineAdministration(lotNumber,dateOfAdministration,vaccine,admp,dose);
        setVaccineAdministration(vaccineAdministration);
        setStateDone();


    }

    /**
     * This function creates a new vaccine administration and saves it in this appointment
     *
     * @param lotNumber The lot number of the vaccine
     * @param dateOfAdministration The date and time the vaccine was administered.
     * @param vaccine The vaccine that was administered
     * @param admp AdministrationProcess
     * @param dose the dose of the vaccine
     * @param snsUserLeaveTime The time the SNS user arrived at the vaccination site.
     */
    public void addVaccineAdministration(String lotNumber, LocalDateTime dateOfAdministration, Vaccine vaccine, AdministrationProcess admp, Dose dose, LocalTime snsUserLeaveTime){
        VaccineAdministration vaccineAdministration = new VaccineAdministration(lotNumber,dateOfAdministration,vaccine,admp,dose);
        setVaccineAdministration(vaccineAdministration);
        setStateDone();
        setSNSUserLeaveTime(snsUserLeaveTime);
    }

    /**
     * This function sets the vaccine administration
     *
     * @param vaccineAdministration The vaccine administration that will be saved in this appointment
     */
    private void setVaccineAdministration(VaccineAdministration vaccineAdministration) {
        this.vaccineAdministration = vaccineAdministration;
    }

    public VaccineAdministration getVaccineAdministration() {
        return vaccineAdministration;
    }
}
