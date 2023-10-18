package com.example.bumerang.service;

import com.example.bumerang.domain.performance.Performance;
import com.example.bumerang.domain.performance.PerformanceDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PerformanceService {

	private final PerformanceDao performanceDao;


	public List<Performance> findAll() {
		return performanceDao.findAll();
	}


}
