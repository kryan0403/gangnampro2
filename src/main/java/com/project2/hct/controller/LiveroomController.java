package com.project2.hct.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.hct.dto.LiveroomDTO;
import com.project2.hct.repository.LiveroomRepository;
import com.project2.hct.service.LiveroomService;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LiveroomController {
	@Autowired
	private LiveroomRepository liveroomrepository;
	
	@Autowired
	private LiveroomService liveroomservice;
	
	@RequestMapping("/videoroomtest")
	public String videoroomtest(LiveroomDTO liveroomdto,HttpServletRequest hsr, Model model) {
		liveroomservice.save(liveroomdto);

		model.addAttribute("liveClno", hsr.getParameter("liveClno"));
		return "videoroomtest";
	}
	@RequestMapping("/videoroomsstu")
	public String videoroomstu(HttpServletRequest hsr, Model model) {
		String liveClno = hsr.getParameter("liveClno");
		model.addAttribute("liveClno", liveClno);
		return "videoroomtest";
	}
}