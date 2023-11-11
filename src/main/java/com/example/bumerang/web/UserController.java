package com.example.bumerang.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.bumerang.web.dto.request.user.PasswdDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.bumerang.domain.user.User;
import com.example.bumerang.service.UserService;
import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.user.JoinDto;
import com.example.bumerang.web.dto.request.user.LoginDto;
import com.example.bumerang.web.dto.request.user.UpdateDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import com.example.bumerang.web.dto.response.likey.LikeyJSListDto;
import com.example.bumerang.web.dto.response.likey.LikeyPFListDto;
import com.example.bumerang.web.dto.response.likey.LikeyRespDto;
import com.example.bumerang.web.dto.response.user.SearchIdDto;
import com.example.bumerang.web.dto.response.user.SearchPwDto;
import com.example.bumerang.web.dto.response.user.UserCreateRespoDto;
import com.example.bumerang.web.dto.response.user.UserJobSearchDto;
import com.example.bumerang.web.dto.response.user.UserPerformanceDto;
import com.example.bumerang.web.dto.response.user.UserRespDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    // 회원가입 기능
    @PostMapping("/user/join")
    public @ResponseBody CMRespDto<?> join(@RequestBody JoinDto joinDto) {
        String userLoginId = joinDto.getUserLoginId();
        String userNickname = joinDto.getUserNickname();
        String userEmail = joinDto.getUserEmail();
        User loginResult = userService.findByLogin(userLoginId);
        User nicknameResult = userService.findByNickname(userNickname);
        User emailResult = userService.findByEmail(userEmail);
        if(loginResult!=null){
            return new CMRespDto<>(-1, "중복되는 아이디입니다.", null);
        }
        if(nicknameResult!=null){
            return new CMRespDto<>(-1, "중복되는 닉네임입니다.", null);
        }
        if(emailResult!=null){
            return new CMRespDto<>(-1, "중복되는 이메일입니다.", null);
        }
        SessionUserDto joinResult = userService.join(joinDto);
        return new CMRespDto<>(1, "회원가입 성공.", joinResult);
    }
    
    // 로그인과 회원가입 화면
    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    // 로그인 기능
    @PostMapping("/user/login")
    public @ResponseBody CMRespDto<?> login(@RequestBody LoginDto loginDto) {
        System.err.println("getUserLoginId"+loginDto.getUserLoginId());
        System.err.println("getUserPassword"+loginDto.getUserPassword());
        SessionUserDto loginResult = userService.findByUser(loginDto);
        System.err.println("loginResultgetUserLoginId"+loginResult.getUserLoginId());
        if (loginResult == null) {
            return new CMRespDto<>(-1, "아이디 혹은 비밀번호를 잘못 입력하셨습니다.", null);
        }
        userService.login(loginResult);
        return new CMRespDto<>(1, "로그인 성공.", loginResult);
    }

    // 로그아웃
    @GetMapping("/s/api/user/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    // 내 회원정보 수정 화면
    @GetMapping("/s/api/user/updateForm/{userId}")
    public String updateForm(Model model) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        UserRespDto userDetail = userService.findByDetail(principal.getUserId());
        System.err.println("getUserAge"+userDetail.getUserAge());
        model.addAttribute("userDetail", userDetail);
        return "userUpdateForm";
    }

    // 회원 비밀번호 수정
    @PutMapping("/s/api/user/passwdUpdate")
    public @ResponseBody CMRespDto<?> passwdUpdate(@RequestBody PasswdDto passwordDto){
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        Integer userId = principal.getUserId();

        if( userId == null ){
            return new CMRespDto<>(1, "존재하지 않는 계정입니다.", null);
        }
        System.err.println("디버그userId: " + userId);
        PasswdDto passwd = userService.updatePasswd(passwordDto.getUserPassword(), userId);
        System.err.println("디버그getUserPassword: "+passwd.getUserPassword());
        return new CMRespDto<>(1, "비밀번호 수정 성공.", passwd);
    }

    // 계정 상세 화면
    @GetMapping("s/api/user/detailForm/{userId}")
    public String detailForm(@PathVariable Integer userId, Model model) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        UserRespDto userDetail = userService.findByDetail(principal.getUserId());
        model.addAttribute("userDetail",userDetail);
        if(userId == null){
            return "404";
        }
        return "userDetailForm";
    }

    // 내가 작성한 글 화면
    @GetMapping("/s/api/user/writeListForm/{userId}")
    public String writeListForm(Model model) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        Integer userId = principal.getUserId();
        UserCreateRespoDto userWriteList = new UserCreateRespoDto();
        List<UserJobSearchDto> myJSList = userService.myJSList(userId);
        List<UserPerformanceDto> myPfList = userService.myPfList(userId);

        userWriteList.setMyJSList(myJSList);
        userWriteList.setMyPfList(myPfList);
        return "writeListForm";
    }

    // 아이디 찾기 화면
    @GetMapping("/user/helpId")
    public String helpIdForm() {
        return "helpIdForm";
    }

    // 비밀번호 찾기 화면
    @GetMapping("/user/helpPw")
    public String helpPwForm() {
        return "helpPwForm";
    }

    // 아이디 찾기
    @PostMapping("/user/findId")
    public String searchId(@RequestParam String userEmail, SearchIdDto userEmailId, Model model) {
        userEmailId.setUserEmail(userEmail);
        SearchIdDto userId = userService.findByLoginId(userEmailId);
        if(userId == null){
            return "redirect:/user/helpId";
        }
        model.addAttribute("userId", userId);
        return "findId";
    }

    @PostMapping("/user/findPw")
    public String searchPw(@RequestParam String userEmail, SearchPwDto userEmailPw, Model model) {
        userEmailPw.setUserEmail(userEmail);
        SearchPwDto userPw = userService.findByPw(userEmailPw);
        if(userPw == null){
            return "redirect:/user/helpPw";
        }
        SimpleMailMessage message = userService.sendMessage(userEmailPw);
        return "findPw";
    }

    // 관심목록 화면
    @GetMapping("/s/api/user/likeyListForm/{userId}")
    public String likeyListForm(Model model){
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        Integer userId = principal.getUserId();
        if(userId == null){
            return "404";
        }
        System.err.println("UserId: " + userId );

        LikeyRespDto userLikeyList = new LikeyRespDto();
        List<LikeyJSListDto> LikeyJSDetail = userService.likeyfindAllJSList(userId);
        List<LikeyPFListDto> LikeyPFDetail = userService.likeyfindAllPFList(userId);
        userLikeyList.setLJSList(LikeyJSDetail);
        userLikeyList.setLPFList(LikeyPFDetail);

        for (LikeyPFListDto item : LikeyPFDetail) {
            System.err.println("UserId: " + item.getThumbnail());
            // 다른 속성도 필요한대로 출력
        }
        model.addAttribute("LikeyJSDetail", LikeyJSDetail);
        model.addAttribute("LikeyPFDetail", LikeyPFDetail);
        return "likeyListForm";
    }

}

