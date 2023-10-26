package com.example.bumerang.domain.admin;

import com.example.bumerang.web.dto.response.admin.*;

import java.util.List;

public interface AdminDao {
    List<UserListDto> findUserList();

    List<JobListDto> findJobList();

	List<PfListDto> findPfList();

    List<NoticeListDto> findNoticeList();

    UserDetailDto findByUserId(Integer userId);
}
