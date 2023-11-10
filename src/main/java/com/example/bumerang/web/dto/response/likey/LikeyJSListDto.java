package com.example.bumerang.web.dto.response.likey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeyJSListDto {
    //  유저에게서 받아올 정보
    private Integer userId;
    private String userNickname;
    private String userProfileImg;
    private String userCareer;

    //  좋아요에서 가져올 정보
    private Integer LikeyId;

    //  Jobsearch에서 가져올 정보
    private Integer jobId;
    private String jobTitle;
    private String jobStartDate;
    private String jobDeadline;
    private String jobGenre;

    // 댓글 테이블에서 가져올 정보
    private Integer commentCount;
    // 뷰 테이블에서 가져올 정보
    private Integer viewCount;
}
