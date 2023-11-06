package com.example.bumerang.web;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bumerang.domain.likey.Likey;
import com.example.bumerang.service.LikeyService;
import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.likey.LikeyDto;
import com.example.bumerang.web.dto.response.CMRespDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class LikeyController {

    private final HttpSession session;
    private final LikeyService likeyService;

    // 추천 기능
    @PostMapping("/s/api/likey")
    public @ResponseBody CMRespDto<?> likey(@RequestBody LikeyDto likeyDto) {
        System.err.println("디버그============");
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        Integer userId = likeyDto.getUserId();
        Integer userPId = principal.getUserId();
        if (userId.equals(userPId)) {
            Integer jobId = likeyDto.getJobId();
            Integer pfId = likeyDto.getPfId();
            Integer commentId = likeyDto.getCommentId();
            likeyDto.setUserId(principal.getUserId());
            Integer jobLikeyId = likeyService.findByJobId(userId, jobId);
            Integer pfLikeyId = likeyService.findByPfId(userId, pfId);
            Integer commentLikeyId = likeyService.findByCommentId(userId, commentId);

            // 구인글 추천
            if (jobId != null) {
                if (jobLikeyId == null) {
                    Likey jobLikey = likeyService.likeyJob(likeyDto);
                    return new CMRespDto<>(1, "구인글 추천 성공", jobLikey);
                }
            }

            // 공연글 추천
            if (pfId != null) {
                if (pfLikeyId == null) {
                    Likey pfLikey = likeyService.likeyPf(likeyDto);
                    return new CMRespDto<>(1, "공연글 추천 성공", pfLikey);
                }
            }

            // 공연글 추천
            if (commentId != null) {
                if (commentLikeyId == null) {
                    Likey commentLikey = likeyService.likeyComment(likeyDto);
                    return new CMRespDto<>(1, "댓글 추천 성공", commentLikey);
                }
            }
        }
        return new CMRespDto<>(-1, "올바르지 않은 요청입니다.", null);
    }
    
    @DeleteMapping("/s/api/unlikey/{likeyId}")
    public @ResponseBody CMRespDto<?> likey(@PathVariable Integer likeyId) {
        Likey likeyPS = likeyService.findById(likeyId);
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        Integer userId = likeyPS.getUserId();
        Integer userPId = principal.getUserId();
        if (userId.equals(userPId)) {
            // 추천 취소
            Likey unLikeyResult = likeyService.unLikey(likeyId);
            return new CMRespDto<>(1, "추천 취소 성공", unLikeyResult);
        }
        return new CMRespDto<>(-1, "올바르지 않은 요청입니다.", null);
    }
}
