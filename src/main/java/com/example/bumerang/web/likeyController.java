package com.example.bumerang.web;

import com.example.bumerang.domain.jobSearch.JobSearch;
import com.example.bumerang.domain.performance.Performance;
import com.example.bumerang.domain.performance.PerformanceDao;
import com.example.bumerang.service.JobSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class likeyController {

    private final JobSearchService jobSearchService;
    private final PerformanceDao performceService;
    // 좋아요 사용자 목록
    @GetMapping("/likey/likeyForm")
    public String mainForm(Model model) {
        List<JobSearch> jobSearchList = jobSearchService.findAll();
        List<Performance> PerfomancesList = performceService.findAll();
        model.addAttribute("PerfomancesList", PerfomancesList);
        model.addAttribute("jobList", jobSearchList);
        return "likey/likeyForm";
    }



}