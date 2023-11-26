package com.projectss.theUltimateTodo.memo.service;

import com.projectss.theUltimateTodo.memo.domain.MemoStore;
import com.projectss.theUltimateTodo.memo.repository.MemoStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoStoreService {

    private final MemoStoreRepository memoStoreRepository;

    public void createMemoStoreByUser(String email) {
        if (memoStoreRepository.existsByEmail(email)) {
            return ;
        }
        MemoStore memoStore = new MemoStore(email);
        memoStoreRepository.save(memoStore);
    }

    public MemoStore getMemoStoreByUser(String email) {
        return memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow(() -> new IllegalStateException("no memo store by user id"));

    }

    public String deleteAllMemoStore(String email) {
        Integer numberDeleted = memoStoreRepository.deleteAllByEmail(email);
        return numberDeleted.toString();
    }


}
