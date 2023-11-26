package com.projectss.theUltimateTodo.todo.dto;

import com.projectss.theUltimateTodo.todo.domain.TodoStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TodoDTO(Long id,
                      String content,
                      TodoStatus status,
                      LocalDate startDate,
                      LocalDate endDate,
                      LocalTime startTime,
                      LocalTime endTime,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
}
