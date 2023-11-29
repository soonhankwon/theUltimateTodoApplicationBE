package com.projectss.theUltimateTodo.todo.quickInput.dto;

import com.projectss.theUltimateTodo.user.domain.User;
import com.projectss.theUltimateTodo.todo.domain.Todo;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ApiResponse(
        @NotNull
        LocalDate date,
        @NotNull
        String content
) {
    public static Todo ofTodo(ApiResponse response, User user) {
        return new Todo(response, user);
    }
}
