package com.projectss.theUltimateTodo.todo.dto;

import com.projectss.theUltimateTodo.todo.domain.TodoStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ToDoRequest(
        @NotBlank
        String content,
        TodoStatus status,
        LocalDateTime start,
        LocalDateTime end
) {
}
