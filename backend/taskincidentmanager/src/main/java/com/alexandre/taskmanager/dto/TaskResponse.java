package com.alexandre.taskmanager.dto;

import com.alexandre.taskmanager.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private String priority;
    private LocalDateTime createdAt;
    private Long userId;
    private String userName;
}
