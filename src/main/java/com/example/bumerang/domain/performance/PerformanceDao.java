package com.example.bumerang.domain.performance;

import com.example.bumerang.web.dto.request.jobSearch.DeadlineDto;
import com.example.bumerang.web.dto.request.performance.UpdateDto;
import com.example.bumerang.web.dto.response.performance.DetailFormDto;
import com.example.bumerang.web.dto.response.performance.PfListDto;
import com.example.bumerang.web.dto.response.performance.PfRespDto;

import java.util.List;

public interface PerformanceDao {
    public Performance findById(Integer pfId);

    public List<Performance> findAll();

    public void insert(Performance performance);

    public void update(Performance performance);

    public void delete(Integer pfId);

    public void dead(DeadlineDto deadlineDto);

    List<PfListDto> findAllPf();

    List<PfListDto> findAllBeestPf();

    public PfRespDto findByRecent();

    public PfRespDto findByUpdate(Integer pfId);

    public PfRespDto findByDelete(Integer pfId);

    DetailFormDto findByPf(Integer pfId);

    void insertSave(UpdateDto postSaveDto);
}
