package com.example.bumerang.web.dto.response.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@NoArgsConstructor
@Setter
@Getter
public class UserDetailDto {
    private Integer userId;
    private String userEmail;
    private String userNickname;
    private String userGender;
    private String userHeight;
    private String userForm;
    private String userTone;
    private String userAge;
    private String userCareer;
    private String userSkill ;
    private String userEducation;
    private String userContactLink;
    private String userStatus;
    private String userRole;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}