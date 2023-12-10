package ru.moscow.profile.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.moscow.profile.dto.ProfileCreateRequest;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.dto.ProfileSearchRequest;
import ru.moscow.profile.entity.Profile;
import ru.moscow.profile.mapper.ProfileMapper;
import ru.moscow.profile.repositories.ProfileRepository;
import ru.moscow.profile.services.impl.ProfileServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceImplTest {

    @Mock
    private ProfileRepository repository;

    @InjectMocks
    private ProfileServiceImpl service;

    @Test
    public void createTest() {
        var request = new ProfileCreateRequest();
        request.setBankId(UUID.randomUUID());
        request.setName("Пётр");
        request.setFamilyName("Петрович");
        request.setMiddleName("Петров");
        request.setEmail("test@gmail.com");
        request.setPhone("71234567890");
        request.setDateOfBirth(LocalDate.of(1990, 1, 1));
        request.setPlaceOfBirth("Москва");
        request.setPassportNumber("1234 1234567");
        request.setRegistrationAddress("registration");
        request.setActualAddress("actual");

        var profile = ProfileMapper.MAPPER.fromProfileCreateRequest(request);

        when(repository.save(any(Profile.class)))
            .thenReturn(profile);

        ProfileResponse response = service.create(request);

        verify(repository).save(any(Profile.class));

        assertNotNull(response);
        assertEquals(profile.getBankId(), response.bankId());
    }

    @Test
    public void getByIdTest() {
        UUID id = UUID.randomUUID();
        var profile = new Profile();
        profile.setId(id);

        when(repository.findById(eq(id))).thenReturn(Optional.of(profile));

        ProfileResponse response = service.getById(id);

        verify(repository).findById(eq(id));

        assertNotNull(response);
        assertEquals(profile.getId(), response.id());
    }

    @Test
    public void findTest() {
        ProfileSearchRequest request = new ProfileSearchRequest(
            "Пётр",
            "Петрович",
            "Петров",
            "test@gmail.com",
            "71234567890"
        );

        Pageable pageable = Pageable.unpaged();

        when(repository.findAll(any(Specification.class), eq(pageable)))
            .thenReturn(new PageImpl<>(List.of(new Profile())));

        Page<ProfileResponse> response = service.find(request, pageable);

        verify(repository).findAll(any(Specification.class), eq(pageable));
        assertNotNull(response);
    }
}
