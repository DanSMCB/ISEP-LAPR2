package app.domain.model;

import java.io.Serializable;
import java.util.Objects;


/**
 * Class that represents an employee of a company
 * @author José Barbosa <1211359@isep.ipp.pt>
 */
public class Employee implements Serializable {
    /**
     * The name of the Employee
     */
    private String name;
    /**
     * The email of the Employee
     */
    private String email;
    /**
     * The role that an Employee has in the company
     */
    private Role role;
    /**
     * The id that identifies the employee in the Company
     */
    private String employeeId;
    /**
     * The address of a given Employee
     */
    private String address;
    /**
     * The phone Number of a given Employee
     */
    private  String phoneNumber;
    /**
     * The citizen card number of a given Employee
     */
    private String ccNumber;

    /**
     * Builds an instance of Employee receiving the id, password, name, role, employee id, address, phone number and citizen card number
     *
     *
     * @param id The Email address of a given Employee
     * @param name The name of a given Employee
     * @param role The role that an Employee has in the company
     * @param employeeId The id that identifies the employee in the Company
     * @param address The address of a given Employee
     * @param phoneNumber The phone Number of a given Employee
     * @param ccNumber The citizen card number of a given Employee
     */
    public Employee(String id, String name, Role role, String employeeId, String address, String phoneNumber, String ccNumber){
        this.name=name;
        this.email=id;
        this.role=role;
        this.employeeId=employeeId;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.ccNumber=ccNumber;
    }

    /**
     * Returns the role of a given Employee
     * @return role
     */
    public Role getRole(){
        return role;
    }

    /**
     * Returns the name of a given Employee
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     * Returns the employee id of a given Employee
     * @return employeeId
     */
    public String getEmployeeId(){
        return employeeId;
    }
    /**
     * Returns the address of a given Employee
     * @return address
     */
    public String getAddress(){
        return address;
    }/**
     * Returns the phone number of a given Employee
     * @return phoneNumber
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }
    /**
     * Returns the email address of a given Employee
     * @return emailAddress
     */
    public String getEmail(){
        return email;
    }
    /**
     * Returns the cc number of a given Employee
     * @return ccNumber
     */
    public String getCcNumber(){
        return ccNumber;
    }

    /**
     * Returns textual description of a given Employee
     *
     * @return characteristics of a given Employee
     */
    @Override
    public String toString() {
        return "Employee{" +
                "name="+ name +
                "emailAddress=" + email +'\'' +
                "role=" + role +'\'' +
                ", employeeId='" + employeeId + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                '}';
    }

    /**
     * Compares a certain object with the wanted object
     * @param o Object to be compared
     * @return Comparison between objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (!Objects.equals(name, employee.getName())) return false;
        if (!Objects.equals(email, employee.getEmail())) return false;
        if (!Objects.equals(role, employee.getRole())) return false;
        if (!Objects.equals(employeeId, employee.getEmployeeId())) return false;
        if (!Objects.equals(address, employee.getAddress())) return false;
        if (!Objects.equals(phoneNumber, employee.getPhoneNumber())) return false;
        return Objects.equals(ccNumber, employee.getCcNumber());
    }

}
