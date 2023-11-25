package com.projectss.theUltimateTodo.todo.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class TodoStore {
    @Id
    private String id;
    @Indexed
    private String email;
    private List<Todo> todos = new ArrayList<>();

    public TodoStore(String email) {
        this.email = email;
    }

    public void saveTodo(Todo todo) {
        this.todos.add(todo);
    }

    public Todo getTodoById(String todoId) {
        return this.todos.stream()
                .filter(todo -> todo.getId().equals(todoId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("no todo by user id"));
    }

    public Optional<List<Todo>> getAllTodos() {
        List<Todo> allTodos = this.todos;
        return Optional.ofNullable(allTodos);
    }

    public List<Todo> getTodosBetweenDates() {
        LocalDate today = LocalDate.now();
        return this.todos.stream()
                .filter(todo -> todo.getStartDate() != null && todo.getEndDate() != null)
                .filter(todo -> (todo.getStartDate().isBefore(today) || todo.getStartDate().isEqual(today))
                        && (todo.getEndDate().isAfter(today) || todo.getEndDate().isEqual(today)))
                .collect(Collectors.toList());
    }
}
