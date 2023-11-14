package com.projectss.theUltimateTodo.memo.service;

import com.projectss.theUltimateTodo.memo.domain.Directory;
import com.projectss.theUltimateTodo.memo.domain.MemoStore;
import com.projectss.theUltimateTodo.memo.dto.DirectoryRequest;
import com.projectss.theUltimateTodo.memo.repository.DirectoryRepository;
import com.projectss.theUltimateTodo.memo.repository.MemoStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectoryService {

    private final MemoStoreRepository memoStoreRepository;
    private final DirectoryRepository directoryRepository;

    public void saveRootDirectory(String email, DirectoryRequest dto) {
        MemoStore memoStore = memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow(() -> new IllegalStateException("no memo store by user email"));
        Directory directory = new Directory(dto);
        directoryRepository.save(directory);
        memoStore.saveDirectory(directory);
        memoStoreRepository.save(memoStore);
    }

    public void saveDirectoryInDirectory(String email, String directoryId, DirectoryRequest dto) {
        if (!memoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no memo store by user email");
        }
        Directory targetDirectory = directoryRepository.findById(directoryId)
                .orElseThrow();
        Directory directory = new Directory(dto);
        directoryRepository.save(directory);
        targetDirectory.saveDirectory(directory);
        directoryRepository.save(targetDirectory);
    }

    public void deleteDirectory(String email, String directoryId) {
        if (!memoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no memo store by user email");
        }
        directoryRepository.deleteById(directoryId);
    }

    public void updateDirectory(String email, String directoryId, DirectoryRequest dto) {
        if (!memoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no memo store by user email");
        }
        Directory directory = directoryRepository.findById(directoryId)
                .orElseThrow(() -> new IllegalArgumentException("no directoryId in db"));
        directory.update(dto);
        directoryRepository.save(directory);
    }

    public void moveDirectoryLocation(String email, String parentDirectoryId, String directoryId, String targetDirectoryId) {
        if (!memoStoreRepository.existsByEmail(email)) {
            throw new IllegalStateException("no memo store by user email");
        }

        Directory parentDirectory = directoryRepository.findById(parentDirectoryId)
                .orElseThrow(() -> new IllegalArgumentException("no parent directoryId in db"));

        Directory directory = directoryRepository.findById(directoryId)
                .orElseThrow(() -> new IllegalArgumentException("no directoryId in db"));

        parentDirectory.deleteDirectory(directory);
        Directory targetDirectory = directoryRepository.findById(targetDirectoryId)
                .orElseThrow(() -> new IllegalArgumentException("no target directoryId in db"));
        targetDirectory.saveDirectory(directory);

        directoryRepository.save(targetDirectory);
        directoryRepository.save(parentDirectory);

    }

    public void moveRootDirectoryLocation(String email, String directoryId, String targetDirectoryId) {
        MemoStore memoStore = memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow();

        Directory directory = directoryRepository.findById(directoryId)
                .orElseThrow(() -> new IllegalArgumentException("no directoryId in db"));
        memoStore.deleteDirectory(directory);

        Directory targetDirectory = directoryRepository.findById(targetDirectoryId)
                .orElseThrow(() -> new IllegalArgumentException("no target directoryId in db"));
        targetDirectory.saveDirectory(directory);

        directoryRepository.save(targetDirectory);
        memoStoreRepository.save(memoStore);
    }

    public void moveDirectoryLocationToRoot(String email, String parentDirectoryId, String directoryId) {
        MemoStore memoStore = memoStoreRepository.findMemoStoreByEmail(email)
                .orElseThrow();

        Directory directory = directoryRepository.findById(directoryId)
                .orElseThrow(() -> new IllegalArgumentException("no directoryId in db"));

        Directory parentDirectory = directoryRepository.findById(parentDirectoryId)
                .orElseThrow(() -> new IllegalArgumentException("no target directoryId in db"));
        parentDirectory.deleteDirectory(directory);
        memoStore.saveDirectory(directory);

        directoryRepository.save(parentDirectory);
        memoStoreRepository.save(memoStore);
    }
}
