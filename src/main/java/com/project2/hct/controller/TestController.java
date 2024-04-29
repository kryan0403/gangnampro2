package com.project2.hct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test")
	public String main() {
		return "test";
	}
	
	@GetMapping("/main")
	public String mainP() {
		return "main";
	}

}
