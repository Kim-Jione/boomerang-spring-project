package com.example.bumerang.web.dto.response.Likey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeyPFListDto {
    //  유저에게서 받아올 정보
    private Integer userId;
    private String userNickname;
    private String userProfileImg;
    //  performance에서 가져올 정보

    private String title;
    private String location;
    private String startdate;
    private String deadline;
    private String agerationg;
    private String runningTime;
    private String thmbnail;

    // 새로 가공할 정보
    private Integer CommentCount;
    private Integer ViewCount;
}
