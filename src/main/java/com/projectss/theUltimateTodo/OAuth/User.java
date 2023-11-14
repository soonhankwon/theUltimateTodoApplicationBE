package com.projectss.theUltimateTodo.OAuth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "`User`")
public class User {

    @Id
    private String id;

    private String userEmail;

    private String nickname;
    private String snsType;

    private String snsId;

    private String profile_image;
    private String thumbnail_image;

    private String role;

    private String ageRange;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;


}


