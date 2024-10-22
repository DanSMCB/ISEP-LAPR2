package app.mapper;

import app.domain.model.Employee;
import app.domain.model.Role;
import app.mapper.dto.EmployeeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;

import java.util.ArrayList;
import java.util.List;

class EmployeeMapperTest {
    @Test
    void listOfEmployeeToDTO() {
        List<Employee>lEmployeeExpected=new ArrayList<Employee>();
        Role roleEmp=new Role("Nurse","555");
        Employee emp1= new Employee("emp1@dgs.pt","Pedro Martins",roleEmp,"124455","Rua do Olival","245467532","12345678");
        lEmployeeExpected.add(emp1);

        List<EmployeeDTO> empDTOexpected=EmployeeMapper.listOfEmployeeToDTO(lEmployeeExpected,roleEmp);

        List<EmployeeDTO> lEmployeeDTO = new ArrayList<EmployeeDTO>();

        Role role=emp1.getRole();
        String name= emp1.getName();
        String address = emp1.getAddress();
        String phoneNumber= emp1.getPhoneNumber();
        String ccNumber= emp1.getCcNumber();
        String emailAddress=emp1.getEmail();
        EmployeeDTO employeeDTO= new EmployeeDTO(name, address,phoneNumber,emailAddress,ccNumber,role);
        employeeDTO.setEmployeeId(emp1.getEmployeeId());
        lEmployeeDTO.add(employeeDTO);


        Assertions.assertEquals(empDTOexpected,lEmployeeDTO);
    }
}