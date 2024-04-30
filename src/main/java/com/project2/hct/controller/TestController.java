package com.project2.hct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project2.hct.entity.Wishlist;

@Controller
public class TestController {

	@GetMapping("/test")
	public String main() {
		Wishlist wish = new Wishlist();
		wish.setWishNo(Long.parseLong("31"));
		Long test = wish.getWishNo();
		Long test2 = wish.getWish_clNo();
		return "test";
	}
	
	@GetMapping("/main")
	public String mainqwe() {
		return "main";
	}

}
