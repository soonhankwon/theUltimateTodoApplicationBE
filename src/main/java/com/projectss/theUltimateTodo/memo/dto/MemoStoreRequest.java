package com.projectss.theUltimateTodo.memo.dto;

import com.projectss.theUltimateTodo.memo.domain.Directory;
import com.projectss.theUltimateTodo.memo.domain.Memo;

import java.util.List;

public record MemoStoreRequest(
        List<Directory> directories,
        List<Memo> memos
) {
}
