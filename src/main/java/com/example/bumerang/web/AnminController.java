package com.example.bumerang.web;

import com.example.bumerang.domain.jobSearch.JobSearch;
import com.example.bumerang.domain.notice.Notice;
import com.example.bumerang.domain.performance.Performance;
import com.example.bumerang.domain.user.User;
import com.example.bumerang.service.JobSearchService;
import com.example.bumerang.service.NoticeService;
import com.example.bumerang.service.PerformanceService;
import com.example.bumerang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class AnminController {
    private final HttpSession session;
    private final UserService userService;
    private final JobSearchService jobSearchService;
    private final PerformanceService performanceService;
    private final NoticeService noticeService;

    // 메인 화면
    @GetMapping("/indexForm")
    public String indexForm() {
        return "admin/indexForm";
    }

    // 차트 화면
    @GetMapping("/chartForm")
    public String chartForm() {
        return "admin/chartForm";
    }

    // 테이블 화면
    @GetMapping("/tableForm")
    public String tableForm() {
        return "admin/tableForm";
    }

    // 사용자관리 화면
    @GetMapping("/userManageForm")
    public String userManageForm(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        return "admin/userManageForm";
    }

    // 구인글관리 화면
    @GetMapping("/jobManageForm")
    public String jobManageForm(Model model) {
        List<JobSearch> jobList = jobSearchService.findAll();
        model.addAttribute("jobList",jobList);
        return "admin/jobManageForm";
    }

    // 구인글관리 화면
    @GetMapping("/pfManageForm")
    public String pfManageForm(Model model) {
        List<Performance> pfList = performanceService.findAll();
        model.addAttribute("pfList",pfList);
        return "admin/pfManageForm";
    }

    // 구인글관리 화면
    @GetMapping("/noticeManageForm")
    public String noticeManageForm(Model model) {
        List<Notice> noticeList = noticeService.findAll();
        model.addAttribute("noticeList",noticeList);
        return "admin/noticeManageForm";
    }
}
