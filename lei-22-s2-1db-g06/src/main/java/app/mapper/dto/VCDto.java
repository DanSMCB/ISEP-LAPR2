package app.mapper.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



/**
 * It's a data transfer object (DTO) that contains all the information about a vaccination center
 *
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>, José Barbosa <1211359@isep.ipp.pt>
 */
public class VCDto implements Serializable {
    /**
     * Name of the mass vaccination center
     */
    private String name;
    /**
     * Address of the mass vaccination center
     */
    private String address;
    /**
     * Phone number of the mass vaccination center
     */
    private long phoneNumber;
    /**
     * Email of the mass vaccination center
     */
    private String email;
    /**
     * Fax number of the mass vaccination center
     */
    private long faxNumber;
    /**
     * Website address of the mass vaccination center
     */
    private String websiteAddress;
    /**
     * Opening hours of the mass vaccination center
     */
    private LocalTime openingHour;
    /**
     * Closing hours of the mass vaccination center
     */
    private LocalTime closingHour;
    /**
     * Slot duration of the mass vaccination center
     */
    private int slotDuration;
    /**
     * Number of vaccines per slot of the mass vaccination center
     */
    private int numberOfVaccinesSlot;
    private static final String DEFAULT_ADDRESS="address";
    private static final long DEFAULT_FAX_NUMBER=1234567890;
    private static final String DEFAULT_WEBSITEADDRESS="website.com";
    private static final LocalTime DEFAULT_OPENINGHOUR=LocalTime.of(10,0);
    private static final LocalTime DEFAULT_CLOSINGHOUR=LocalTime.of(20,0);
    private static final int DEFAULT_SLOTDURATION=1;
    private static final int DEFAULT_NUMBEROFVACCINESSLOT=1;

    /**
     * Create a new vaccination center with the received attributes
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
     */
    public VCDto(String name, String address, long phoneNumber, String email, long faxNumber, String websiteAddress, LocalTime openingHour, LocalTime closingHour, int slotDuration, int numberOfVaccinesSlot) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.faxNumber = faxNumber;
        this.websiteAddress = websiteAddress;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.slotDuration = slotDuration;
        this.numberOfVaccinesSlot = numberOfVaccinesSlot;
    }

    /**
     * Create a new vaccination center with the received attributes
     * @param name name of the vaccination center
     * @param phoneNumber phone number of the vaccination center
     * @param email email of the vaccination center
     */
    public VCDto(String name, long phoneNumber, String email) {
        this.name=name;
        this.address=DEFAULT_ADDRESS;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.faxNumber = DEFAULT_FAX_NUMBER;
        this.websiteAddress = DEFAULT_WEBSITEADDRESS;
        this.openingHour = DEFAULT_OPENINGHOUR;
        this.closingHour = DEFAULT_CLOSINGHOUR;
        this.slotDuration = DEFAULT_SLOTDURATION;
        this.numberOfVaccinesSlot = DEFAULT_NUMBEROFVACCINESSLOT;
    }

    /**
     * Return the name of the mass vaccination center
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the mass vaccination center
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the address of the mass vaccination center
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the mass vaccination center
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Return the phone number of the mass vaccination center
     * @return phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number of the mass vaccination center
     * @param phoneNumber phone number of the mass vaccination center
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Return the email of the mass vaccination center
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the mass vaccination center
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return the fax number of the mass vaccination center
     * @return fax number
     */
    public long getFaxNumber() {
        return faxNumber;
    }

    /**
     * Set the fax number of the mass vaccination center
     * @param faxNumber fax number of the mass vaccination center
     */
    public void setFaxNumber(long faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * Return the website address of the mass vaccination center
     * @return website address
     */
    public String getWebsiteAdress() {
        return websiteAddress;
    }

    /**
     * Set the website address of the mass vaccination center
     * @param websiteAddress website address of the mass vaccination center
     */
    public void setWebsiteAdress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    /**
     * Return the opening hours of the mass vaccination center
     * @return opening hours
     */
    public LocalTime getOpeningHour() {
        return openingHour;
    }

    /**
     * Set the opening hours of the mass vaccination center
     * @param openingHour opening hours of the mass vaccination center
     */
    public void setOpeningHour(LocalTime openingHour) {
        this.openingHour = openingHour;
    }

    /**
     * Return the closing hours of the mass vaccination center
     * @return closing hours
     */
    public LocalTime getClosingHour() {
        return closingHour;
    }

    /**
     * Set the closing hours of the mass vaccination center
     * @param closingHour closing hours of the mass vaccination center
     */
    public void setClosingHour(LocalTime closingHour) {
        this.closingHour = closingHour;
    }

    /**
     * Return the slot duration of the mass vaccination center
     * @return slot duration
     */
    public int getSlotDuration() {
        return slotDuration;
    }

    /**
     * Set the slot duration of the mass vaccination center
     * @param slotDuration slot duration of the mass vaccination center
     */
    public void setSlotDuration(int slotDuration) {
        this.slotDuration = slotDuration;
    }
    /**
     * Return the number of vaccines per slot of the mass vaccination center
     * @return number of vaccines per slot
     */
    public int getNumberOfVaccinesSlot() {
        return numberOfVaccinesSlot;
    }

    /**
     * Set the number of vaccines per slot of the mass vaccination center
     * @param numberOfVaccinesSlot number of vaccines per slot of the mass vaccination center
     */
    public void setNumberOfVaccinesSlot(int numberOfVaccinesSlot) {
        this.numberOfVaccinesSlot = numberOfVaccinesSlot;
    }

    /**
     * The function returns a string that contains the name, address, phone number, email, fax number, website address,
     * opening hours, closing hours, slot duration and number of vaccines per slot of the clinic
     *
     * @return The toString method is being returned.
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone number: " + phoneNumber + "\nEmail: "+email+"\nFax number: " + faxNumber + "\nWebsite address: " +websiteAddress+ "\nOpening hours:" + openingHour.format(DateTimeFormatter.ofPattern("HH:mm")) + "\nClosing hours: " + closingHour.format(DateTimeFormatter.ofPattern("HH:mm"))+"\nSlot duration: " + slotDuration+"\nNumber of vaccines per slot: "+numberOfVaccinesSlot;
    }
}
