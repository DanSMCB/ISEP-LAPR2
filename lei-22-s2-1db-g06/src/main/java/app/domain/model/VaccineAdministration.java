package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A vaccine administration that is a record of a vaccine being administered to an SNS User
 */
public class VaccineAdministration implements Serializable {

    private String lotNumber;
    private LocalDateTime dateOfAdministration;
    private Vaccine vaccine;
    private AdministrationProcess admp;
    private Dose dose;

    public VaccineAdministration(String lotNumber, LocalDateTime dateOfAdministration,Vaccine vaccine, AdministrationProcess admp,Dose dose){
        this.lotNumber = lotNumber;
        this.dateOfAdministration = dateOfAdministration;
        this.vaccine = vaccine;
        this.admp=admp;
        this.dose=dose;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public LocalDateTime getDateOfAdministration() {
        return dateOfAdministration;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public AdministrationProcess getAdmp() {
        return admp;
    }

    public Dose getDose() {
        return dose;
    }
}
