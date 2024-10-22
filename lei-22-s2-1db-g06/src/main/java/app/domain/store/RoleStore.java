package app.domain.store;
import app.domain.model.Role;
import app.domain.shared.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoleStore implements Serializable {
    /**
     * List that contains all Roles
     */
    private static List<Role> lr = new ArrayList<>(List.of(new Role[]{new Role("Nurse", Constants.ROLE_NURSE), new Role("Receptionist", Constants.ROLE_RECEPTIONIST), new Role("Center Coordinator", Constants.ROLE_CENTER_COORDINATOR)}));
    /**
     * Returns list of Roles
     * @return list of Roles
     */
    public static List<Role> getListOfRole(){
        return lr;
    }

    /**
     * Get the role with the given id, or throw an exception if there is no such role.
     *
     * @param id The id of the role you want to get
     * @return A Role object
     */
    public static Role getRole(String id) throws Exception {
        for (Role role: lr) {
            if (role.getRoleId().equals(id))
                return role;
        }
        throw new Exception("There are no such Role with the id "+id);
    }
}
