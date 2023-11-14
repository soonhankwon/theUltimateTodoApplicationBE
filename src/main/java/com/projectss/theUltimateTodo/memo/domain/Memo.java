package com.projectss.theUltimateTodo.memo.domain;

import com.projectss.theUltimateTodo.memo.dto.MemoRequest;
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

    public void update(MemoRequest dto) {
        this.title = dto.title();
        this.content = dto.content();
    }
}