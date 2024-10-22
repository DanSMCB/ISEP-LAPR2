package app.domain.model;

import app.domain.store.AdmProcessStore;
import app.domain.store.DoseStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTest {

    @Test
    void getId() {
        String id = "1234";
        Vaccine vaccine = new Vaccine(id, "name", "brand", new AdmProcessStore());
        Assertions.assertEquals(id, vaccine.getId());
    }

    @Test
    void getName() {
        String name = "name";
        Vaccine vaccine = new Vaccine("1234", name, "brand", new AdmProcessStore());
        Assertions.assertEquals(name, vaccine.getName());
    }

    @Test
    void getBrand() {
        String brand = "brand";
        Vaccine vaccine = new Vaccine("1234", "name", brand, new AdmProcessStore());
        Assertions.assertEquals(brand, vaccine.getBrand());
    }

    @Test
    void getAdmProcessStore() {
        AdmProcessStore admProcessStore = new AdmProcessStore();
        Vaccine vaccine = new Vaccine("1234", "name", "brand", admProcessStore);
        Assertions.assertEquals(admProcessStore, vaccine.getAdmProcessStore());
    }

    @Test
    void validateAdmProcess() {
        AdmProcessStore admpStore = new AdmProcessStore();
        AdministrationProcess admp = new AdministrationProcess(new DoseStore(), 2, 80, 20);
        AdministrationProcess admp2 = new AdministrationProcess(new DoseStore(), -1, -1, 20);
        Vaccine vaccine = new Vaccine("1234", "name", "brand", admpStore);
        Assertions.assertTrue(vaccine.validateAdmProcess(admp));
        Assertions.assertFalse(vaccine.validateAdmProcess(admp2));
    }

    @Test
    void createAdmProcess() {
        DoseStore doseStore = new DoseStore();
        AdmProcessStore admpStore = new AdmProcessStore();
        AdministrationProcess admp = new AdministrationProcess(doseStore, 2,80,20);
        Vaccine vaccine = new Vaccine("1234", "name", "brand", admpStore);
        AdministrationProcess admpToTest = vaccine.createAdmProcess(doseStore, 2, 80, 20);
        Assertions.assertEquals(admpToTest, admp);
    }

    @Test
    void getAdmProcess() throws Exception {
        SNSUser snsUserTest=new SNSUser("Joana","Rua das Chaves","female",123456789,"jfps@sapo.pt", LocalDate.of(2005,4,19),12345678,123456789);
        AdministrationProcess admp = new AdministrationProcess(new DoseStore(), 2, 80, 20);
        AdministrationProcess admp2 = new AdministrationProcess(new DoseStore(), 4, 60, 10);
        AdmProcessStore admProcessStore=new AdmProcessStore();
        admProcessStore.saveAdmProcess(admp);
        admProcessStore.saveAdmProcess(admp2);
        AdministrationProcess admpTest=admProcessStore.getAdmProcess(snsUserTest.getAge());
        assertEquals(admpTest,admp2);
    }
}