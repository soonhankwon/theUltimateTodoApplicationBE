package com.projectss.theUltimateTodo.memo.service;

import com.projectss.theUltimateTodo.memo.domain.Directory;
import com.projectss.theUltimateTodo.memo.domain.Memo;
import com.projectss.theUltimateTodo.memo.domain.MemoStore;
import com.projectss.theUltimateTodo.memo.dto.MemoRequest;
import com.projectss.theUltimateTodo.memo.repository.DirectoryRepository;
import com.projectss.theUltimateTodo.memo.repository.MemoRepository;
import com.projectss.theUltimateTodo.memo.repository.MemoStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoStoreRepository memoStoreRepository;
    private final MemoRepository memoRepository;
    private final DirectoryRepository directoryRepository;

    public void createRootMemo(String email, MemoRequest dto) {
        MemoStore memoStore = memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow();
        Memo memo = new Memo(dto);
        memoRepository.save(memo);
        memoStore.saveMemo(memo);
        memoStoreRepository.save(memoStore);
    }

    public void createMemoInDirectory(String email, String directoryId, MemoRequest dto) {
        if(!memoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no memo store by user email");
        }
        Memo memo = new Memo(dto);

        Directory directory = directoryRepository.findById(directoryId)
                .orElseThrow(() -> new IllegalStateException("no directory by directory id"));

        memoRepository.save(memo);
        directory.saveMemo(memo);
        directoryRepository.save(directory);
    }

    public void updateMemo(String email, String memoId, MemoRequest dto) {
        if(!memoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no memo store by user email");
        }
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalStateException("no memo by memo id"));
        memo.update(dto);
        memoRepository.save(memo);
    }

    public void deleteMemo(String email, String memoId) {
        if(!memoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no memo store by user email");
        }
        memoRepository.deleteById(memoId);
    }

    public void moveRootMemoLocation(String email, String memoId, String targetDirectoryId) {
        MemoStore memoStore = memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow();
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalStateException("no memo by memo id"));
        memoStore.deleteMemo(memo);

        Directory directory = directoryRepository.findById(targetDirectoryId)
                .orElseThrow(() -> new IllegalStateException("no directory by directory id"));
        directory.saveMemo(memo);

        directoryRepository.save(directory);
        memoStoreRepository.save(memoStore);
    }

    public void moveLocationToDirectory(String email, String memoId, String directoryId, String targetDirectoryId) {
        if(!memoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no memo store by user email");
        }
        Directory directory = directoryRepository.findById(directoryId)
                .orElseThrow(() -> new IllegalStateException("no directory by directory id"));
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalStateException("no memo by memo id"));
        directory.deleteMemo(memo);
        directoryRepository.save(directory);

        Directory targetDirectory = directoryRepository.findById(targetDirectoryId)
                .orElseThrow(() -> new IllegalStateException("no directory by target directory id"));
        targetDirectory.saveMemo(memo);
        directoryRepository.save(targetDirectory);
    }

    public void moveLocationToRoot(String email, String memoId, String parentDirectoryId) {
        MemoStore memoStore = memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow(() -> new IllegalStateException("no memo store by user email"));
        Directory parentDirectory = directoryRepository.findById(parentDirectoryId)
                .orElseThrow(() -> new IllegalStateException("no directory by directory id"));
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalStateException("no memo by memo id"));
        parentDirectory.deleteMemo(memo);
        memoStore.saveMemo(memo);

        directoryRepository.save(parentDirectory);
        memoStoreRepository.save(memoStore);
    }
}
