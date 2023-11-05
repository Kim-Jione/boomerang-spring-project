package com.example.bumerang.web;

import com.example.bumerang.domain.jobSearch.JobSearch;
import com.example.bumerang.domain.performance.Performance;
import com.example.bumerang.service.JobSearchService;
import com.example.bumerang.service.PerformanceService;
import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.request.jobSearch.DeadlineDto;
import com.example.bumerang.web.dto.response.CMRespDto;
import com.example.bumerang.web.dto.response.jobSearch.JobListDto;
import com.example.bumerang.web.dto.response.jobSearch.JobMainDto;
import com.example.bumerang.web.dto.response.performance.PfListDto;
import com.example.bumerang.web.dto.response.performance.PfMainDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class MainController {
	private final HttpSession session;
	private final JobSearchService jobSearchService;
	private final PerformanceService performanceService;

	// 구인정보글 메인 화면
	@GetMapping({"/","/jobSearch/mainForm"})
	public String jobMainForm(Model model) {
		SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
		if (principal == null) {
			
		}
		List<JobListDto> jobList = jobSearchService.findAllJob();
		List<JobListDto> bestJobList = jobSearchService.findAllBeestJob();
		model.addAttribute("jobList",jobList);
		model.addAttribute("bestJobList",bestJobList);
		return "jobMainForm";
	}

	// 마감하기 기능
	@PutMapping("/s/api/main/deadline")
	public @ResponseBody CMRespDto<?> deadline(@RequestBody DeadlineDto deadlineDto) {
		SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
		Integer jobId = deadlineDto.getJobId();
		Integer userId = deadlineDto.getUserId();
		Integer pfId = deadlineDto.getPfId();
        Integer userPId = principal.getUserId();
        if(userId.equals(userPId)){
			deadlineDto.setUserId(principal.getUserId());
			if(jobId!=null){
				JobSearch jobPS = jobSearchService.findById(jobId);
				if(jobPS.getUserId().equals(userPId)){
					JobSearch deadlineResult = jobSearchService.deadline(deadlineDto);
					return new CMRespDto<>(1, "구인글 마감하기 성공.", deadlineResult);
				}
		}
			if(pfId!=null){
				Performance pfPS = performanceService.findById(pfId);
				if(pfPS.getUserId().equals(userPId)){
					Performance deadlineResult = performanceService.deadline(deadlineDto);
					return new CMRespDto<>(1, "공연글 마감하기 성공.", deadlineResult);
				}
			}
        }
		return new CMRespDto<>(-1, "올바르지 않은 요청입니다.", null);
	}

	// 공연글 메인 화면
	@GetMapping("/performance/mainForm")
	public String pfMainForm() {
		PfMainDto pfMainResp = new PfMainDto();
		List<PfListDto> pfList = performanceService.findAllPf();
		List<PfListDto> bestPfList = performanceService.findAllBeestPf();
		pfMainResp.setPfList(pfList);
		pfMainResp.setBestPfList(bestPfList);
		return "pfMainForm";
	}

	@GetMapping("/404")
	public String errorForm(){
		return "404";
	}
}
