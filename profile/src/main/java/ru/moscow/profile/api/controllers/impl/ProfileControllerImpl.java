package ru.moscow.profile.api.controllers.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.RestController;
import ru.moscow.profile.api.controllers.ProfileController;
import ru.moscow.profile.dto.ProfileCreateRequest;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.dto.ProfileSearchRequest;
import ru.moscow.profile.exceptions.ApplicationValidatorException;
import ru.moscow.profile.services.ProfileService;
import ru.moscow.profile.validators.ApplicationValidator;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class ProfileControllerImpl implements ProfileController {

    private final ProfileService service;

    private final Map<String, ApplicationValidator> validatorMap;

    /**
     * Контроллер учётной записи с инициализацией всех необходимых ApplicationValidator
     *
     * @param service      реализация контроллера
     * @param validatorMap промаркированный список ApplicationValidator
     *                     по типу источника данных для создания записи
     */
    public ProfileControllerImpl(
        @Qualifier("profileServiceImpl") ProfileService service,
        @Qualifier("initApplicationValidators") Map<String, ApplicationValidator> validatorMap
    ) {
        this.service = service;
        this.validatorMap = validatorMap;
    }

    @Override
    public ResponseEntity<ProfileResponse> create(String source, ProfileCreateRequest request) {

        //В случае если у нас нет такого source типа то отправляем ошибку.
        var validator = Optional.of(validatorMap.get(source))
            .orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_IMPLEMENTED));

        var errors = validator.validateObject(request);

        if (errors.hasErrors()) {
            throw new ApplicationValidatorException(HttpStatus.BAD_REQUEST, errors);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(service.create(request));
        }
    }

    @Override
    public ProfileResponse getById(UUID id) {
        return service.getById(id);
    }

    @Override
    public Page<ProfileResponse> find(
        String name,
        String familyName,
        String middleName,
        String email,
        String phone,
        Pageable pageable) {

        var searchRequest = new ProfileSearchRequest(
            name,
            familyName,
            middleName,
            email,
            phone
        );
        return service.find(searchRequest, pageable);
    }
}
