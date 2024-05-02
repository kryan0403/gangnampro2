package com.project2.hct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@GetMapping("/test")
	public String init1() {
		return "test";
	}
	
	@RequestMapping("/")
	public String init2() {
		return "main";
	}

	@RequestMapping("/mainPage")
	public String init3() {
		return "redirect:healthclass/All/1";
	}
}
