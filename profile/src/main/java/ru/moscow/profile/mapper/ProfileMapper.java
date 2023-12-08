package ru.moscow.profile.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.moscow.profile.dto.ProfileResponse;
import ru.moscow.profile.entity.Profile;

@Mapper
public interface ProfileMapper {
    ProfileMapper MAPPER = Mappers.getMapper(ProfileMapper.class);

    /**
     * Преобразование из сущности в DTO ответа
     *
     * @param profile - Учетная запись клиента
     * @return DTO для ответа
     */
    ProfileResponse toProfileResponse(Profile profile);

}
