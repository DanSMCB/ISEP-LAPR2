package app.mapper.dto;

import app.domain.model.Role;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for Employee objects.
 * @author Afonso Costa <1211343@isep.ipp.pt> José Barbosa <1211359@isep.ipp.pt>
 */
public class EmployeeDTO implements Serializable {
    /**
     * Name of a given Employee
     */
    public String name;
    /**
     * Code that identifies a given Employee
     */
    public String employeeId;
    /**
     * Address of a given Employee
     */
    public String address;
    /**
     * Phone number of a certain Employee
     */
    public String phoneNumber;
    /**
     * Electronic address of a given Employee
     */
    public String emailAddress;
    /**
     * Citizen Card Number of a given Employee
     */
    public String ccNumber;
    /**
     * Role that a given Employee has in the Company
     */
    public Role role;

    /**
     * Password that a given Employee has in the Company
     */
    public String password;

    /**
     * Creates a new EmployeeDto with attributes name, employeeId, address, phoneNumber, emailAddress, ccNumber, role.
     * @param name : employee's name
     * @param employeeId : employee's ID
     * @param address : employee's address
     * @param phoneNumber : employee's phone number
     * @param emailAddress : employee's email address
     * @param ccNumber : employee's citizen card number
     * @param role : employee's role
     */
 public EmployeeDTO(String name, String employeeId, String password, String address, String phoneNumber, String emailAddress, String ccNumber, Role role){
     this.name=name;
     this.employeeId=employeeId;
     this.password=password;
     this.address=address;
     this.phoneNumber=phoneNumber;
     this.emailAddress=emailAddress;
     this.ccNumber=ccNumber;
     this.role=role;
 }

    /**
     * Creates a new EmployeeDto with attributes name, address, phoneNumber, emailAddress, ccNumber, role.
     * @param name : employee's name
     * @param address : employee's address
     * @param phoneNumber : employee's phone number
     * @param emailAddress : employee's email address
     * @param ccNumber : employee's citizen card number
     * @param role : employee's role
     */
    public EmployeeDTO(String name, String address, String phoneNumber, String emailAddress, String ccNumber, Role role){
        this.name=name;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.emailAddress=emailAddress;
        this.ccNumber=ccNumber;
        this.role=role;
    }

    /**
     * Returns the name of a Given Employee
     * @return name of Employee
     */
    public String getName(){
        return name;
    }
    /**
     * Returns the employee id of a Given Employee
     * @return employee ID of Employee
     */
    public String getEmployeeId(){
        return employeeId;
    }
    /**
     * Returns the address of a Given Employee
     * @return address of Employee
     */
    public String getAddress(){
        return address;
    }
    /**
     * Returns the phone Number of a Given Employee
     * @return phone Number of Employee
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }
    /**
     * Returns the email Address of a Given Employee
     * @return email address of Employee
     */
    public String getEmailAddress(){
        return emailAddress;
    }
    /**
     * Returns the citizen card number of a Given Employee
     * @return cc Number of Employee
     */
    public String getCcNumber(){
        return ccNumber;
    }
    /**
     * Returns the role of a Given Employee
     * @return role of Employee
     */
    public Role getRole(){
        return role;
    }

    public String getPassord(){
        return password;
    }

    /**
     * Set the name of the Employee
     * @param name name of the Employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the identification code of a given Employee
     * @param employeeId identification code of a given Employee
     */

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Set the address of a given Employee
     * @param address address of a given Employee
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set the phone number of a given Employee
     * @param phoneNumber
     */

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Set the email address of a given Employee
     * @param emailAddress email address of a given Employee
     */

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Set the citizen card number of a given Employee
     * @param ccNumber citizen card number of a given Employee
     */
    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    /**
     * Set the role of a given Employee
     * @param role role of a given Employee
     */

    public void setRole(Role role) {
        this.role = role;
    }
    /**
     * Compares a certain object with the wanted object
     * @param o Object to be compared
     * @return Comparison between objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDTO)) return false;

        EmployeeDTO that = (EmployeeDTO) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(employeeId, that.employeeId)) return false;
        if (!Objects.equals(password, that.password))return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(phoneNumber, that.phoneNumber)) return false;
        if (!Objects.equals(emailAddress, that.emailAddress)) return false;
        if (!Objects.equals(ccNumber, that.ccNumber)) return false;
        return Objects.equals(role, that.role);
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", role='" + role + '\'';
    }
}

