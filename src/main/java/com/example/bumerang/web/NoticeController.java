package com.example.bumerang.web;

import com.example.bumerang.service.NoticeService;
import com.example.bumerang.web.dto.request.notice.WriteDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class NoticeController {
    private final HttpSession session;
    private final NoticeService noticeService;

    // 공지사항 작성하기 화면
    @GetMapping("/notice/writeForm")
    public String writeForm() {
        return "notice/writeForm";
    }

    // 공지사항 작성하기 기능
    @PostMapping("/notice/write")
    public @ResponseBody CMRespDto<?> write(@RequestBody WriteDto writeDto) {
        noticeService.insert(writeDto.toEntity());
        return new CMRespDto<>(1, "공지사항 작성하기 성공.", null);
    }
}
