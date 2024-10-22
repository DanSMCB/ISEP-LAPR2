package app.domain.model;

import app.mapper.dto.VCDto;

import java.io.Serializable;
import java.time.LocalTime;

public class HealthCareCenter extends VaccinationCenter implements Serializable {


    /**
     * Create a new health care center with the received attributes
     * @param name name of the vaccination center
     * @param address address of the vaccination center
     * @param phone_number phone number of the vaccination center
     * @param email email of the vaccination center
     * @param fax_number fax number of the vaccination center
     * @param website_address website address of the vaccination center
     * @param openingHour opening hours of the vaccination center
     * @param closingHour closing hours of the vaccination center
     * @param slotDuration slot duration of the vaccination center
     * @param numberOfVaccinesSlot number of vaccines per slot of the vaccination center
     * @param aces healthcare center group
     * @param ars regional health association
     */
    private String aces;
    private String ars;

    public HealthCareCenter(String name, String address, long phone_number, String email, long fax_number, String website_address, LocalTime openingHour, LocalTime closingHour, int slotDuration, int numberOfVaccinesSlot, String aces, String ars) {
        super(name, address, phone_number, email, fax_number, website_address, openingHour, closingHour, slotDuration, numberOfVaccinesSlot);
        this.aces=aces;
        this.ars=ars;
    }

    /**
     * This function compares the name, phone number and email address of the VC with the VC DTO object passed as a
     * parameter
     *
     * @param vcDto This is the DTO object that is passed in to the method.
     * @return A boolean value of the comparison.
     */
    public boolean vcMatches(VCDto vcDto){
        String nameToCompare=vcDto.getName();
        long phoneNumberToCompare=vcDto.getPhoneNumber();
        String emailToCompare= vcDto.getEmail();
        String name= super.getName();
        long phoneNumber= super.getPhone_number();
        String emailAddress= super.getEmail();
        return nameToCompare.equals(name) && phoneNumberToCompare == phoneNumber && emailToCompare.equals(emailAddress);
    }
    public String getACES() {
        return this.aces;
    }
    public String getARS() {
        return this.ars;
    }
    @Override
    public String toString() {
        return super.toString() + "\nARS: "+getARS() + "\nACES: " + getACES();
    }
}
