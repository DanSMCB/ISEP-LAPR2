package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    void getDesignation() {
        Role role1=new Role("Nurse","123");
        String designationExpected="Nurse";
        String designationTest= role1.getDesignation();
        Assertions.assertEquals(designationExpected,designationTest);

    }

    @Test
    void getRoleId() {
        Role role1=new Role("Nurse","123");
        String roleIdExpected="123";
        String roleIdTest=role1.getRoleId();
        Assertions.assertEquals(roleIdExpected,roleIdTest);
    }
}