package com.projectss.theUltimateTodo.memo.controller;

import com.projectss.theUltimateTodo.OAuth.SecurityUser;
import com.projectss.theUltimateTodo.memo.dto.MemoRequest;
import com.projectss.theUltimateTodo.memo.service.MemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
@Tag(name = "메모 API")
public class MemoController {

    private final MemoService memoService;

    @Operation(summary = "최상위 루트 메모 저장 API", description = "루트 메모 저장 & 업데이트", responses = @ApiResponse(responseCode = "201"))
    @PostMapping
    public ResponseEntity<String> createRootMemo(@AuthenticationPrincipal SecurityUser securityUser,
                                                 @RequestBody MemoRequest dto) {
        String email = securityUser.getUsername();
        memoService.createRootMemo(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @Operation(summary = "디렉토리 안에 메모 저장", description = "디렉토리 하위 메모 저장", responses = @ApiResponse(responseCode = "201"))
    @PostMapping("/{directoryId}")
    public ResponseEntity<String> createMemoInDirectory(@AuthenticationPrincipal SecurityUser securityUser,
                                                        @PathVariable String directoryId,
                                                        @RequestBody MemoRequest dto) {
        String email = securityUser.getUsername();
        memoService.createMemoInDirectory(email, directoryId, dto);
        return ResponseEntity.ok().body("created");
    }

    @Operation(summary = "메모 업데이트 API")
    @PatchMapping("/{memoId}")
    public ResponseEntity<String> updateMemo(@AuthenticationPrincipal SecurityUser securityUser,
                                             @PathVariable String memoId,
                                             @RequestBody MemoRequest dto) {
        String email = securityUser.getUsername();
        memoService.updateMemo(email, memoId, dto);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "루트 메모 위치 수정 API", description = "루트 메모 드래그 앤 드랍")
    @PutMapping("/{memoId}/{targetDirectoryId}")
    public ResponseEntity<String> updateRootMemoLocation(@AuthenticationPrincipal SecurityUser securityUser,
                                                         @PathVariable String memoId,
                                                         @PathVariable String targetDirectoryId) {
        String email = securityUser.getUsername();
        memoService.moveRootMemoLocation(email, memoId, targetDirectoryId);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "메모 to 디렉토리 위치 수정 API", description = "메모 드래그 앤 드랍")
    @PutMapping("/{memoId}/{parentDirectoryId}/{targetDirectoryId}")
    public ResponseEntity<String> updateMemoLocationToDirectory(@AuthenticationPrincipal SecurityUser securityUser,
                                                                @PathVariable String memoId,
                                                                @PathVariable String parentDirectoryId,
                                                                @PathVariable String targetDirectoryId) {
        String email = securityUser.getUsername();
        memoService.moveLocationToDirectory(email, memoId, parentDirectoryId, targetDirectoryId);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "메모 to 최상단 API API", description = "메모 to 최상단 드래그 앤 드랍")
    @PutMapping("/{memoId}/{parentDirectoryId}/root/move")
    public ResponseEntity<String> updateMemoLocationToRoot(@AuthenticationPrincipal SecurityUser securityUser,
                                                           @PathVariable String memoId,
                                                           @PathVariable String parentDirectoryId) {
        String email = securityUser.getUsername();
        memoService.moveLocationToRoot(email, memoId, parentDirectoryId);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "메모 삭제 API")
    @DeleteMapping("/{memoId}")
    public ResponseEntity<String> deleteMemo(@AuthenticationPrincipal SecurityUser securityUser,
                                             @PathVariable String memoId) {
        String email = securityUser.getUsername();
        memoService.deleteMemo(email, memoId);
        return ResponseEntity.ok().body("deleted");
    }
}
