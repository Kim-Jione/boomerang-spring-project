package com.example.bumerang.web.dto.request.user;

import com.example.bumerang.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDto {
    private Integer userId;
    private String userPassword;
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
    private String userProfileImg;

    public User toEntity(){
        return User.builder()
                .userPassword(this.userPassword)
                .userEmail(this.userEmail)
                .userNickname(this.userNickname)
                .userGender(this.userGender)
                .userHeight(this.userHeight)
                .userForm(this.userForm)
                .userTone(this.userTone)
                .userAge(this.userAge)
                .userCareer(this.userCareer)
                .userSkill(this.userSkill)
                .userEducation(this.userEducation)
                .userContactLink(this.userContactLink)
                .userProfileImg(this.userProfileImg)
                .userId(this.userId)
                .build();
    }
}
