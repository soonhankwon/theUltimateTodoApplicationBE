package com.projectss.theUltimateTodo.memo.controller;

import com.projectss.theUltimateTodo.OAuth.SecurityUser;
import com.projectss.theUltimateTodo.memo.domain.MemoStore;
import com.projectss.theUltimateTodo.memo.service.MemoStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memo-stores")
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
        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
    }
}
