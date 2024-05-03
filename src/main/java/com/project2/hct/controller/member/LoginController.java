package com.project2.hct.controller.member;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project2.hct.service.LiveroomService;

@Controller
public class LoginController {
	
	@Autowired
	private LiveroomService liveroomservice;
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@GetMapping("/main")
	public String main(Model model) {
		
		String id = SecurityContextHolder.getContext().getAuthentication().getName();	//아이디값
		System.out.println("id = "+id);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();		//role값
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("livelist",liveroomservice.getList());
		return "main";
	}

}
