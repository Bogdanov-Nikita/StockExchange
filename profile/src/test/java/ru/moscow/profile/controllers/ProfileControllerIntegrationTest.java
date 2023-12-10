package ru.moscow.profile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.moscow.profile.dto.ProfileCreateRequest;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
public class ProfileControllerIntegrationTest {

    @Container
    public static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createAndSearchMethod() throws Exception {

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

        //create
        mockMvc.perform(post("/v1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", "mail")
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(jsonPath("$.name").value(request.getName()))
            .andExpect(status().isCreated())
            .andReturn();

        //search
        mockMvc.perform(get("/v1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "Пётр"))
            .andExpect(jsonPath("$.content[0].email").value(request.getEmail()))
            .andExpect(status().isOk())
            .andReturn();

    }

}
