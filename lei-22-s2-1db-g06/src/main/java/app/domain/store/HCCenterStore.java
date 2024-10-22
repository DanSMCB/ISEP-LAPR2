package app.domain.store;

import app.domain.model.HealthCareCenter;
import app.domain.model.MassVaccinationCenter;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.mapper.HCCenterMapper;
import app.mapper.MVCenterMapper;
import app.mapper.dto.HCCDto;
import app.mapper.dto.VCDto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

public class HCCenterStore implements Serializable {
    /**
     * List responsible for storing the Healthcare Centers
     */
    private List<HealthCareCenter> healthcareCenters = new ArrayList<>();

    /**
     * Returns a healthcare center of the list
     * @return a healthcare center with the specified index
     */
    public HealthCareCenter getHCCenter(int index){
        return healthcareCenters.get(index);
    }

    /**
     * Returns the size of the list of the registered healthcare centers
     * @return healthcareCenters.size() : size of healthcareCenters
     */
    public int getSize(){
        return healthcareCenters.size();
    }

    /**
     * This function returns a list of all the healthcare centers
     *
     * @return A list of healthcare centers.
     */
    public List<HealthCareCenter> getHCCenterList() {
        return healthcareCenters;
    }
    /**
     * This function takes a VCDto object and returns a VaccinationCenter object that matches the VCDto object
     *
     * @param vcDto Vaccination Center in Dto
     * @return The method is returning a VaccinationCenter object that matches the VCDto object.
     */
    public HealthCareCenter toModel(VCDto vcDto){
        for (HealthCareCenter vc:healthcareCenters) {
            if(vc.vcMatches(vcDto)){
                return vc;
            }
        }
        return null;
    }

    public void addHealthcareCenter(LocalTime oo, LocalTime co) {
        HealthCareCenter hcc1=new HealthCareCenter("Healhcare Center Example","address2",111111111, "hcc1@sem2.pt",111111111,"hcc1.com",oo,co,10,10, "ACES1","ARS1");
        this.healthcareCenters.add(hcc1);
    }

    public HealthCareCenter createHCCenter(HCCDto hccDto) {
        return toModel(hccDto);
    }
    public HealthCareCenter toModel(HCCDto hccDto){
        String name = hccDto.getName();
        String address = hccDto.getAddress();
        long phoneNumber = hccDto.getPhoneNumber();
        String emailAddress = hccDto.getEmail();
        long faxNumber = hccDto.getFaxNumber();
        String websiteAddress = hccDto.getWebsiteAdress();
        LocalTime openingHour = hccDto.getOpeningHour();
        LocalTime closingHour = hccDto.getClosingHour();
        int slotDuration = hccDto.getSlotDuration();;
        int numberOfVaccinesSlot = hccDto.getNumberOfVaccinesSlot();
        String ars = hccDto.getARS();
        String aces = hccDto.getACES();
        return new HealthCareCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration,numberOfVaccinesSlot,ars, aces);
    }

    public boolean validateHCCenter(HealthCareCenter hcc) {
        if (hcc == null) return false;
        return true;
    }

    public void saveHCCenter(HealthCareCenter hcc) {
        if (validateHCCenter(hcc)){
            this.healthcareCenters.add(hcc);
        }
    }

    public boolean checkIfNull() {
        return healthcareCenters.isEmpty();
    }

    public void printVaccinationStore(){
        for(int i=0;i<healthcareCenters.size();i++){
            System.out.println("No. " + (i+1));
            System.out.println(healthcareCenters.get(i).toString());
        }
    }
}
