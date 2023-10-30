package com.example.bumerang.web;


import com.example.bumerang.domain.comment.Comment;
import com.example.bumerang.domain.user.User;
import com.example.bumerang.service.CommentService;
import com.example.bumerang.service.UserService;
import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.comment.CommentDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class CommentController {
    private final HttpSession session;
    private final CommentService commentService;
    private final UserService userService;

    // 댓글 생성
    @PostMapping("/s/api/comment/write")
    public @ResponseBody CMRespDto<?> write(@RequestBody CommentDto commentDto) {
        SessionUserDto principal = (SessionUserDto)session.getAttribute("principal");
        Integer userId = commentDto.getUserId();
        Integer userPId = principal.getUserId();
        if(userId == userPId){
            commentService.create(commentDto.toComment());
            return new CMRespDto<>(1, "댓글 생성 성공.", commentDto);
        }
        return new CMRespDto<>(-1, "올바르지 않은 요청입니다.", null);
    }

    // 댓글 삭제
    @DeleteMapping("/s/api/comment/delete")
    public @ResponseBody CMRespDto<?> delete(@RequestBody CommentDto commentDto) {
        SessionUserDto principal = (SessionUserDto)session.getAttribute("principal");
        Integer userId = commentDto.getUserId();
        Integer userPId = principal.getUserId();
        if(userId == userPId){
            commentService.delete(commentDto.getCommentId());
            return new CMRespDto<>(1, "댓글 삭제 성공.", commentDto);
        }
        return new CMRespDto<>(-1, "올바르지 않은 요청입니다.", null);
    }
    
    // 댓글 수정
    @PutMapping("/s/api/comment/update")
    public @ResponseBody CMRespDto<?> update(@RequestBody CommentDto commentDto) {
        SessionUserDto principal = (SessionUserDto)session.getAttribute("principal");
        Integer userId = commentDto.getUserId();
        Integer userPId = principal.getUserId();
        if(userId == userPId){
            commentService.update(commentDto.getCommentId(), commentDto.getCommentContent());
            return new CMRespDto<>(1, "댓글 수정 성공.", commentDto);
        }
        return new CMRespDto<>(-1, "본인이 작성한 댓글이 아닙니다.", null);
    }
}
