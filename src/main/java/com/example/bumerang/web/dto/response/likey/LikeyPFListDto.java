package com.example.bumerang.web.dto.response.likey;

import com.example.bumerang.web.dto.response.performance.PfCommentDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class LikeyPFListDto {
    private Integer pfId;
    private String pfTitle;
    private String pfLocation;
    private String pfStartDate;
    private String pfDeadline;
    private String pfThumbnail;
    private Integer viewCount;
    private String pfAgerating;
    private String pfGenre;
    // DB 칼럼 아님
    private Boolean isPrice; // true = 유료 false = 무료
    private Boolean isDead; // 마감됐는지 확인 여부 true = 상영중 false = 마감
    private String imgPath;

}
