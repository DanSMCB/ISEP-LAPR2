package app.controller;

import app.domain.model.*;
import app.domain.store.*;
import app.mapper.AppointmentMapper;
import app.mapper.SMSMapper;
import app.mapper.VaccineMapper;
import app.mapper.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class RecordAdministrationOfVaccineController {

    private SNSUserStore snsUserStore;

    private SNSUser snsUser;

    private SNSUserAppointmentStore snsUserAppointmentStore;

    private AppointmentDto appointmentDTO;

    private VaccineStore vaccineStore;

    private VaccineType vaccineType;

    private LocalDate dateToCheck;

    private Vaccine vaccine;

    private int currentVaccineDose;

    private LocalDateTime dateOfAdministration;

    private Dose dose;

    private Appointment appointment;

    private final Company company;
    private final VaccinationCenter vaccinationCenter;

    public RecordAdministrationOfVaccineController(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter=vaccinationCenter;
        this.company = App.getInstance().getCompany();
    }

    public List<AppointmentDto> getWaitingListDTO(){
        this.dateToCheck = LocalDate.now();
        List<Appointment> waitingList = vaccinationCenter.getWaitingList(dateToCheck);
        List <AppointmentDto> waitingListDTO = AppointmentMapper.toDto(waitingList);
        return waitingListDTO;
    }

    public void getSNSUser(AppointmentDto appointmentDto) {
        SNSUserDTO snsUserDTO = appointmentDto.getSnsUser();
        long snsNumber = snsUserDTO.getSnsNumber();
        this.snsUserStore = company.getSnsUserStore();
        this.snsUser = snsUserStore.findSnsUserByNumber(snsNumber);
    }

    public int getCurrentDose() {
        this.vaccineType = appointment.getVaccineType();
        int currentVaccineDose = snsUserAppointmentStore.getCurrentVaccineDose(vaccineType);
        return currentVaccineDose;
    }

    public List<VaccineDto> getVaccineList(AppointmentDto appointmentDTO) {
        VaccineTypeDto vaccineTypeDto = appointmentDTO.getVaccineType();
        VaccineTypeStore vaccineTypeStore = company.getVaccineTypeStore();
        this.vaccineType = vaccineTypeStore.find(vaccineTypeDto);
        this.vaccineStore = vaccineType.getVaccineStore();
        List<Vaccine> lVaccines = vaccineStore.getVaccineList();
        List<VaccineDto> lVaccinesDto = VaccineMapper.toDto(lVaccines);
        return lVaccinesDto;

    }

    public void findVaccine(VaccineDto vaccineDto) {
        this.vaccine = vaccineStore.findVaccine(vaccineDto);
    }

    public void getVaccine() throws Exception {
        this.vaccine = snsUserAppointmentStore.getVaccine(vaccineType);
    }

    public void saveVaccineAdministration(String lotNumber, LocalTime leavingTime) throws Exception {
        AdmProcessStore admpStore = vaccine.getAdmProcessStore();
        int age = snsUser.getAge();
        AdministrationProcess admp = admpStore.getAdmProcess(age);
        Dose dose = admp.findDose(currentVaccineDose+1);
        appointment.addVaccineAdministration(lotNumber, dateOfAdministration, vaccine, admp, dose, leavingTime);

    }

    public void findAppointment(AppointmentDto appointmentDTO) throws Exception {
        this.snsUserAppointmentStore = snsUser.getSnsUserAppointmentStore();
        this.appointment = snsUserAppointmentStore.findAppointment(appointmentDTO);
    }



    public SMSDto generateSMS() {
        SMSStore smsStore = snsUser.getSmsStore();
        SMS sms = smsStore.generateSMS();
        return SMSMapper.smsToDto(sms);
    }
}
