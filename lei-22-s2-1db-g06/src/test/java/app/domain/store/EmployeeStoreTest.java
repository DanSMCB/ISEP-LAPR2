package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeStoreTest {
    private static Employee employee1 = new Employee("admin@lei.sem2.pt", "name", new Role("role", "ID"), "employeeid", "address", "phoneNumber", "ccNumber");
    private static Employee employee2 = new Employee("admin1@lei.sem2.pt",  "name1", new Role("role1", "ID1"), "employeeid1", "address1", "phoneNumber1", "ccNumber1");

    private static  Employee employee3=new Employee("user@sem2.pt","joão",new Role("Admin","1223"),"12344","Rua do Hospital","123455555","12345678");
    private static List <Employee> listEmployee1 = List.of(employee1);

    private static List <Employee> listEmployee2 = List.of(employee2);

    private List<Employee> listEmployee= new ArrayList<>();

    private EmployeeStore empStore = new EmployeeStore();
    @Test
    void getListOfEmployees() {
        EmployeeStore empStore=new EmployeeStore();
        List<Employee> listEmployeeExpected= new ArrayList<>();
        List<Employee> listEmployeeTest=empStore.getListOfEmployees();
        Assertions.assertEquals(listEmployeeExpected,listEmployeeTest);



    }

    @Test
    void saveEmployee() {
        empStore.saveEmployee(employee1);
        assertEquals(listEmployee1, empStore.getListOfEmployees());

    }

    @Test
    void validateEmployee() {
        assertFalse(empStore.validateEmployee(null));
        assertTrue(empStore.validateEmployee(employee1));
    }

    @Test
    void addEmployee() {
        empStore.addEmployee(employee2);
        assertEquals(listEmployee2, empStore.getListOfEmployees() );
    }
    @Test
    void testFindEmployeeByEmail() throws Exception {
            Email email=new Email("user@sem2.pt");
            empStore.getListOfEmployees().add(employee3);
           Employee eTest= empStore.findEmployeeByEmail(email.getEmail());
             assertEquals(eTest,employee3);
        }
    }
