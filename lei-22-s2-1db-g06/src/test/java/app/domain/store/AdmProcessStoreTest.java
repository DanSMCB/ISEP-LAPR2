package app.domain.store;

import app.domain.model.AdministrationProcess;
import app.domain.model.SNSUser;
import app.domain.model.Vaccine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdmProcessStoreTest {
//    private  List<AdministrationProcess> administrationProcessList=new ArrayList<>();

    @Test
    void validateAdmProcess() {
        AdmProcessStore admpStore = new AdmProcessStore();
        AdministrationProcess admp = new AdministrationProcess(new DoseStore(), 2, 80, 20);
        AdministrationProcess admp2 = new AdministrationProcess(new DoseStore(), -1, -1, 20);
        Assertions.assertTrue(admpStore.validateAdmProcess(admp));
        Assertions.assertFalse(admpStore.validateAdmProcess(admp2));
    }

    @Test
    void createAdmProcess() {
        DoseStore doseStore = new DoseStore();
        AdmProcessStore admpStore = new AdmProcessStore();
        AdministrationProcess admp = new AdministrationProcess(doseStore, 2,80,20);
        AdministrationProcess admpToTest = admpStore.createAdmProcess(doseStore, 2, 80, 20);
        Assertions.assertEquals(admpToTest, admp);
    }

    @Test
    void getAdmProcess() throws Exception {
        SNSUser snsUserTest=new SNSUser("Joana","Rua das Chaves","female",123456789,"jfps@sapo.pt", LocalDate.of(2005,4,19),12345678,123456789);
        AdministrationProcess admp = new AdministrationProcess(new DoseStore(), 2, 80, 20);
        AdministrationProcess admp2 = new AdministrationProcess(new DoseStore(), 5, 19, 5);
        AdmProcessStore admProcessStore=new AdmProcessStore();
       admProcessStore.saveAdmProcess(admp);
       admProcessStore.saveAdmProcess(admp2);
        AdministrationProcess wantedAdmp= admProcessStore.getAdmProcess(snsUserTest.getAge());
        assertEquals(admp2,wantedAdmp);
    }
}