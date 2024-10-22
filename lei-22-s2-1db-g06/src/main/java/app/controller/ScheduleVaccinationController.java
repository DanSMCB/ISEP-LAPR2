package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.store.*;
import app.mapper.HCCenterMapper;
import app.mapper.MVCenterMapper;
import app.mapper.SMSMapper;
import app.mapper.VaccineTypeMapper;
import app.mapper.dto.*;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

/**
 * Class that is responsible for the scheduling of a vaccination appointment.
 */
public class ScheduleVaccinationController {
    private Company company;
    private SNSUser snsUser;
    private  VaccinationCenter vaccinationCenter;

    public void setOngoingVaccineType() {
        this.vaccineType = company.getOngoingOutbreakVaccineType();
    }

    private VaccineType vaccineType;

    private Appointment appointment;

    private List<MassVaccinationCenter>mvcWithVtList;
    private boolean snsLoggedIn;

    public ScheduleVaccinationController() {
        this.company = App.getInstance().getCompany();
        this.snsLoggedIn = App.getInstance().getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_SNS_USER);
    }

    public boolean getIsSnsLoggedIn(){
        return snsLoggedIn;
    }

    /**
     * This function returns the description of the vaccine type that is currently being used to treat an outbreak
     *
     * @return The description of the vaccine type that is currently being used to treat the outbreak.
     */
    public String getOngoingOutbreak(){
        VaccineType outbreakVaccineType=company.getOngoingOutbreakVaccineType();
        String outbreak=outbreakVaccineType.getDescription();
        return outbreak;
    }
    /**
     * This function returns a list of vaccine types
     *
     * @return A list of VaccineTypeDto objects.
     */
    public List<VaccineTypeDto> getListOfVaccineTypes(){
        VaccineTypeStore vtStore= company.getVTStore();
        List<VaccineType> listVT= vtStore.getVaccineTypes();
        List<VaccineTypeDto> listVTDto=VaccineTypeMapper.toDto(listVT);

        return listVTDto;
    }
    public void selectVaccineType(VaccineTypeDto vtDto){
        VaccineTypeStore vtStore= company.getVTStore();
        this.vaccineType=vtStore.find(vtDto);
    }


    /**
     * This function checks if the SNS user has an appointment for the vaccine type
     *
     * @param snsNumber The number of the SNS user.
     * @return A boolean value (true if the user has an appointment for the vaccine type)
     */
    public boolean checkSnsUserAppointment(long snsNumber) {
        SNSUserStore snsUserStore= company.getSnsUserStore();
        this.snsUser=snsUserStore.findSnsUserByNumber(snsNumber);
        return snsUser.appointmentExists(vaccineType);
    }

    public boolean checkSnsUserAppointment() {
        SNSUserStore snsUserStore= company.getSnsUserStore();
        this.snsUser=snsUserStore.findSnsUserByEmail(App.getInstance().getCurrentUserSession().getUserId());
        return snsUser.appointmentExists(vaccineType);
    }
    /**
     * This function returns a list of MVCs that have the vaccine type specified by the user
     *
     * @return A list of MVCDto that have the vaccine type.
     */
    public List<MVCDto> getMassVaccinationCenters(){
        MVCenterStore mvcStore = company.getMVCenterStore();
        this.mvcWithVtList = mvcStore.getMVCenterListWithVt(vaccineType);
        return MVCenterMapper.toDto(mvcWithVtList);
    }
    /**
     * This function returns a list of all the healthcare centers in the company
     *
     * @return A list of HCCDto.
     */
    public List<HCCDto> getHealthcareCenters(){
        HCCenterStore hccStore = company.getHCCenterStore();
        List<HealthCareCenter> hccList = hccStore.getHCCenterList();
        return HCCenterMapper.toDto(hccList);
    }
    public void toModel(VCDto vcDto) {
        if(vcDto instanceof HCCDto){
            this.vaccinationCenter=company.getHCCenterStore().toModel(vcDto);
        }
        else{
            this.vaccinationCenter=company.getMVCenterStore().toModel(vcDto);
        }
    }
    /**
     * The function creates an appointment for a user, if the slot is valid
     *
     * @param date the date of the appointment
     * @param time the time of the appointment
     * @return if the Appointment has been created or not
     */
    public boolean createAppointment(LocalDate date, LocalTime time) throws Exception {
        Slot sl=vaccinationCenter.findSlot(date,time);
        if(sl.validateSlot()){
            this.appointment=vaccinationCenter.createVaccinationAppointment(vaccineType,snsUser,date,time);
            snsUser.addAppointment(appointment);
            sl.addScheduledVaccine();
            return true;
        } else{
            return false;
        }
    }
    /**
     * Generate an SMS from the appointment and vaccination center, and return it as a DTO
     *
     * @return SMSDto
     */
    public SMSDto generateSMS(){
        SMSStore smsStore=snsUser.getSMSStore();
        SMS sms=smsStore.generateSMS(appointment,vaccinationCenter);
        SMSDto generatedSMS= SMSMapper.smsToDto(sms);

        return generatedSMS;
    }

}
