package com.projectss.theUltimateTodo.memo.repository;

import com.projectss.theUltimateTodo.memo.domain.Memo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends MongoRepository<Memo, String> {
}
