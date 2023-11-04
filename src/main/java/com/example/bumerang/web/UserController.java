package com.example.bumerang.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
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

    // 회원가입 화면
    @GetMapping("/user/joinForm")
    public @ResponseBody CMRespDto<?> joinForm() {
        return new CMRespDto<>(1, "회원가입 화면 불러오기 성공.", null);
    }

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
    @PostMapping("/s/api/user/logout")
    public @ResponseBody CMRespDto<?> logout() {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if(principal==null){
            return new CMRespDto<>(-1, "로그인 상태가 아닙니다.", null);
        }
        session.invalidate();
        return new CMRespDto<>(1, "로그아웃 성공.", principal);
    }

    // 내 회원정보 수정 화면
    @GetMapping("/s/api/user/updateForm")
    public @ResponseBody CMRespDto<?> updateForm() {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        UserRespDto userDetail = userService.findByDetail(principal.getUserId());
        return new CMRespDto<>(1, "계정정보 불러오기 성공.", userDetail);
    }

    // 회원수정기능
    @PutMapping("/s/api/user/update")
    public @ResponseBody CMRespDto<?> updateUser(@RequestPart("profileImage") MultipartFile profileImage, @RequestPart("updateDto") UpdateDto updateDto) {

        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        Integer userId = updateDto.getUserId();
        Integer userPId = principal.getUserId();
        System.err.println("디버그userId: "+userId);
        System.err.println("디버그userPId: "+userPId);
        if (userId.equals(userPId)) {
            try {
                // 이미지 업로드 및 업데이트
                String imagePath = userService.uploadProfileImage(profileImage);
                // UpdateDto에 imagePath를 설정
                updateDto.setUserProfileImg(imagePath);
                // 사용자 정보 업데이트
                UserRespDto userUpdateResult = userService.update(updateDto);
                return new CMRespDto<>(1, "회원 수정 성공.", userUpdateResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new CMRespDto<>(-1, "올바르지 않은 요청입니다.", null);
    }

    // 계정 상세 화면
    @GetMapping("/s/api/user/detailForm/{userId}")
    public @ResponseBody CMRespDto<?> detailForm(@PathVariable Integer userId) {
        UserRespDto userDetail = userService.findByDetail(userId);
        return new CMRespDto<>(1, "계정정보 불러오기 성공.", userDetail);
    }

    // 내가 작성한 구인글 화면
    @GetMapping("/s/api/user/writeListForm")
    public @ResponseBody CMRespDto<?> writeListForm() {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        Integer userId = principal.getUserId();
        UserCreateRespoDto userWriteList = new UserCreateRespoDto();
        List<UserJobSearchDto> myJSList = userService.myJSList(userId);
        List<UserPerformanceDto> myPfList = userService.myPfList(userId);
        userWriteList.setMyJSList(myJSList);
        userWriteList.setMyPfList(myPfList);
        return new CMRespDto<>(1, "작성한 글 불러오기 성공.", userWriteList);
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
        return new CMRespDto<>(1, "아이디 찾기 성공.", userLoginId);
    }

    // 비밀번호 찾기
    @PostMapping("/user/searchPw")
    public @ResponseBody CMRespDto<?> searchPw(@RequestBody SearchPwDto searchPwDto) {
        SearchPwDto userPassword = userService.findByPw(searchPwDto);
        SimpleMailMessage message = userService.sendMessage(userPassword, searchPwDto);
        return new CMRespDto<>(1, "비밀번호 찾기 성공.", message);
    }

    // 관심목록 화면
    @GetMapping("/s/api/user/likeyListForm")
    public @ResponseBody CMRespDto<?> likeyListForm(){
        LikeyRespDto userLikeyList = new LikeyRespDto();
        List<LikeyJSListDto> LikeyJSDetail = userService.likeyfindAllJSList();
        List<LikeyPFListDto> LikeyPFDetail = userService.likeyfindAllPFList();
        userLikeyList.setLJSList(LikeyJSDetail);
        userLikeyList.setLPFList(LikeyPFDetail);
        return new CMRespDto<>(1, "관심목록 불러오기 성공.", userLikeyList);
    }

}

