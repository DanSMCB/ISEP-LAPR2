package app.domain.model;
import app.domain.store.SMSStore;
import app.domain.store.SNSUserAppointmentStore;
import app.mapper.dto.SNSUserDTO;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * Creates and retrieves information from SNS users
 * @author Daniel Braga <1200801@isep.ipp.pt>, Afonso Costa <1211343@isep.ipp.pt>
 */

public class SNSUser implements Serializable {

    /**
     * Name of the SNS user
     */
    private String name;
    /**
     * Address of the SNS user
     */
    private String address;
    /**
     * Sex of the SNS user
     */
    private String sex;
    /**
     * Phone number of the SNS user
     */
    private long phoneNumber;
    /**
     * Email of the SNS user
     */
    private String email;
    /**
     * Birth date of the SNS user
     */
    private LocalDate birthDate;
    /**
     * SNS number of the SNS user
     */
    private long snsNumber;
    /**
     * Citizen card number of the SNS user
     */
    private long citizenCardNumber;
    /**
     * Store responsible for storing SMS
     */
    private SMSStore smsStore;

    /**
     * Store that will be used to store the appointments of the user.
     */
    private SNSUserAppointmentStore snsUserAppointmentStore;

    private int age;
    /**
     * Return the name of the SNS user
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the SNS user
     * @param name
     */
    public void setName(String name) {
        checkNameFormat(name);
        this.name = name;
    }

    /**
     * Return the address of the SNS user
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the SNS user
     * @param address
     */
    public void setAddress(String address) {
        checkAddressFormat(address);
        this.address = address;
    }

    /**
     * Return the sex of the SNS user
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Set the sex of the SNS user
     * @param sex
     */
    public void setSex(String sex) {
        checkSexFormat(sex);
        this.sex = sex;
    }

    /**
     * Return the phone number of the SNS user
     * @return phoneNumber
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number of the SNS user
     * @param phoneNumber
     */
    public void setPhoneNumber(long phoneNumber) {
        checkPhoneNumberFormat(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Return the email of the SNS user
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the SNS user
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean checkEmail(String email) {
        try {
            new Email(email);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    /**
     * Return the birth date of the SNS user
     * @return birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Set the birth date of the SNS user
     * @param birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Return the sns number of the SNS user
     * @return snsNumber
     */
    public long getSnsNumber() {
        return snsNumber;
    }

    /**
     * Set the SNS number of the SNS user
     * @param snsNumber
     */
    public void setSnsNumber(long snsNumber) {
        checkSNSNumberFormat(snsNumber);
        this.snsNumber = snsNumber;
    }

    /**
     * Return the citizen card number of the SNS user
     * @return CitizenCardNumber
     */
    public long getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /**
     * Set the citizen card number of the SNS user
     * @param citizenCardNumber
     */
    public void setCitizenCardNumber(long citizenCardNumber) {
        checkCitizenCardNumberFormat(citizenCardNumber);
        this.citizenCardNumber = citizenCardNumber;
    }

    /**
     * Number of digits of the phone number attribute
     */
    private final static int PHONE_NUMBER_DIGITS = 9;
    /**
     * Number of digits of the sns number attribute
     */
    private final static int SNS_NUMBER_DIGITS = 9;
    /**
     * Number of digits of the citizen card number attribute
     */
    private final static int CITIZEN_CARD_NUMBER_DIGITS = 8;

    /**
     * Create a new SNS user with the received attributes
     * @param name name of the SNS user
     * @param address address of the SNS user
     * @param sex sex of the SNS user
     * @param phoneNumber phone number of the SNS user
     * @param email email of the SNS user
     * @param birthdate birth date of the SNS user
     * @param snsNumber sns number of the SNS user
     * @param citizenCardNumber citizen card number of the SNS user
     */
    public SNSUser(String name, String address, String sex, long phoneNumber, String email, LocalDate birthdate, long snsNumber, long citizenCardNumber){
        this.name = name;
        this.address=address;
        this.sex=sex;
        this.phoneNumber=phoneNumber;
        setEmail(email);
        this.birthDate = birthdate;
        this.snsNumber = snsNumber;
        this.citizenCardNumber=citizenCardNumber;
        this.snsUserAppointmentStore=new SNSUserAppointmentStore();
        this.smsStore = new SMSStore();
    }

    /**
     * Create a new SNS user without the "sex" attribute since it's optional
     * @param name name of the SNS user
     * @param address address of the SNS user
     * @param phoneNumber phone number of the SNS user
     * @param email email of the SNS user
     * @param birthdate birth date of the SNS user
     * @param snsNumber sns number of the SNS user
     * @param citizenCardNumber citizen card number of the SNS user
     */
    public SNSUser(String name, String address, long phoneNumber, String email, LocalDate birthdate, long snsNumber, long citizenCardNumber){
        this.name = name;
        this.address=address;
        this.phoneNumber=phoneNumber;
        setEmail(email);
        this.birthDate = birthdate;
        this.snsNumber = snsNumber;
        this.citizenCardNumber=citizenCardNumber;
        this.snsUserAppointmentStore=new SNSUserAppointmentStore();
        this.smsStore = new SMSStore();
    }

    /**
     * A method that returns a string with the name, address, sex(if the user pretends to say it), phone number, email, birthdate, SNS number and citizen card number of a SNS User.
     */
    @Override
    public String toString(){
        if(sex==null){
            return "Name: " + name + "\nAddress: " + address + "\nPhone number: " + phoneNumber + "\nEmail: "+email+"\nBirthdate: "+ birthDate.format(DateTimeFormatter.ofPattern("d/M/uuuu"))+"\nSNS Number: "+snsNumber+"\nCitizen Card Number: "+citizenCardNumber+"\n";

    }else return "Name: " + name + "\nAddress: " + address + "\nSex: "+sex+"\nPhone number: " + phoneNumber + "\nEmail: "+email+"\nBirthdate: "+birthDate.format(DateTimeFormatter.ofPattern("d/M/uuuu"))+"\nSNS Number: "+snsNumber+"\nCitizen Card Number: "+citizenCardNumber+"\n";
    }

    /**
     * Validates the name format of the SNS user
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
     * Validates the address format of the SNS user
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
     * Validates the sex format of the SNS user
     * @param sex
     */
    public static boolean checkSexFormat(String sex){
        try{
            if ((!sex.equalsIgnoreCase("male") && !sex.equalsIgnoreCase("female") && !sex.equalsIgnoreCase("NA") && !sex.equalsIgnoreCase("feminino") && !sex.equalsIgnoreCase("masculino") && !sex.equalsIgnoreCase("") && !sex.equals(null)))
                throw new IllegalArgumentException();
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid sex format.");
            return false;
        }
    }

    /**
     * Validates the phone number format of the SNS user
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
     * Validates the SNS number format of the SNS user
     * @param snsNumber
     */
    public static boolean checkSNSNumberFormat(long snsNumber){
        try{
            if (String.valueOf(snsNumber).length()!=SNS_NUMBER_DIGITS)
                throw new IllegalArgumentException();
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid sns number format.");
            return false;
        }
    }

    /**
     * Validates the citizen card number format of the SNS user
     * @param citizenCardNumber
     */
    public static boolean checkCitizenCardNumberFormat(long citizenCardNumber){
        try{
            if (String.valueOf(citizenCardNumber).length()!=CITIZEN_CARD_NUMBER_DIGITS)
                throw new IllegalArgumentException();
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Invalid citizen card number format.");
            return false;
        }
    }
    /**
     * This function checks if the user's number is the same as the number passed in as a parameter
     *
     * @param snsNumber The number of the user you want to check.
     * @return boolean
     */
    public boolean checkUserNumber(long snsNumber){
        return this.snsNumber == snsNumber;
    }

    /**
     * This function checks if an appointment exists for a given vaccine type
     *
     * @param vaccineType The vaccine type for which the appointment is being checked.
     * @return A boolean value.
     */
    public boolean appointmentExists(VaccineType vaccineType){
        return snsUserAppointmentStore.appointmentExists(vaccineType);
    }

    /**
     * Add an appointment to the user's appointment store.
     *
     * @param appointment The appointment object that you want to add to the store.
     */
    public void addAppointment(Appointment appointment){
        snsUserAppointmentStore.addAppointment(appointment);
    }
    /**
     * Returns the SMSStore object.
     *
     * @return The smsStore object.
     */
    public SMSStore getSMSStore() {
        return smsStore;
    }


    /**
     * > This function returns the value of the variable `snsUserAppointmentStore`
     *
     * @return The snsUserAppointmentStore object.
     */
    public SNSUserAppointmentStore getSnsUserAppointmentStore() {
        return snsUserAppointmentStore;
    }

    /**
     * It compares two SNSUsers and returns true if they are equal.
     *
     * @param obj The object to be compared.
     * @return The hashCode of the object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SNSUser snsUser = (SNSUser) obj;
        return getPhoneNumber() == snsUser.getPhoneNumber() && getSnsNumber() == snsUser.getSnsNumber() && getCitizenCardNumber() == snsUser.getCitizenCardNumber() && Objects.equals(getSnsUserAppointmentStore(), snsUser.getSnsUserAppointmentStore()) && Objects.equals(getName(), snsUser.getName()) && Objects.equals(getAddress(), snsUser.getAddress()) && Objects.equals(getSex(), snsUser.getSex()) && Objects.equals(getEmail(), snsUser.getEmail()) && Objects.equals(getBirthDate(), snsUser.getBirthDate()) && Objects.equals(smsStore, snsUser.smsStore);
    }

    /**
     * Get all the appointments that are scheduled for today.
     *
     * @return A list of Appointment objects.
     */
    public List<Appointment> getTodayAppointment() {
        return snsUserAppointmentStore.getTodayAppointment();
    }
    /**
     * It compares an SNSUser with an SNSUserDTO.
     *
     * @param snsUserDTO The SNSUserDTO object to compare to.
     * @return A boolean value.
     */
    public boolean equals(SNSUserDTO snsUserDTO) {
        if (snsUserDTO==null) return false;
        return getPhoneNumber() == snsUserDTO.getPhoneNumber() && getSnsNumber() == snsUserDTO.getSnsNumber() && getCitizenCardNumber() == snsUserDTO.getCitizenCardNumber() && getName().equals(snsUserDTO.getName()) && getAddress().equals(snsUserDTO.getAddress()) && getSex().equals(snsUserDTO.getSex()) && getEmail().equals(snsUserDTO.getEmail()) && getBirthDate().equals(snsUserDTO.getBirthDate());
    }

    public boolean checkUserEmail(Email email) {
        return this.email.equals(email);
    }
    /**
     * Get the age of the SNS user by subtracting the birthdate from the current date.
     *
     * @return The age of the person in years.
     */
    public int getAge(){
        long age=birthDate.until(LocalDate.now(), ChronoUnit.YEARS);
        return (int) age;
    }

    public SMSStore getSmsStore() {
        return smsStore;
    }
}
