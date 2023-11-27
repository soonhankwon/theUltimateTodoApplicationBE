package com.projectss.theUltimateTodo.todo.dto;

import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.domain.TodoStatus;

import java.time.LocalDateTime;

public record ToDoResponse(
        Long id,
        String content,
        LocalDateTime startTime,
        LocalDateTime endTime,
        TodoStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ToDoResponse ofResponse(Todo todo) {
        return new ToDoResponse(
                todo.getId(),
                todo.getContent(),
                todo.getStartTime(),
                todo.getEndTime(),
                todo.getStatus(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}
