package com.example.bumerang.service;

import com.example.bumerang.domain.comment.CommentDao;
import com.example.bumerang.domain.jobSearch.JobSearch;
import com.example.bumerang.domain.jobSearch.JobSearchDao;
import com.example.bumerang.domain.likey.LikeyDao;
import com.example.bumerang.web.dto.request.jobSearch.UpdateDto;
import com.example.bumerang.web.dto.request.jobSearch.WriteDto;
import com.example.bumerang.web.dto.response.jobSearch.BestJobDto;
import com.example.bumerang.web.dto.response.jobSearch.JobCommentDto;
import com.example.bumerang.web.dto.response.jobSearch.JobDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Service
public class JobSearchService {

	private final HttpSession session;
	private final JobSearchDao jobSearchDao;
	private final CommentDao commentDao;
	private final LikeyDao likeyDao;


	public JobSearch write(WriteDto writeDto) {
		jobSearchDao.insert(writeDto.toEntity());
		return jobSearchDao.writeResult(writeDto.getUserId());
	}

	public List<JobSearch> findAll() {
		return jobSearchDao.findAll();
	}

	public JobDetailDto findByJob(Integer jobId) {
		return jobSearchDao.findByJobDetail(jobId);
	}

	public JobSearch update(UpdateDto updateDto) {
		jobSearchDao.update(updateDto.toEntity());
		return jobSearchDao.findById(updateDto.getJobId());
	}

	public JobSearch delete(Integer jobId) {
		jobSearchDao.delete(jobId);
		JobSearch deleteResult = jobSearchDao.findById(jobId);
		return deleteResult;
	}

	public  List<JobCommentDto> jobFindAll(Integer jobId) {
		return commentDao.findAllJob(jobId);
	}

	public Integer likeyCount(Integer jobId) {
		return likeyDao.likeyCount(jobId);
	}

	public List<JobDetailDto> findAllJob() {
		return jobSearchDao.findAllJob();
	}

	public List<BestJobDto> findAllBeestJob() {
		return jobSearchDao.findAllBestJob();
	}

	public JobSearch findById(Integer jobId) {
		return jobSearchDao.findById(jobId);
	}
}
