package app.mapper;

import app.domain.model.Employee;
import app.domain.model.Role;
import app.mapper.dto.EmployeeDTO;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {
    /**
     * Returns the list of EmployeeDTO with the selected role, receiving the list of Employees and the role selected by the Administrator
     * @param listEmployee List of Employees of the Company
     * @param selectedRole Role selected by the Administrator
     * @return listOfEmployeesDTO
     */
    public static List<EmployeeDTO> listOfEmployeeToDTO(List <Employee> listEmployee, Role selectedRole){
        List<EmployeeDTO> lEmployeeDTO = new ArrayList<EmployeeDTO>();
        for (Employee employee:listEmployee) {
            Role role=employee.getRole();
            if(selectedRole.equals(role)){
                String name= employee.getName();
                String employeeId= employee.getEmployeeId();
                String address = employee.getAddress();
                String phoneNumber= employee.getPhoneNumber();
                String ccNumber= employee.getCcNumber();
                String emailAddress=employee.getEmail();

                EmployeeDTO employeeDTO= new EmployeeDTO(name, address,phoneNumber,emailAddress,ccNumber,role);
                employeeDTO.setEmployeeId(employeeId);
                lEmployeeDTO.add(employeeDTO);
            }
        }
        return lEmployeeDTO;
    }
    public static Employee createEmployee(EmployeeDTO empDto){
       String name= empDto.getName();
       String address=empDto.getAddress();
       String employeeId= empDto.getEmployeeId();
       Role role= empDto.getRole();
       String phoneNumber= empDto.getPhoneNumber();
       String email= empDto.getEmailAddress();
       String ccNumber= empDto.getCcNumber();
       return new Employee(email,name,role,employeeId,address,phoneNumber,ccNumber);
    }
}