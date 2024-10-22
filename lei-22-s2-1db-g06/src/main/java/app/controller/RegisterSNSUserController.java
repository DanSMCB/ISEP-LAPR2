package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.store.SNSUserStore;
import pt.isep.lei.esoft.auth.AuthFacade;

public class RegisterSNSUserController {

    private Company company;
    private SNSUserStore snsuserStore;
    private SNSUser snsUser;
    private AuthFacade auth;
    private String pwd;

    public RegisterSNSUserController(){
        this(App.getInstance().getCompany());
        this.snsuserStore = this.company.getSnsUserStore();
        this.auth = this.company.getAuthFacade();
    }

    public RegisterSNSUserController(Company company){
        this.company = company;
        this.snsUser = null;
    }

    public boolean checkIfSNSUserStoreNull(){ return snsuserStore.checkIfNull();}

    public void printSNSUserStore(){ snsuserStore.printSNSUserStore();}
}
