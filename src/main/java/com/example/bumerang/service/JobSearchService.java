package com.example.bumerang.service;

import com.example.bumerang.domain.comment.CommentDao;
import com.example.bumerang.domain.jobSearch.JobSearch;
import com.example.bumerang.domain.jobSearch.JobSearchDao;
import com.example.bumerang.domain.jobSearchPosition.JobSearchPositionDao;
import com.example.bumerang.domain.likey.LikeyDao;
import com.example.bumerang.domain.view.ViewDao;
import com.example.bumerang.web.dto.request.jobSearch.DeadlineDto;
import com.example.bumerang.web.dto.request.jobSearch.UpdateDto;
import com.example.bumerang.web.dto.request.jobSearch.WriteDto;
import com.example.bumerang.web.dto.response.jobSearch.*;
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


	//구인글 작성
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

	//구인글 목록
	public List<JobSearch> findAll() {
		return jobSearchDao.findAll();
	}

	//구인글 상세보기
	public DetailFormDto findByJob(Integer userId, Integer jobId) {
		List<String> jobPositionList = jobSearchPositionDao.findPositionList(jobId);
		List<JobCommentDto> findByCommentList = commentDao.findByJobCommentList(jobId);
		DetailFormDto findByJob = jobSearchDao.findByJob(jobId);
		findByJob.setJobPositionTitle(jobPositionList);
		findByJob.setCommentList(findByCommentList);
		viewDao.count(null,jobId, userId);
		return findByJob;
	}

	//구인글 수정
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

	//구인글 삭제
	public JobRespDto delete(Integer jobId) {
		jobSearchDao.delete(jobId);
		JobRespDto deleteResult = jobSearchDao.findByDelete(jobId);
		List<String> jobPositionList = jobSearchPositionDao.findPositionList(jobId);
		deleteResult.setJobPositionTitle(jobPositionList);
		return deleteResult;
	}


	public List<JobListDto> findAllJob() {
		return jobSearchDao.findAllJob();
	}

	public List<JobListDto> findAllBeestJob() {
		return jobSearchDao.findAllBestJob();
	}

	public JobSearch findById(Integer jobId) {
		return jobSearchDao.findById(jobId);
	}

	public JobSearch deadline(DeadlineDto deadlineDto) {
		jobSearchDao.dead(deadlineDto);
		JobSearch deadlineResult = jobSearchDao.findById(deadlineDto.getJobId());
		return deadlineResult;
	}
}
