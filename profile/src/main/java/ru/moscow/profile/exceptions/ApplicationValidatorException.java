package ru.moscow.profile.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

/**
 * Exception для ошибок валидации
 */
@Getter
public class ApplicationValidatorException extends RuntimeException {

    private final LocalDateTime timestamp;
    private final Integer status;
    private final String error;
    private final String massage;

    /**
     * @param status HTTP код
     * @param error  ошибка валидации
     */
    public ApplicationValidatorException(HttpStatus status, Errors error) {
        this.massage = (error.getFieldError() != null) ? error.getFieldError().getDefaultMessage() : null;
        this.timestamp = LocalDateTime.now();
        this.error = status.getReasonPhrase();
        this.status = status.value();
    }

}
