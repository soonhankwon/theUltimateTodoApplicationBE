package com.projectss.theUltimateTodo.memo.repository;

import com.projectss.theUltimateTodo.memo.domain.Directory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends MongoRepository<Directory, String> {
}
