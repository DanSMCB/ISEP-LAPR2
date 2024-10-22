package app.domain.model;


import java.io.Serializable;
import java.time.format.DateTimeFormatter;

;

/**
 * SMS is a class that represents a SMS message that is sent to the user to notify him of his appointment
 */
public class SMS implements Serializable {
    private String smsContent;
    /**Creates an instance of SMS receiving an appointment and a vaccination center. It then sets the smsContent to a
     * string that contains the name of the vaccination center, the date and time of the appointment.
     * @param appointment : a scheduled appointment
     * @param vaccinationCenter : the location of the scheduled appointment
     */
    public SMS(Appointment appointment,VaccinationCenter vaccinationCenter){
        this.smsContent="Your vaccination appointment is scheduled.\nYou should be at "+vaccinationCenter.getName() + " on "+ appointment.getDate().format(DateTimeFormatter.ofPattern("d/M/uuuu")) + " at " +appointment.getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + ".\n";

    }

    /**Creates an instance of SMS. It then sets the smsContent to a string that contains a message detailing that the sns user recovery time has finished
     */
    public SMS() {
        this.smsContent="Your recovery time has finished. You can now leave the center.";
    }

    /**
     * This function returns the SMS Content
     *
     * @return The SMS Content from this instance of SMS.
     */
    public String getSmsContent(){
         return smsContent;
    }
}
