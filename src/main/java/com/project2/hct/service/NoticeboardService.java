package com.project2.hct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.entity.Noticeboard;
import com.project2.hct.repository.NoticeboardRepository;

@Service
public class NoticeboardService {
	@Autowired
	private NoticeboardRepository noticeboardrepository;
	
	public List<Noticeboard> getMainList() {
		// TODO Auto-generated method stub
		return noticeboardrepository.findmainboard();
	}
}