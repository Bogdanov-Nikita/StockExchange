package ru.moscow.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProfileCreateRequest {
    private UUID bankId;
    private String name;
    private String familyName;
    private String middleName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String passportNumber;
    private String registrationAddress;
    private String actualAddress;
}
