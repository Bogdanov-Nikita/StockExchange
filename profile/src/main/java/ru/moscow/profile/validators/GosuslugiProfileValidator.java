package ru.moscow.profile.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.moscow.profile.dto.ApplicationType;
import ru.moscow.profile.dto.ProfileCreateRequest;

/**
 * Проверка условий для создания профиля из банковского приложения
 */
@Component
public class GosuslugiProfileValidator implements ApplicationValidator {

    @Override
    public void validate(Object target, Errors errors) {
        var request = (ProfileCreateRequest) target;

        if (request.getBankId() == null) {
            errors.rejectValue("bankId", "bankId.blank", "Bank id is blank");
        }

        if (request.getPhone() == null || request.getPhone().isBlank()) {
            errors.rejectValue("phone", "phone.blank", "Phone is blank");
        }

        if (request.getName() == null || request.getName().isBlank()) {
            errors.rejectValue("name", "name.blank", "Name is blank");
        }

        if (request.getFamilyName() == null || request.getFamilyName().isBlank()) {
            errors.rejectValue("familyName", "familyName.blank", "Family name is blank");
        }

        if (request.getMiddleName() == null || request.getMiddleName().isBlank()) {
            errors.rejectValue("middleName", "middleName.blank", "Middle name is blank");
        }

        if (request.getDateOfBirth() == null) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.blank", "Date of birth is blank");
        }

        if (request.getPlaceOfBirth() == null) {
            errors.rejectValue("placeOfBirth", "placeOfBirth.blank", "Place of birth is blank");
        }

        if (request.getPassportNumber() == null || request.getPassportNumber().isBlank()) {
            errors.rejectValue("passportNumber", "passportNumber.blank", "Passport number is blank");
        }

        if (request.getRegistrationAddress() == null || request.getRegistrationAddress().isBlank()) {
            errors.rejectValue("registrationAddress", "registrationAddress.blank", "Registration address is blank");
        }

    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.GOSUSLUGI;
    }
}
