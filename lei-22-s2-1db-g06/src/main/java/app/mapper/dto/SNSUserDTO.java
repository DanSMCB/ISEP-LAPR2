package app.mapper.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Objects;

/**
 * DTO for SNS Users
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public class SNSUserDTO implements Serializable {

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
    public SNSUserDTO(String name, String address, String sex, long phoneNumber, String email, LocalDate birthdate, long snsNumber, long citizenCardNumber){
        this.name = name;
        this.address=address;
        this.sex=sex;
        this.phoneNumber=phoneNumber;
        this.email = email;
        this.birthDate = birthdate;
        this.snsNumber = snsNumber;
        this.citizenCardNumber=citizenCardNumber;
    }

    /**
     * Return the name of the SNS user
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Return the address of the SNS user
     * @return address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Return the sex of the SNS user
     * @return sex
     */
    public String getSex() {
        return sex;
    }
    /**
     * Return the phone number of the SNS user
     * @return phoneNumber
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Return the email of the SNS user
     * @return email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Return the birth date of the SNS user
     * @return birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }
    /**
     * Return the sns number of the SNS user
     * @return snsNumber
     */
    public long getSnsNumber() {
        return snsNumber;
    }
    /**
     * Return the citizen card number of the SNS user
     * @return citizenCardNumber
     */
    public long getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /**
     * Verifies if an SNSUserDTO object has the same data as another SNSUserDTO object.
     * @param obj : object to be compared to this instance
     * @return boolean : returns true if the object has the same memory address and/or the same attributes. Returns false if those conditions aren't met or if the object is from another class.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SNSUserDTO that = (SNSUserDTO) obj;
        return getPhoneNumber() == that.getPhoneNumber() && getSnsNumber() == that.getSnsNumber() && getCitizenCardNumber() == that.getCitizenCardNumber() && Objects.equals(getName(), that.getName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getSex(), that.getSex()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getBirthDate(), that.getBirthDate());
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
}
