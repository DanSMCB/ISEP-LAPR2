package app.controller;

import app.domain.model.Company;
import app.domain.model.HealthCareCenter;
import app.domain.model.MassVaccinationCenter;
import app.domain.model.VaccinationCenter;
import app.domain.store.HCCenterStore;
import app.domain.store.MVCenterStore;
import app.mapper.HCCenterMapper;
import app.mapper.MVCenterMapper;
import app.mapper.dto.HCCDto;
import app.mapper.dto.MVCDto;
import app.mapper.dto.VCDto;

import java.util.ArrayList;
import java.util.List;

/**
 * It's a controller class for NurseUI that handles the conversion of VaccinationCenterDTOs to models and vice versa.
 *
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class NurseController {

    /**
     * A reference to the company object in the App class.
     */
    private Company company;

    /**
     * Constructor that creates a new instance of NurseController.
     */
    public NurseController() {
        company = App.getInstance().getCompany();
    }

    /**
     * This function returns a list of Mass Vaccination Centers from the company and returning it as a list of MVCDto.
     *
     * @return A list of MassVaccinationCentersDTO
     */

    public List<MVCDto> getMassVaccinationCenters() {
        MVCenterStore mvcStore = company.getMVCenterStore();
        List<MassVaccinationCenter> mvcList = mvcStore.getMVCenterList();
        return MVCenterMapper.toDto(mvcList);
    }

    /**
     * This function returns a list of HealthCareCenters from the company and returning it as a list of HCCDto.
     *
     * @return A list of HealthCareCentersDTO
     */

    public List<HCCDto> getHealthCareCenters() {
        HCCenterStore hccStore = company.getHCCenterStore();
        List<HealthCareCenter> hccList = hccStore.getHCCenterList();
        return HCCenterMapper.toDto(hccList);
    }

    /**
     * Get all vaccination centers, which are either mass vaccination centers or health care centers.
     *
     * @return A list of all vaccination centers.
     */

    public List<VCDto> getVaccinationCenters() {
        List<MVCDto> mvcDtoList = getMassVaccinationCenters();
        List<HCCDto> hccDtoList = getHealthCareCenters();
        List<VCDto> vcDtoList = new ArrayList<>();
        vcDtoList.addAll(mvcDtoList);
        vcDtoList.addAll(hccDtoList);
        return vcDtoList;
    }
    /**
     * If the VCDto is a MVCDto, then convert it to a MVCenter, otherwise convert it to a HCCenter.
     *
     * @param vcDto The DTO that we want to convert to a model.
     * @return A VaccinationCenter object
     */

    public VaccinationCenter toModel(VCDto vcDto) throws Exception {
        if (vcDto instanceof MVCDto) {
            MVCenterStore mvcStore = company.getMVCenterStore();
            return mvcStore.toModel(vcDto);
        } else if (vcDto instanceof HCCDto) {
            HCCenterStore hccStore = company.getHCCenterStore();
            return hccStore.toModel(vcDto);
        }
        throw new IllegalArgumentException("Recived VCDto is neither a MVCDto nor a HCCDto");
    }

}
