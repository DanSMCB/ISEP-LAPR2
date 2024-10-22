package app.domain.store.validation;

import app.domain.model.VaccineType;
import java.util.regex.Pattern;
/**
 * Contains validations for attributes of vaccine types in order for them to meet the acceptance criteria.
 * @author Inês Costa <1210814@isep.ipp.pt>
 */
public class VaccineTypeArgumentValidation {

    /**
     * Verifies that the vaccine type code meets acceptance criteria.
     * @param vt : vaccine type
     * @return boolean true if it meets the acceptance criteria and false if it doesn't
     */
    public static boolean codeValidation(VaccineType vt){
        String regexCode = "[\\p{Alpha}\\d]{5}";
        Pattern patternCode = Pattern.compile(regexCode);
        return patternCode.matcher(vt.getCode()).matches();
    }

    /**
     * Verifies if the vaccine type description is blank or empty.
     * @param vt : vaccine type
     * @return boolean true if the description is not blank or empty and false if it is
     */
    public static boolean descriptionValidation(VaccineType vt){
        return !vt.getDescription().isBlank();
    }
}
