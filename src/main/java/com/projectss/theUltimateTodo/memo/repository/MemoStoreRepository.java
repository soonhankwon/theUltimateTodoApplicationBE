package com.projectss.theUltimateTodo.memo.repository;

import com.projectss.theUltimateTodo.memo.domain.MemoStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemoStoreRepository extends MongoRepository<MemoStore, String> {
    Optional<MemoStore> findMemoStoreByEmail(String email);
    boolean existsByEmail(String email);
}
