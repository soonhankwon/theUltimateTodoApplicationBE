package com.projectss.theUltimateTodo.todo.service;

import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.dto.TodoDTO;
import com.projectss.theUltimateTodo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public Todo add(TodoDTO todoDTO) {
        /*if (todoRepository.existsById(todoDTO.id())) {
            throw new DuplicateKeyException("이미 존재하는 Todo 입니다.");
        }*/

        Todo todo = new Todo();
        todo.add(todoDTO);
        todoRepository.save(todo);

        return todo;
    }

    public Todo searchById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Todo> searchAll() {
        return todoRepository.findAll();
    }

    public List<Todo> searchInProgress() {
        LocalDate currentDate = LocalDate.now();

        return todoRepository.findTodosInProgress(currentDate);
    }

    @Transactional
    public Todo update(TodoService service, TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(todoDTO.id())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 todo 입니다."));

        todo.update(service, todoDTO);

        return todo;
    }

    @Transactional
    public void delete(Long id) {
        this.todoRepository.deleteById(id);
    }
}
