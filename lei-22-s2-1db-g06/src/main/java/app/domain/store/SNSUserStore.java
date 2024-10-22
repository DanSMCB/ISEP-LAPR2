package app.domain.store;

import app.domain.model.SNSUser;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

/**
 * Save SNS users objects
 * @author Daniel Braga <1200801@isep.ipp.pt>, Afonso Costa <1211343@isep.ipp.pt>
 */

public class SNSUserStore implements Serializable {

    private List<SNSUser> snsUserList = new ArrayList<>();

    /**
     * Create and return a new instance of a sns user
     * @param name name of the SNS user
     * @param address address of the SNS user
     * @param sex sex of the SNS user
     * @param phoneNumber phone number of the SNS user
     * @param email email of the SNS user
     * @param birthdate birth date of the SNS user
     * @param snsNumber sns number of the SNS user
     * @param citizenCardNumber citizen card number of the SNS user
     * @return created SNS user
     */
    public SNSUser createSNSUser(String name, String address, String sex, long phoneNumber, String email, LocalDate birthdate, long snsNumber, long citizenCardNumber){
        return new SNSUser(name,address,sex,phoneNumber,email,birthdate,snsNumber,citizenCardNumber);
    }

    /**
     * Create and return a new instance of a sns user without the sex attribute
     * @param name name of the SNS user
     * @param address address of the SNS user
     * @param phoneNumber phone number of the SNS user
     * @param email email of the SNS user
     * @param birthdate birth date of the SNS user
     * @param snsNumber sns number of the SNS user
     * @param citizenCardNumber citizen card number of the SNS user
     * @return created SNS user
     */
    public SNSUser createSNSUser(String name, String address, long phoneNumber, String email, LocalDate birthdate, long snsNumber, long citizenCardNumber){
        return new SNSUser(name,address,phoneNumber,email,birthdate,snsNumber,citizenCardNumber);
    }

    /**
     * returns boolean value of snsuserStore being empty.
     * @return snsuserStore.isEmpty() : true if list is empty, false otherwise
     */
    public boolean checkIfNull() {
        return snsUserList.isEmpty();
    }

    /**
     * Prints registered SNS users.
     */
    public void printSNSUserStore() {
        for (int i = 0; i < snsUserList.size(); i++) {
            System.out.println(snsUserList.get(i).toString());
        }
    }

    /**
     * Returns the size of the list of the registered SNS users
     * @return snsuserStore.size() : size of snsuserStore
     */
    public int getSize(){
        return snsUserList.size();
    }

    /**
     * Verifies if attributes of new SNS user meet acceptance criteria (AC).
     * @param snsUser : SNS user
     * @return boolean value : true if attributes meet AC, false otherwise
     */
    public boolean validateSNSUser(SNSUser snsUser) {
        if (snsUser == null) return false;
        else if (!checkRepeatedPhoneNumber(snsUser.getPhoneNumber())){
            return false;
        }else if (!checkRepeatedEmail(snsUser.getEmail())){
            return false;
        }else if (!checkRepeatedsnsNumber(snsUser.getSnsNumber())){
            return false;
        }else return checkRepeatedCitizenCardNumber(snsUser.getCitizenCardNumber());
    }

    /**
     * Validates and saves new SNS user into list of SNS users
     * @param snsUser : SNS user
     */
    public boolean saveSNSUser(SNSUser snsUser) {
        if (!validateSNSUser(snsUser)){
            return false;
        }
        return this.snsUserList.add(snsUser);
    }

    /**
     * Verifies if the address to create a sns user is already associated with another user
     * @param address
     * @return boolean value : true if the address is not registered, false otherwise
     */
    public boolean checkRepeatedAddress(String address){
        for(int i = 0; i< snsUserList.size(); i++){
            if(address.equals(snsUserList.get(i).getAddress())){
                System.out.println("This address is already associated with other sns user.");
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies if the phone number to create a sns user is already associated with another user
     * @param phoneNumber
     * @return boolean value : true if the phone number is not registered, false otherwise
     */
    public boolean checkRepeatedPhoneNumber(long phoneNumber){
        for(int i = 0; i< snsUserList.size(); i++){
            if(phoneNumber==(snsUserList.get(i).getPhoneNumber())){
                System.out.println("This phone number is already associated with other sns user.\n");
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies if the email to create a sns user is already associated with another user
     * @param email
     * @return boolean value : true if the email is not registered, false otherwise
     */
    public boolean checkRepeatedEmail(String email){
        for(int i = 0; i< snsUserList.size(); i++){
            if(email.equals(snsUserList.get(i).getEmail())){
                System.out.println("This email is already associated with other sns user.\n");
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies if the sns number to create a sns user is already associated with another user
     * @param snsNumber
     * @return boolean value : true if the sns number is not registered, false otherwise
     */
    public boolean checkRepeatedsnsNumber(long snsNumber){
        for(int i = 0; i< snsUserList.size(); i++){
            if(snsNumber==(snsUserList.get(i).getSnsNumber())){
                System.out.println("This sns number is already associated with other sns user.\n");
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies if the citizen card number to create a sns user is already associated with another user
     * @param citizenCardNumber
     * @return boolean value : true if the citizen card number is not registered, false otherwise
     */
    public boolean checkRepeatedCitizenCardNumber(long citizenCardNumber){
        for(int i = 0; i< snsUserList.size(); i++){
            if(citizenCardNumber==(snsUserList.get(i).getCitizenCardNumber())){
                System.out.println("This citizen card number is already associated with other sns user.\n");
                return false;
            }
        }
        return true;
    }
    /**
     * This function takes a long number as a parameter and returns a SNSUser object
     *
     * @param snsNumber The number of the user you want to find.
     * @return The SNSUser object that has the same number as the snsNumber parameter.
     */
    public SNSUser findSnsUserByNumber(long snsNumber){
        if (snsUserList.isEmpty())
            return null;
        for (SNSUser snsUser: snsUserList) {
            if(snsUser.checkUserNumber(snsNumber)){
                return snsUser;
            }
        }
        return null;
    }

    public SNSUser findSnsUserByEmail(Email email) {
        if (snsUserList.isEmpty())
            return null;
        for (SNSUser snsUser: snsUserList) {
            if(snsUser.checkUserEmail(email)){
                return snsUser;
            }
        }
        return null;
    }
}
