package com.projectss.theUltimateTodo.memo.controller;

import com.projectss.theUltimateTodo.security.SecurityUser;
import com.projectss.theUltimateTodo.memo.domain.MemoStore;
import com.projectss.theUltimateTodo.memo.dto.MemoStoreRequest;
import com.projectss.theUltimateTodo.memo.service.MemoStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memo-stores2")
@Tag(name = "메모 최상위 루트(구조) 조회 API")
public class MemoStoreController {

    private final MemoStoreService memoStoreService;

    @Operation(summary = "메모 최상위 루트(구조) 조회 API")
    @GetMapping
    public ResponseEntity<MemoStore> getRootByUser(@AuthenticationPrincipal SecurityUser securityUser) {
        String email = securityUser.getUsername();
        MemoStore memoStoreByUser = memoStoreService.getMemoStoreByUser(email);
        return ResponseEntity.ok().body(memoStoreByUser);
    }

    @Operation(summary = "메모 스토어 생성 API - 테스트용")
    @PostMapping
    public ResponseEntity<String> createMemoStore(@AuthenticationPrincipal SecurityUser securityUser) {
        String email = securityUser.getUsername();
        memoStoreService.createMemoStoreByUser(email);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @Operation(summary = "로컬 메모 클라우드 동기화 API")
    @PostMapping("/sync")
    public ResponseEntity<String> syncLocalMemoToCloud(@AuthenticationPrincipal SecurityUser securityUser,
                                                       @RequestBody MemoStoreRequest request) {
        String email = securityUser.getUsername();
        memoStoreService.syncLocalMemoStoreToCloud(email, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("synced");
    }

    @Operation(summary = "메모 모두 delete - 테스트용")
    @DeleteMapping
    public ResponseEntity<String> deleteMemoStore(@AuthenticationPrincipal SecurityUser securityUser) {
        String email = securityUser.getUsername();
        String deleted = memoStoreService.deleteAllMemoStore(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleted + " deleted");
    }

}
