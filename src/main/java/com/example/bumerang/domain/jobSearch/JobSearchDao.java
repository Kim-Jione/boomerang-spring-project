package com.example.bumerang.domain.jobSearch;

import com.example.bumerang.web.dto.SearchDto;
import com.example.bumerang.web.dto.request.jobSearch.DeadlineDto;
import com.example.bumerang.web.dto.response.PagingDto;
import com.example.bumerang.web.dto.response.jobSearch.DetailFormDto;
import com.example.bumerang.web.dto.response.jobSearch.JobListDto;
import com.example.bumerang.web.dto.response.jobSearch.JobRespDto;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JobSearchDao {
    public JobSearch findById(Integer jobId);

    public List<JobSearch> findAll();

    public void insert(JobSearch job);

    public void update(JobSearch job);

    public void delete(Integer jobId);

    public DetailFormDto findByJob(Integer userId, Integer jobId);

    public List<JobListDto> findAllJob(SearchDto searchDto);

    public List<JobListDto> findAllBestJob();

    public JobRespDto findByRecent();

    public JobRespDto findByUpdateResult(Integer jobId);

    public JobRespDto findByDelete(Integer jobId);

    public void dead(DeadlineDto deadlineDto);

    public PagingDto paging(SearchDto searchDto);
}
