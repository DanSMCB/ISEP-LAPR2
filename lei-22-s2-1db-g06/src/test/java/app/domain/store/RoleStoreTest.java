package app.domain.store;

import app.domain.model.Role;
import app.domain.shared.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleStoreTest {
    @Test
    void getListOfRole() {
        RoleStore roleStore= new RoleStore();
        List<Role> lrExpected = new ArrayList<>(List.of(new Role[]{new Role("Nurse", Constants.ROLE_NURSE), new Role("Receptionist", Constants.ROLE_RECEPTIONIST), new Role("Center Coordinator", Constants.ROLE_CENTER_COORDINATOR)}));
        List<Role> lrTest=roleStore.getListOfRole();
        Assertions.assertEquals(lrExpected,lrTest);
    }
}