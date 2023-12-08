package ru.moscow.profile.api.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.moscow.profile.api.controllers.ProfileController;
import ru.moscow.profile.dto.ProfileCreateRequest;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.dto.ProfileSearchRequest;
import ru.moscow.profile.services.ProfileService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProfileControllerImpl implements ProfileController {

    private final ProfileService service;

    @Override
    public ResponseEntity<ProfileResponse> create(String source, ProfileCreateRequest request) {
        //service.
        return null;
    }

    @Override
    public ProfileResponse getById(UUID id) {
        return service.getById(id);
    }

    @Override
    public Page<ProfileResponse> find(
        String name,
        String familyName,
        String middleName,
        String email,
        String phone,
        Pageable pageable) {

        var searchRequest = new ProfileSearchRequest(
            name,
            familyName,
            middleName,
            email,
            phone
        );
        return service.find(searchRequest, pageable);
    }
}
