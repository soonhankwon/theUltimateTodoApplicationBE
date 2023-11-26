package com.projectss.theUltimateTodo.todo.service;

import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.domain.TodoStore;
import com.projectss.theUltimateTodo.todo.dto.TodoDTO;
import com.projectss.theUltimateTodo.todo.repository.TodoRepository;
import com.projectss.theUltimateTodo.todo.repository.TodoStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoStoreRepository todoStoreRepository;
    private final TodoStoreService todoStoreService;

    @Transactional
    public void add(String email, TodoDTO todoDTO) {
        TodoStore todoStore = todoStoreRepository.findTodoStoreByEmail(email)
                .orElseThrow();

        Todo todo = new Todo();
        todo.add(todoDTO);
        todoRepository.save(todo);
        todoStore.saveTodo(todo);
        todoStoreRepository.save(todoStore);
    }

    public Todo getTodoById(String email, String todoId) {
        TodoStore todoStore = todoStoreService.getTodoStoreByUser(email);

        return todoStore.getTodoById(todoId);
    }

    public List<Todo> getTodosById(String email) {
        TodoStore todoStore = todoStoreService.getTodoStoreByUser(email);

        Optional<List<Todo>> optionalTodos = todoStore.getAllTodos();
        List<Todo> todos;

        if (optionalTodos.isPresent()) {
            todos = optionalTodos.get();
        } else {
            throw new IllegalStateException("Todos not found");
        }

        return todos;
    }

    @Transactional
    public void updateTodo(String email, String todoId, TodoDTO todoDTO) {
        if (!todoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no todo store by user id");
        }
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalStateException("no todo by todo id"));
        todo.update(todoDTO);
        todoRepository.save(todo);
    }

    @Transactional
    public void delete(String email, String todoId) {
        if (!todoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no todo store by user id");
        }
        todoRepository.deleteById(todoId);
    }
}
