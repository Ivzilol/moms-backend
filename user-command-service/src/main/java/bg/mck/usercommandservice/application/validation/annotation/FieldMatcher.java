package bg.mck.usercommandservice.application.validation.annotation;

import bg.mck.usercommandservice.application.validation.FieldMatcherValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldMatcherValidator.class)
public @interface FieldMatcher {

    String first();

    String second();

    String message() default "Passwords should match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
