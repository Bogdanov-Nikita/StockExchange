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
    public void validate(Object target, Errors errors) {
        var request = (ProfileCreateRequest) target;

        if (request.getPhone() == null || request.getPhone().isBlank()) {
            errors.rejectValue("phone", "phone.blank", "Phone is blank");
        }
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.MOBILE;
    }
}
