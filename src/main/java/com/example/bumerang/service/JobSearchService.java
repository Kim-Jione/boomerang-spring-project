package com.example.bumerang.service;

import com.example.bumerang.domain.comment.CommentDao;
import com.example.bumerang.domain.jobSearch.JobSearch;
import com.example.bumerang.domain.jobSearch.JobSearchDao;
import com.example.bumerang.domain.jobSearchPosition.JobSearchPositionDao;
import com.example.bumerang.domain.likey.LikeyDao;
import com.example.bumerang.domain.view.ViewDao;
import com.example.bumerang.web.dto.request.jobSearch.UpdateDto;
import com.example.bumerang.web.dto.request.jobSearch.WriteDto;
import com.example.bumerang.web.dto.response.jobSearch.BestJobDto;
import com.example.bumerang.web.dto.response.jobSearch.DetailFormDto;
import com.example.bumerang.web.dto.response.jobSearch.JobCommentDto;
import com.example.bumerang.web.dto.response.jobSearch.JobRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Service
public class JobSearchService {

	private final HttpSession session;
	private final JobSearchDao jobSearchDao;
	private final JobSearchPositionDao jobSearchPositionDao;
	private final CommentDao commentDao;
	private final LikeyDao likeyDao;
	private final ViewDao viewDao;


	public JobRespDto write(WriteDto writeDto) {
		JobSearch jobSearch = writeDto.toJobSearch();
		jobSearchDao.insert(jobSearch);
		List<String> jobPositionList = writeDto.getJobPositionList();
		JobRespDto writeResult = jobSearchDao.findByRecent();
		for(String jobPositionTitle : jobPositionList){
			jobSearchPositionDao.insertPosition(jobPositionTitle, writeResult.getJobId());
		}
		writeResult.setJobPositionTitle(jobPositionList);
		return writeResult;
	}

	public List<JobSearch> findAll() {
		return jobSearchDao.findAll();
	}

	public DetailFormDto findByJob(Integer jobId) {
		return jobSearchDao.findByJob(jobId);
	}

	public JobRespDto update(UpdateDto updateDto) {
		JobSearch jobSearch = updateDto.toJobSearch();
		jobSearchDao.update(jobSearch);
		List<String> jobPositionList = updateDto.getJobPositionList();
		for(String jobPositionTitle : jobPositionList){
			jobSearchPositionDao.updatePosition(jobPositionTitle, updateDto.getJobId());
		}
		JobRespDto updateResult = jobSearchDao.findByUpdate(updateDto.getJobId());
		updateResult.setJobPositionTitle(jobPositionList);
		return updateResult;
	}

	public JobRespDto delete(Integer jobId) {
		jobSearchDao.delete(jobId);
		JobRespDto deleteResult = jobSearchDao.findByDelete(jobId);
		List<String> jobPositionList = jobSearchPositionDao.findPositionList(jobId);
		deleteResult.setJobPositionTitle(jobPositionList);
		return deleteResult;
	}

	public  List<JobCommentDto> findByCommentList(Integer jobId) {
		List<JobCommentDto> findByCommentList = commentDao.findByCommentList(jobId);
		return findByCommentList;
	}

	public List<DetailFormDto> findAllJob() {
		return jobSearchDao.findAllJob();
	}

	public List<BestJobDto> findAllBeestJob() {
		return jobSearchDao.findAllBestJob();
	}

	public JobSearch findById(Integer jobId) {
		return jobSearchDao.findById(jobId);
	}

}
