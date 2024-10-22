package app.domain.store;

import app.domain.model.Appointment;
import app.domain.model.SMS;
import app.domain.model.VaccinationCenter;
import app.mapper.dto.SMSDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SMSStore implements Serializable {
    /**
     * List responsible for storing SMS
     */
    private List<SMS> listSMS;
    /**
     * This function generates an SMS object and adds it to the list of SMS objects
     *
     * @param appointment The appointment object that is to be sent to the user.
     * @param vaccinationCenter The vaccination center where the appointment is scheduled.
     * @return SMS
     */
    public SMS generateSMS(Appointment appointment, VaccinationCenter vaccinationCenter){
        SMS sms= new SMS(appointment,vaccinationCenter);
        listSMS.add(sms);
        return sms;
    }

    public SMSStore() {
        this.listSMS=new ArrayList<>();
    }

    public SMS generateSMS() {
        SMS sms= new SMS();
        listSMS.add(sms);
        return sms;
    }
}
