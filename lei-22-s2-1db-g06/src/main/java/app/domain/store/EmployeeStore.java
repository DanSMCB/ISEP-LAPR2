package app.domain.store;

import app.domain.model.Employee;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements methods for mapping VaccineType DTOs to VaccineType and vice-versa.
 * @author Afonso Costa <1211343@isep.ipp.pt> José Barbosa <1211359@isep.ipp.pt>
 */
public class EmployeeStore implements Serializable {
    /**
     * List that contains all Employees
     */
    private List<Employee> listEmployee= new ArrayList<>();

    /**
     * Returns the list of Employess
     * @return list of Employees
     */
    public List <Employee> getListOfEmployees() {
        return listEmployee;
    }

    /**
     * Saves an employee
     * @param emp : employee
     */
    public void saveEmployee(Employee emp) {
        if (validateEmployee(emp)){
            addEmployee(emp);
        }
    }

    /**
     * Validates the employee
     * @param emp : employee
     * @return boolean with the value "true"
     */
    public boolean validateEmployee(Employee emp) {
        if (emp == null) return false;
        return true;
    }

    /**
     * Adds an employee
     * @param emp : employee
     */
    public void addEmployee(Employee emp) {
        listEmployee.add(listEmployee.size(),emp);
    }
    public Employee findEmployeeByEmail(String email) throws Exception {
        if(!(listEmployee.isEmpty())){
            for (Employee employee : listEmployee) {
                if (email.equals(employee.getEmail())) {
                    return employee;
                }
            }
        }
        throw  new Exception("There are no Employees with the given email address");
    }


}
