package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

class EmployeeTest {
    @Test
    void getRole() {
        Employee emp1= new Employee("emp1@dgs.pt","João Silva",new Role("Nurse","555"),"1244","Rua das Flores","931345788","14523521");
        Role roleExpected= new Role("Nurse","555");
        Role roleTest=emp1.getRole();
        Assertions.assertEquals(roleExpected,roleTest);

    }

    @Test
    void getName() {
        Employee emp1= new Employee("emp1@dgs.pt","João Silva",new Role("Nurse","555"),"1244","Rua das Flores","931345788","14523521");
        String nameTest=emp1.getName();
        String nameExpected="João Silva";
        Assertions.assertEquals(nameExpected,nameTest);
    }

    @Test
    void getEmployeeId() {
        Employee emp1= new Employee("emp1@dgs.pt", "João Silva",new Role("Nurse","555"),"1244","Rua das Flores","931345788","14523521");
        String employeeIdExpected= "1244";
        String employeeIdTest=emp1.getEmployeeId();
        Assertions.assertEquals(employeeIdExpected,employeeIdTest);
    }

    @Test
    void getAddress() {
        Employee emp1= new Employee("emp1@dgs.pt","João Silva",new Role("Nurse","555"),"1244","Rua das Flores","931345788","14523521");
        String addressExpected="Rua das Flores";
        String addressTest=emp1.getAddress();
       Assertions.assertEquals(addressExpected,addressTest);

    }

    @Test
    void getPhoneNumber() {
        Employee emp1= new Employee("emp1@dgs.pt","João Silva",new Role("Nurse","555"),"1244","Rua das Flores","931345788","14523521");
        String phoneNumberExpected="931345788";
        String phoneNumberTest=emp1.getPhoneNumber();
        Assertions.assertEquals(phoneNumberExpected,phoneNumberTest);
    }


    @Test
    void getEmailAddress() {
        Employee emp1= new Employee("emp1@dgs.pt","João Silva",new Role("Nurse","555"),"1244","Rua das Flores","931345788","14523521");
        String emailExpected=("emp1@dgs.pt");
        String emailTest= emp1.getEmail();
        Assertions.assertEquals(emailExpected,emailTest);
    }

    @Test
    void getCcNumber() {
        Employee emp1= new Employee("emp1@dgs.pt","João Silva",new Role("Nurse","555"),"1244","Rua das Flores","931345788","14523521");
        String ccNumberExpected="14523521";
        String ccNumberTest=emp1.getCcNumber();
        Assertions.assertEquals(ccNumberExpected,ccNumberTest);
    }
}

