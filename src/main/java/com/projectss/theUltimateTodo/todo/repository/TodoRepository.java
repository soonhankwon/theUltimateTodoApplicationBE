package com.projectss.theUltimateTodo.todo.repository;

import com.projectss.theUltimateTodo.todo.domain.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
}
