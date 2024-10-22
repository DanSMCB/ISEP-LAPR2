package app.controller;

import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.mapper.AppointmentMapper;
import app.mapper.dto.AppointmentDto;

import java.time.LocalTime;

import java.util.List;
/**
 * Controller for User/Domain interaction in the registering of the arrival of a SNS user.
 * @author Afonso Costa <1211343@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalController {
    private Company company;
    private SNSUser snsUser;

    /**
     * Creates a new instance of RegisterSNSUserArrivalController.
     */
    public RegisterSNSUserArrivalController() {
        company=App.getInstance().getCompany();
    }
    /**
     * Return a list of AppointmentDtos that are associated with the user that has the given snsNumber.
     *
     * @param snsNumber The social security number of the user.
     * @return A list of AppointmentDto objects.
     */
    public List<AppointmentDto> checkSNSAppointment(long snsNumber) throws Exception {
        snsUser=company.getSnsUserStore().findSnsUserByNumber(snsNumber);
        return AppointmentMapper.toDto(snsUser.getTodayAppointment());
    }

    /**
     * Change the state of the appointment to waiting and set the arrival time to now.
     *
     * @param appointmentDto The appointmentDto object that is passed in from the client.
     */
    public void changeAppointmentStateToWaiting(AppointmentDto appointmentDto) throws Exception {
        Appointment appointment = snsUser.getSnsUserAppointmentStore().findAppointment(appointmentDto);
        appointment.setStateWaiting();
        appointment.setSNSUserArrivalTime(LocalTime.now());
    }


}
