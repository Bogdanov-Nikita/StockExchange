package ru.moscow.profile.api.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.moscow.profile.dto.ProfileCreateRequest;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.validators.ValidatorConstants;

import java.util.UUID;

/**
 * Контроллер для работы с учётной записью клиента
 */
@Validated
@RestController
@RequestMapping("/v1/profile")
public interface ProfileController {

    /**
     * Создание учётной записи
     *
     * @param source  тип источника данных для создания записи
     * @param request данные для учётной записи
     * @return созданная запись в логе
     */
    @PostMapping
    ResponseEntity<ProfileResponse> create(
        @RequestHeader("x-Source") String source,
        @Valid @RequestBody ProfileCreateRequest request
    );

    /**
     * Поиск по ID учётной записи
     *
     * @param id идентификатор учётной записи
     * @return учётная запись
     */
    @GetMapping(path = "/{id}")
    ProfileResponse getById(@PathVariable UUID id);

    /**
     * Поиск по полям
     *
     * @param familyName Фамилия
     * @param name       Имя
     * @param middleName Отчество
     * @param phone      Телефон (в формате 7ХХХХХХХХХХ)
     * @param email      Электронная почта
     * @param pageable   пагинация и сортировка
     * @return список найденных учётных записи
     */

    @GetMapping
    Page<ProfileResponse> find(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "family_name", required = false) String familyName,
        @RequestParam(value = "middle_name", required = false) String middleName,
        @RequestParam(value = "email", required = false) String email,
        @Pattern(
            regexp = ValidatorConstants.PhonePatternValidation.VALUE,
            message = ValidatorConstants.PhonePatternValidation.MESSAGE
        )
        @RequestParam(value = "phone", required = false) String phone,
        Pageable pageable
    );
}
