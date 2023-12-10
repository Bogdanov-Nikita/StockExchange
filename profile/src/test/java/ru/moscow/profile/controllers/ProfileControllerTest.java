package ru.moscow.profile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.moscow.profile.api.controllers.impl.ProfileControllerImpl;
import ru.moscow.profile.dto.ProfileCreateRequest;
import ru.moscow.profile.dto.ProfileResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProfileControllerImpl service;

    @Test
    public void testCreateProfile() throws Exception {
        var profileCreateRequest = new ProfileCreateRequest();

        mockMvc.perform(post("/v1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-Source", "mail")
                .content(objectMapper.writeValueAsString(profileCreateRequest)))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void testGetById() throws Exception {
        var id = UUID.randomUUID();

        when(service.getById(eq(id))).thenReturn(any(ProfileResponse.class));

        mockMvc.perform(get("/v1/profile/" + id)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        verify(service).getById(eq(id));
    }

    @Test
    public void testFind() throws Exception {

        var request = new ProfileResponse(
            UUID.randomUUID(),
            UUID.randomUUID(),
            "Пётр",
            "Петрович",
            "Петров",
            "test@gmail.com",
            "71234567890",
            LocalDate.of(1990, 1, 1),
            "Москва",
            "1234 1234567",
            "registration",
            "actual");

        var responsePages = new PageImpl<>(List.of(request));

        when(service.find(
            anyString(),
            anyString(),
            anyString(),
            anyString(),
            anyString(),
            any(Pageable.class)))
            .thenReturn(responsePages);

        mockMvc.perform(get("/v1/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "Пётр"))
            .andExpect(status().isOk())
            .andReturn();

        verify(service).find(
            eq("Пётр"),
            isNull(),
            isNull(),
            isNull(),
            isNull(),
            any(Pageable.class));
    }
}
