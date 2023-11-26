package com.projectss.theUltimateTodo.todo.quickInput;

import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.domain.TodoStore;
import com.projectss.theUltimateTodo.todo.quickInput.dto.ApiResponse;
import com.projectss.theUltimateTodo.todo.repository.TodoRepository;
import com.projectss.theUltimateTodo.todo.repository.TodoStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final TodoStoreRepository todoStoreRepository;
    private final TodoRepository todoRepository;
    private final ApiClient apiClient;

    @Transactional
    public void getApiMethod(String email, String input) {
        TodoStore todoStore = todoStoreRepository.findTodoStoreByEmail(email)
                .orElseThrow();
        Todo todo = new Todo();

        ApiResponse response = apiClient.getApiMethod(input);
        todo.quickAdd(response);
        todoRepository.save(todo);
        todoStore.saveTodo(todo);
        todoStoreRepository.save(todoStore);
    }
}
