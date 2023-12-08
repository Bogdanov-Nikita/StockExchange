package ru.moscow.profile.repositories;

import org.springframework.data.jpa.domain.Specification;
import ru.moscow.profile.dto.ProfileSearchRequest;
import ru.moscow.profile.entity.Profile;

import java.text.MessageFormat;

/**
 * Спецификация для генерации поискового запроса
 */
public final class ProfileSpecifications {

    /**
     * Динамический запрос для получения профиля пользователя
     * по различным поисковым критериям
     *
     * @param request критерии поиска
     * @return спецификация поискового запроса
     */
    public static Specification<Profile> searchRequest(ProfileSearchRequest request) {

        var name = (request.getName() != null) ? nameContains(request.getName()) : null;
        var familyName = (request.getFamilyName() != null) ? familyNameContains(request.getFamilyName()) : null;
        var middleName = (request.getMiddleName() != null) ? middleNameContains(request.getMiddleName()) : null;
        var email = (request.getEmail() != null) ? emailContains(request.getEmail()) : null;
        var phone = (request.getPhone() != null) ? phoneContains(request.getPhone()) : null;

        return Specification.where(name)
            .and(familyName)
            .and(middleName)
            .and(email)
            .and(phone);
    }

    private static Specification<Profile> phoneContains(String phone) {
        return (root, query, builder) -> builder.like(root.get("phone"), contains(phone));
    }

    private static Specification<Profile> emailContains(String email) {
        return (root, query, builder) -> builder.like(root.get("email"), contains(email));
    }

    private static Specification<Profile> middleNameContains(String middleName) {
        return (root, query, builder) -> builder.like(root.get("middleName"), contains(middleName));
    }

    private static Specification<Profile> familyNameContains(String familyName) {
        return (root, query, builder) -> builder.like(root.get("familyName"), contains(familyName));
    }

    private static Specification<Profile> nameContains(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), contains(name));
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }


}
