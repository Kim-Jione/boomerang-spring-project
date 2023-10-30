package com.example.bumerang.domain.performance;

import lombok.Builder;
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
    private String pfStartDate;
    private String pfDeadline;
    private String pfBookingmethod;
    private String pfProduction;
    private String pfLocation;
    private String pfRunningtime;
    private Integer pfPrice;
    private String pfGenre;
    private String pfThumbnail;
    private Integer userId;
    private String pfStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Builder
    public Performance(String pfAgerating, String pfStartDate, String pfDeadline, String pfBookingmethod, String pfProduction,
                       String pfLocation, String pfRunningtime, Integer pfPrice, String pfGenre, String pfThumbnail,
                       String pfTitle, String pfContent, Integer userId, Integer pfId) {

        this.pfAgerating = pfAgerating;
        this.pfStartDate = pfStartDate;
        this.pfDeadline = pfDeadline;
        this.pfBookingmethod = pfBookingmethod;
        this.pfProduction = pfProduction;
        this.pfLocation = pfLocation;
        this.pfRunningtime = pfRunningtime;
        this.pfPrice = pfPrice;
        this.pfGenre = pfGenre;
        this.pfThumbnail = pfThumbnail;
        this.pfTitle = pfTitle;
        this.pfContent = pfContent;
        this.userId = userId;
        this.pfId = pfId;
    }

}
