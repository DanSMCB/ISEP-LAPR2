package app.mapper;

import app.domain.model.Role;
import app.domain.model.VaccineType;
import app.mapper.dto.RoleDTO;
import app.mapper.dto.VaccineTypeDto;

import java.util.ArrayList;
import java.util.List;

public class RoleMapper {
    /**
     * List of RoleDTO
     */
    private static List<RoleDTO> lRolesDto;

    /**
     * Returns the list of RolesDTO
     * @param lr list of roles
     * @return list of RolesDTO
     */
    public static List<RoleDTO> roleToDTO (List<Role> lr) {
        List<RoleDTO> lrDTO = new ArrayList<RoleDTO>();
        for (Role role : lr) {
            String designation = role.getDesignation();
            String roleId = role.getRoleId();
            RoleDTO roleDTO= new RoleDTO(designation,roleId);
            lrDTO.add(roleDTO);
        }

        return lrDTO;
    }
}
