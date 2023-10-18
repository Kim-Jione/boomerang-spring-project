package com.example.bumerang.web;

import com.example.bumerang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class AnminController {
    private final HttpSession session;
    private final UserService userService;

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
}
