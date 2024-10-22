package app.domain.store;

import app.domain.model.Appointment;
import app.domain.model.MassVaccinationCenter;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.mapper.MVCenterMapper;
import app.mapper.dto.MVCDto;
import app.mapper.dto.VCDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Save mass vaccination center objects
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public class MVCenterStore implements Serializable {
    /**
     * List responsible for storing Mass Vaccination Centers
     */

    private List<MassVaccinationCenter> massVaccinationCenters = new ArrayList<>();

    /**
     * Returns a mass vaccination center of the list
     * @return a mass vaccination center with the specified index
     */
    public MassVaccinationCenter getMVCenter(int index){
        return massVaccinationCenters.get(index);
    }

    /**
     * It creates a new MassVaccinationCenter object from a MassVaccinationCenterDto object
     *
     * @param mvcDto The DTO object that will be converted to a model object.
     * @return A MassVaccinationCenter
     */
    public MassVaccinationCenter createMVCenter(MVCDto mvcDto){
        return MVCenterMapper.toModel(mvcDto);
    }

    /**
     * Prints registered mass vaccination centers
     */
    public void printVaccinationStore(){
        for(int i=0;i<massVaccinationCenters.size();i++){
            System.out.println("No. " + (i+1));
            System.out.println(massVaccinationCenters.get(i).toString());
        }
    }

    /**
     * Returns the size of the list of the registered mass vaccination centers
     * @return massVaccinationCenters.size() : size of massVaccinationCenters
     */
    public int getSize(){
        return massVaccinationCenters.size();
    }

    /**
     * returns boolean value of massVaccinationCenters being empty.
     * @return massVaccinationCenters.isEmpty() : true if list is empty, false otherwise
     */
    public boolean checkIfNull(){return massVaccinationCenters.isEmpty();}

    /**
     * Verifies if attributes of new mass vaccination center meet acceptance criteria (AC).
     * @param mvc :mass vaccination center
     * @return boolean value : true if attributes meet AC, false otherwise
     */
    public boolean validateMVCenter(MassVaccinationCenter mvc) {
        if (mvc == null) return false;
        return true;
    }

    /**
     * Validates and saves new mass vaccination center into list of mass vaccination centers
     * @param mvc : mass vaccination center
     */
    public void saveMVCenter(MassVaccinationCenter mvc) {
        if (validateMVCenter(mvc)){
            this.massVaccinationCenters.add(mvc);
        }
    }

    /**
     * Returns the list of all the mass vaccination centers
     *
     * @return A list of MassVaccinationCenters
     */
    public List<MassVaccinationCenter> getMVCenterList() {
        return massVaccinationCenters;
    }
    /**
     * This function returns a list of mass vaccination centers that have the same vaccine type as the one passed in as a
     * parameter
     *
     * @param vaccineType The vaccine type that you want to find the mass vaccination centers for.
     * @return A list of MassVaccinationCenters that have the same vaccine type as the one passed in.
     */
    public List<MassVaccinationCenter> getMVCenterListWithVt(VaccineType vaccineType){
        List<MassVaccinationCenter>mvcWithVtList=new ArrayList<>();
        for (MassVaccinationCenter mvc:massVaccinationCenters){
                VaccineType vtCompare=mvc.getVaccineType();
            if(vaccineType == vtCompare){
                mvcWithVtList.add(mvc);
            }
        }
        return mvcWithVtList;
    }
    /**
     * This function takes a VCDto object and returns a VaccinationCenter object that matches the VCDto object
     *
     * @param vcDto the DTO object that we want to convert to a model object
     * @return The vaccination center that matches the given DTO.
     */
    public MassVaccinationCenter toModel(VCDto vcDto){
        for (MassVaccinationCenter vc:massVaccinationCenters) {
            if(vc.vcMatches(vcDto)){
                return vc;
            }
        }
        return null;
    }
}

