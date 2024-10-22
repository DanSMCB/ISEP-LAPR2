package app.mapper;

import app.domain.model.HealthCareCenter;
import app.mapper.dto.HCCDto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HCCenterMapper {
    /**
     * It takes a list of HealthCareCenter objects and returns a list of HCCDto
     *
     * @param healthcareCenterList the list of healthcare centers that we want to convert to DTOs
     * @return A list of HCCDto
     */
    public static List<HCCDto> toDto(List<HealthCareCenter> healthcareCenterList){
        List<HCCDto> hccenterListDto=new ArrayList<>();

        for (HealthCareCenter hcc : healthcareCenterList)
        {
            String name =hcc.getName();
            String address = hcc.getAddress();
            long phoneNumber  = hcc.getPhone_number();
            String email= hcc.getEmail();
            long faxNumber=hcc.getFax_number();
            String website_address= hcc.getWebsite_address();
            LocalTime opening_hours= hcc.getOpeningHour();
            LocalTime closing_hours=hcc.getClosingHour();
            int slot_duration=hcc.getSlotDuration();
            int n_vaccine_p_slot=hcc.getNumberOfVaccinesSlot();
            String aces = hcc.getACES();
            String ars = hcc.getARS();

            HCCDto hccDto = new HCCDto(name,address,phoneNumber,email,faxNumber,website_address,opening_hours,closing_hours,slot_duration,n_vaccine_p_slot, aces, ars);
            hccenterListDto.add(hccDto);
        }
        return hccenterListDto;
    }
}
