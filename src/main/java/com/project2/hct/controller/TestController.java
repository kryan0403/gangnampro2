package com.project2.hct.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project2.hct.entity.Liveroom;
import com.project2.hct.entity.Noticeboard;
import com.project2.hct.service.LiveroomService;
import com.project2.hct.service.NoticeboardService;

@Controller
public class TestController {

	@Autowired
	private NoticeboardService noticeboardservice;
	
	@Autowired
	private LiveroomService liveroomservice;
	
	@RequestMapping("/main")
	public String main(Model model) {
		model.addAttribute("livelist",liveroomservice.getList());
		return "main";
	}
	@RequestMapping("/videoroomtest")
	public String videoroomtest() {
		return "videoroomtest";
	}

	@RequestMapping("/MainboardList")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> noticemain() {
		List<Noticeboard> nblist = noticeboardservice.getMainList();
		Map map = new HashMap<>();
		map.put("nblist", nblist);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
