package com.projectss.theUltimateTodo.todo.dto;

import com.projectss.theUltimateTodo.todo.domain.Todo;

import java.time.LocalDateTime;

public record ToDoResponse(
        Long id,
        String content,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ToDoResponse ofResponse(Todo todo) {
        return new ToDoResponse(
                todo.getId(),
                todo.getContent(),
                todo.getStartTime(),
                todo.getEndTime(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}
