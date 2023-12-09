package ru.moscow.profile.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Email(message = "Not correct e-mail")
    private String email;

    @Pattern(regexp = "^7\\d{10}$", message = "Not correct phone")
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String placeOfBirth;

    @Pattern(regexp = "^\\d{4} \\d{7}$", message = "Not correct passport number")
    private String passportNumber;

    private String registrationAddress;
    private String actualAddress;
}
