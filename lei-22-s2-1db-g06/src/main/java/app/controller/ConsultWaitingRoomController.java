package app.controller;

import app.domain.model.VaccinationCenter;
import app.mapper.AppointmentMapper;
import app.mapper.dto.AppointmentDto;

import java.time.LocalDate;

import java.util.List;

/**
 * This class is responsible for getting the waiting list for a given date and vaccination center.
 *
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class ConsultWaitingRoomController {

    /**
     * Creates an instance of Waiting Room Controller
     */
    public ConsultWaitingRoomController() {
    }

    /**
     * Get the waiting list for a given vaccination center.
     *
     * @param vaccinationCenter The vaccination center to get the waiting list
     * @return A waiting list with AppointmentDto objects.
     */
    public List<AppointmentDto> getWaitingListDTO(VaccinationCenter vaccinationCenter) {
        LocalDate dateToCheck = LocalDate.now();
        return AppointmentMapper.toDto(vaccinationCenter.getWaitingList(dateToCheck));
    }
}
