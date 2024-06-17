package bg.mck.usercommandservice.application.validation;

import bg.mck.usercommandservice.application.service.UserCommandService;
import bg.mck.usercommandservice.application.validation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserCommandService userCommandService;

    public UniqueEmailValidator(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return this.userCommandService.findUserByEmail(value) == null;
    }
}
