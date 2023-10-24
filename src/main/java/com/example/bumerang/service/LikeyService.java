package com.example.bumerang.service;

import com.example.bumerang.domain.likey.LikeyDao;
import com.example.bumerang.web.dto.request.likey.LikeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LikeyService {

	private final LikeyDao likeyDao;


	public void likey(Integer userId, LikeyDto likeyDto) {
		likeyDto.setUserId(userId);
		likeyDao.insert(likeyDto.toLikey());
	}
}
