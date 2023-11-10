package com.example.bumerang.web.dto.response.likey;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private Integer CommentCount;
    // 뷰 테이블에서 가져올 정보
    private Integer ViewCount;

    private List<String> jobPositionTitle;
    private Boolean isDead;         // 마감됐는지 확인 여부
    private Boolean isToday;        // 오늘 게시글 작성됐는지 확인 여부
}