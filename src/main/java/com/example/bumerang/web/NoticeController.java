package com.example.bumerang.web;

import java.util.List;

import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.notice.WriteDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import com.example.bumerang.web.dto.response.admin.NoticeDetailDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.bumerang.domain.notice.Notice;
import com.example.bumerang.service.NoticeService;
import com.example.bumerang.web.dto.response.notice.DetailFormDto;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final HttpSession session;
    private final NoticeService noticeService;

    // 공지사항 목록 화면
    @GetMapping("/notice/listForm")
    public String listForm(Model model) {
        List<Notice> noticeList = noticeService.findAll();
        model.addAttribute("noticeList", noticeList);
        return "noticeListForm";
    }

    // 공지사항 상세보기 화면
    @GetMapping("/notice/detailForm/{noticeId}")
    public String detailForm(@PathVariable Integer noticeId,Model model) {
        DetailFormDto noticeDetail = noticeService.findByNotice(noticeId);
        if(noticeDetail==null){
            return "redirect:/404";
        }
        model.addAttribute("notice", noticeDetail);
        return "noticeDetailForm";
    }

    // 공지글 작성하기 화면
    @GetMapping("/s/api/auth/notice/writeForm")
    public String writeForm() {
        return "noticeWriteForm";
    }

    // 공지글 수정하기 화면
    @GetMapping("/s/api/auth/notice/updateForm/{noticeId}")
    public String updateForm(@PathVariable Integer noticeId,Model model) {
        SessionUserDto principal = (SessionUserDto)session.getAttribute("principal");
        DetailFormDto noticePS = noticeService.findByNotice(noticeId);
        Integer userId = noticePS.getUserId();
        Integer userPId = principal.getUserId();
        if(userId.equals(userPId)){
            model.addAttribute("notice",noticePS);
            return "noticeUpdateForm";
        }
        return "/404";
    }

    // 공지글 등록하기 기능
    @PostMapping("/s/api/auth/noticeWrite")
    public @ResponseBody CMRespDto<?> write(@RequestBody WriteDto writeDto) {
        System.err.println("디버그 컨트롤러도착");
        System.err.println("디버그 getNoticeTitle: "+writeDto.getNoticeTitle());
        System.err.println("디버그 getNoticeContent: "+writeDto.getNoticeContent());
        System.err.println("디버그 getNoticeType: "+writeDto.getNoticeType());
        System.err.println("디버그 getUserId: "+writeDto.getUserId());
        Notice noticePS = noticeService.writeNotice(writeDto);
        System.err.println("디버그 서비스끝");
        return new CMRespDto<>(1, "공지사항 등록 성공.", noticePS);
    }

    // 공지글 수정하기 기능
    @PutMapping("/s/api/auth/noticeUpdate")
    public @ResponseBody CMRespDto<?> updateNotice(@RequestBody NoticeDetailDto noticeDetailDto) {
        System.err.println("디버그 컨트롤러도착");
        System.err.println("디버그 getNoticeTitle: "+noticeDetailDto.getNoticeTitle());
        System.err.println("디버그 getNoticeContent: "+noticeDetailDto.getNoticeContent());
        System.err.println("디버그 getNoticeType: "+noticeDetailDto.getNoticeType());
        System.err.println("디버그 getUserId: "+noticeDetailDto.getUserId());
        Notice noticePS = noticeService.updateNotice(noticeDetailDto);
        System.err.println("디버그 서비스끝");
        return new CMRespDto<>(1, "공지글 정보 수정 성공.", noticePS);
    }

    // 공지글 삭제하기 기능
    @DeleteMapping("/s/api/auth/noticeDelete/{noticeId}")
    public @ResponseBody CMRespDto<?> deleteNotice(@PathVariable Integer noticeId) {
        Notice noticePS = noticeService.deleteNotice(noticeId);
        return new CMRespDto<>(1, "공지글 정보 삭제 성공.", noticePS);
    }
}
