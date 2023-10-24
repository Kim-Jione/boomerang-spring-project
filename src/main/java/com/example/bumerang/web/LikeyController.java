package com.example.bumerang.web;

import com.example.bumerang.domain.likey.Likey;
import com.example.bumerang.service.LikeyService;
import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.likey.LikeyDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class LikeyController {

    private final HttpSession session;
    private final LikeyService likeyService;

    // 구인글 좋아요 기능
//    @PostMapping("/likey/{jobId}")
//    public @ResponseBody CMRespDto<?> likey(LikeyDto likeyDto) {
//        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
//        if (principal == null) {
//            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
//        }
//        Integer jobId = likeyDto.getJobId();
//        Integer pfId = likeyDto.getPfId();
//        Integer userId = principal.getUserId();
//        Integer jobLikeyId = likeyService.findByJobId(userId, jobId);
//        Integer pfLikeyId = likeyService.findByPfId(userId, pfId);
//
//
//        if (jobLikeyId == null) {
//            Likey likeyPS = likeyService.likey(principal.getUserId(), jobId);
//            return new CMRespDto<>(1, "구인글 추천 성공", likeyPS);
//        }
//
//        Likey likeyPS = likeyService.unLikey(likeyId);
//        return new CMRespDto<>(1, "구인글 추천 취소 성공", likeyPS);
//    }

    // 구인글 좋아요 기능
    @PostMapping("/likey/{jobId}")
    public @ResponseBody CMRespDto<?> likey(LikeyDto likeyDto) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        Integer jobId = likeyDto.getJobId();
        Integer pfId = likeyDto.getPfId();
        Integer userId = principal.getUserId();
        Integer jobLikeyId = likeyService.findByJobId(userId, jobId);
        Integer pfLikeyId = likeyService.findByPfId(userId, pfId);

        // 구인글 좋아요
        if (jobId != null) {
            if (jobLikeyId == null) {
                Likey jobLikey = likeyService.likeyJob(userId, jobId);
                return new CMRespDto<>(1, "구인글 추천 성공", jobLikey);
            }
            Likey jobLikey = likeyService.unLikey(jobLikeyId);
            return new CMRespDto<>(1, "구인글 추천 취소 성공", jobLikey);
        }

        // 공연글 좋아요
        if (pfId != null) {
            if (pfLikeyId == null) {
                Likey pfLikey = likeyService.likeyPf(userId, pfId);
                return new CMRespDto<>(1, "공연글 추천 성공", pfLikey);
            }
            Likey pfLikey = likeyService.unLikey(pfLikeyId);
            return new CMRespDto<>(1, "공연글 추천 취소 성공", pfLikey);
        }

    }
}
