package ru.moscow.profile.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.moscow.profile.dto.ApplicationType;
import ru.moscow.profile.dto.ProfileCreateRequest;


/**
 * Проверка условий для создания профиля из мобильного приложения
 */
@Component
public class MobileProfileValidator implements ApplicationValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileCreateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var request = (ProfileCreateRequest) target;

        if (request.getName().isBlank()) {
            errors.rejectValue("name", "name.blank", "Name is empty");
        }
        if (request.getPhone().isBlank()) {
            errors.rejectValue("phone", "phone.blank", "Phone is empty");
        }
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.MOBILE;
    }
}
