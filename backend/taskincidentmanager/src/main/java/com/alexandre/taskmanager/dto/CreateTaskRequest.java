package com.alexandre.taskmanager.dto;

import com.alexandre.taskmanager.entity.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull
    private TaskPriority priority;

    @NotNull(message = "User ID is required")
    @NonNull
    private Long userId;
}
