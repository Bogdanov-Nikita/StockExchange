package ru.moscow.profile.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.moscow.profile.dto.ProfileSearchRequest;
import ru.moscow.profile.entity.Profile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProfileRepositoryTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void testSaveProfile() {
        // Create a new profile
        var profile = new Profile();
        profile.setName("Иван");
        profile.setFamilyName("Иванов");
        profile.setMiddleName("Иваныч");

        // Save the profile to the database
        var savedProfile = profileRepository.save(profile);

        // Verify that the profile is saved correctly
        Assertions.assertNotNull(savedProfile.getId());
        Assertions.assertEquals("Иван", savedProfile.getName());
        Assertions.assertEquals("Иванов", savedProfile.getFamilyName());
        Assertions.assertEquals("Иваныч", savedProfile.getMiddleName());
    }

    @Test
    public void testFindProfileById() {
        // Create a new profile
        var profile = new Profile();
        profile.setName("Пётр");
        profile.setFamilyName("Петров");
        profile.setMiddleName("Петрович");

        // Save the profile to the database
        var savedProfile = profileRepository.save(profile);

        // Find the profile by ID
        var foundProfile = profileRepository.findById(savedProfile.getId());

        // Verify that the profile is found correctly
        Assertions.assertTrue(foundProfile.isPresent());
        Assertions.assertEquals("Пётр", foundProfile.get().getName());
        Assertions.assertEquals("Петров", foundProfile.get().getFamilyName());
        Assertions.assertEquals("Петрович", foundProfile.get().getMiddleName());
    }

    /*@Test
    public void testFindProfileBySpecification() {
        // Create a new profile
        var profile = new Profile();
        profile.setName("Пётр");
        profile.setFamilyName("Петров");
        profile.setMiddleName("Петрович");
        var profile2 = new Profile();
        profile2.setName("Иван");
        profile2.setFamilyName("Иванов");
        profile2.setMiddleName("Иваныч");

        // Save the profile to the database
        var savedProfile = profileRepository.save(profile);
        //var savedProfile2 = profileRepository.save(profile2);

        // Find the profile by ID
        var foundProfile = profileRepository.findAll();//ProfileSpecifications.searchRequest(new ProfileSearchRequest().setName("Иван")));

        // Verify that the profile is found correctly
        Assertions.assertEquals(1,foundProfile.size());
        Assertions.assertEquals("Пётр", foundProfile.get(0).getName());
        Assertions.assertEquals("Петров", foundProfile.get(0).getFamilyName());
        Assertions.assertEquals("Петрович", foundProfile.get(0).getMiddleName());
    }*/


}


