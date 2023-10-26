package com.example.bumerang.web.dto.response.admin;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class JobDetailDto {
    private Integer jobId;
    private String jobContentTitle;
    private String jobContent;
    private String jobGenre;
    private String jobArtTitle;
    private String jobStartDate;
    private String jobProductionDate;
    private Integer jobTo;
    private String jobPay;
    private String jobGender;
    private String jobContact;
    private String jobDeadline;
    private Integer userId;
    private String jobStatus;
    private String jobPositionTitles; // 포지션모음
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
