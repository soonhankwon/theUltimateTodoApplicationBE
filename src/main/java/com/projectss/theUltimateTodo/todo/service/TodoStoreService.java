package com.projectss.theUltimateTodo.todo.service;

import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.domain.TodoStore;
import com.projectss.theUltimateTodo.todo.repository.TodoStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoStoreService {
    private final TodoStoreRepository todoStoreRepository;

    public void createTodoStoreByUser(String email) {
        if (!todoStoreRepository.existsByEmail(email)) {
            TodoStore todoStore = new TodoStore(email);
            todoStoreRepository.save(todoStore);
        }
    }

    public TodoStore getTodoStoreByUser(String email) {
        return todoStoreRepository.findTodoStoreByEmail(email)
                .orElseThrow(() -> new IllegalStateException("no todo store by user id"));
    }

    public List<Todo> getTodoInProgress(String email) {
        TodoStore todoStore = getTodoStoreByUser(email);

        return todoStore.getTodosBetweenDates();
    }
}
