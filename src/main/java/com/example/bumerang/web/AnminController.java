package com.example.bumerang.web;

import com.example.bumerang.domain.user.User;
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
        System.err.println("디버그: "+userList.get(0).getUserNickname());
        System.err.println("디버그: "+userList.get(0).getUserGender());
        model.addAttribute("userList",userList);
        return "admin/userManageForm";
    }
}
