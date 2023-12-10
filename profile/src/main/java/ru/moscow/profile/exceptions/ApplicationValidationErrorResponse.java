package ru.moscow.profile.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public final class ApplicationValidationErrorResponse {
    private final LocalDateTime timestamp;
    private final Integer status;
    private final String error;
    private final String massage;
    private final String path;

    /**
     * @param exception информация об ошибке валидации
     * @param path      путь url
     */
    public ApplicationValidationErrorResponse(ApplicationValidatorException exception, String path) {
        this.timestamp = exception.getTimestamp();
        this.status = exception.getStatus();
        this.error = exception.getError();
        this.massage = exception.getMassage();
        this.path = path;
    }
}
