package app.domain.store;

import app.domain.model.*;
import app.domain.shared.Constants;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

/**
 * Saves Appointment objects
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>, Daniel Braga <1200801@isep.ipp.pt>
 */
public class AppointmentStore implements Serializable {
    /**
    * List of Appointment objects.
    */
    private  List<Appointment> listAppointment;

    /**
     * Returns the size of the list of the appointments
     * @return listAppointment.size() : size of listAppointment
     */
    public int getSize(){
        return listAppointment.size();
    }

    /**
     * Returns an appointment of the list
     * @return an appointment with the specified index
     */
    public Appointment getAppointment(int index){
        return listAppointment.get(index);
    }

    public void addAppointment(Appointment appointment){
        listAppointment.add(appointment);
    }

    /**
     * Get the waiting list for a given date.
     *
     * @param dateToCheck The date to check for appointments
     * @return A list of appointments that are waiting to be seen
     */
    public List<Appointment> getWaitingList(LocalDate dateToCheck) {
        List<Appointment> waitingList = new ArrayList<>();
        for (Appointment appointment: listAppointment) {
            if (dateToCheck.isEqual(appointment.getDate()) && appointment.checkStateWaiting()) {
                waitingList.add(appointment);
            }
        }
        Collections.sort(waitingList);
        return waitingList;
    }

    /**
     * Create a new Appointment Store with an empty appointment list.
     */
    public AppointmentStore() {
        listAppointment = new ArrayList<>();
    }

    /**
     * Creates a new appointment for a vaccination.
     *
     * @param vaccineType The type of vaccine that the user wants to get.
     * @param snsUser The user who is making the appointment.
     * @param date The date of the appointment.
     * @param time The time of the appointment.
     * @return An appointment object is being returned.
     */
    public Appointment createVaccinationAppointment(VaccineType vaccineType , SNSUser snsUser, LocalDate date, LocalTime time) {
        Appointment appointment = new Appointment(vaccineType,snsUser,date,time);
        listAppointment.add(appointment);
        return appointment;
    }
    /**
     * This function returns an array of integers, where each integer represents the number of fully vaccinated people on a
     * given day
     *
     * @param initialDate The initial date of the interval
     * @param finalDate the final date of the interval
     * @return An array of integers.
     */
    public int[]getNumberOfFullyVaccinated(LocalDate initialDate,LocalDate finalDate) throws Exception {
        long daysInInterval=initialDate.until(finalDate, ChronoUnit.DAYS)+1;
        int[]fullVaccinatedArray=new int[(int)daysInInterval];
        int i =0;
        for (LocalDate day = LocalDate.of(initialDate.getYear(), initialDate.getMonth(),initialDate.getDayOfMonth()); day.isBefore(finalDate) ; day=day.plusDays(1)) {
           int fullVaccinatedCounter=0;
            for (Appointment appointment:listAppointment) {
                if (appointment.checkStateDone()) {
                    SNSUser snsUser=appointment.getSnsUser();
                    int age=snsUser.getAge();
                    LocalDate date=appointment.getDate();
                    int numberOfDose=appointment.getVaccineAdministration().getDose().getDoseNumber();
                    Vaccine vaccine=appointment.getVaccineAdministration().getVaccine();
                    AdministrationProcess admProcess=vaccine.getAdmProcess(age);
                    int totalNumberOfDoses=admProcess.getNumberOfDoses();
                    if(numberOfDose==totalNumberOfDoses){
                        if(date.equals(day)){
                            fullVaccinatedCounter=fullVaccinatedCounter+1;
                        }
                    }
                }
            }
            fullVaccinatedArray[i]=fullVaccinatedCounter;
            i++;
        }
        return fullVaccinatedArray;
    }


    /**
     * This function returns an array of integers, which represents the number of patients in the waiting room at a certain
     * time interval
     *
     * @param dayToCheck the day you want to check
     * @param timeInterval the interval in minutes that you want to check the number of people in the center
     * @return The method returns an array of integers.
     */
    public int[] getArrivalNumberList(LocalDate dayToCheck, int timeInterval) {
        if (!validateTimeInterval(timeInterval))
            throw new InvalidParameterException("Invalid time interval");
        int lenght = 720/timeInterval;
        int[] arrivalNumberList = new int[lenght];
        for (int interval = 0; interval < lenght; interval++) {
            int leavingCounter = 0, arrivalCounter = 0;
            LocalTime lowerTime = LocalTime.of(8,0).plusMinutes(((long) timeInterval * interval));
            LocalTime upperTime = LocalTime.of(8,0).plusMinutes(((long) timeInterval * (interval+1)));
            for(Appointment appointment : listAppointment) {
                if (dayToCheck.equals(appointment.getDate())) {
                    if (appointment.checkStateDone() && (appointment.getSNSUserLeaveTime().isBefore(upperTime) && !appointment.getSNSUserLeaveTime().isBefore(lowerTime))) {
                        leavingCounter++;
                    }
                    if (!(appointment.checkStateToDo()) && (appointment.getSNSUserArrivalTime().isBefore(upperTime) && !appointment.getSNSUserArrivalTime().isBefore(lowerTime))) {
                        arrivalCounter++;
                    }
                }
            }
            arrivalNumberList[interval] = arrivalCounter-leavingCounter;
        }
        return arrivalNumberList;
    }

    public Appointment createVaccinationAppointment(VaccineType vaccineType, SNSUser snsUser, LocalDate date, LocalTime time, LocalDateTime arrivalTime,LocalDateTime leavingTime) {
        Appointment appointment = new Appointment(vaccineType,snsUser,date,time,arrivalTime,leavingTime);
        listAppointment.add(appointment);
        return appointment;
    }


    /**
     * This method returns if the time interval is valid to create the Arrival Number List.
     *
     * @param timeInterval The time interval in minutes.
     * @return The method returns a boolean value.
     */
    public boolean validateTimeInterval(int timeInterval) {
        if (timeInterval<0) return false;
        double lenghtDouble =(((double) 720)/timeInterval);
        return lenghtDouble%1 == 0;
    }
}
