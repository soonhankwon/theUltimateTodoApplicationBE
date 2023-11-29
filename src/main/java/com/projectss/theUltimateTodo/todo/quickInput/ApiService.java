package com.projectss.theUltimateTodo.todo.quickInput;

import com.projectss.theUltimateTodo.user.domain.User;
import com.projectss.theUltimateTodo.user.repository.UserRepository;
import com.projectss.theUltimateTodo.todo.domain.Todo;
import com.projectss.theUltimateTodo.todo.quickInput.dto.ApiResponse;
import com.projectss.theUltimateTodo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ApiClient apiClient;

    @Transactional
    public void createQuickMemo(String email, String input) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() -> new IllegalStateException("no user by email"));

        ApiResponse response = apiClient.getQuickInputByGpt(input);
        Todo todo = ApiResponse.ofTodo(response, user);
        todoRepository.save(todo);
    }
}
