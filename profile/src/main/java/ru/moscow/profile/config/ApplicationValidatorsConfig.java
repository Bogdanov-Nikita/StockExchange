package ru.moscow.profile.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.moscow.profile.exceptions.ApplicationValidationErrorResponse;
import ru.moscow.profile.exceptions.ApplicationValidatorException;
import ru.moscow.profile.exceptions.DuplicateApplicationValidatorKeyException;
import ru.moscow.profile.validators.ApplicationValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@RestControllerAdvice
public class ApplicationValidatorsConfig {

    /**
     * Размечаем в Map все ApplicationValidator для нахождения подходящего
     *
     * @param validators список ApplicationValidator собранный контекстом spring.
     * @return validator список всех ApplicationValidator для проверки входных параметров для создания учётной записи
     * <p>
     * Пояснение: собираем существующий список ApplicationValidator через ApplicationContext и маркируем названиями
     * нужными нам штатный вариант Map нам не подходит
     */
    @Bean
    public Map<String, ApplicationValidator> initApplicationValidators(List<ApplicationValidator> validators) {
        var result = new HashMap<String, ApplicationValidator>();

        validators.forEach(validator -> {
            //ApplicationValidator не должны иметь одинаковые ключи.
            //Каждому ApplicationValidator должен один к одному соответствовать элемент из перечисления.
            if (result.containsKey(validator.getType().getTitle())) {
                throw new DuplicateApplicationValidatorKeyException();
            } else {
                result.put(validator.getType().getTitle(), validator);
            }
        });
        return result;
    }

    /**
     * @param exception ошибка валидации
     * @param request   запрос
     * @param response  ответ
     * @return кастомный ответ
     */
    @ExceptionHandler(ApplicationValidatorException.class)
    public ApplicationValidationErrorResponse applicationValidationPassHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        ApplicationValidatorException exception
    ) {
        var error = new ApplicationValidationErrorResponse(exception, request.getContextPath());
        log.error("Error path: " + request.getContextPath() + "\n Exception: " + exception);
        return error;
    }

}
