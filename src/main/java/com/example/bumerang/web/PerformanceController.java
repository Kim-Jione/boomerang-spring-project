package com.example.bumerang.web;

import com.example.bumerang.domain.user.User;
import com.example.bumerang.service.PerformanceService;
import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.performance.UpdateDto;
import com.example.bumerang.web.dto.request.performance.WriteDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import com.example.bumerang.web.dto.response.performance.DetailFormDto;
import com.example.bumerang.web.dto.response.performance.PfRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class PerformanceController {

    private final HttpSession session;
    private final PerformanceService performanceService;

    // 공연글 작성하기 화면
    @GetMapping("/s/api/performance/writeForm")
    public @ResponseBody CMRespDto<?> writeForm() {
        return new CMRespDto<>(1, "공연글 작성하기 화면 불러오기 성공.", null);
    }

    // 공연글 등록하기 기능
    @PostMapping("/s/api/performance/write")
    public @ResponseBody CMRespDto<?> write(@RequestPart("thumbnail") MultipartFile thumbnail, @RequestPart WriteDto writeDto) {
        SessionUserDto principal = (SessionUserDto)session.getAttribute("principal");
        Integer userId = writeDto.getUserId();
        Integer userPId = principal.getUserId();
        if(userId.equals(userPId)){
            try {
                // 썸네일 업로드 및 업데이트
                String imagePath = performanceService.uploadThumbnail(thumbnail);
                // UpdateDto에 imagePath를 설정
                writeDto.setPfThumbnail(imagePath);
                // 공연글 등록 업데이트
                PfRespDto writeResult = performanceService.write(writeDto);
                return new CMRespDto<>(1, "공연글 등록 성공.", writeResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new CMRespDto<>(-1, "올바르지 않은 요청입니다.", null);
    }

    // 공연글 상세보기 화면
    @GetMapping("/s/api/performance/detailForm/{pfId}")
    public @ResponseBody CMRespDto<?> detailForm(@PathVariable Integer pfId, Model model) {
        SessionUserDto userPS = (SessionUserDto)session.getAttribute("principal");
        Integer userId = userPS.getUserId();
        DetailFormDto pfDetail = performanceService.findByPf(userId, pfId);
        return new CMRespDto<>(1, "공연글 상세보기 화면 불러오기 성공.", pfDetail);
    }


}
