package app.domain.model;

import app.domain.store.AppointmentStore;
import app.mapper.dto.VCDto;

import java.io.Serializable;
import java.time.LocalTime;


/**
 * Creates and retrieves information from mass vaccination centers
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public class MassVaccinationCenter extends VaccinationCenter implements Serializable {

    /**
     * Vaccine type the mass vaccination center administers
     */
    private VaccineType vaccineType;

    /**
     * Create a new mass vaccination center with the recived atributes
     * @param name name of the vaccination center
     * @param address address of the vaccination center
     * @param phoneNumber phone number of the vaccination center
     * @param email email of the vaccination center
     * @param faxNumber fax number of the vaccination center
     * @param websiteAddress website address of the vaccination center
     * @param openingHour opening hours of the vaccination center
     * @param closingHour closing hours of the vaccination center
     * @param slotDuration slot duration of the vaccination center
     * @param numberOfVaccinesSlot number of vaccines per slot of the vaccination center
     * @param vaccineType vaccine type the mass vaccination center administers
     */
    public MassVaccinationCenter(String name, String address, long phoneNumber, String email, long faxNumber, String websiteAddress, LocalTime openingHour, LocalTime closingHour, int slotDuration, int numberOfVaccinesSlot, VaccineType vaccineType){
        super(name, address, phoneNumber, email, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, numberOfVaccinesSlot);
        this.vaccineType=vaccineType;
    }

    @Override
    public String toString() {
        return super.toString() + "\nVaccine type it administers: "+vaccineType.getDescription();
    }

    /**
     * Set the vaccine type the mass vaccination center administers
     * @param vt
     */
    public void setVaccineType(VaccineType vt){
        this.vaccineType = vt;
    };

    /**
     * Return the vaccine type the the mass vaccination center administers
     * @return vaccine type
     */
    public VaccineType getVaccineType(){return vaccineType;}
    /**
     * This function compares the name, phone number and email address of the VC with the VC DTO object passed as a
     * parameter
     *
     * @param vcDto This is the DTO object that is passed in to the method.
     * @return A boolean value of comparison.
     */
    public boolean vcMatches(VCDto vcDto){
        String nameToCompare=vcDto.getName();
        long phoneNumberToCompare=vcDto.getPhoneNumber();
        String emailToCompare= vcDto.getEmail();
        String name= super.getName();
        long phoneNumber= super.getPhone_number();
        String emailAddress= super.getEmail();

        if(nameToCompare.equals(name) && phoneNumberToCompare==phoneNumber && emailToCompare.equals(emailAddress)){
            return true;
        }
        return false;
    }
}
