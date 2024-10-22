package app.mapper;

import app.domain.model.Appointment;
import app.mapper.dto.AppointmentDto;
import app.mapper.dto.SNSUserDTO;
import app.mapper.dto.VaccineTypeDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * It converts a list of Appointment objects into a list of AppointmentDto objects
 * @author Afonso Costa <1211343@isep.ipp.pt>, Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class AppointmentMapper {

    /**
     * It takes a list of appointments and returns a list of appointmentDTOs
     *
     * @param waitingList the list of appointments that we want to convert to DTOs
     * @return A list of AppointmentDto objects.
     */
    public static List<AppointmentDto> toDto(List<Appointment> waitingList) {
        List<AppointmentDto> waitingListDTO = new ArrayList<>();
        for (Appointment appointment : waitingList) {
            VaccineTypeDto vaccineTypeDto = VaccineTypeMapper.toDto(appointment.getVaccineType());
            SNSUserDTO snsUserDto = SNSUserMapper.toDto(appointment.getSnsUser());
            LocalTime time = appointment.getTime();
            LocalDate date = appointment.getDate();
            AppointmentDto appointmentDto = new AppointmentDto(vaccineTypeDto, snsUserDto, date, time);
            waitingListDTO.add(appointmentDto);
        }
        return waitingListDTO;
    }
}
