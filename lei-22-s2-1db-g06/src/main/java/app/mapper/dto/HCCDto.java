package app.mapper.dto;

import app.domain.store.AppointmentStore;

import java.io.Serializable;
import java.time.LocalTime;

public class HCCDto extends VCDto implements Serializable {

    /**
     * Store of appointments of the mass vaccination center
     */
    private AppointmentStore appointmentStore;

    /**
     * Creates an instance of HCCDto receiving the name, address, phone Number, email, fax Number,website Address, opening Hour, closing Hour, slot Duration, number of Vaccine
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
     * @param aces healthcare center group
     * @param ars regional health association
     */

    private String aces;
    private String ars;
    public HCCDto(String name, String address, long phoneNumber, String email, long faxNumber, String websiteAddress, LocalTime openingHour, LocalTime closingHour, int slotDuration, int numberOfVaccinesSlot, String aces, String ars) {
        super(name, address, phoneNumber, email, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, numberOfVaccinesSlot);
        this.aces=aces;
        this.ars=ars;
        this.appointmentStore=new AppointmentStore();
    }

    /**
     * Creates an instance of HCCDto receiving the name, phone Number and email
     * @param name name of the vaccination center
     * @param phoneNumber phone number of the vaccination center
     * @param email email of the vaccination center
     */
    public HCCDto(String name, long phoneNumber,String email){
        super(name,phoneNumber,email);
    }


    @Override
    public String toString() {
        return super.getName();
    }

    public String getARS() {
        return ars;
    }

    public String getACES() {
        return aces;
    }

    /**
     * Returns the appointmentStore of the mass vaccination center.
     * @return appointmentStore.
     */
    public AppointmentStore getAppointmentStore() {
        return appointmentStore;
    }
}
