package app.domain.model;

import app.domain.shared.Constants;
import app.domain.store.*;
import app.mapper.dto.VaccineDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.AuthFacade;

import javax.management.relation.RoleList;
import java.util.ArrayList;
import java.util.List;


class CompanyTest {
    AuthFacade authFacade = new AuthFacade();
    MVCenterStore mvCenterStore = new MVCenterStore();
    VaccineTypeStore vaccineTypeStore = new VaccineTypeStore();
    SNSUserStore snsUserStore = new SNSUserStore();
    HCCenterStore hcCenterStore = new HCCenterStore();
    VaccineType ongoingOutbreak = new VaccineType("54321","COVID-19","live-attenuated");

    EmployeeStore empStore = new EmployeeStore();
    Company company = new Company("company");

    @Test
    void testGetRoles() {
        List<Role> roleList = new ArrayList<>(List.of(new Role[]{new Role("Nurse", Constants.ROLE_NURSE), new Role("Receptionist", Constants.ROLE_RECEPTIONIST), new Role("Center Coordinator", Constants.ROLE_CENTER_COORDINATOR)}));
        List<Role> roleList1 = new ArrayList<>(List.of(new Role[]{new Role("Nurse", Constants.ROLE_NURSE), new Role("Receptionist", Constants.ROLE_RECEPTIONIST)}));

        Assertions.assertEquals(company.getRoles(), roleList);
        Assertions.assertNotEquals(company.getRoles(), roleList1);
    }
}