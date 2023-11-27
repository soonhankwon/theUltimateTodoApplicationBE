package com.projectss.theUltimateTodo.todo.controller;

import com.projectss.theUltimateTodo.OAuth.SecurityUser;
import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.dto.ToDoRequest;
import com.projectss.theUltimateTodo.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
@Tag(name = "TO-DO API")
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "TO-DO 저장 API", responses = @ApiResponse(responseCode = "201"))
    @PostMapping
    public ResponseEntity<String> createToDo(@AuthenticationPrincipal SecurityUser securityUser,
                                             @RequestBody ToDoRequest request) {
        String email = securityUser.getUsername();
        todoService.createToDo(email, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @Operation(summary = "사용자 TO-DO 상세조회 API")
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoById(@AuthenticationPrincipal SecurityUser securityUser,
                                            @PathVariable Long todoId) {
        String email = securityUser.getUsername();
        Todo todoById = todoService.getTodoByIdAndUser(email, todoId);
        return ResponseEntity.ok().body(todoById);
    }

    @Operation(summary = "사용자 TO-DO 목록조회 API")
    @GetMapping
    public ResponseEntity<List<Todo>> getTodosByUser(@AuthenticationPrincipal SecurityUser securityUser) {
        String email = securityUser.getUsername();
        List<Todo> todosById = todoService.getTodosByUser(email);
        return ResponseEntity.ok().body(todosById);
    }

    @Operation(summary = "사용자 진행중 TO-DO 목록조회 API")
    @GetMapping("/in-progress")
    public ResponseEntity<List<Todo>> getTodoInProgressByUser(@AuthenticationPrincipal SecurityUser securityUser) {
        String email = securityUser.getUsername();
        List<Todo> todoInProgress = todoService.getTodoInProgress(email);
        return ResponseEntity.ok().body(todoInProgress);
    }

    @Operation(summary = "사용자 TO-DO 수정 API")
    @PatchMapping("/{todoId}")
    public ResponseEntity<String> updateTodoByUser(@AuthenticationPrincipal SecurityUser securityUser,
                                                   @PathVariable Long todoId,
                                                   @RequestBody ToDoRequest toDoRequest) {
        String email = securityUser.getUsername();
        todoService.updateTodoByUser(email, todoId, toDoRequest);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "사용자 TO-DO 삭제 API")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoByUser(@AuthenticationPrincipal SecurityUser securityUser,
                                                   @PathVariable Long todoId) {
        String email = securityUser.getUsername();
        todoService.deleteByUser(email, todoId);
        return ResponseEntity.ok().body("deleted");
    }
}
