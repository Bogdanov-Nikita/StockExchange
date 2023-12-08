package ru.moscow.profile.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ProfileResponse(
    UUID id,
    UUID bankId,
    String name,
    String familyName,
    String middleName,
    String email,
    String phone,
    LocalDate dateOfBirth,
    String placeOfBirth,
    String passportNumber,
    String registrationAddress,
    String actualAddress
) {
}
