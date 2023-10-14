package com.example.bumerang.web;

import com.example.bumerang.service.JobSearchService;
import com.example.bumerang.web.dto.request.jobSearch.WriteDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiredArgsConstructor
@Controller
public class JobSearchController {

    private final JobSearchService jobSearchService;

    // 구인정보 작성하기 화면
    @GetMapping("/jobSearch/writeForm")
    public String writeForm() {
        return "jobSearch/writeForm";
    }


    // 구인정보 작성하기 기능
    @PostMapping("/jobSearch/write")
    public @ResponseBody CMRespDto<?> write(@RequestBody WriteDto writeDto) {
        jobSearchService.write(writeDto);
        return new CMRespDto<>(1, "구인정보 작성하기 성공.", null);
    }

}
