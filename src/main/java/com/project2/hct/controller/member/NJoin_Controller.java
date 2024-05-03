package com.project2.hct.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project2.hct.dto.JoinDTO;
import com.project2.hct.service.NJoinService;

@Controller
public class NJoin_Controller {
	
	@Autowired
	private NJoinService joinService;
	
	@GetMapping("joinForm")
	public String register() {
	
		return "member/join01";
	}
	
	// 일반(normal) 회원
	@GetMapping("n_joinForm")
	public String n_joinForm() {
	
		return "member/n_joinForm";
	}
	
	// 일반 회원 회원가입
	@PostMapping("/n_join")
	public String n_join(JoinDTO joinDto) {
		
		System.out.println(joinDto.getMemId());
		joinService.n_join(joinDto);
		
		//회원가입 완료 후 바로 로그인 페이지로 이동하도록
		return "member/loginForm";
	}
	
}
