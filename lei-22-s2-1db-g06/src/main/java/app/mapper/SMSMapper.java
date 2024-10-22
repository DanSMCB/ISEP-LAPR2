package app.mapper;

import app.domain.model.SMS;
import app.domain.store.SMSStore;
import app.mapper.dto.SMSDto;

public class SMSMapper {
    /**
     * It takes an SMS object and returns an SMSDto object
     *
     * @param sms The SMS object that you want to convert to a DTO.
     * @return SMSDto
     */
    public static SMSDto smsToDto(SMS sms){
        String smsContent= sms.getSmsContent();
         SMSDto smsDto= new SMSDto(smsContent);
        return smsDto;
    }
}
