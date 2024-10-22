package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.Role;
import app.domain.store.EmployeeStore;
import app.domain.store.RoleStore;
import app.mapper.EmployeeMapper;
import app.mapper.RoleMapper;
import app.mapper.dto.EmployeeDTO;
import app.mapper.dto.RoleDTO;

import java.util.List;

/**
 * Controller for User/Domain interaction in the registering of a new Employee.
 * @author Afonso Costa <1211343@isep.ipp.pt>
 */
public class RegisterEmployeeController {

    private Employee emp;

    private List<Role> listRole;

    private List<RoleDTO> listRoleDto;

    private EmployeeStore empStore;

    private Company company;

    public RegisterEmployeeController() {
        this.company = App.getInstance().getCompany();
        this.empStore = company.getEmployeeStore();
    }

    /**
     * Creates a new employee
     * @param empDto : Employee Dto
     */
    public void createEmployee(EmployeeDTO empDto) {
        this.emp = EmployeeMapper.createEmployee(empDto);
    }

    /**
     * Saves an Employee
     */
    public void saveEmployee() {
        empStore.saveEmployee(emp);

    }

    /**
     * Gets all available roles for an Employee
     * @return : A list of Role Dto
     */
    public List<RoleDTO> getRoles() {
        this.listRole = RoleStore.getListOfRole();
        this.listRoleDto = RoleMapper.roleToDTO(listRole);
        return listRoleDto;
    }

    public void createUser(String password) {
        company.getAuthFacade().addUserWithRole(emp.getName(),emp.getEmail(),password, emp.getRole().getRoleId());
    }
}
