package app.domain.model;

import app.domain.shared.Constants;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CenterCoordinatorTest {

    @Test
    void getVaccinationCenter() {
         MassVaccinationCenter mvc1=new MassVaccinationCenter("vc1","Rua",123455677,"email",1234567780,"website", LocalTime.of(12,30),LocalTime.of(18,30),10,5,new VaccineType("1234","Gripe","asdadsa"));
        CenterCoordinator cc1 = null;
        try{
            cc1=new CenterCoordinator("cc1@sem2.pt","Rui","123455","Rua de Góis","12345678890","1234567",mvc1);
        } catch (Exception ignored){}
        VaccinationCenter vcTest= cc1.getVaccinationCenter();
        assertEquals(vcTest,mvc1);

    }

}