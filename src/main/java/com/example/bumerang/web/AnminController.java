package com.example.bumerang.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.bumerang.web.dto.response.admin.UserRespDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bumerang.service.AdminService;
import com.example.bumerang.web.dto.response.CMRespDto;
import com.example.bumerang.web.dto.response.admin.CommentDetailDto;
import com.example.bumerang.web.dto.response.admin.CommentListDto;
import com.example.bumerang.web.dto.response.admin.ExitListDto;
import com.example.bumerang.web.dto.response.admin.GenreDto;
import com.example.bumerang.web.dto.response.admin.JobDetailDto;
import com.example.bumerang.web.dto.response.admin.JobListDto;
import com.example.bumerang.web.dto.response.admin.NoticeDetailDto;
import com.example.bumerang.web.dto.response.admin.NoticeListDto;
import com.example.bumerang.web.dto.response.admin.PfDetailDto;
import com.example.bumerang.web.dto.response.admin.PfListDto;
import com.example.bumerang.web.dto.response.admin.PostListDto;
import com.example.bumerang.web.dto.response.admin.UserDetailDto;
import com.example.bumerang.web.dto.response.admin.UserListDto;
import com.example.bumerang.web.dto.response.admin.ViewListDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class AnminController {

    private final HttpSession session;
    private final AdminService adminService;

    // 메인 화면
    @GetMapping("/s/api/auth/indexForm")
    public String indexForm(Model model) {
        List<PostListDto> postPS = adminService.findAllPost();
        ViewListDto viewPS = adminService.findByView();
        ExitListDto exitPS = adminService.findByExit();
        model.addAttribute("postPS", postPS);
        model.addAttribute("viewPS", viewPS);
        model.addAttribute("exitPS", exitPS);
        return "admin/indexForm";
    }

    // 사용자 관리 목록 화면
    @GetMapping("/s/api/auth/manage/userListForm")
    public String findUserListForm(Model model) {
        List<UserListDto> userList = adminService.findUserList();
        model.addAttribute("userList",userList);
        return "admin/manage/userListForm";
    }

    // 사용자 상세보기 화면
    @GetMapping("/s/api/auth/manage/userDetailForm/{userId}")
    public String findUserDetailForm(@PathVariable Integer userId, Model model) {
        UserRespDto userPS = adminService.findByUserId(userId);
        model.addAttribute("userPS",userPS);
        return "admin/manage/userDetailForm";
    }

    // 사용자 수정하기 화면
    @GetMapping("/s/api/auth/manage/userUpdateForm/{userId}")
    public String findUserUpdateForm(@PathVariable Integer userId, Model model) {
        UserRespDto userPS = adminService.findByUserId(userId);
        model.addAttribute("userPS",userPS);
        return "admin/manage/userUpdateForm";
    }

    // 사용자 수정하기 기능
    @PutMapping("/s/api/auth/manage/userUpdate")
    public @ResponseBody CMRespDto<?> updateUser(@RequestBody UserDetailDto userDetailDto ) {
        UserRespDto userPS = adminService.updateUser(userDetailDto);
        return new CMRespDto<>(1, "사용자 정보 수정 성공.", userPS);
    }

    // 사용자 삭제하기 기능
    @DeleteMapping("/s/api/auth/manage/userDelete/{userId}")
    public @ResponseBody CMRespDto<?> deleteUser(@PathVariable Integer userId) {
        UserRespDto userPS = adminService.deleteUser(userId);
        return new CMRespDto<>(1, "사용자 정보 삭제 성공.", userPS);
    }

    // 구인글 관리 목록 화면
    @GetMapping("/s/api/auth/manage/jobListForm")
    public String findManageJobListForm(Model model) {
        List<JobListDto> jobList = adminService.findJobList();
        model.addAttribute("jobList",jobList);
        return "admin/manage/jobListForm";
    }

    // 구인글 상세보기 화면
    @GetMapping("/s/api/auth/manage/jobDetailForm/{jobId}")
    public String findJobDetailForm(@PathVariable Integer jobId, Model model) {
        JobDetailDto jobPS = adminService.findByJobId(jobId);
        model.addAttribute("jobPS",jobPS);
        return "admin/manage/jobDetailForm";
    }

    // 구인글 수정하기 화면
    @GetMapping("/s/api/auth/manage/jobUpdateForm/{jobId}")
    public String findJobUpdateForm(@PathVariable Integer jobId, Model model) {
        JobDetailDto jobPS = adminService.findByJobId(jobId);
        List<String> jobPositionTitle = adminService.findByJobPosition(jobId);
        jobPS.setJobPositionTitle(jobPositionTitle);
        model.addAttribute("jobPS",jobPS);
        return "admin/manage/jobUpdateForm";
    }

    // 구인글 수정하기 기능
    @PutMapping("/s/api/auth/manage/jobUpdate")
    public @ResponseBody CMRespDto<?> updateJob(@RequestBody JobDetailDto jobDetailDto ) {
        JobDetailDto jobPS = adminService.updateJob(jobDetailDto);
        return new CMRespDto<>(1, "구인글 정보 수정 성공.", jobPS);
    }

    // 구인글 삭제하기 기능
    @DeleteMapping("/s/api/auth/manage/jobDelete/{jobId}")
    public @ResponseBody CMRespDto<?> deleteJob(@PathVariable Integer jobId) {
        JobDetailDto jobPS = adminService.deleteJob(jobId);
        return new CMRespDto<>(1, "구인글 정보 삭제 성공.", jobPS);
    }

    // 공연글 관리 목록 화면
    @GetMapping("/s/api/auth/manage/pfListForm")
    public String findManagePfListForm(Model model) {
        List<PfListDto> pfList = adminService.findPfList();
        model.addAttribute("pfList", pfList);
        return "admin/manage/pfListForm";
    }

    // 공연글 상세보기 화면
    @GetMapping("/s/api/auth/manage/pfDetailForm/{pfId}")
    public String findPfDetailForm(@PathVariable Integer pfId, Model model) {
        PfDetailDto pfPS = adminService.findByPfId(pfId);
        model.addAttribute("pfPS", pfPS);
        return "admin/manage/pfDetailForm";
    }

    // 공연글 수정하기 화면
    @GetMapping("/s/api/auth/manage/pfUpdateForm/{pfId}")
    public String findPfUpdateForm(@PathVariable Integer pfId, Model model) {
        PfDetailDto pfPS = adminService.findByPfId(pfId);
        model.addAttribute("pfPS", pfPS);
        return "admin/manage/pfUpdateForm";
    }

    // 공연글 수정하기 기능
    @PutMapping("/s/api/auth/manage/pfUpdate")
    public @ResponseBody CMRespDto<?> pfUpdate(@RequestBody PfDetailDto pfDetailDto) {
        PfDetailDto pfPS = adminService.updatePf(pfDetailDto);
        return new CMRespDto<>(1, "공연글 정보 수정 성공.", pfPS);
    }

    // 공연글 삭제하기 기능
    @DeleteMapping("/s/api/auth/manage/pfDelete/{pfId}")
    public @ResponseBody CMRespDto<?> deletePf(@PathVariable Integer pfId) {
        PfDetailDto pfPS = adminService.deletePf(pfId);
        return new CMRespDto<>(1, "공연글 정보 삭제 성공.", pfPS);
    }

    // 공지글 관리 목록 화면
    @GetMapping("/s/api/auth/manage/noticeListForm")
    public String findManageNoticeListForm(Model model) {
        List<NoticeListDto> noticeList = adminService.findNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "admin/manage/noticeListForm";
    }

    // 공지글 상세보기 화면
    @GetMapping("/s/api/auth/manage/noticeDetailForm/{noticeId}")
    public String findNoticeDetailForm(@PathVariable Integer noticeId, Model model) {
        NoticeDetailDto noticePS = adminService.findByNoticeId(noticeId);
        model.addAttribute("noticePS", noticePS);
        return "admin/manage/noticeDetailForm";
    }

    // 공지글 수정하기 화면
    @GetMapping("/s/api/auth/manage/noticeUpdateForm/{noticeId}")
    public String findNoticeUpdateForm(@PathVariable Integer noticeId, Model model) {
        NoticeDetailDto noticePS = adminService.findByNoticeId(noticeId);
        model.addAttribute("noticePS",noticePS);
        return "admin/manage/noticeUpdateForm";
    }

    // 공지글 수정하기 기능
    @PutMapping("/s/api/auth/manage/noticeUpdate")
    public @ResponseBody CMRespDto<?> updateNotice(@RequestBody NoticeDetailDto noticeDetailDto) {
        NoticeDetailDto noticePS = adminService.updateNotice(noticeDetailDto);
        return new CMRespDto<>(1, "공지글 정보 수정 성공.", noticePS);
    }

    // 공지글 삭제하기 기능
    @DeleteMapping("/s/api/auth/manage/noticeDelete/{noticeId}")
    public @ResponseBody CMRespDto<?> deleteNotice(@PathVariable Integer noticeId) {
        NoticeDetailDto noticePS = adminService.deleteNotice(noticeId);
        return new CMRespDto<>(1, "공지글 정보 삭제 성공.", noticePS);
    }

    // 구인글 신고 목록 화면
    @GetMapping("/s/api/auth/report/jobListForm")
    public String findReportJobListForm(Model model) {
        List<JobListDto> reportJobList = adminService.findReportJobList();
        model.addAttribute("jobList", reportJobList);
        return "admin/report/jobListForm";
    }

    // 공연글 신고 목록 화면
    @GetMapping("/s/api/auth/report/pfListForm")
    public String findReportPfListForm(Model model) {
        List<PfListDto> reportPfList = adminService.findReportPfList();
        model.addAttribute("pfList", reportPfList);
        return "admin/report/pfListForm";
    }

    // 댓글 신고 목록 화면
    @GetMapping("/s/api/auth/report/commentListForm")
    public String findReportCommentListForm(Model model) {
        List<CommentListDto> reportCommentList = adminService.findReportCommentList();
        model.addAttribute("commentList", reportCommentList);
        return "admin/report/commentListForm";
    }

    // 댓글 삭제하기 기능
    @DeleteMapping("/s/api/auth/manage/commentDelete/{commentId}")
    public @ResponseBody CMRespDto<?> deleteComment(@PathVariable Integer commentId) {
        CommentDetailDto commentPS = adminService.deleteComment(commentId);
        return new CMRespDto<>(1, "댓글 정보 삭제 성공.", commentPS);
    }

    // 구인글 통계 화면
    @GetMapping("/s/api/auth/statistics/jobChartForm")
    public String findJobChartForm(Model model) {
        List<GenreDto> jobStatistics = adminService.findByGenreJob();
        model.addAttribute("jobPS",jobStatistics);
        return "admin/statistics/jobChartForm";
    }
}