package ru.moscow.profile.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.dto.ProfileSearchRequest;

import java.util.UUID;

@Service
public interface ProfileService {

    /**
     * Поиск по ID учётной записи
     *
     * @param id идентификатор учётной записи
     * @return учётная запись
     */
    ProfileResponse getById(UUID id);

    /**
     * Поиск по полям
     *
     * @param request  критерии поиска
     * @param pageable пагинация и сортировка
     * @return список найденных учётных запись
     */
    Page<ProfileResponse> find(ProfileSearchRequest request, Pageable pageable);

}
