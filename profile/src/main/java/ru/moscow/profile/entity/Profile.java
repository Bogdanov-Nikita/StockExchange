package ru.moscow.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Учетная запись клиента
 */
@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile")
public class Profile {

    /**
     * Идентификатор клиента
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * Идентификатор клиента в банке
     */
    @Column(name = "bank_id", nullable = false)
    private UUID bankId;

    /**
     * Имя
     */
    @Column(name = "name")
    private String name;

    /**
     * Фамилия
     */
    @Column(name = "family_name")
    private String familyName;

    /**
     * Отчество
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * e-mail
     */
    private String email;

    /**
     * Телефон(в формате 7ХХХХХХХХХХ)
     */
    String phone;

    /**
     * Дата рождения
     */
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * Место рождения
     */
    @Column(name = "place_of_birth")
    private String placeOfBirth;

    /**
     * Номер паспорта(вместе с серией в формате ХХХХ ХХХХХХ)
     */
    @Column(name = "passport_number")
    private String passportNumber;

    /**
     * Адрес регистрации
     */
    @Column(name = "registration_address")
    private String registrationAddress;

    /**
     * Адрес проживания
     */
    @Column(name = "actual_address")
    private String actualAddress;

}
