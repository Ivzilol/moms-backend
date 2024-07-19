package bg.mck.utils;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
public class ValidationUtil {


    private static Validator validator;

    public ValidationUtil(Validator validator) {
        ValidationUtil.validator = validator;
    }

    public static boolean isValid(Object entityDTO, String dtoName) throws  NoSuchMethodException, MethodArgumentNotValidException {
        BindingResult bindingResult = new BeanPropertyBindingResult(entityDTO, dtoName);
        validator.validate(entityDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            MethodParameter methodParameter = new MethodParameter(ValidationUtil.class.getMethod("isValid", Object.class, String.class), 0);
            throw new MethodArgumentNotValidException(methodParameter,bindingResult);
        } else {
            return true;
        }
    }
}
