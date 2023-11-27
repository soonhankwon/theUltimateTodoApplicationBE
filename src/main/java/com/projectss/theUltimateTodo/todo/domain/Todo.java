package com.projectss.theUltimateTodo.todo.domain;

import com.projectss.theUltimateTodo.OAuth.User;
import com.projectss.theUltimateTodo.todo.dto.ToDoRequest;
import com.projectss.theUltimateTodo.todo.quickInput.dto.ApiResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Enumerated(EnumType.STRING)
    private TodoStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(ToDoRequest request, User user) {
        this.startTime = request.start();
        this.endTime = request.end();
        this.content = request.content();
        this.status = TodoStatus.NOT_DONE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.user = user;
    }

    public Todo(ApiResponse response, User user) {
        if (ObjectUtils.isEmpty(response.date())) {
            this.defaultStartDateTime();
            this.defaultEndDateTime();
        } else {
            this.startTime = response.date().atStartOfDay();
            this.endTime = response.date().atTime(23, 59);
        }
        this.content = response.content();
        this.status = TodoStatus.NOT_DONE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.user = user;
    }

    public void defaultStartDateTime() {
        this.startTime = LocalDate.now().atStartOfDay();
    }

    public void defaultEndDateTime() {
        this.endTime = LocalDate.now().plusDays(1).atTime(0, 0);
    }

    public void update(ToDoRequest toDoRequest) {
        this.content = toDoRequest.content();
        this.status = toDoRequest.status();
        this.startTime = toDoRequest.start();
        this.endTime = toDoRequest.end();
        this.updatedAt = LocalDateTime.now();
    }
}
