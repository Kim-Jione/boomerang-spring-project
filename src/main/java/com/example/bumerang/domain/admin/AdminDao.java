package com.example.bumerang.domain.admin;

import com.example.bumerang.web.dto.response.admin.JobListDto;
import com.example.bumerang.web.dto.response.admin.NoticeListDto;
import com.example.bumerang.web.dto.response.admin.PfListDto;
import com.example.bumerang.web.dto.response.admin.UserListDto;
import com.example.bumerang.web.dto.response.jobSearch.JobCommentDto;

import java.util.List;

public interface AdminDao {
    List<UserListDto> findUserList();

    List<JobListDto> findJobList();

	List<PfListDto> findPfList();

    List<NoticeListDto> findNoticeList();

}
