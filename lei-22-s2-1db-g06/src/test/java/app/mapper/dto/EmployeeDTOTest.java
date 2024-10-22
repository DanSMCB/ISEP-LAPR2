package app.mapper.dto;

import app.domain.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDTOTest {

    @Test
    void getName() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String nameExpected="Pedro Andrade";
        String nameTest= empDTO.getName();
        Assertions.assertEquals(nameExpected,nameTest);
    }

    @Test
    void getEmployeeId() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String employeeIdExpected="12345";
        String employeeIdTest= empDTO.getEmployeeId();
        Assertions.assertEquals(employeeIdExpected,employeeIdTest);
    }

    @Test
    void getAddress() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012",("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String addressExpected="Rua do Carmo";
        String addressTest= empDTO.getAddress();
        Assertions.assertEquals(addressExpected,addressTest);
    }

    @Test
    void getPhoneNumber() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String phoneNumberExpected="968763012";
        String phoneNumberTest= empDTO.getPhoneNumber();
        Assertions.assertEquals(phoneNumberExpected,phoneNumberTest);
    }

    @Test
    void getEmailAddress() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String emailAddressExpected= ("pedroand@dgs.pt");
        String emailAdressTest= empDTO.getEmailAddress();
        Assertions.assertEquals(emailAddressExpected,emailAdressTest);

    }

    @Test
    void getCcNumber() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1", "Rua do Carmo","968763012",("pedroand@dgs.pt"),"67896534",new Role("Receptionist","111"));
        String ccNumberExpected="67896534";
        String ccNumberTest= empDTO.getCcNumber();
       Assertions.assertEquals(ccNumberExpected, ccNumberTest);

    }

    @Test
    void getRole() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        Role roleExpected= new Role("Recepcionist","111");
        Role roleTest= empDTO.getRole();
        Assertions.assertEquals(roleExpected,roleTest);
    }

    @Test
    void setName() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String nameExpected= "João Andrade";
        String nameWanted="João Andrade";
        empDTO.setName(nameWanted);
        String nameTest= empDTO.getName();
        Assertions.assertEquals(nameExpected,nameTest);
    }

    @Test
    void setEmployeeId() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String employeeIdExpected= "54341";
        String employeeIdWanted="54341";
        empDTO.setEmployeeId(employeeIdWanted);
        String employeeIdTest= empDTO.getEmployeeId();
        Assertions.assertEquals(employeeIdExpected,employeeIdTest);
    }

    @Test
    void setAddress() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String addressExpected= "Rua da Igreja";
        String addressWanted="Rua da Igreja";
        empDTO.setAddress(addressWanted);
        String addressTest= empDTO.getAddress();
        Assertions.assertEquals(addressExpected,addressTest);

    }

    @Test
    void setPhoneNumber() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012",("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String phoneNumberExpected= "934567192";
        String phoneNumberWanted="934567192";
        empDTO.setPhoneNumber(phoneNumberWanted);
        String phoneNumberTest= empDTO.getPhoneNumber();
        Assertions.assertEquals(phoneNumberExpected,phoneNumberTest);
    }

    @Test
    void setEmailAddress() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String emailExpected=("andradep@dgs.pt") ;
        String emailWanted=("andradep@dgs.pt") ;
        empDTO.setEmailAddress(emailWanted);
        String emailTest= empDTO.getEmailAddress();
        Assertions.assertEquals(emailExpected,emailTest);

    }

    @Test
    void setCcNumber() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012", ("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        String ccNumberExpected= "54341";
        String ccNumberWanted="54341";
        empDTO.setCcNumber(ccNumberWanted);
        String ccNumberTest= empDTO.getCcNumber();
        Assertions.assertEquals(ccNumberExpected,ccNumberTest);
    }

    @Test
    void setRole() {
        EmployeeDTO empDTO= new EmployeeDTO("Pedro Andrade","12345", "emp1","Rua do Carmo","968763012",("pedroand@dgs.pt"),"67896534",new Role("Recepcionist","111"));
        Role roleExpected=new Role("Nurse","555") ;
        Role roleWanted=new Role("Nurse","555") ;
        empDTO.setRole(roleWanted);
        Role roleTest= empDTO.getRole();
        Assertions.assertEquals(roleExpected,roleTest);
    }
}