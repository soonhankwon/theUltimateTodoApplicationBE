package com.projectss.theUltimateTodo.todo.controller;

import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.dto.TodoDTO;
import com.projectss.theUltimateTodo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/create")
    public Todo create(@RequestBody TodoDTO todoDTO) {

        return todoService.add(todoDTO);
    }

    @GetMapping("/{read}")
    public Todo readById(@PathVariable("read") Long id) {

        return todoService.searchById(id);
    }

    @GetMapping("/readAll")
    public List<Todo> readAll() {

        return todoService.searchAll();
    }

    @GetMapping("/readInProgress")
    public List<Todo> readInProgress() {

        return todoService.searchInProgress();
    }

    @PatchMapping("/update")
    public Todo update(@RequestBody TodoDTO todoDTO) {

        return todoService.update(todoService, todoDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Todo> delete(@PathVariable Long id) {

        this.todoService.delete(id);

        return ResponseEntity.ok().build();
    }
}
