package ru.moscow.profile.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/profile")
public interface ProfileController {

    /**
     * Создание учетной записи
     *
     * @param request данные для учётной записи
     * @return созданная запись в логе
     */
    void create();

    /**
     * Поиск по ID события
     *
     * @param actionEventId идентификатор события
     * @return запись в логе
     */
    @GetMapping(path = "/{actionEventId}")
    void getById(@PathVariable UUID actionEventId);
    /**
     * Поиск по полям
     *
     * @param dateStart      Дата с
     * @param dateEnd        Дата по
     * @param userLogin      Логин
     * @param description    Описание объекта
     * @param entityName     Объект
     * @param actionType     Тип события (в UI - Тип действия)
     * @param actionLevel    Уровень приложения в котором сработало событие
     * @param clerkName      Сотрудник
     * @param organizationId Организация
     * @param region         Регион
     * @param jobPosition    Должность
     * @param email          Электронная почта
     * @param pageable       пагинация и сортировка
     * @return список найденных записей в логе
     */
    void find();

}
