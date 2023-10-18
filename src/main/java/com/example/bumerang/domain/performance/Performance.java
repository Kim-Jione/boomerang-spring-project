package com.example.bumerang.domain.performance;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class Performance {
    private Integer pfId;
    private String pfTitle;
    private String pfContent;
    private String pfAgerating;
    private String pfDate;
    private String pfBookingmethod;
    private String pfProduction;
    private String pfLocation;
    private Integer pfRunningtime;
    private Integer pfPrice;
    private Integer pfView;
    private String pfGenre;
    private String pfThumbnail;
    private Integer userId;
    private Timestamp createdAt;
}
