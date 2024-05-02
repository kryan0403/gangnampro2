package com.project2.hct.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project2.hct.dto.JoinDTO;
import com.project2.hct.service.BJoinService;

@Controller
public class BJoin_Controller {

	@Autowired
	private BJoinService joinService;
	
	// 비즈니스(=business) 회원
	@GetMapping("b_joinForm")
	public String b_joinForm() {
		
		return "member/b_joinForm";
	}
	// 비즈니스 회원 회원가입
	@PostMapping("/b_join")
	public String b_join(JoinDTO joinDto) {
		
		System.out.println("joinDto:"+joinDto.getCertName());
		joinService.b_join(joinDto);	
		
		//회원가입 완료 후 바로 로그인 페이지로 이동하도록
		return "redirect:member/loginForm";
	}

}
