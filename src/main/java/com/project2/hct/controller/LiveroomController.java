package com.project2.hct.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.hct.dto.LiveroomDTO;
import com.project2.hct.service.LiveroomService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LiveroomController {
	
	@Autowired
	private LiveroomService liveroomservice;
	
	@RequestMapping("/videoroomtest")
	public String videoroomtest(LiveroomDTO liveroomdto,HttpServletRequest hsr, Model model) throws IOException{
		liveroomservice.save(liveroomdto);
		String liveclno = hsr.getParameter("liveClno");
		model.addAttribute("liveclno", liveclno);
		return "videoroomtest";
	}
	@RequestMapping("/videoroomsstu")
	public String videoroomstu(HttpServletRequest hsr, Model model) {
		String liveclno = hsr.getParameter("liveClno");
		model.addAttribute("liveclno", liveclno);
		return "videoroomtest";
	}
	@RequestMapping("/delete/{liveclno}")
	public String delete(@PathVariable("liveclno") String live_clno) throws IOException {
		System.out.println(live_clno);
		live_clno += "(";
		int idx = live_clno.indexOf("(");
		live_clno = live_clno.substring(0,idx);
		liveroomservice.delete(live_clno);
		return"liveroom";
	}
}
