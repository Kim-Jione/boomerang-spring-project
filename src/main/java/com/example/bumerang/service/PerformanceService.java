package com.example.bumerang.service;

import com.example.bumerang.domain.comment.CommentDao;
import com.example.bumerang.domain.likey.LikeyDao;
import com.example.bumerang.domain.performance.Performance;
import com.example.bumerang.domain.performance.PerformanceDao;
import com.example.bumerang.domain.view.ViewDao;
import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.jobSearch.DeadlineDto;
import com.example.bumerang.web.dto.request.performance.UpdateDto;
import com.example.bumerang.web.dto.request.performance.WriteDto;
import com.example.bumerang.web.dto.response.performance.DetailFormDto;
import com.example.bumerang.web.dto.response.performance.PfCommentDto;
import com.example.bumerang.web.dto.response.performance.PfListDto;
import com.example.bumerang.web.dto.response.performance.PfRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PerformanceService {

	private final HttpSession session;
	private final PerformanceDao performanceDao;
	private final CommentDao commentDao;
	private final LikeyDao likeyDao;
	private final ViewDao viewDao;

	public Performance deadline(DeadlineDto deadlineDto) {
		performanceDao.dead(deadlineDto);
		Performance deadlineResult = performanceDao.findById(deadlineDto.getPfId());
		return deadlineResult;
	}

	public List<PfListDto> findAllPf() {
		List<PfListDto> pfList = performanceDao.findAllPf();
		return pfList;
	}

	public List<PfListDto> findAllBeestPf() {
		List<PfListDto> bestPfList = performanceDao.findAllBeestPf();
		return bestPfList;
	}

	public PfRespDto write(WriteDto writeDto) {
		Performance performance = writeDto.toPerformance();
		performanceDao.insert(performance);
		PfRespDto writeResult = performanceDao.findByRecent();
		return writeResult;
	}

public DetailFormDto findByPf(SessionUserDto userPS, Integer pfId){
	System.err.println("디버그1");
	Integer userId = userPS.getUserId();
	System.err.println("디버그2");
	List<PfCommentDto> findByCommentList = commentDao.findByPfCommentList(pfId);
	System.err.println("디버그3");
	DetailFormDto findByPf = performanceDao.findByPf(pfId);
	System.err.println("디버그4");
	findByPf.setCommentList(findByCommentList);
	viewDao.count(pfId, userId);
	return findByPf;
}

	public PfRespDto update(UpdateDto updateDto){
		Performance performance = updateDto.toPerformance();
		performanceDao.update(performance);
		PfRespDto updateResult = performanceDao.findByUpdate(updateDto.getPfId());
		return updateResult;
	}

	public PfRespDto delete(Integer pfId) {
		performanceDao.delete(pfId);
		PfRespDto deleteResult = performanceDao.findByDelete(pfId);
		return deleteResult;
	}
	public Performance findById(Integer pfId) {
		return performanceDao.findById(pfId);
		}

	public void 썸네일없는게시글등록하기(UpdateDto postSaveDto, Integer userId) {
		performanceDao.insertSave(postSaveDto);
	}

}
