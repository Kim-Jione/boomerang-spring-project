package com.example.bumerang.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
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

        UserRespDto userDetailFrom = userService.findByDetail(userId);
        UserRespDto userDetail = userService.findByDetail(userId);
        model.addAttribute("userDetailFrom",userDetailFrom );
        model.addAttribute("userDetail",userDetail );
        return "userUpdateForm";
    }


    // 회원 프로필 수정기능
    @PostMapping("/s/api/user/Imageupdate")
    public @ResponseBody CMRespDto<?> updateUser(
            @RequestPart("profileImage") MultipartFile profileImage,
            @RequestPart("ImgUpdateDto") ImgUpdateDto imgUpdateDto)  throws Exception {

        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
//
        if (principal == null) {
            return new CMRespDto<>(-1, "로그인 해주세요.", null);
        }

        int pos = profileImage.getOriginalFilename().lastIndexOf(".");

        String extension = profileImage.getOriginalFilename().substring(pos + 1);
        String imagePath = "C:/bumerang/img/profile/";
        String imgSaveName = UUID.randomUUID().toString();
        String imgName = imgSaveName + "." + extension;

        File makeFileFolder = new File(imagePath);
        if (!makeFileFolder.exists()) {
            if (!makeFileFolder.mkdir()) {
                throw new Exception("File.mkdir():Fail.");
            }
        }

        File dest = new File(imagePath, imgName);

        try {
            Files.copy(profileImage.getInputStream(), dest.toPath());
        }   catch (IOException e){
            e.printStackTrace();
            System.out.println("디버그: 사진저장");
        }
        imgUpdateDto.setUserProfileImg(imgName);
        userService.imageUpdateUser(imgUpdateDto.getUserProfileImg());

        return new CMRespDto<>(1, "업로드 성공", imgName);
//
//        Integer userId = principal.getUserId();
//        String imagePath = userService.uploadProfileImage(profileImage, userId);
//
//        if (imagePath == null) {
//            return new CMRespDto<>(-1, "이미지 업로드에 실패했습니다.", null);
//        }
//        System.err.println("auserId: " + userId);
//        imgUpdateDto.setUserProfileImg(imagePath);
//        ImgUpdateDto imgUpdateResult = userService.imageUpdateUser(imgUpdateDto, userId);
//        System.err.println("getUserProfileImg: "+imgUpdateResult.getUserProfileImg());
//        return new CMRespDto<>(1, "이미지 변경 성공", imageUpdateUser);
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
    // 회원 정보 수정
    @PutMapping("/s/api/user/userConfigUpdate")
    public @ResponseBody CMRespDto<?> userUpdate(@RequestBody UpdateDto updateDto){
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        Integer userId = principal.getUserId();

        UserRespDto userUpdateResult = userService.update(updateDto, userId);
        System.err.println("userUpdateResult:"+userUpdateResult);
        return new CMRespDto<>(1, "비밀번호 수정 성공.", userUpdateResult);
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

