package GSari.ParcialMutantes.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, String []> {

    private static final String ValidCharacters = "ACTG";

    @Override
    public void initialize(ValidDna constraintAnnotation) {

    }
    @Override
    public boolean isValid(String [] dna, ConstraintValidatorContext constraintValidatorContext) {
        if (dna == null) {
            return false;
        }

        int lon = dna.length;
        if (lon == 0) {
            return false;
        }
        for (String sequence : dna) {
            if (sequence == null || sequence.length() != lon) {
                return false;
            }
            for (char c : sequence.toCharArray()) {
                if (ValidCharacters.indexOf(c) == -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
