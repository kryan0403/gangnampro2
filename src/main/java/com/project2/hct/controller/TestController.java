package com.project2.hct.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project2.hct.entity.Noticeboard;
import com.project2.hct.service.NoticeboardService;

@Controller
public class TestController {
	@Autowired
	private NoticeboardService noticeboardservice;

	
	@RequestMapping("/")
	public String init() {
		return "redirect:main";
	}
	
	@RequestMapping("main")
	public String main() {
		return "redirect:main";
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
