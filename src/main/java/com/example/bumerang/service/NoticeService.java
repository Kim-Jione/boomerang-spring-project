package com.example.bumerang.service;

import com.example.bumerang.domain.notice.Notice;
import com.example.bumerang.domain.notice.NoticeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Service
public class NoticeService {

	private final HttpSession session;
	private final NoticeDao noticeDao;


	public void insert(Notice writeDto) {
		noticeDao.insert(writeDto);
	}
}
