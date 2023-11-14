package com.projectss.theUltimateTodo.memo.controller;

import com.projectss.theUltimateTodo.memo.dto.MemoRequest;
import com.projectss.theUltimateTodo.memo.service.MemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
@Tag(name = "메모 API")
public class MemoController {

    private final MemoService memoService;

    @Operation(summary = "최상위 루트 메모 저장 API", description = "루트 메모 저장 & 업데이트")
    @PostMapping
    public ResponseEntity<?> createRootMemo(@RequestBody MemoRequest dto) {
        String loginUserEmail = "test@naver.com";
        memoService.createRootMemo(loginUserEmail, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @Operation(summary = "디렉토리 안에 메모 저장", description = "디렉토리 하위 메모 저장")
    @PostMapping("/{directoryId}")
    public ResponseEntity<?> createMemoInDirectory(@PathVariable String directoryId,
                                                   @RequestBody MemoRequest dto) {
        String loginUserEmail = "test@naver.com";
        memoService.createMemoInDirectory(loginUserEmail, directoryId, dto);
        return ResponseEntity.ok().body("success");
    }

    @Operation(summary = "메모 업데이트 API")
    @PatchMapping("/{memoId}")
    public ResponseEntity<?> updateMemo(@PathVariable String memoId,
                                        @RequestBody MemoRequest dto) {
        String loginUserEmail = "test@naver.com";
        memoService.updateMemo(loginUserEmail, memoId, dto);
        return ResponseEntity.ok().body("success");
    }

    @Operation(summary = "루트 메모 위치 수정 API", description = "루트 메모 드래그 앤 드랍")
    @PutMapping("/{memoId}/{targetDirectoryId}")
    public ResponseEntity<?> updateRootMemoLocation(@PathVariable String memoId,
                                                @PathVariable String targetDirectoryId) {
        String loginUserEmail = "test@naver.com";
        memoService.moveRootMemoLocation(loginUserEmail, memoId, targetDirectoryId);
        return ResponseEntity.ok().body("success");
    }

    @Operation(summary = "메모 to 디렉토리 위치 수정 API", description = "메모 드래그 앤 드랍")
    @PutMapping("/{memoId}/{parentDirectoryId}/{targetDirectoryId}")
    public ResponseEntity<?> updateMemoLocationToDirectory(@PathVariable String memoId,
                                                @PathVariable String parentDirectoryId,
                                                @PathVariable String targetDirectoryId) {
        String loginUserEmail = "test@naver.com";
        memoService.moveLocationToDirectory(loginUserEmail, memoId, parentDirectoryId, targetDirectoryId);
        return ResponseEntity.ok().body("success");
    }

    @Operation(summary = "메모 to 최상단 API API", description = "메모 to 최상단 드래그 앤 드랍")
    @PutMapping("/{memoId}/{parentDirectoryId}/root/move")
    public ResponseEntity<?> updateMemoLocationToRoot(@PathVariable String memoId,
                                                @PathVariable String parentDirectoryId) {
        String loginUserEmail = "test@naver.com";
        memoService.moveLocationToRoot(loginUserEmail, memoId, parentDirectoryId);
        return ResponseEntity.ok().body("success");
    }

    @Operation(summary = "메모 삭제 API")
    @DeleteMapping("/{memoId}")
    public ResponseEntity<?> deleteMemo(@PathVariable String memoId) {
        String loginUserEmail = "test@naver.com";
        memoService.deleteMemo(loginUserEmail, memoId);
        return ResponseEntity.ok().body("success");
    }
}
