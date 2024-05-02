package com.project2.hct.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.hct.entity.Member;
import com.project2.hct.service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	@GetMapping("MyPage")
	public String mypage(HttpServletRequest hsr, HttpServletResponse response, Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			System.out.println("mypage id = "+id);
			Member member = mypageService.getMember(id);
			model.addAttribute("member",member);
			return "member/mypage";
		}else {
			return "redirect:main";
		}
		
	}

}
