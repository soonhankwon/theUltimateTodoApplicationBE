package com.projectss.theUltimateTodo.todo.controller;

import com.projectss.theUltimateTodo.OAuth.SecurityUser;
import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.dto.TodoDTO;
import com.projectss.theUltimateTodo.todo.service.TodoService;
import com.projectss.theUltimateTodo.todo.service.TodoStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;
    private final TodoStoreService todoStoreService;

    @PostMapping
    public ResponseEntity<String> create(@AuthenticationPrincipal SecurityUser securityUser,
                                         @RequestBody TodoDTO todoDTO) {
        String email = securityUser.getUsername();

        todoService.add(email, todoDTO);

        return ResponseEntity.ok().body("created");
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoById(@AuthenticationPrincipal SecurityUser securityUser,
                                            @PathVariable String todoId) {
        String email = securityUser.getUsername();

        Todo todoById = todoService.getTodoById(email, todoId);

        return ResponseEntity.ok().body(todoById);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodosById(@AuthenticationPrincipal SecurityUser securityUser) {
        String email = securityUser.getUsername();

        List<Todo> todosById = todoService.getTodosById(email);

        return ResponseEntity.ok().body(todosById);
    }

    @GetMapping("/in-progress")
    public ResponseEntity<List<Todo>> getTodoInProgress(@AuthenticationPrincipal SecurityUser securityUser) {
        String email = securityUser.getUsername();

        List<Todo> todoInProgress = todoStoreService.getTodoInProgress(email);

        return ResponseEntity.ok().body(todoInProgress);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<String> updateTodo(@AuthenticationPrincipal SecurityUser securityUser,
                                             @PathVariable String todoId,
                                             @RequestBody TodoDTO todoDTO) {
        String email = securityUser.getUsername();

        todoService.updateTodo(email, todoId, todoDTO);

        return ResponseEntity.ok().body("updated");
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@AuthenticationPrincipal SecurityUser securityUser,
                                             @PathVariable String todoId) {
        String email = securityUser.getUsername();

        todoService.delete(email, todoId);

        return ResponseEntity.ok().body("deleted");
    }
}
