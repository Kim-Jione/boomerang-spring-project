package com.example.bumerang.web.dto.response.jobSearch;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobListDto {
    private Integer jobId;
    private String jobGenre;
    private String jobDeadline;
    private String jobContentTitle;
    private List<String> jobPositionTitle;
    private String userProfileImg;
    private String userNickname;
    private String userCareer;
    private Integer userId;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeyCount;
    private Boolean isToday;
}
