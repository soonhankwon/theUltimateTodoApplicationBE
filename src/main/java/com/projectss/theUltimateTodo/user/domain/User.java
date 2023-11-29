package com.projectss.theUltimateTodo.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`user`")
public class User {

    @Id
    private String id;

    private String userEmail;

    private String nickname;

    private String snsType;

    private String snsId;

    private String openId;

    private String profile_image;

    private String thumbnail_image;

    private String role;

    private String ageRange;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}


