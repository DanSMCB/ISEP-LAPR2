package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.store.SNSUserStore;
import app.mapper.SNSUserMapper;
import app.mapper.dto.SNSUserDTO;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * Controller for UI/Domain interaction in the reading of the csv file to register sns users
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public class ReadCSVFileController {

    private Company company;
    private SNSUserStore snsUserStore;
    private AuthFacade authFacade;
    private SNSUser snsUser;

    public ReadCSVFileController(){
        this(App.getInstance().getCompany());
        this.snsUserStore = this.company.getSnsUserStore();
        this.authFacade=this.company.getAuthFacade();
    }

    public ReadCSVFileController(Company company){
        this.company = company;
        this.snsUser=null;
    }

    /**
     * Creates a new sns user
     * @param snsUserDTO : SNS User Dto
     */
    public void createUser(SNSUserDTO snsUserDTO){
        this.snsUser = SNSUserMapper.createSNSUser(snsUserDTO);
    }

    /**
     * Save the created sns user
     */
    public boolean saveUser(){
        return snsUserStore.saveSNSUser(snsUser);
    }

    /**
     * Register the created sns user in the system
     */
    public void addUser(String name, String email, String pwd, String roleID){
        authFacade.addUserWithRole(name,email,pwd,roleID);
    }

}
