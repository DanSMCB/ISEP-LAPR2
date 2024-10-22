package app.domain.store;

import app.domain.model.Appointment;
import app.domain.model.SNSUser;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.mapper.dto.AppointmentDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class stores all the appointments for a user
 * @author Afonso <1211343@isep.ipp.pt>
 */
public class SNSUserAppointmentStore implements Serializable {
    private List<Appointment> listSnsUserAppointments=new ArrayList<>();

    /**
     * If the user has an appointment for the vaccine type, return true, otherwise throw an exception.
     *
     * @param vaccineType The vaccine type that the user wants to get vaccinated with.
     * @return The method returns a boolean value.
     */
    public boolean appointmentExists(VaccineType vaccineType){
        if (listSnsUserAppointments.isEmpty())
            return false;
        for (Appointment savedAppointment : listSnsUserAppointments) {
                if(savedAppointment.vaccineTypeMatches(vaccineType)){
                    return true;
                }
            }
        return false;
    }

    /**
     * This function adds an appointment to the list of appointments for a user
     *
     * @param appointment The appointment object that you want to add to the user's list of appointments.
     */
    public void addAppointment(Appointment appointment){
        listSnsUserAppointments.add(appointment);
    }

    /**
     * Get all the appointments that are scheduled for today
     *
     * @return A list of Appointment objects.
     */
    public List<Appointment> getTodayAppointment() {
        List<Appointment> lTodayAppointment = new ArrayList<>();
        for (Appointment appointment : listSnsUserAppointments) {
            if (appointment.checkStateToDo() && appointment.checkIfToday()) {
                lTodayAppointment.add(appointment);
            }
        }
        return lTodayAppointment;
    }

    /**
     * Finds an appointment in the list of appointments of the user
     *
     * @param appointmentDto The appointmentDto object that is used to find the appointment in the list.
     * @return The appointment that is being searched for.
     */
    public Appointment findAppointment(AppointmentDto appointmentDto) throws Exception {
        for (Appointment appointment: listSnsUserAppointments) {
            if(appointment.equals(appointmentDto)){
                return appointment;
            }
        }
        throw new Exception("Appointment is not stored");
    }

    /**
     * Return the number of the sns user's current vaccine dose for the specified vaccine type
     *
     * @param vaccineType The vaccine type to check for.
     * @return the number of the sns user's current vaccine dose for the specified vaccine type.
     */
    public int getCurrentVaccineDose(VaccineType vaccineType) {
        if (appointmentExists(vaccineType)) {
            int count = 0;
            for (Appointment apoint: listSnsUserAppointments) {
                if (apoint.vaccineTypeMatches(vaccineType) && apoint.checkStateToDo()) {
                    count++;
                }
            }
            return count;
        }
        return 0;

    }

    /**
     * Return the vaccine that is associated with the vaccine type.
     *
     * @param vaccineType The type of vaccine you want to get.
     * @return The vaccine that is associated with the vaccine type.
     */
    public Vaccine getVaccine(VaccineType vaccineType) throws Exception {
        for (Appointment apoint: listSnsUserAppointments) {
            if (apoint.vaccineTypeMatches(vaccineType) && apoint.getVaccineAdministration()!=null) {
                return apoint.getVaccineAdministration().getVaccine();
            }
        }
        throw new Exception("No vaccine has associated with this vaccine type");
    }
}
