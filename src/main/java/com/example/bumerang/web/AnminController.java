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
    public String findUserListForm(Model model) {
        List<UserListDto> userList = adminService.findUserList();
        model.addAttribute("userList",userList);
        return "admin/manage/userListForm";
    }

    // 사용자 상세보기 화면
    @GetMapping("/manage/userDetailForm/{userId}")
    public String findUserDetailForm(@PathVariable Integer userId, Model model) {
        UserDetailDto userPS = adminService.findByUserId(userId);
        model.addAttribute("userPS",userPS);
        return "admin/manage/userDetailForm";
    }

    // 사용자 수정하기 화면
    @GetMapping("/manage/userUpdateForm/{userId}")
    public String findUserUpdateForm(@PathVariable Integer userId, Model model) {
        UserDetailDto userPS = adminService.findByUserId(userId);
        model.addAttribute("userPS",userPS);
        return "admin/manage/userUpdateForm";
    }

    // 사용자 수정하기 기능
    @PutMapping("/manage/userUpdate")
    public @ResponseBody CMRespDto<?> updateUser(@RequestBody UserDetailDto userDetailDto ) {
        UserDetailDto userPS = adminService.updateUser(userDetailDto);
        return new CMRespDto<>(1, "사용자 정보 수정 성공.", userPS);
    }

    // 사용자 삭제하기 기능
    @DeleteMapping("/manage/userDelete/{userId}")
    public @ResponseBody CMRespDto<?> deleteUser(@PathVariable Integer userId) {
        UserDetailDto userPS = adminService.deleteUser(userId);
        return new CMRespDto<>(1, "사용자 정보 삭제 성공.", userPS);
    }

    // 구인글 관리 목록 화면
    @GetMapping("/manage/jobListForm")
    public String findManageJobListForm(Model model) {
        List<JobListDto> jobList = adminService.findJobList();
        model.addAttribute("jobList",jobList);
        return "admin/manage/jobListForm";
    }

    // 구인글 상세보기 화면
    @GetMapping("/manage/jobDetailForm/{jobId}")
    public String findJobDetailForm(@PathVariable Integer jobId, Model model) {
        JobDetailDto jobPS = adminService.findByJobId(jobId);
        model.addAttribute("jobPS",jobPS);
        return "admin/manage/jobDetailForm";
    }

    // 구인글 수정하기 화면
    @GetMapping("/manage/jobUpdateForm/{jobId}")
    public String findJobUpdateForm(@PathVariable Integer jobId, Model model) {
        JobDetailDto jobPS = adminService.findByJobId(jobId);
        List<String> jobPositionTitle = adminService.findByJobPosition(jobId);
        jobPS.setJobPositionTitle(jobPositionTitle);
        model.addAttribute("jobPS",jobPS);
        return "admin/manage/jobUpdateForm";
    }

    // 구인글 수정하기 기능
    @PutMapping("/manage/jobUpdate")
    public @ResponseBody CMRespDto<?> updateJob(@RequestBody JobDetailDto jobDetailDto ) {
        JobDetailDto jobPS = adminService.updateJob(jobDetailDto);
        return new CMRespDto<>(1, "구인글 정보 수정 성공.", jobPS);
    }

    // 구인글 삭제하기 기능
    @DeleteMapping("/manage/jobDelete/{jobId}")
    public @ResponseBody CMRespDto<?> deleteJob(@PathVariable Integer jobId) {
        JobDetailDto jobPS = adminService.deleteJob(jobId);
        return new CMRespDto<>(1, "구인글 정보 삭제 성공.", jobPS);
    }

    // 공연글 관리 목록 화면
    @GetMapping("/manage/pfListForm")
    public String findManagePfListForm(Model model) {
        List<PfListDto> pfList = adminService.findPfList();
        model.addAttribute("pfList", pfList);
        return "admin/manage/pfListForm";
    }

    // 공연글 상세보기 화면
    @GetMapping("/manage/pfDetailForm/{pfId}")
    public String findPfDetailForm(@PathVariable Integer pfId, Model model) {
        PfDetailDto pfPS = adminService.findByPfId(pfId);
        model.addAttribute("pfPS", pfPS);
        return "admin/manage/pfDetailForm";
    }

    // 공연글 수정하기 화면
    @GetMapping("/manage/pfUpdateForm/{pfId}")
    public String findPfUpdateForm(@PathVariable Integer pfId, Model model) {
        PfDetailDto pfPS = adminService.findByPfId(pfId);
        model.addAttribute("pfPS", pfPS);
        return "admin/manage/pfUpdateForm";
    }
    
    // 공연글 수정하기 기능
    @PutMapping("/manage/pfUpdate")
    public @ResponseBody CMRespDto<?> pfUpdate(@RequestBody PfDetailDto pfDetailDto) {
        PfDetailDto pfPS = adminService.updatePf(pfDetailDto);
        return new CMRespDto<>(1, "공연글 정보 수정 성공.", pfPS);
    }

    // 공연글 삭제하기 기능
    @DeleteMapping("/manage/pfDelete/{pfId}")
    public @ResponseBody CMRespDto<?> deletePf(@PathVariable Integer pfId) {
        PfDetailDto pfPS = adminService.deletePf(pfId);
        return new CMRespDto<>(1, "공연글 정보 삭제 성공.", pfPS);
    }

    // 공지글 관리 목록 화면
    @GetMapping("/manage/noticeListForm")
    public String findManageNoticeListForm(Model model) {
        List<NoticeListDto> noticeList = adminService.findNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "admin/manage/noticeListForm";
    }

    // 공지글 상세보기 화면
    @GetMapping("/manage/noticeDetailForm/{noticeId}")
    public String findNoticeDetailForm(@PathVariable Integer noticeId, Model model) {
        NoticeDetailDto noticePS = adminService.findByNoticeId(noticeId);
        model.addAttribute("noticePS", noticePS);
        return "admin/manage/noticeDetailForm";
    }
    
    // 공지글 수정하기 화면
    @GetMapping("/manage/noticeUpdateForm/{noticeId}")
    public String findNoticeUpdateForm(@PathVariable Integer noticeId, Model model) {
        NoticeDetailDto noticePS = adminService.findByNoticeId(noticeId);
        model.addAttribute("noticePS",noticePS);
        return "admin/manage/noticeUpdateForm";
    }

    // 공지글 수정하기 기능
    @PutMapping("/manage/noticeUpdate")
    public @ResponseBody CMRespDto<?> updateNotice(@RequestBody NoticeDetailDto noticeDetailDto) {
        NoticeDetailDto noticePS = adminService.updateNotice(noticeDetailDto);
        return new CMRespDto<>(1, "공지글 정보 수정 성공.", noticePS);
    }
    
    // 공지글 삭제하기 기능
    @DeleteMapping("/manage/noticeDelete/{noticeId}")
    public @ResponseBody CMRespDto<?> deleteNotice(@PathVariable Integer noticeId) {
        NoticeDetailDto noticePS = adminService.deleteNotice(noticeId);
        return new CMRespDto<>(1, "공지글 정보 삭제 성공.", noticePS);
    }

    // 구인글 신고 목록 화면
    @GetMapping("/report/jobListForm")
    public String findReportJobListForm(Model model) {
        List<JobListDto> reportJobList = adminService.findReportJobList();
        model.addAttribute("jobList", reportJobList);
        return "admin/report/jobListForm";
    }

    // 공연글 신고 목록 화면
    @GetMapping("/report/pfListForm")
    public String findReportPfListForm(Model model) {
        List<PfListDto> reportPfList = adminService.findReportPfList();
        model.addAttribute("pfList", reportPfList);
        return "admin/report/pfListForm";
    }

    // 댓글 신고 목록 화면
    @GetMapping("/report/commentListForm")
    public String findReportCommentListForm(Model model) {
        List<CommentListDto> reportCommentList = adminService.findReportCommentList();
        model.addAttribute("commentList", reportCommentList);
        return "admin/report/commentListForm";
    }
}
