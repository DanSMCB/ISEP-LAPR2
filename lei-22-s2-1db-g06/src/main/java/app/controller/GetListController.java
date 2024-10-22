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

public class GetListController {
    /**
     *Returns the list of Roles in DTO
     * @return list of Roles in DTO
     */
     public static List<RoleDTO> getRoleList(){
         RoleStore roleStore=Company.getRoleStore();
         List<Role> lr=roleStore.getListOfRole();
         List <RoleDTO> lrDTO= RoleMapper.roleToDTO(lr);
         return lrDTO;
     }

    /**
     * Returns a list of Employees that have the selected role, receiving that role
     * @param selectedRole Role selected by the Administrator
     * @return list of Employees of the selected role
     */
    public static List <EmployeeDTO> getListOfEmployeesByRole(Role selectedRole){
        EmployeeStore empStore= App.getInstance().getCompany().getEmployeeStore();
        List<Employee> listEmployee=empStore.getListOfEmployees();
        List<EmployeeDTO> lEmployeeDTO=EmployeeMapper.listOfEmployeeToDTO(listEmployee,selectedRole);
         return lEmployeeDTO;
    }
}
