package com.project2.hct.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project2.hct.service.LiveroomService;
import com.project2.hct.service.NoticeboardService;

@Controller
public class TestController {
	@Autowired
	private NoticeboardService noticeboardservice;
	@Autowired
	private LiveroomService liveroomservice;
	
	@RequestMapping("/")
	public String init() {
		return "redirect:main";
	}
	
	
	@RequestMapping("/mainPage")
	public String init2() {
		return "redirect:healthclass/All/1";
	}
	/*
	@RequestMapping("/favicon.ico")
	public String init3(Model model) {
		model.addAttribute("livelist",liveroomservice.getList());
		return "main";
	}
	@RequestMapping("/image/{file}")
	public String init4(@PathVariable("file") String file) {
		return "redirect:/main";
	}
	*/
	@RequestMapping("/MainboardList")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> noticemain() {
		//List<Noticeboard> nblist = noticeboardservice.getMainList();
		Map map = new HashMap<>();
		//map.put("nblist", nblist);
		return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
	}
}
