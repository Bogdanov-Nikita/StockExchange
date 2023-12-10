package ru.moscow.profile.validators;

import org.springframework.validation.Validator;
import ru.moscow.profile.dto.ApplicationType;
import ru.moscow.profile.dto.ProfileCreateRequest;

/**
 * Обобщение для проверок
 * используемых для создания
 * учётной записи из разных приложений для каждого приложения своя реализация
 */
public interface ApplicationValidator extends Validator {

    @Override
    default boolean supports(Class<?> clazz) {
        return ProfileCreateRequest.class.equals(clazz);
    }

    /**
     * @return тип приложения
     * Предупреждение: для каждого типа должна быть только одна активная реализация,
     * иначе будет выброшено исключение DuplicateApplicationValidatorKeyException
     * @see ru.moscow.profile.exceptions.DuplicateApplicationValidatorKeyException
     */
    ApplicationType getType();
}
