package com.projectss.theUltimateTodo.memo.domain;

import com.projectss.theUltimateTodo.memo.dto.DirectoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "directories")
public class Directory {

    @Id
    private String id;

    private String name;

    @DBRef
    private List<Directory> directories = new ArrayList<>();

    @DBRef
    private List<Memo> memos = new ArrayList<>();

    public Directory(DirectoryRequest dto) {
        if(dto.name() == null || dto.name().trim().isEmpty()) {
            this.name = "temp directory";
        } else {
            this.name = dto.name();
        }
    }

    public void update(DirectoryRequest dto) {
        this.name = dto.name();
    }

    public void saveDirectory(Directory directory) {
        this.directories.add(directory);
    }

    public void deleteDirectory(Directory directory) {
        this.directories.remove(directory);
    }

    public void saveMemo(Memo memo) {
        this.memos.add(memo);
    }

    public void deleteMemo(Memo memo) {
        this.memos.remove(memo);
    }

}
