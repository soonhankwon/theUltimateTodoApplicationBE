package com.projectss.theUltimateTodo.memo.service;

import com.projectss.theUltimateTodo.memo.domain.MemoStore;
import com.projectss.theUltimateTodo.memo.dto.MemoStoreRequest;
import com.projectss.theUltimateTodo.memo.repository.DirectoryRepository;
import com.projectss.theUltimateTodo.memo.repository.MemoRepository;
import com.projectss.theUltimateTodo.memo.repository.MemoStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoStoreService {

    private final MemoStoreRepository memoStoreRepository;
    private final DirectoryRepository directoryRepository;
    private final MemoRepository memoRepository;

    public void createMemoStoreByUser(String email) {
        if (memoStoreRepository.existsByEmail(email)) {
            return;
        }
        MemoStore memoStore = new MemoStore(email);
        memoStoreRepository.save(memoStore);
    }

    public MemoStore getMemoStoreByUser(String email) {
        return memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow(() -> new IllegalStateException("no memo store by user email"));

    }

    public String deleteAllMemoStore(String email) {
        Integer numberDeleted = memoStoreRepository.deleteAllByEmail(email);
        return numberDeleted.toString();
    }

    public void syncLocalMemoStoreToCloud(String email, MemoStoreRequest request) {
        MemoStore memoStore = memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow(() -> new IllegalStateException("no memo store by user email"));

        request.directories()
                .forEach(directory -> {
                    if (!directory.getMemos().isEmpty()) {
                        directory.getMemos().forEach(memo -> {
                            memoRepository.save(memo);
                            directory.saveMemo(memo);
                        });
                    }
                    directoryRepository.save(directory);
                    memoStore.saveDirectory(directory);
                });

        request.memos()
                .forEach(i -> {
                    memoRepository.save(i);
                    memoStore.saveMemo(i);
                });

        memoStoreRepository.save(memoStore);
    }
}
