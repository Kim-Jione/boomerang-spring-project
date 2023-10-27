package com.example.bumerang.web.dto.response.Likey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeyJSListDto {
    //  유저에게서 받아올 정보
    private Integer userId;
    private String userNickname;
    private String userProfileImg;
    //  Jobsearch에서 가져올 정보
    private String userPosition;
    private String userCareer;
    private String jobStartDate;
    private String jobDeadline;
    // 새로 가공할 정보
    private Integer CommentCount;
    private Integer ViewCount;
}
