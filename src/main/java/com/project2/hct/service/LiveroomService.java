package com.project2.hct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.entity.Liveroom;
import com.project2.hct.repository.LiveroomRepository;

@Service
public class LiveroomService {
	@Autowired
	private LiveroomRepository liveroomrepository;

	public List<Liveroom> getList() {
		// TODO Auto-generated method stub
		return liveroomrepository.findroom();
	}
	
}
