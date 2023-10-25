package com.example.bumerang.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.bumerang.domain.admin.AdminDao;
import com.example.bumerang.web.dto.response.admin.JobListDto;
import com.example.bumerang.web.dto.response.admin.NoticeListDto;
import com.example.bumerang.web.dto.response.admin.PfListDto;
import com.example.bumerang.web.dto.response.admin.UserListDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AdminService {

	private final HttpSession session;
    private final AdminDao adminDao;
    
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

}
