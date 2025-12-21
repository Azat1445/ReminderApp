package org.example.reminderapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.reminderapp.dto.ReminderCreateDto;
import org.example.reminderapp.dto.ReminderFilterDto;
import org.example.reminderapp.dto.ReminderResponseDto;
import org.example.reminderapp.service.ReminderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReminderController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ReminderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReminderService reminderService;

    @Test
    void createReminder_shouldReturn201() throws Exception {
        ReminderCreateDto createDto = new ReminderCreateDto();
        createDto.setTitle("test");
        createDto.setDescription("desc");
        createDto.setRemindAt(OffsetDateTime.now());
        createDto.setUserId(1L);

        ReminderResponseDto responseDto = new ReminderResponseDto();
        responseDto.setId(5L);
        responseDto.setTitle("test");

        when(reminderService.create(any(ReminderCreateDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/reminders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.title").value("test"));
    }

    @Test
    void findAllReminders() throws Exception {
        ReminderResponseDto responseDto = new ReminderResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("test");

        when(reminderService.findAll(any(ReminderFilterDto.class),
                any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(responseDto)));

        mockMvc.perform(get("/api/reminders")
                        .param("title", "test")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].title").value("test"));
    }
}
