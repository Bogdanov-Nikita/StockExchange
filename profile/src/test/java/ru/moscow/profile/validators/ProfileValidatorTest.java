package ru.moscow.profile.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ru.moscow.profile.dto.ProfileCreateRequest;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileValidatorTest {

    ProfileCreateRequest profileGood, profileAllBad;

    @BeforeEach
    void initProfile() {
        profileGood = new ProfileCreateRequest();

        profileGood.setBankId(UUID.randomUUID());
        profileGood.setName("Пётр");
        profileGood.setFamilyName("Петрович");
        profileGood.setMiddleName("Петров");
        profileGood.setEmail("test@gmail.com");
        profileGood.setPhone("71234567890");
        profileGood.setDateOfBirth(LocalDate.of(1990, 1, 1));
        profileGood.setPlaceOfBirth("Москва");
        profileGood.setPassportNumber("1234 1234567");
        profileGood.setRegistrationAddress("registration");
        profileGood.setActualAddress("actual");

        profileAllBad = new ProfileCreateRequest();
    }

    static class ValidatiorArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                Arguments.of(new BankProfileValidator(),
                    new String[]{"bankId", "name", "familyName", "middleName", "dateOfBirth", "passportNumber"}),
                Arguments.of(new GosuslugiProfileValidator(),
                    new String[]{"bankId", "name", "familyName", "middleName", "phone", "dateOfBirth", "placeOfBirth", "passportNumber", "registrationAddress"}),
                Arguments.of(new MailProfileValidator(),
                    new String[]{"name", "email"}),
                Arguments.of(new MobileProfileValidator(),
                    new String[]{"phone"})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ValidatiorArgumentsProvider.class)
    public void validValuesTest(ApplicationValidator validator, String[] fields) {
        Errors errors = new BeanPropertyBindingResult(profileGood, "profileCreateRequest");

        validator.validate(profileGood, errors);

        assertFalse(errors.hasErrors());
        assertEquals(0, errors.getErrorCount());

        for (String field : fields) {
            assertNull(errors.getFieldError(field));
        }

    }

    @ParameterizedTest
    @ArgumentsSource(ValidatiorArgumentsProvider.class)
    public void notValidValuesTest(ApplicationValidator validator, String[] fields) {
        Errors errors = new BeanPropertyBindingResult(profileAllBad, "profileCreateRequest");

        validator.validate(profileAllBad, errors);

        assertTrue(errors.hasErrors());
        assertEquals(fields.length, errors.getErrorCount());

        for (String field : fields) {
            assertNotNull(errors.getFieldError(field));
        }
    }

}