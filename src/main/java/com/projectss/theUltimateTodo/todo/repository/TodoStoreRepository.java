package com.projectss.theUltimateTodo.todo.repository;

import com.projectss.theUltimateTodo.todo.domain.TodoStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoStoreRepository extends MongoRepository<TodoStore, String> {
    Optional<TodoStore> findTodoStoreByEmail(String email);

    boolean existsByEmail(String email);
}
