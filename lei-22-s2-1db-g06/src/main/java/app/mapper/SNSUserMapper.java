package app.mapper;

import app.domain.model.SNSUser;
import app.mapper.dto.SNSUserDTO;

import java.time.LocalDate;

/**
 * Implements methods for mapping SNSUser DTOs to SNS User and vice-versa.
 * @author Daniel Braga <1200801@isep.ipp.pt>, Rodrigo Peireso <1211345@isep.ipp.pt>, Afonso Costa <1211343@isep.ipp.pt>
 */
public class SNSUserMapper {

    /**
     * Create and return a new instance of a SNS user using SNSUser DTO information
     * @param snsUserDTO SNS user Dto with the attributes to create the sns user
     * @return created sns user
     */
    public static SNSUser createSNSUser(SNSUserDTO snsUserDTO){
        String name = snsUserDTO.getName();
        String address= snsUserDTO.getAddress();
        String sex= snsUserDTO.getSex();
        long phoneNumber= snsUserDTO.getPhoneNumber();
        String email = snsUserDTO.getEmail();
        LocalDate birthDate = snsUserDTO.getBirthDate();
        long snsNumber = snsUserDTO.getSnsNumber();
        long citizenCardNumber=snsUserDTO.getCitizenCardNumber();
        if(sex!= null && !sex.equals("NA")){
            return new SNSUser(name,address,sex,phoneNumber,email,birthDate,snsNumber,citizenCardNumber);
        }else return new SNSUser(name,address,phoneNumber,email,birthDate,snsNumber,citizenCardNumber);
    }

    /**
     * It takes a SNSUser object and returns a SNSUserDTO object
     *
     * @param snsUser The SNSUser object that we want to convert to a DTO.
     * @return A new SNSUserDTO object.
     */
    public static SNSUserDTO toDto(SNSUser snsUser) {
        String name = snsUser.getName();
        String address = snsUser.getAddress();
        String sex = snsUser.getSex();
        long phoneNumber = snsUser.getPhoneNumber();
        String email = snsUser.getEmail();
        LocalDate birthDate = snsUser.getBirthDate();
        long snsNumber = snsUser.getSnsNumber();
        long citizenCardNumber = snsUser.getCitizenCardNumber();
        return new SNSUserDTO(name, address, sex, phoneNumber, email, birthDate, snsNumber, citizenCardNumber);
    }
}
