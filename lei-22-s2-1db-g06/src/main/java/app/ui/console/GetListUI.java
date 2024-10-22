package app.ui.console;

import app.controller.GetListController;
import app.domain.model.Role;
import app.mapper.dto.EmployeeDTO;
import app.mapper.dto.RoleDTO;
import app.ui.console.utils.Utils;

import java.util.List;

import static app.ui.console.utils.Utils.readLineFromConsole;
import static app.ui.console.utils.Utils.showAndSelectOne;

public class GetListUI implements Runnable {

    public List<RoleDTO> getRoleList(){
        return GetListController.getRoleList();
    }
    public List<EmployeeDTO> getListOfEmployeesByRole(Role selectedRole){
        return GetListController.getListOfEmployeesByRole(selectedRole);
    }

    @Override
    public void run() {

        int option=0;
        do{
            System.out.println("1 - Show list of Employees according to criteria.");
            System.out.println();
            System.out.println();
            System.out.println("0 - Cancel");
            boolean error;
            do {
                try {
                    option = Integer.valueOf(readLineFromConsole("Type your option"));
                    error=false;
                } catch (Exception e) {
                    System.out.println("\nInvalid option.\n");
                    error=true;
                }
            }while (error);//error=true;
            if(option==1) {
                List<RoleDTO> lrDTO = getRoleList();
                try {
                    RoleDTO roleDTO = (RoleDTO) showAndSelectOne(lrDTO, "\"-----Roles-----\"");
                    Role selectedRole = new Role(roleDTO.getDesignation(), roleDTO.getRoleId());
                    Utils.showList(getListOfEmployeesByRole(selectedRole), "\"-----List of Employees with selected role-----\"");

                }catch (Exception e2) {
                    System.out.println("\nInvalid option\n");
                }




            }
        }while(option!=0);
    }

    }

