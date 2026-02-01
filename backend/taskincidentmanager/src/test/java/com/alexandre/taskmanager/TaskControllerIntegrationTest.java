package com.alexandre.taskmanager;

import com.alexandre.taskmanager.entity.User;
import com.alexandre.taskmanager.repository.TaskRepository;
import com.alexandre.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    private User testUser;

    @BeforeEach
    void setup() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
        testUser = new User();
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        userRepository.save(testUser);
    }

    @Test
    @DisplayName("POST /api/tasks/{userId} - should create task and return 200 with details")
    void createTask_shouldReturn200AndTaskDetails() throws Exception {
        String json = "{" +
                "\"title\": \"Test Task\"," +
                "\"description\": \"desc\"," +
                "\"priority\": \"HIGH\"}";

        MvcResult result = mockMvc.perform(post("/api/tasks/" + testUser.getId())
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response).contains("Test Task");
        assertThat(response).contains("HIGH");
        assertThat(response).contains("OPEN");
        assertThat(result.getResponse().getStatus())
                .withFailMessage("Expected 200 OK but got %s. Response: %s", result.getResponse().getStatus(), response)
                .isEqualTo(200);
        
        // Verify the task was saved with all required fields
        assertThat(response).contains("\"title\":\"Test Task\"");
        assertThat(response).contains("\"priority\":\"HIGH\"");
        assertThat(response).contains("\"status\":\"OPEN\"");
        assertThat(response).contains("\"createdAt\"");
    }
}
