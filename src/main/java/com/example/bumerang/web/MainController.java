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
<<<<<<< Updated upstream
	public String jobMainForm(Model model) {
		List<JobListDto> jobList = jobSearchService.findAllJob();
		List<JobListDto> bestJobList = jobSearchService.findAllBestJob();
=======
	public String jobMainForm(Model model, SearchDto searchDto) {
		// 테스트 위한 임시 로그인 - 메인페이지 들어오면 로그인됨
		// LoginDto loginDto = new LoginDto();
		// loginDto.setUserLoginId("ssar");
		// loginDto.setUserPassword("1234");
		// SessionUserDto loginResult = userService.findByUser(loginDto);
		// userService.login(loginResult);
        // 테스트 위한 임시 로그인
        Integer page = searchDto.getPage();
        String keyword = searchDto.getKeyword();
        String jobGenre = searchDto.getJobGenre();
        String jobGender = searchDto.getJobGender();
        String jobPositionTitle = searchDto.getJobPositionTitle();
        Boolean isDead = searchDto.getIsDead();
        // 페이지수 비어있을 때 초기화
        if (page == null) {
            page = 0;
            searchDto.setPage(page);
		}
        System.err.println("디버그 컨트롤러 실행");
        System.err.println("keyword: "+keyword);
        System.err.println("jobGenre: "+jobGenre);
        System.err.println("jobGender: "+jobGender);
        System.err.println("jobPositionTitle: "+jobPositionTitle);
        System.err.println("isDead: "+isDead);
        // 페이지 수 설정
        Integer startNum = page * 12;
        searchDto.setStartNum(startNum);
        System.err.println("startNum: "+startNum);
		PagingDto paging = jobSearchService.paging(searchDto);
        paging.makeBlockInfoByPostAll(keyword);
        List<JobListDto> jobList = jobSearchService.findAllJob(searchDto);
        List<JobListDto> bestJobList = jobSearchService.findAllBestJob();
        System.err.println(""+paging.getTotalCount());
        model.addAttribute("paging", paging); // 페이징
>>>>>>> Stashed changes
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
		List<PfListDto> bestPfList = performanceService.findAllBestPf();
		pfMainResp.setPfList(pfList);
		pfMainResp.setBestPfList(bestPfList);
		return "pfMainForm";
	}

	@GetMapping("/404")
	public String errorForm(){
		return "404";
	}
}
