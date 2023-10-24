package com.example.bumerang.domain.jobSearchPosition;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class JobSearchPosition {
    private Integer jobPositionId;
    private List<String> jobPositionTitle;
    private Integer jobId;


    @Builder
    public JobSearchPosition(Integer jobPositionId, List<String> jobPositionTitle) {
        this.jobPositionTitle = jobPositionTitle;
    }
}
