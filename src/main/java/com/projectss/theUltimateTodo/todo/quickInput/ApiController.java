package com.projectss.theUltimateTodo.todo.quickInput;

import com.projectss.theUltimateTodo.OAuth.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/input")
public class ApiController {
    private final ApiService apiService;

    @GetMapping
    public ResponseEntity<String> getApiMethod(@AuthenticationPrincipal SecurityUser securityUser,
                                               @RequestBody String input) {
        String email = securityUser.getUsername();

        apiService.getApiMethod(email, input);

        return ResponseEntity.ok().body("created");
    }
}
