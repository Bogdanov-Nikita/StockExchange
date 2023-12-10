package ru.moscow.profile.mapper;

import org.junit.jupiter.api.Test;
import ru.moscow.profile.dto.ProfileCreateRequest;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.entity.Profile;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileMapperTest {

    @Test
    void testToProfileResponse() {
        // инициализация Profile
        Profile profile = new Profile();
        profile.setId(UUID.randomUUID());
        profile.setBankId(UUID.randomUUID());
        profile.setName("Tester");
        profile.setFamilyName("TestSurName");
        profile.setMiddleName("TestMiddleName");
        profile.setDateOfBirth(LocalDate.of(1990, 1, 1));
        profile.setPlaceOfBirth("TestPlace");
        profile.setPassportNumber("1234 567890");
        profile.setRegistrationAddress("TestRegAddress");
        profile.setActualAddress("TestActualAddress");

        // маппинг
        ProfileResponse response = ProfileMapper.MAPPER.toProfileResponse(profile);

        // Проверка
        assertEquals(profile.getId(), response.id());
        assertEquals(profile.getBankId(), response.bankId());
        assertEquals(profile.getName(), response.name());
        assertEquals(profile.getFamilyName(), response.familyName());
        assertEquals(profile.getMiddleName(), response.middleName());
        assertEquals(profile.getDateOfBirth(), response.dateOfBirth());
        assertEquals(profile.getPlaceOfBirth(), response.placeOfBirth());
        assertEquals(profile.getPassportNumber(), response.passportNumber());
        assertEquals(profile.getRegistrationAddress(), response.registrationAddress());
        assertEquals(profile.getActualAddress(), response.actualAddress());
    }

    @Test
    void testFromProfileCreateRequest() {
        // инициализация ProfileCreateRequest DTO
        ProfileCreateRequest request = new ProfileCreateRequest();
        request.setEmail("testEmail@test.com");
        request.setPhone("1234567890");
        request.setPassportNumber("12345678");

        // маппинг
        Profile profile = ProfileMapper.MAPPER.fromProfileCreateRequest(request);

        // Проверка
        assertEquals(request.getEmail(), profile.getEmail());
        assertEquals(request.getPhone(), profile.getPhone());
        assertEquals(request.getPassportNumber(), profile.getPassportNumber());
    }

}