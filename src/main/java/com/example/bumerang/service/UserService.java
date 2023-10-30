package com.example.bumerang.service;

import com.example.bumerang.domain.Likey.LikeyDao;
import com.example.bumerang.domain.user.User;
import com.example.bumerang.domain.user.UserDao;
import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.user.JoinDto;
import com.example.bumerang.web.dto.request.user.LoginDto;
import com.example.bumerang.web.dto.request.user.SearchDto;
import com.example.bumerang.web.dto.request.user.UpdateDto;
import com.example.bumerang.web.dto.response.user.UserJobSearchDto;
import com.example.bumerang.web.dto.response.user.UserPerformanceDto;
import com.example.bumerang.web.dto.response.likey.LikeyJSListDto;
import com.example.bumerang.web.dto.response.likey.LikeyPFListDto;
import com.example.bumerang.web.dto.response.user.UserRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

    private final HttpSession session;
    private final UserDao userDao;
    private final LikeyDao likeyDao;

    public SessionUserDto join(JoinDto joinDto) {
        userDao.insert(joinDto.toEntity());
        SessionUserDto joinResult = userDao.findByUser(joinDto.toLoginDto());
        return joinResult;
    }

    public SessionUserDto findByUser(LoginDto loginDto) {
        SessionUserDto userPS = userDao.findByUser(loginDto);
        return userPS;
    }

    public void login(SessionUserDto userPS) {
        session.setAttribute("principal", userPS);
    }

    public List<User> findAll() {
        List<User> userList = userDao.findAll();
        return userList;
    }

    public User findById(Integer userId) {
        return userDao.findById(userId);
    }

    public User update(UpdateDto updateDto) {
        userDao.update(updateDto.toEntity());
        System.err.println("디버그 updateDto.getUserId()" + updateDto.getUserId());
        User userPS = userDao.findById(updateDto.getUserId());
        return userPS;
    }

    public UserRespDto findByDetail(Integer userId) {
        UserRespDto userDetail = userDao.findByDetail(userId);
        return userDetail;
    }

    public List<UserJobSearchDto> myJSList(Integer userId) {
        List<UserJobSearchDto> myJSList = userDao.myJSList(userId);
        return myJSList;
    }

    public List<UserPerformanceDto> myPfList(Integer userId) {
        List<UserPerformanceDto> myPfList = userDao.myPfList(userId);
        return myPfList;
    }

    public SearchDto findAccount(SearchDto searchDto) {
        SearchDto searchId = userDao.findByEmail(searchDto.getUserEmail());
        return searchId;
    }

    public List<LikeyJSListDto> likeyfindAllJSList() {

        return likeyDao.likeyFindSJList();
    }

    public List<LikeyPFListDto> likeyfindAllPFList() {
        return likeyDao.likeyFindPFList();
    }
}
