package app.mapper;

import app.domain.model.MassVaccinationCenter;
import app.domain.model.VaccineType;
import app.mapper.dto.MVCDto;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

public class MVCenterMapper {

    /**
     * It takes a DTO and returns a model
     *
     * @param mvcDto the DTO object that contains the data that we want to convert to a model object.
     * @return A MassVaccinationCenter object
     */
    public static MassVaccinationCenter toModel(MVCDto mvcDto){
        String name = mvcDto.getName();
        String address = mvcDto.getAddress();
        long phoneNumber = mvcDto.getPhoneNumber();
        String emailAddress = mvcDto.getEmail();
        long faxNumber = mvcDto.getFaxNumber();
        String websiteAddress = mvcDto.getWebsiteAdress();
        LocalTime openingHour = mvcDto.getOpeningHour();
        LocalTime closingHour = mvcDto.getClosingHour();
        int slotDuration = mvcDto.getSlotDuration();;
        int numberOfVaccinesSlot = mvcDto.getNumberOfVaccinesSlot();
        VaccineType vaccineType = mvcDto.getVaccineType();
        return new MassVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration,numberOfVaccinesSlot,vaccineType);
    };

    /**
     * It converts a list of MassVaccinationCenter objects into a list of MVCDto objects.
     *
     * @param mvcWithVtList the list of MassVaccinationCenter objects that you want to convert to a list of MVCDto objects.
     * @return A list of MVCDto objects.
     */
    public static List<MVCDto> toDto(List<MassVaccinationCenter> mvcWithVtList) {
        List<MVCDto> mvcWithVtDTOList = new ArrayList<>();
        for (MassVaccinationCenter mvc : mvcWithVtList) {
            String name = mvc.getName();
            String adress = mvc.getAddress();
            long phoneNumber = mvc.getPhone_number();
            String emailAdress = mvc.getEmail();
            long faxNumber = mvc.getFax_number();
            String websiteAddress = mvc.getWebsite_address();
            LocalTime openingHour = mvc.getOpeningHour();
            LocalTime closingHour = mvc.getClosingHour();
            int slotDuration = mvc.getSlotDuration();
            int numberOfVaccinesSlot = mvc.getNumberOfVaccinesSlot();
            VaccineType vaccineType = mvc.getVaccineType();
            mvcWithVtDTOList.add(new MVCDto(name, adress, phoneNumber, emailAdress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, numberOfVaccinesSlot, vaccineType));
        }
        return mvcWithVtDTOList;
    }
}
