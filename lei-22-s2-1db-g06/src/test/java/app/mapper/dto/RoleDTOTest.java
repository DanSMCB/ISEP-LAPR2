package app.mapper.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleDTOTest {

    @Test
    void getRoleId() {
        RoleDTO roleDTO= new RoleDTO("Center Coordinator","444");
        String roleIdTExpected="444";
        String roleIdTest= roleDTO.getRoleId();
        Assertions.assertEquals(roleIdTest,roleIdTExpected);
    }

    @Test
    void getDesignation() {
        RoleDTO roleDTO= new RoleDTO("Center Coordinator","444");
        String designationExpected="Center Coordinator";
        String designationTest= roleDTO.getDesignation();
        Assertions.assertEquals(designationTest,designationExpected);

    }

    @Test
    void setDesignation() {
        RoleDTO roleDTO= new RoleDTO("Center Coordinator","444");
        String designationExpected= "Nurse";
        String designationWanted="Nurse";
        roleDTO.setDesignation(designationWanted);
        String designationTest= roleDTO.getDesignation();
        Assertions.assertEquals(designationExpected,designationTest);
    }

    @Test
    void setRoleId() {
        RoleDTO roleDTO= new RoleDTO("Center Coordinator","444");
        String roleIdExpected= "111";
        String roleIdWanted="111";
        roleDTO.setDesignation(roleIdWanted);
        String roleIdTest= roleDTO.getDesignation();
        Assertions.assertEquals(roleIdExpected,roleIdTest);
    }
}
