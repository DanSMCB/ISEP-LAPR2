package app.mapper.dto;

import app.domain.model.VaccineType;
import app.domain.store.AppointmentStore;

import java.io.Serializable;
import java.time.LocalTime;


public class MVCDto extends VCDto implements Serializable {
    /**
     * The vaccine type of a given vaccine
     */
    private VaccineType vaccineType;

    /**
     * Store of appointments of the mass vaccination center
     */
    private AppointmentStore appointmentStore;

    /**
     * Creates an instance of MVCDto receiving the name, address, phone Number, email, fax Number,website Address, opening Hour, closing Hour, slot Duration, number of Vaccine
     * @param name name of the vaccination center
     * @param address address of the vaccination center
     * @param phoneNumber phone number of the vaccination center
     * @param email email of the vaccination center
     * @param faxNumber fax number of the vaccination center
     * @param websiteAdress website address of the vaccination center
     * @param openingHour opening hours of the vaccination center
     * @param closingHour closing hours of the vaccination center
     * @param slotDuration slot duration of the vaccination center
     * @param numberOfVaccinesSlot number of vaccines per slot of the vaccination center
     * @param vaccineType The vaccine type of a given vaccine
     */
    public MVCDto(String name, String address, long phoneNumber, String email, long faxNumber, String websiteAdress, LocalTime openingHour, LocalTime closingHour, int slotDuration, int numberOfVaccinesSlot, VaccineType vaccineType) {
        super(name, address, phoneNumber, email, faxNumber, websiteAdress, openingHour, closingHour, slotDuration, numberOfVaccinesSlot);
        this.vaccineType=vaccineType;
        this.appointmentStore=new AppointmentStore();
    }
    /**
     * Creates an instance of MVCDto receiving the name, phone Number and email
     * @param name name of the vaccination center
     * @param phoneNumber phone number of the vaccination center
     * @param email email of the vaccination center
     */
    public MVCDto(String name, long phoneNumber,String email,VaccineType vaccineType) {
        super(name, phoneNumber, email);
        this.vaccineType = vaccineType;
        this.appointmentStore=new AppointmentStore();
    }
    /**
     * This function returns the vaccine type of the vaccine
     *
     * @return vaccineType
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }

    /**
     * This function sets the vaccine type of the vaccine
     *
     * @param vaccineType The type of vaccine that was administered.
     */
    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    @Override
    /**
     *  A method that returns a string representation of the object.
      */

    public String toString() {
        return super.getName();
    }

    /**
     * Returns the appointmentStore of the mass vaccination center.
     * @return appointmentStore.
     */
    public AppointmentStore getAppointmentStore() {
        return appointmentStore;
    }
}
