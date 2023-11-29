package com.projectss.theUltimateTodo.todo.repository;

import com.projectss.theUltimateTodo.todo.domain.TodoStatus;
import com.projectss.theUltimateTodo.user.domain.User;
import com.projectss.theUltimateTodo.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findTodoByIdAndUser(Long id, User user);

    List<Todo> findAllByUser(User user);

    List<Todo> findAllByUserAndStatus(User user, TodoStatus status);
}
