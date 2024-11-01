package GSari.ParcialMutantes.validators;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Constraint(validatedBy = DnaValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidDna {
    String message() default "La secuencia de ADN es inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
