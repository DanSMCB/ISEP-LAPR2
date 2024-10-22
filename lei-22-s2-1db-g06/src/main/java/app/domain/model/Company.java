package app.domain.model;

import app.domain.store.*;
import app.mapper.dto.VaccineDto;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>, Rodrigo Peireso <1211345@isep.ipp.pt>, José Barbosa <1211359@isep.ipp.pt>, Afonso Costa <1211343@isep.ipp.pt>, Inês Costa <1210814@isep.ipp.pt>, Daniel Braga <1200801@isep.ipp.pt>
 */
public class Company implements Serializable {
    /**
     * Designation of the Company
     */
    private String designation;
    private transient AuthFacade authFacade;
    /**
     * Store responsible for storing Mass Vaccination Centers
     */
    private MVCenterStore mvCenterStore;
    /**
     * Store responsible for storing Vaccine Types
     */
    private VaccineTypeStore vaccineTypeStore;
    /**
     * Store responsible for storing SNS Users
     */
    private SNSUserStore snsUserStore;
    /**
     * Store responsible for storing Healthcare Centers
     */
    private HCCenterStore hcCenterStore;
    /**
     * Vaccine type of the ongoing outbreak
     */
    private VaccineType ongoingOutbreak;
    /**
     * Store responsible for storing Roles
     */
    private static RoleStore roleStore;
    /**
     * Store responsible for storing Employees
     */
    private EmployeeStore empStore;
    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.mvCenterStore =new MVCenterStore();
        this.vaccineTypeStore=new VaccineTypeStore();
        this.roleStore=new RoleStore();
        this.empStore=new EmployeeStore();
        this.snsUserStore=new SNSUserStore();
        this.hcCenterStore=new HCCenterStore();
    }

    public String getDesignation() {
        return designation;
    }

    public void setAuthFacade(AuthFacade authFacade) {
        this.authFacade=authFacade;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }
    /**
     * This function returns the Store of Mass Vaccination Centers
     *
     * @return mvCenterStore
     */
    public MVCenterStore getMVCenterStore(){
        return mvCenterStore;
    }
    /**
     *
     * Returns the store responsible for storing vaccine types.
     * @return vaccineTypeStore
     */
    public VaccineTypeStore getVTStore(){
        return vaccineTypeStore;
    }

    /**
     *
     * Returns the store responsible for storing Roles
     * @return roleStore
     */
    public static RoleStore getRoleStore(){
        return roleStore;
    }
    /**
     *
     * Returns the store responsible for storing Employees
     *
     * @return employeeStore
     */
    public EmployeeStore getEmployeeStore(){
        return empStore;
    }

    /**
     * This function returns the Store of SNS Users
     *
     * @return snsUserStore
     */
    public SNSUserStore getSnsUserStore(){return snsUserStore;}

    /**
     * Returns tne store responsible for storing Healthcare Centers
     * @return hcCenterStore
     */
    public HCCenterStore getHCCenterStore() {
        return hcCenterStore;
    }

    /**
     * Create and return a new instance of a vaccine using Vaccine DTO information
     * @param vaccineDto vaccine Dto with the atributes to create the vaccine
     * @param vt vaccine type to create the vaccine
     * @return created vaccine
     */
    public Vaccine createVaccine(VaccineDto vaccineDto, VaccineType vt) {
        return vaccineTypeStore.createVaccine(vaccineDto, vt);
    }


    /**
     * Validate the vaccine
     * @param vaccine vaccine to be validated
     * @return vaccine validated
     */
    public boolean validateVaccine(Vaccine vaccine, VaccineType selectedVt) {
        return vaccineTypeStore.validateVaccine(vaccine, selectedVt);
    }

    /**
     * Save the created vaccine
     * @param vaccine vaccine to be saved
     * @param vt vaccine type to save the vaccine
     */
    public void saveVaccine(Vaccine vaccine, VaccineType vt) {
        vaccineTypeStore.saveVaccine(vaccine, vt);
    }

    /**
     * Gets a list of employee roles
     * @return list of Roles
     */
    public List<Role> getRoles() {
        return this.roleStore.getListOfRole();
    }
    /**
     * Compares a certain object with the wanted object
     * @param o Object to be compared
     * @return Comparison between objects
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;

        Company company = (Company) o;

        if (!Objects.equals(designation, company.designation)) return false;
        if (!Objects.equals(authFacade, company.authFacade)) return false;
        if (!Objects.equals(mvCenterStore, company.mvCenterStore))
            return false;
        if (!Objects.equals(vaccineTypeStore, company.vaccineTypeStore))
            return false;
        if (!Objects.equals(roleStore, company.roleStore)) return false;
        return Objects.equals(empStore, company.empStore);
    }
    /**
     * This function returns the Ongoing Outbreak Vaccine Type
     *
     * @return The vaccine type of the ongoing outbreak.
     */
    public VaccineType getOngoingOutbreakVaccineType(){
        return this.ongoingOutbreak;
    }
    /**
     * This function sets the Ongoing Outbreak Vaccine Type to the vaccineType parameter recived
     *
     * @param vaccineType The vaccine type that is being the outbreak.
     */
    public void setOngoingOutbreakVaccineType(VaccineType vaccineType){
          ongoingOutbreak=vaccineType;
    }

    public VaccineTypeStore getVaccineTypeStore(){
        return vaccineTypeStore;
    }

}
