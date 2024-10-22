package app.domain.store;

import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.mapper.VaccineTypeMapper;
import app.mapper.dto.VaccineDto;
import app.mapper.dto.VaccineTypeDto;
import app.domain.store.validation.VaccineTypeArgumentValidation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Saves VaccineType objects.
 * @author Daniel Braga <1200801@isep.ipp.pt>, Inês Costa <1210814@isep.ipp.pt>, Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class VaccineTypeStore implements Serializable {
    private static List<String> listVaccineTechnologies = List.of("live-attenuated", "inactivated", "subunit", "toxoid", "viral vector", "messenger RNA (mRNA)");
    private List<VaccineType> listVT = new ArrayList<>();

    /**
     * Prints registered vaccine types.
     */
    public void printVaccineTypeStore() {
        System.out.println("\n----- Registered Vaccine Types -----\n");
        if (getSize() == 0)
            System.out.println("\nThere are no registered vaccine types yet.\n");
        else {
            for (int i = 0; i < listVT.size(); i++) {
                System.out.println("No. " + (i + 1) + " " + listVT.get(i).toString());
            }
        }
        System.out.println();
    }

    /**
     * Returns the list of available vaccine technologies.
     *
     * @return listVaccineTechnologies : list of available vaccine technologies
     */
    public static List<String> getListVaccineTechnologies() {
        return listVaccineTechnologies;
    }

    /**
     * Returns the size of the list of registered vaccine technologies.
     *
     * @return listVT.size() : size of listVT
     */
    public int getSize() {
        return listVT.size();
    }

    /**
     * returns boolean value of listVT being empty.
     *
     * @return listVT.isEmpty() : true if list is empty, false otherwise
     */
    public boolean checkIfNull() {
        return listVT.isEmpty();
    }

    /**
     * Finds vaccine type in list in specified index.
     *
     * @param index : index of list
     * @return listVT.get(index) : returns vaccine type in index
     */
    public VaccineType getVaccineType(int index) {
        if (index >= listVT.size() || index < 0) {
            System.out.println("Invalid option, please type a valid index.");
            return null;
        } else {
            return listVT.get(index);
        }
    }

    /**
     * Returns list of vaccine types.
     *
     * @return listVT : list of vaccine types
     */
    public List<VaccineType> getVaccineTypes() {
        return this.listVT;
    }

    /**
     * > Create a new VaccineType object from a VaccineTypeDto object
     *
     * @param vtDto The DTO object that is passed in.
     * @return A VaccineType object
     */
    public VaccineType createVaccineType(VaccineTypeDto vtDto) {
        VaccineType vt = VaccineTypeMapper.toModel(vtDto);
        return vt;
    }

    /**
     * Verifies if attributes of new vaccine type meet acceptance criteria (AC).
     *
     * @param vt :vaccine type
     * @return boolean value : true if attributes meet AC, false otherwise
     */
    public boolean validateVaccineType(VaccineType vt) {
        boolean flag = true;
        try {
            if (!VaccineTypeArgumentValidation.codeValidation(vt))
                throw new IllegalArgumentException("\nInvalid code! Code must have 5 alphanumeric characters.");
        } catch (IllegalArgumentException e2) {
            System.out.println("\n" + e2.getMessage());
            flag = false;
        } finally {
            try {
                if (!VaccineTypeArgumentValidation.descriptionValidation(vt))
                    throw new IllegalArgumentException("\nInvalid description! Description must not be empty or blank.");
            } catch (IllegalArgumentException e3) {
                System.out.println("\n" + e3.getMessage());
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Validates and saves new vaccine type into list of vaccine types.
     *
     * @param vt : vaccine type
     */
    public void saveVaccineType(VaccineType vt) {
        if (validateVaccineType(vt) && !listVT.contains(vt))
            addVaccineType(vt);
        else if (listVT.contains(vt)) {
            System.out.println("\nVaccine type already registered. New vaccine type not saved.\n");
            System.out.println("\n-----Returning to Vaccine Type creation menu-----\n");
        }
    }

    /**
     * Adds vaccine type to vaccine type list.
     *
     * @param vt : vaccine type
     */
    public void addVaccineType(VaccineType vt) {
        if (listVT.size() == 0)
            listVT.add(0, vt);
        else
            listVT.add(listVT.size(), vt);
    }

    /**
     * Finds the location of an already registered vaccine type and returns it. Otherwise it returns the new vaccine type itself.
     *
     * @param vt : vaccine type
     * @return vt : new vaccine type or vacType : existent vaccine type with the same data
     */
    public VaccineType find(VaccineType vt) {
        for (VaccineType vacType : this.listVT) {
            if (vacType.equals(vt))
                return vacType;
        }
        return vt;
    }

    /**
     * Save the vaccine in the vaccine store of a given vaccine type
     *
     * @param vaccine vaccine to be saved
     * @param vt      vaccine type of the saved vaccine
     */
    public void saveVaccine(Vaccine vaccine, VaccineType vt) {
        VaccineType vtToStore = find(vt);
        vtToStore.saveVaccine(vaccine);
    }

    /**
     * Validate the recived vaccine of a given vaccine type
     *
     * @param vaccine vaccine to be validated
     * @param vt      vaccine type of the validated vaccine
     * @return vaccine validation
     */
    public boolean validateVaccine(Vaccine vaccine, VaccineType vt) {
        VaccineType vtToValidate = find(vt);
        return vtToValidate.validateVaccine(vaccine);
    }

    /**
     * Create and return a new instance of a vaccine using Vaccine DTO information
     *
     * @param vaccineDto vaccine Dto with the atributes to create the vaccine
     * @param vt         vaccine type to create the vaccine
     * @return created vaccine
     */
    public Vaccine createVaccine(VaccineDto vaccineDto, VaccineType vt) {
        VaccineType vtToCreate = find(vt);
        return vtToCreate.createVaccine(vaccineDto);
    }

    /**
     * This function takes a vaccine type DTO and returns a vaccine type if the vaccine type DTO matches a vaccine
     * type in the list of vaccine types
     *
     * @param vtDto The vaccine type data transfer object.
     * @return The vaccine type that matches the vaccine type dto.
     */
    public VaccineType find(VaccineTypeDto vtDto){
        for (VaccineType vaccineType : listVT) {
            if (vaccineType.checkVaccineType(vtDto)) {
                return vaccineType;
            }

        }
        return null;
    }

    /**
     * For each vaccine type, it checks if the vaccine store has the vaccine, and if it does, it returns it
     *
     * @param vaccineName The name of the vaccine to be found.
     * @return The vaccine being returned.
     */
    public Vaccine findVaccine(String vaccineName) {
        Vaccine vaccine = null;
        for (VaccineType vt: listVT) {
            VaccineStore vaccineStore = vt.getVaccineStore();
            vaccine = vaccineStore.findVaccine(vaccineName);
            if (vaccine != null)
                return vaccine;
        }
        return null;
    }


    /**
     * Find the vaccine type that contains the vaccine with the given name.
     *
     * @param vaccineName The name of the vaccine to be found.
     * @return The VaccineType that contains the vaccine with the name vaccineName.
     */
    public VaccineType findVaccineType(String vaccineName) {
        Vaccine vaccine = null;
        for (VaccineType vt: listVT) {
            VaccineStore vaccineStore = vt.getVaccineStore();
            vaccine = vaccineStore.findVaccine(vaccineName);
            if (vaccine != null)
                return vt;
        }
        return null;
    }
}
