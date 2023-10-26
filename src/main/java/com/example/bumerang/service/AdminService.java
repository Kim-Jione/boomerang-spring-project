package com.example.bumerang.service;

import com.example.bumerang.domain.admin.AdminDao;
import com.example.bumerang.domain.user.UserDao;
import com.example.bumerang.web.dto.response.admin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Service
public class AdminService {

	private final HttpSession session;
    private final AdminDao adminDao;
    private final UserDao userDao;

    public List<UserListDto> findUserList() {
        List<UserListDto> userList = adminDao.findUserList();
        return userList;
    }

    public List<JobListDto> findJobList() {
        List<JobListDto> jobList = adminDao.findJobList();
        return jobList;
    }

    public List<PfListDto> findPfList() {
        List<PfListDto> pfList = adminDao.findPfList();
        return pfList;
    }
    public List<NoticeListDto> findNoticeList() {
        List<NoticeListDto> noticeList = adminDao.findNoticeList();
        return noticeList;
    }

    public UserDetailDto findById(Integer userId) {
        UserDetailDto userPS = adminDao.findByUserId(userId);
        return userPS;
    }
}
