package com.projectss.theUltimateTodo.todo.repository;

import com.projectss.theUltimateTodo.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findById(Long id);

    @Query("SELECT e FROM Todo e WHERE e.startDate <= :currentDate AND e.endDate >= :currentDate")
    List<Todo> findTodosInProgress(@Param("currentDate") LocalDate currentDate);
}
