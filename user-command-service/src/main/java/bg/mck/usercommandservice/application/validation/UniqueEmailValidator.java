package bg.mck.usercommandservice.application.validation;

import bg.mck.usercommandservice.application.service.UserRegisterService;
import bg.mck.usercommandservice.application.validation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRegisterService userRegisterService;

    public UniqueEmailValidator(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return this.userRegisterService.findUserByEmail(value) == null;
    }
}
