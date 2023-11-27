package com.projectss.theUltimateTodo.todo.service;

import com.projectss.theUltimateTodo.OAuth.User;
import com.projectss.theUltimateTodo.OAuth.UserRepository;
import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.domain.TodoStatus;
import com.projectss.theUltimateTodo.todo.dto.ToDoRequest;
import com.projectss.theUltimateTodo.todo.dto.ToDoResponse;
import com.projectss.theUltimateTodo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createToDo(String email, ToDoRequest request) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() -> new IllegalStateException("no user by email"));

        Todo todo = new Todo(request, user);
        todoRepository.save(todo);
    }

    public ToDoResponse getTodoByIdAndUser(String email, Long todoId) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() -> new IllegalStateException("no user by email"));

        Todo toDo = todoRepository.findTodoByIdAndUser(todoId, user)
                .orElseThrow(() -> new IllegalArgumentException("no todo by id"));
        return ToDoResponse.ofResponse(toDo);
    }

    public List<ToDoResponse> getTodosByUser(String email) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() -> new IllegalStateException("no user by email"));

        return todoRepository.findAllByUser(user)
                .stream()
                .map(ToDoResponse::ofResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateTodoByUser(String email, Long todoId, ToDoRequest toDoRequest) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() -> new IllegalStateException("no user by email"));

        Todo todo = todoRepository.findTodoByIdAndUser(todoId, user)
                .orElseThrow(() -> new IllegalStateException("no todo by user"));

        todo.update(toDoRequest);
        todoRepository.save(todo);
    }

    @Transactional
    public void deleteByUser(String email, Long todoId) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() -> new IllegalStateException("no user by email"));

        Todo todo = todoRepository.findTodoByIdAndUser(todoId, user)
                .orElseThrow(() -> new IllegalStateException("no todo by user"));

        todoRepository.delete(todo);
    }

    public List<ToDoResponse> getTodoInProgress(String email) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() -> new IllegalStateException("no user by email"));

        return todoRepository.findAllByUserAndStatus(user, TodoStatus.NOT_DONE)
                .stream()
                .map(ToDoResponse::ofResponse)
                .collect(Collectors.toList());
    }
}
