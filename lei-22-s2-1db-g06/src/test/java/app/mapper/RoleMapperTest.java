package app.mapper;

import app.domain.model.Role;
import app.mapper.dto.RoleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class RoleMapperTest {

    @Test
    void roleToDTO() {
        List<Role> lr= new ArrayList<Role>();
        Role role= new Role("Nurse","555");
        lr.add(role);

        List<RoleDTO>expectedlr=RoleMapper.roleToDTO(lr);

        List<RoleDTO> lrDTO = new ArrayList<RoleDTO>();

        String designation = role.getDesignation();
        String roleId = role.getRoleId();
        RoleDTO roleDTO= new RoleDTO(designation,roleId);
        lrDTO.add(roleDTO);

        Assertions.assertEquals(expectedlr,lrDTO);

    }
}