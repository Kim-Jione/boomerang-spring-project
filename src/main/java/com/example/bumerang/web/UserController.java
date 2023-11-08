package com.example.bumerang.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.bumerang.web.dto.request.user.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.bumerang.domain.user.User;
import com.example.bumerang.service.UserService;
import com.example.bumerang.web.dto.SessionUserDto;
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
    
    // 로그인 화면
    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "loginForm";

    }

    // 로그인 기능
    @PostMapping("/user/login")
    public @ResponseBody CMRespDto<?> login(@RequestBody LoginDto loginDto) {
        SessionUserDto loginResult = userService.findByUser(loginDto);
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
    @GetMapping("user/updateForm/{userId}")
    public String updateForm(@PathVariable Integer userId, Model model) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");

        if(principal == null){
            return "404";
        }
        UserRespDto userDetailFrom = userService.findByDetail(userId);
        UserRespDto userDetail = userService.findByDetail(userId);
        model.addAttribute("userDetailFrom",userDetailFrom );
        model.addAttribute("userDetail",userDetail );
        return "userUpdateForm";
    }


    // 회원 프로필 수정기능
    @PutMapping("/s/api/user/Imageupdate")
    public String updateUser(@RequestPart("profileImage") MultipartFile profileImage, @RequestPart("ImgUpdateDto") ImgUpdateDto imgUpdateDto) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");

        // 이미지 업로드 및 업데이트
        String imagePath = userService.uploadProfileImage(profileImage);
        try {
            // imgUpdateDto에 imagePath를 설정
            imgUpdateDto.setUserProfileImg(imagePath);
            // 사용자 정보 업데이트
            System.err.println(imagePath);
            UserRespDto userUpdateResult = userService.imageUpdateUser(imgUpdateDto);
            System.err.println(userUpdateResult);
            return "userUpdate";

        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 로직 추가
            return "404"; // 오류 페이지로 리디렉션
        }
    }

    // 회원 비밀번호 수정
    @PutMapping("/s/api/user/passwdUpdate")
    public String passwdUpdate(@RequestParam PasswdDto passwordDto){
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        PasswdDto passwd = userService.updatePasswd(passwordDto);
        return  "userUpdate";
    }
    // 회원 정보 수정
    @PutMapping("/s/api/user/userConfigUpdate")
    public String userUpdate(@RequestBody UpdateDto updateDto){
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");

            UserRespDto userUpdateResult = userService.update(updateDto);
            System.err.println("userUpdateResult:"+userUpdateResult);
        return  "userUpdate";
    }

    // 계정 상세 화면
    @GetMapping("user/detailForm/{userId}")
    public String detailForm(@PathVariable Integer userId, Model model) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        UserRespDto userDetail = userService.findByDetail(userId);
        model.addAttribute("userDetail",userDetail);
        if(userId == null){
            return "404";
        }
        return "detailForm";
    }

    // 내가 작성한 글 화면
    @GetMapping("/s/api/user/writeListForm")
    public String writeListForm() {
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
    @GetMapping("/user/searchIdForm")
    public @ResponseBody CMRespDto<?> searchIdForm() {
        return new CMRespDto<>(1, "아이디 찾기 화면 불러오기 성공.", null);
    }

    // 비밀번호 찾기 화면
    @GetMapping("/user/searchPwForm")
    public @ResponseBody CMRespDto<?> searchPwForm() {
        return new CMRespDto<>(1, "비밀번호 찾기 화면 불러오기 성공.", null);
    }

    // 아이디 찾기
    @PostMapping("/user/searchId")
    public @ResponseBody CMRespDto<?> searchId(@RequestBody SearchIdDto searchIdDto) {
        SearchIdDto userLoginId = userService.findByLoginId(searchIdDto);
        if(userLoginId==null){
            return new CMRespDto<>(1, "존재하지 않는 계정입니다.", null);
        }
        return new CMRespDto<>(1, "아이디 찾기 성공.", userLoginId);
    }

    // 비밀번호 찾기
    @PostMapping("/user/searchPw")
    public @ResponseBody CMRespDto<?> searchPw(@RequestBody SearchPwDto searchPwDto) {
        SearchPwDto userPassword = userService.findByPw(searchPwDto);
        if(userPassword==null){
            return new CMRespDto<>(1, "존재하지 않는 계정입니다.", null);
        }
        SimpleMailMessage message = userService.sendMessage(searchPwDto);
        return new CMRespDto<>(1, "비밀번호 찾기 성공.", message);
    }

    // 관심목록 화면
    @GetMapping("/user/likeyListForm")
    public String likeyListForm(Model model){
        LikeyRespDto userLikeyList = new LikeyRespDto();
        List<LikeyJSListDto> LikeyJSDetail = userService.likeyfindAllJSList();
        List<LikeyPFListDto> LikeyPFDetail = userService.likeyfindAllPFList();
        userLikeyList.setLJSList(LikeyJSDetail);
        userLikeyList.setLPFList(LikeyPFDetail);
        model.addAttribute("LikeyJSDetail", LikeyPFDetail);
        model.addAttribute("LikeyPFDetail", LikeyPFDetail);
        return "likeyListForm";
    }

}

