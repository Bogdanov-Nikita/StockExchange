package ru.moscow.profile.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.moscow.profile.validators.ValidatorConstants;

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

    @Email(message = ValidatorConstants.EmailValidation.MESSAGE)
    private String email;

    @Pattern(
        regexp = ValidatorConstants.PhonePatternValidation.VALUE,
        message = ValidatorConstants.PhonePatternValidation.MESSAGE
    )
    private String phone;

    private LocalDate dateOfBirth;
    private String placeOfBirth;

    @Pattern(
        regexp = ValidatorConstants.PassportPatternValidation.VALUE,
        message = ValidatorConstants.PassportPatternValidation.MESSAGE
    )
    private String passportNumber;

    private String registrationAddress;
    private String actualAddress;
}
