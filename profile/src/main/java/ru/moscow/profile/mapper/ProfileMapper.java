package ru.moscow.profile.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.moscow.profile.dto.ProfileCreateRequest;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.entity.Profile;

@Mapper
public interface ProfileMapper {
    ProfileMapper MAPPER = Mappers.getMapper(ProfileMapper.class);

    /**
     * Преобразование из сущности учётной записи в DTO для ответа
     *
     * @param profile - Учетная запись клиента
     * @return DTO для ответа
     */
    ProfileResponse toProfileResponse(Profile profile);

    /**
     * Преобразование из DTO запроса для создания учётной записи в сущность
     *
     * @param request DTO запроса
     * @return Учетная запись клиента
     */
    Profile fromProfileCreateRequest(ProfileCreateRequest request);

}
