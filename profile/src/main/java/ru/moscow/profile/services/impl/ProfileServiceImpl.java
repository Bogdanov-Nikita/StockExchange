package ru.moscow.profile.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.moscow.profile.dto.ProfileCreateRequest;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.dto.ProfileSearchRequest;
import ru.moscow.profile.mapper.ProfileMapper;
import ru.moscow.profile.repositories.ProfileRepository;
import ru.moscow.profile.repositories.ProfileSpecifications;
import ru.moscow.profile.services.ProfileService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;

    @Override
    public ProfileResponse create(ProfileCreateRequest request) {
        return ProfileMapper.MAPPER
            .toProfileResponse(repository.save(ProfileMapper.MAPPER.fromProfileCreateRequest(request)));
    }

    @Override
    public ProfileResponse getById(UUID id) {
        return ProfileMapper.MAPPER.toProfileResponse(
            repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public Page<ProfileResponse> find(ProfileSearchRequest request, Pageable pageable) {
        if ((request != null) && !request.isEmpty()) {
            return repository.findAll(ProfileSpecifications.searchRequest(request), pageable)
                .map(ProfileMapper.MAPPER::toProfileResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
