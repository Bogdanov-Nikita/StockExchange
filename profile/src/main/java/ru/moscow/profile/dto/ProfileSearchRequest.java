package ru.moscow.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DTO для поисковых запросов
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProfileSearchRequest {
    /**
     * Имя
     */
    private String name;

    /**
     * Фамилия
     */
    private String familyName;
    /**
     * Отчество
     */
    private String middleName;

    /**
     * e-mail
     */
    private String email;

    /**
     * Телефон(в формате 7ХХХХХХХХХХ)
     */
    private String phone;

    /**
     * @return Истина если есть не пустой хотя бы один из критериев поиска
     */
    public boolean isEmpty() {
        var name = ((this.name == null) || this.name.isEmpty());
        var familyName = ((this.familyName == null) || this.familyName.isEmpty());
        var middleName = ((this.middleName == null) || this.middleName.isEmpty());
        var email = ((this.email == null) || this.email.isEmpty());
        var phone = ((this.phone == null) || this.phone.isEmpty());

        return name && familyName && middleName && email && phone;
    }

}
