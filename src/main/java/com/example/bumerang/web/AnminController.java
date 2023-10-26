package com.example.bumerang.web;

import com.example.bumerang.domain.admin.AdminDao;
import com.example.bumerang.service.AdminService;
import com.example.bumerang.web.dto.response.CMRespDto;
import com.example.bumerang.web.dto.response.admin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class AnminController {
    private final HttpSession session;
    private final AdminService adminService;
    private final AdminDao adminDao;

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

    // 사용자 관리 목록 화면
    @GetMapping("/manage/userListForm")
    public String userListForm(Model model) {
        List<UserListDto> userList = adminService.findUserList();
        model.addAttribute("userList",userList);
        return "admin/manage/userListForm";
    }

    // 사용자 상세보기 화면
    @GetMapping("/manage/userDetailForm/{userId}")
    public String userDetailForm(@PathVariable Integer userId, Model model) {
        UserDetailDto userPS = adminService.findByUserId(userId);
        model.addAttribute("userPS",userPS);
        return "admin/manage/userDetailForm";
    }

    // 사용자 수정하기 화면
    @GetMapping("/manage/userUpdateForm/{userId}")
    public String userUpdateForm(@PathVariable Integer userId, Model model) {
        UserDetailDto userPS = adminService.findByUserId(userId);
        model.addAttribute("userPS",userPS);
        return "admin/manage/userUpdateForm";
    }

    // 사용자 수정하기 기능
    @PutMapping("/manage/userUpdate")
    public @ResponseBody CMRespDto<?> userUpdate(@RequestBody UserDetailDto userDetailDto ) {
        UserDetailDto userPS = adminService.updateUser(userDetailDto);
        return new CMRespDto<>(1, "사용자 정보 수정 성공.", userPS);
    }

    // 사용자 삭제하기 기능
    @DeleteMapping("/manage/userDelete/{userId}")
    public @ResponseBody CMRespDto<?> userDelete(@PathVariable Integer userId) {
        UserDetailDto userPS = adminService.deleteUser(userId);
        return new CMRespDto<>(1, "사용자 정보 삭제 성공.", userPS);
    }

    // 구인글 관리 목록 화면
    @GetMapping("/manage/jobListForm")
    public String findManageJobList(Model model) {
        List<JobListDto> jobList = adminService.findJobList();
        model.addAttribute("jobList",jobList);
        return "admin/manage/jobListForm";
    }

    // 구인글 상세보기 화면
    @GetMapping("/manage/jobDetailForm/{jobId}")
    public String jobDetailForm(@PathVariable Integer jobId, Model model) {
        JobDetailDto jobPS = adminService.findByJobId(jobId);
        model.addAttribute("jobPS",jobPS);
        return "admin/manage/jobDetailForm";
    }

    // 구인글 수정하기 화면
    @GetMapping("/manage/jobUpdateForm/{jobId}")
    public String jobUpdateForm(@PathVariable Integer jobId, Model model) {
        JobDetailDto jobPS = adminService.findByJobId(jobId);
        List<String> jobPositionTitle = adminService.findByJobPosition(jobId);
        jobPS.setJobPositionTitle(jobPositionTitle);
        model.addAttribute("jobPS",jobPS);
        return "admin/manage/jobUpdateForm";
    }

    // 구인글 수정하기 기능
    @PutMapping("/manage/jobUpdate")
    public @ResponseBody CMRespDto<?> jobUpdate(@RequestBody JobDetailDto jobDetailDto ) {
        System.err.println("디버그: getJobPositionTitle "+jobDetailDto.getJobPositionTitle().get(0));
        System.err.println("디버그: getJobPositionTitle "+jobDetailDto.getJobPositionTitle().get(1));
        System.err.println("디버그: getJobPositionTitle "+jobDetailDto.getJobPositionTitle().get(2));
        System.err.println("디버그: getJobPositionTitles "+jobDetailDto.getJobPositionTitles());
        JobDetailDto jobPS = adminService.updateJob(jobDetailDto);
        return new CMRespDto<>(1, "구인글 정보 수정 성공.", jobPS);
    }

    // 공연글 관리 목록 화면
    @GetMapping("/manage/pfListForm")
    public String findManagePfList(Model model) {
        List<PfListDto> pfList = adminService.findPfList();
        model.addAttribute("pfList",pfList);
        return "admin/manage/pfListForm";
    }

    // 공지글 관리 목록 화면
    @GetMapping("/manage/noticeListForm")
    public String findManageNoticeList(Model model) {
        List<NoticeListDto> noticeList = adminService.findNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "admin/manage/noticeListForm";
    }
    
    // 구인글 신고 목록 화면
    @GetMapping("/report/jobListForm")
    public String findReportJobList(Model model) {
        List<NoticeListDto> reportJobList = adminService.findNoticeList();
        model.addAttribute("reportJobList", reportJobList);
        return "admin/report/jobListForm";
    }

}
