package app.mapper.dto;

import java.io.Serializable;

public class SMSDto implements Serializable {
    /**
     * String that contains the details of the appointment
     */
    private String smsContent;

    /**
     * Creates an instance of SMSDtp receiving smsContent
     * @param smsContent String that contains the details of the appointment
     */

    public SMSDto(String smsContent){
        this.smsContent=smsContent;
    }
    /**
     * This function returns the smsContent
     *
     * @return The smsContent variable is being returned.
     */
    public String getSmsContent() {
        return smsContent;
    }
}
