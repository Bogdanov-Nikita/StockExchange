package ru.moscow.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Тип приложения из которого приходят данные клиента
 * <p>
 * Предупреждение: для каждого типа должна быть только одна активная реализация.
 */
@Getter
@AllArgsConstructor
public enum ApplicationType {
    MAIL("mail"),
    MOBILE("mobile"),
    BANK("bank"),
    GOSUSLUGI("gosuslugi");

    /**
     * Название http-заголовка "x-Source" для типа приложения
     */
    private final String title;

}
