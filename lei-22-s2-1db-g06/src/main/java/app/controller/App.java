/**
 * It's a singleton class that holds the company object and the authentication facade
 */
/**
 * It's a singleton class that holds the company object and the authentication facade
 */
package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.mapper.VaccineTypeMapper;
import app.mapper.dto.AdmProcessDto;
import app.mapper.dto.DoseDto;
import app.mapper.dto.MVCDto;
import app.mapper.dto.VaccineDto;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {


    private Company company;
    private AuthFacade authFacade;
    private App()
    {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * This function returns the company.
     *
     * @return The company object.
     */
    public Company getCompany()
    {
        return this.company;
    }


    /**
     * Get the current user session from the authentication facade.
     *
     * @return The current user session.
     */
    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    /**
     * This function returns true if the user is logged in, false otherwise.
     *
     * @param email The email address of the user
     * @param pwd The password to use for authentication.
     * @return A boolean value.
     */
    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    /**
     * This function logs the user out of the application.
     */
    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    public Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap()
    {
        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_NURSE,Constants.ROLE_NURSE);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_CENTER_COORDINATOR, Constants.ROLE_CENTER_COORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_SNS_USER, Constants.ROLE_SNS_USER);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("SNS User", "snsuser@lei.sem2.pt", "123456",Constants.ROLE_SNS_USER);
        VaccineType covid=new VaccineType("54321","COVID-19","live-attenuated");
        VaccineType tetanus=new VaccineType("12345","TETANUS","toxoid");
        this.company.getVTStore().saveVaccineType(covid);
        this.company.getVTStore().saveVaccineType(tetanus);
        this.company.setOngoingOutbreakVaccineType(covid);
        LocalTime op = LocalTime.of(6,0);
        LocalTime cl = LocalTime.of(23,0);
        MassVaccinationCenter mvc = this.company.getMVCenterStore().createMVCenter(new MVCDto("Mass Vaccination Center Example","address1",123456789,"email1@gmail.com",1234567890,"website_Address1",op,cl,10,20,covid));
        this.company.getHCCenterStore().addHealthcareCenter(op,cl);
        this.company.getMVCenterStore().saveMVCenter(mvc);
        SNSUser sns2 = new SNSUser("Diogo","address2","male",123123120,"email2@gmail.com",LocalDate.of(2000, 1, 1),123123120,12312310);
        this.company.getSnsUserStore().saveSNSUser(sns2);
        CreateVaccineController createVaccineController = new CreateVaccineController(company);
        createVaccineController.createVaccine(new VaccineDto("EMEA/H/C/005791", "Spikevax", "Moderna"), VaccineTypeMapper.toDto(this.company.getVTStore().getVaccineType(0)));
        createVaccineController.createAdmProcess(new AdmProcessDto(2, 100, 5));
        createVaccineController.createDose(new DoseDto(1, 5, 0));
        createVaccineController.createDose(new DoseDto(2, 5, 10));
        createVaccineController.saveVaccine();
        /*SNSUser sns3 = new SNSUser("name3","address3","male",123123125,"email3@gmail.com",new Date(),111111111,12315609);
        this.company.getSnsUserStore().saveSNSUser(sns3);*/
//        LocalDate date3 = LocalDate.now();
//        LocalTime time2 = LocalTime.now();
//        sns2.addAppointment(mvc.createVaccinationAppointment(covid,sns2, date3, time2));
//        sns2.getSnsUserAppointmentStore().getTodayAppointment().get(0).setStateDone();
//        try {
//            sns2.getSnsUserAppointmentStore().findAppointment(new AppointmentDto(VaccineTypeMapper.toDto(covid), SNSUserMapper.toDto(sns2), date3, time2)).addVaccineAdministration("12A1-11", LocalDateTime.now(), covid.getVaccineStore().getVaccineList().get(0), covid.getVaccineStore().getVaccineList().get(0).getAdmProcess(sns2.getAge()), covid.getVaccineStore().getVaccineList().get(0).getAdmProcess(sns2.getAge()).findDose(1));
//        } catch (Exception ignored){
//        }


        this.authFacade.addUserWithRole("nurse","nurse@lei.sem2.pt", "123456", Constants.ROLE_NURSE);
        this.authFacade.addUserWithRole("recep","recep@lei.sem2.pt", "123456", Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("centerc","centerc@lei.sem2.pt", "123456", Constants.ROLE_CENTER_COORDINATOR);
        try {
            company.getEmployeeStore().addEmployee(new CenterCoordinator("centerc@lei.sem2.pt", "centerc","emp1","adress","914852639","12312310",mvc));
        } catch (Exception ignored){}

        //US6
        String s1 = "07:00";
        String s2="23:00";
        LocalTime date1 = LocalTime.parse(s1, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime date2 = LocalTime.parse(s2, DateTimeFormatter.ofPattern("HH:mm"));
        MassVaccinationCenter mvc1 = new MassVaccinationCenter("Mass Vaccination Center 1","address1",914852639,"gmail@email.com",Long.parseLong("3456123354"),"websiteaddress1",date1,date2,5,10,covid);
        HealthCareCenter hcc1 = new HealthCareCenter("Healthcare Center 1","address2",937462738,"gmail2@emil.com",Long.parseLong("8762745374"),"websiteaddress2",date1,date2,5,10,"aces","ars");
        company.getMVCenterStore().saveMVCenter(mvc1);
        company.getHCCenterStore().saveHCCenter(hcc1);

        //Appointment in the current day on mvc1(mass vaccination center 1)
        sns2.addAppointment(mvc1.createVaccinationAppointment(covid,sns2,LocalDate.now(),LocalTime.now(),LocalDateTime.now(),LocalDateTime.now()));
        //Appointment in the current day on mvc1
        sns2.addAppointment(mvc1.createVaccinationAppointment(covid,sns2,LocalDate.now(),LocalTime.now(),LocalDateTime.now(),LocalDateTime.now()));
        //Appointment in the current day on mvc1
        sns2.addAppointment(mvc1.createVaccinationAppointment(tetanus,sns2,LocalDate.now(),LocalTime.now(),LocalDateTime.now(),LocalDateTime.now()));
        //Appointment not in the current day on mvc1
        sns2.addAppointment(mvc1.createVaccinationAppointment(covid,sns2,LocalDate.of(2022,6,14),LocalTime.now(),LocalDateTime.now(),LocalDateTime.now()));
        //Appointment in the current day on hcc1(healthcare center 1)
        sns2.addAppointment(hcc1.createVaccinationAppointment(covid,sns2,LocalDate.now(),LocalTime.now(),LocalDateTime.now(),LocalDateTime.now()));
        //Appointment in the current day on hcc1
        sns2.addAppointment(hcc1.createVaccinationAppointment(tetanus,sns2,LocalDate.now(),LocalTime.now(),LocalDateTime.now(),LocalDateTime.now()));
        //Appointment not in the current day on hcc1
        sns2.addAppointment(hcc1.createVaccinationAppointment(covid,sns2,LocalDate.of(2022,6,30),LocalTime.now(),LocalDateTime.now(),LocalDateTime.now()));


//        sns2.getSnsUserAppointmentStore().getTodayAppointment().get(0).setStateWaiting();
//        sns2.addAppointment(mvc.createVaccinationAppointment(covid,sns2,date,time));
//        try {
//            sns2.getSnsUserAppointmentStore().findAppointment(new AppointmentDto(VaccineTypeMapper.toDto(covid), SNSUserMapper.toDto(sns2), date, time )).setStateWaiting();
//        } catch (Exception ignored){
//        }


    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }
}
