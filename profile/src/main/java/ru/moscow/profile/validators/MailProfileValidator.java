package ru.moscow.profile.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.moscow.profile.dto.ApplicationType;
import ru.moscow.profile.dto.ProfileCreateRequest;

/**
 * Проверка условий для создания профиля из mail приложения
 */
@Component
public class MailProfileValidator implements ApplicationValidator {

    @Override
    public void validate(Object target, Errors errors) {
        var request = (ProfileCreateRequest) target;

        if (request.getName().isBlank()) {
            errors.rejectValue("name", "name.blank", "Name is empty");
        }
        if (request.getEmail().isBlank()) {
            errors.rejectValue("email", "email.blank", "E-mail is empty");
        }
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.MAIL;
    }
}
