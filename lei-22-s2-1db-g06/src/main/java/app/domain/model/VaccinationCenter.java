package app.domain.model;

import app.domain.store.AppointmentStore;
import app.mapper.dto.VCDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Objects;

/**
 * Creates and retrieves information from vaccination centers
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public abstract class VaccinationCenter implements Serializable {

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
    private long phone_number;
    /**
     * Email of the mass vaccination center
     */
    private String email;
    /**
     * Fax number of the mass vaccination center
     */
    private long fax_number;
    /**
     * Website address of the mass vaccination center
     */
    private String website_address;
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
    /**
    * Store of appointments of the vaccination center
    */
    private AppointmentStore appointmentStore;

    /**
     * List responsible for storing days
     */
    private DayList dayList;

    /**
     * Number of digits of the phone number attribute
     */
    private final static int PHONE_NUMBER_DIGITS = 9;
    /**
     * Number of digits of the fax number attribute
     */
    private final static int FAX_NUMBER_DIGITS = 10;

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
        checkNameFormat(name);
        this.name = name;
    }

    /**
     * Return the address of the mass vaccination center
     * @return address
     */
    public String getAddress() { return address; }

    /**
     * Set the address of the mass vaccination center
     * @param address
     */
    public void setAddress(String address) {
        checkAddressFormat(address);
        this.address = address;
    }

    /**
     * Return the phone number of the mass vaccination center
     * @return phone number
     */
    public long getPhone_number() {
        return phone_number;
    }

    /**
     * Set the phone number of the mass vaccination center
     * @param phone_number
     */
    public void setPhone_number(long phone_number) {
        checkPhoneNumberFormat(phone_number);
        this.phone_number = phone_number;
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
//        checkEmailFormat(email);
        this.email = email;
    }

    /**
     * Return the fax number of the mass vaccination center
     * @return fax number
     */
    public long getFax_number() {
        return fax_number;
    }

    /**
     * Set the fax number of the mass vaccination center
     * @param fax_number
     */
    public void setFax_number(long fax_number) {
        checkFaxNumberFormat(fax_number);
        this.fax_number = fax_number;
    }

    /**
     * Return the website address of the mass vaccination center
     * @return website address
     */
    public String getWebsite_address() {
        return website_address;
    }

    /**
     * Set the website address of the mass vaccination center
     * @param website_address
     */
    public void setWebsite_address(String website_address) {
        checkWebsiteAddressFormat(website_address);
        this.website_address = website_address;
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
     * @param openingHour
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
     * @param closingHour
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
     * @param slotDuration
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
     * @param numberOfVaccinesSlot
     */
    public void setNumberOfVaccinesSlot(int numberOfVaccinesSlot) {
        this.numberOfVaccinesSlot = numberOfVaccinesSlot;
    }

    /**
     * Create a new vaccination center with the received attributes
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
     */
    public VaccinationCenter(String name, String address, long phone_number, String email, long fax_number, String website_address, LocalTime openingHour, LocalTime closingHour, int slotDuration, int numberOfVaccinesSlot){
        this.name=name;
        this.address=address;
        this.phone_number=phone_number;
        this.email=email;
        this.fax_number=fax_number;
        this.website_address=website_address;
        this.appointmentStore = new AppointmentStore();
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.slotDuration = slotDuration;
        this.numberOfVaccinesSlot = numberOfVaccinesSlot;
        this.dayList = new DayList(openingHour, closingHour, slotDuration, numberOfVaccinesSlot);
    }

    /**
     * Validates the name format of the mass vaccination center
     * @param name
     */
    public static boolean checkNameFormat(String name){
        try{
            if (name==null || (name.trim().equals("")))
                throw new IllegalArgumentException();
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid name format.");
            return false;
        }
    }
    /**
     * Validates the address format of the mass vaccination center
     * @param address
     */
    public static boolean checkAddressFormat(String address){
        try{
            if (address==null || (address.trim().equals("")))
                throw new IllegalArgumentException();
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid address format.");
            return false;
        }
    }
    /**
     * Validates the phone number format of the mass vaccination center
     * @param phoneNumber
     */
    public static boolean checkPhoneNumberFormat(long phoneNumber){
        try{
            if (String.valueOf(phoneNumber).length()!=PHONE_NUMBER_DIGITS)
                throw new IllegalArgumentException();
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid phone number format.");
            return false;
        }
    }
    /**
     * Validates the fax number format of the mass vaccination center
     * @param fax_number
     */
    public static boolean checkFaxNumberFormat(long fax_number){
        try{
            if (String.valueOf(fax_number).length()!=FAX_NUMBER_DIGITS)
                throw new IllegalArgumentException();
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid address format.");
            return false;
        }
    }
    /**
     * Validates the website address format of the mass vaccination center
     * @param website_address
     */
    public static boolean checkWebsiteAddressFormat(String website_address){
        try{
            if (website_address==null || (website_address.trim().equals("")))
                throw new IllegalArgumentException();
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid website address format.");
            return false;
        }
    }

//    public static boolean checkEmailFormat(String email){
//        try{
//            Email.validate(email);
//        }catch (IllegalArgumentException e){
//            System.out.println("Invalid email format.");
//            return false;
//        }
//    }

    @Override
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone number: " + phone_number + "\nEmail: "+email+"\nFax number: " + fax_number + "\nWebsite address: " +website_address+ "\nOpening hours: " + openingHour.format(DateTimeFormatter.ofPattern("HH:mm")) + "\nClosing hours: " + closingHour.format(DateTimeFormatter.ofPattern("HH:mm")) +"\nSlot duration: " + slotDuration +"\nNumber of vaccines per slot: "+ numberOfVaccinesSlot;
    }

    /**
     * Get the waiting list for a given date.
     *
     * @param dateToCheck The date to check for appointments
     * @return A list of appointments that are waiting to be seen
     */
    public List<Appointment> getWaitingList(LocalDate dateToCheck) {
        return appointmentStore.getWaitingList(dateToCheck);
    }

    /**
     * This function receives a date and time and returns the corresponding slot
     * @param date The date of the appointment
     * @param time The time of the appointment
     * @return slot
     * @throws Exception
     */
    public Slot findSlot(LocalDate date, LocalTime time) throws Exception {
        DaySchedule day = dayList.findDay(date);
        return day.findSlot(time);
    }
    /**
     * This function creates a new appointment for a vaccination
     *
     * @param vaccineType The type of vaccine that the appointment is for.
     * @param snsUser The user who is getting the vaccine.
     * @param time The time of the appointment
     * @param date The date of the appointment
     * @return An appointment.
     */
    public Appointment createVaccinationAppointment(VaccineType vaccineType, SNSUser snsUser, LocalDate date, LocalTime time){
        return this.appointmentStore.createVaccinationAppointment(vaccineType, snsUser,date,time);
    }
    /**
     * This function is used to determine if a given Vaccination Center DTO matches this instance of a Vaccination Center
     *
     * @param vcDto The Vaccination Center DTO that is being matched.
     * @return A boolean value.
     */
    public abstract boolean vcMatches(VCDto vcDto);
   public int[] getNumberOfFullyVaccinated(LocalDate initialDate,LocalDate finalDate) throws Exception {
        return appointmentStore.getNumberOfFullyVaccinated(initialDate,finalDate);

    }


    /**
     * This function returns the appointmentStore of the vaccination center.
     *
     * @return The appointmentStore.
     */
    public AppointmentStore getAppointmentStore() {
        return appointmentStore;
    }



    /**
     * This function creates a vaccination appointment for a user
     *
     * @param vaccineType The type of vaccine that the user is getting.
     * @param snsUser The user who is getting the vaccine
     * @param date The date of the appointment
     * @param time The time of the appointment
     * @param arrivalTime The time the user arrived at the vaccination center
     * @param leavingTime The time the user leaves the vaccination center
     * @return An appointment.
     */
    public Appointment createVaccinationAppointment(VaccineType vaccineType, SNSUser snsUser, LocalDate date, LocalTime time, LocalDateTime arrivalTime,LocalDateTime leavingTime){
        return appointmentStore.createVaccinationAppointment(vaccineType, snsUser,date,time,arrivalTime,leavingTime);
    }

    /**
     * Returns the number of vaccinated users in the current day
     * @return n_vaccinated: number of vaccinated users in the current day
     */
    public int getNumberOfVaccinatedCurrentDay(){
        int n_vaccinated=0;

        for (int i=0;i<appointmentStore.getSize();i++){
            if (appointmentStore.getAppointment(i).checkStateDone()){
                if(appointmentStore.getAppointment(i).checkIfToday()){
                    n_vaccinated++;
                }
            }
        }

        return n_vaccinated;
    }
}
