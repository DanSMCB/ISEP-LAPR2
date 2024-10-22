package app.mapper.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {
    /**
     * Designation of a given role
     */
    private String designation;
    /**
     * Code that identifies a certain role
     */
    private String roleId;

    /**
     * Builds an instance of RoleDTO, receiving the designation and the roleId.
     * @param designation Designation of a given role
     * @param roleId Code that identifies a certain role
     */
    public RoleDTO(String designation, String roleId){
        this.designation=designation;
        this.roleId=roleId;
    }

    /**
     * Compares a certain object with the wanted object
     * @param o Object to be compared
     * @return Comparison between objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDTO)) return false;

        RoleDTO roleDTO = (RoleDTO) o;

        if (!designation.equals(roleDTO.designation)) return false;
        return roleId.equals(roleDTO.roleId);
    }

    /**
     * Returns textual description of a given Employee
     *
     * @return characteristics of a given Employee
     */
    @Override
    public String toString() {
        return getDesignation();

    }

    /**
     * Returns the roleID
     * @return roleID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Returns the designation of a certain Role
     * @return
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Set the designation of a given Role
     * @param designation designation of a given role
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Set the identification code of a given Role
     * @param roleId identification code of a given Role
     */

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

