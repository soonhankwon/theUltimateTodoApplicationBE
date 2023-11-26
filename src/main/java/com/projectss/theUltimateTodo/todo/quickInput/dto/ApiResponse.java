package com.projectss.theUltimateTodo.todo.quickInput.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ApiResponse(
        @NotNull
        LocalDate date,
        @NotNull
        String content
) {
}
