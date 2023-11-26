package com.projectss.theUltimateTodo.memo.domain;

import com.projectss.theUltimateTodo.memo.dto.MemoContentUpdateRequest;
import com.projectss.theUltimateTodo.memo.dto.MemoRequest;
import com.projectss.theUltimateTodo.memo.dto.MemoTitleUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "memos")
public class Memo {

    @Id
    private String id;

    private String title;

    private String content;

    public Memo(MemoRequest memoRequest) {
        this.title = memoRequest.title();
        this.content = memoRequest.content();
    }

    public <T> void update(T request) {
        if(request instanceof MemoTitleUpdateRequest) {
            this.title = ((MemoTitleUpdateRequest) request).title();
            return;
        }
        if(request instanceof MemoContentUpdateRequest) {
            this.content = ((MemoContentUpdateRequest) request).content();
            return;
        }
        throw new IllegalStateException("request type invalid");
    }
}