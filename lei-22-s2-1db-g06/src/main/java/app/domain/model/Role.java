package app.domain.model;

import java.io.Serializable;

/**
 * Implements methods for getting the roleId and designtation of a role.
 * @author Afonso Costa <1211343@isep.ipp.pt>
 */
public class Role implements Serializable {
    /**
     * Designation of a given role
     */
    private String designation;
    /**
     * Code that identifies a certain role
     */
    private String roleId;

    /**
     * Builds an instance of Role receiving the designation and role id
     * @param designation Designation of a given role
     * @param roleId Code that identifies a certain role
     */
    public Role(String designation,String roleId){
        this.designation=designation;
        this.roleId=roleId;
    }

    /**
     * Returns the designation of a given Role
     * @return designation
     */
    public String getDesignation(){
        return designation;
    }

    /**
     *   Returns the role Id of a given Role
     * @return roleId
     */
    public String getRoleId(){
        return roleId;
    }


    /**
     * Returns the designtion of the roles
     * @return designation of the roles
     */
    public String toString() {
        return getDesignation();

    }


    /**
     * Compares a certain object with the wanted object
     * @param o Object to be compared
     * @return Comparison between objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (!designation.equals(role.designation)) return false;
        return roleId.equals(role.roleId);
    }

}
