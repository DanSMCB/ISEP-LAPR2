package app.mapper.dto;

import app.domain.model.AdministrationProcess;
import app.domain.store.DoseStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdmProcessDtoTest {

    @Test
    void getNumberOfDoses() {
        int numberOfDoses = 2;
        AdmProcessDto admp = new AdmProcessDto(numberOfDoses, 10,20);
        Assertions.assertEquals(admp.getNumberOfDoses(), numberOfDoses);
    }

    @Test
    void getMaximumAge() {
        int maximumAge = 10;
        AdmProcessDto admp = new AdmProcessDto(2, maximumAge,20);
        Assertions.assertEquals(admp.getMaximumAge(), maximumAge);
    }

    @Test
    void getMinimumAge() {
        int minimumAge = 20;
        AdmProcessDto admp = new AdmProcessDto(2, 10,minimumAge);
        Assertions.assertEquals(admp.getMinimumAge(), minimumAge);
    }

    @Test
    void setNumberOfDoses() {
        AdmProcessDto admp = new AdmProcessDto(2, 10,20);
        int toChange = 1;
        admp.setNumberOfDoses(toChange);
        Assertions.assertEquals(toChange, admp.getNumberOfDoses());
    }

    @Test
    void setMaximumAge() {
        AdmProcessDto admp = new AdmProcessDto(2, 10,20);
        int toChange = 1;
        admp.setMaximumAge(toChange);
        Assertions.assertEquals(toChange, admp.getMaximumAge());
    }

    @Test
    void setMinimumAge() {
        AdmProcessDto admp = new AdmProcessDto(2, 10,20);
        int toChange = 1;
        admp.setMinimumAge(toChange);
        Assertions.assertEquals(toChange, admp.getMinimumAge());
    }
}