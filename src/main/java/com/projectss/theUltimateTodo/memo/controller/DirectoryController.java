package com.projectss.theUltimateTodo.memo.controller;

import com.projectss.theUltimateTodo.OAuth.SecurityUser;
import com.projectss.theUltimateTodo.memo.dto.DirectoryRequest;
import com.projectss.theUltimateTodo.memo.service.DirectoryService;
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
@RequestMapping("/api/directories")
@Tag(name = "디렉토리 API")
public class DirectoryController {

    private final DirectoryService directoryService;

    @Operation(summary = "최상위 디렉토리 저장", description = "최상위 디렉토리 저장", responses = @ApiResponse(responseCode = "201"))
    @PostMapping
    public ResponseEntity<String> createRootDirectory(@AuthenticationPrincipal SecurityUser securityUser,
                                                      @RequestBody DirectoryRequest dto) {
        String email = securityUser.getUsername();
        directoryService.saveRootDirectory(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @Operation(summary = "디렉토리 안에 디렉토리 저장 API", description = "디렉토리 저장", responses = @ApiResponse(responseCode = "201"))
    @PostMapping("/{directoryId}")
    public ResponseEntity<String> createDirectory(@AuthenticationPrincipal SecurityUser securityUser,
                                                  @PathVariable String directoryId,
                                                  @RequestBody DirectoryRequest dto) {
        String email = securityUser.getUsername();
        directoryService.saveDirectoryInDirectory(email, directoryId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @Operation(summary = "디렉토리 수정 API", description = "디렉토리 수정")
    @PatchMapping("/{directoryId}")
    public ResponseEntity<String> updateDirectory(@AuthenticationPrincipal SecurityUser securityUser,
                                                  @PathVariable String directoryId,
                                                  @RequestBody DirectoryRequest dto) {
        String email = securityUser.getUsername();
        directoryService.updateDirectory(email, directoryId, dto);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "최상단 디렉토리 위치 수정 API", description = "최상단 디렉토리 드래그 앤 드랍")
    @PutMapping("/{directoryId}/{targetDirectoryId}")
    public ResponseEntity<String> updateRootDirectoryLocation(@AuthenticationPrincipal SecurityUser securityUser,
                                                              @PathVariable String directoryId,
                                                              @PathVariable String targetDirectoryId) {
        String email = securityUser.getUsername();
        directoryService.moveRootDirectoryLocation(email, directoryId, targetDirectoryId);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "디렉토리 to 디렉토리 API", description = "디렉토리 to 디렉토리 드래그 앤 드랍")
    @PutMapping("/{parentDirectoryId}/{directoryId}/{targetDirectoryId}")
    public ResponseEntity<String> updateDirectoryLocation(@AuthenticationPrincipal SecurityUser securityUser,
                                                          @PathVariable String parentDirectoryId,
                                                          @PathVariable String directoryId,
                                                          @PathVariable String targetDirectoryId) {
        String email = securityUser.getUsername();
        directoryService.moveDirectoryLocation(email, parentDirectoryId, directoryId, targetDirectoryId);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "디렉토리 to 최상단 API", description = "디렉토리 to 최상단 드래그 앤 드랍")
    @PutMapping("/{parentDirectoryId}/{directoryId}/move-root")
    public ResponseEntity<String> updateDirectoryLocationToRoot(@AuthenticationPrincipal SecurityUser securityUser,
                                                                @PathVariable String parentDirectoryId,
                                                                @PathVariable String directoryId) {
        String email = securityUser.getUsername();
        directoryService.moveDirectoryLocationToRoot(email, parentDirectoryId, directoryId);
        return ResponseEntity.ok().body("updated");
    }

    @Operation(summary = "디렉토리 삭제 API", description = "디렉토리 삭제")
    @DeleteMapping("/{directoryId}")
    public ResponseEntity<String> deleteDirectory(@AuthenticationPrincipal SecurityUser securityUser,
                                                  @PathVariable String directoryId) {
        String email = securityUser.getUsername();
        directoryService.deleteDirectory(email, directoryId);
        return ResponseEntity.ok().body("deleted");
    }
}
